package edu.fje.daw2.pj7daw2.service;

import edu.fje.daw2.pj7daw2.dto.AireDTO;
import edu.fje.daw2.pj7daw2.dto.AqicnResponseDTO;
import edu.fje.daw2.pj7daw2.exception.CiutatNotFoundException;
import edu.fje.daw2.pj7daw2.exception.TokenInvalidException;
import edu.fje.daw2.pj7daw2.model.mongo.ContaminacioData;
import edu.fje.daw2.pj7daw2.repository.mongo.ContaminacioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaminacioService {

    private final ContaminacioRepository contaminacioRepository;
    private final RestClient restClient;

    @Value("${aqicn.api.token}")
    private String token;

    @Value("${aqicn.api.url}")
    private String apiUrl;

    public AireDTO obtenirContaminacio(String ciutat) {

        // Si ya existe en MongoDB, devolvemos el dato guardado
        Optional<ContaminacioData> existent = contaminacioRepository.findByCiutat(ciutat);
        if (existent.isPresent()) {
            return mapToAireDTO(existent.get());
        }

        // Si no existe, llamamos a la API
        String url = apiUrl + "/" + ciutat + "/?token=" + token;

        AqicnResponseDTO resposta = restClient.get()
                .uri(url)
                .retrieve()
                .body(AqicnResponseDTO.class);

        if (resposta == null) {
            throw new TokenInvalidException();
        }
        if (!"ok".equals(resposta.getStatus())) {
            throw new CiutatNotFoundException(ciutat);
        }

        // Guardamos en MongoDB
        ContaminacioData dades = new ContaminacioData();
        dades.setCiutat(ciutat);
        dades.setAqi(resposta.getData().getAqi());
        dades.setNivell(calcularNivell(resposta.getData().getAqi()));
        dades.setHoraUltimaMesura(resposta.getData().getTime().getS());
        dades.setDataConsulta(LocalDateTime.now());

        contaminacioRepository.save(dades);

        return mapToAireDTO(dades);
    }

    private AireDTO mapToAireDTO(ContaminacioData dades) {
        return AireDTO.builder()
                .ciutat(dades.getCiutat())
                .aqi(dades.getAqi())
                .nivell(dades.getNivell())
                .horaUltimaMesura(dades.getHoraUltimaMesura())
                .recomanacio(calcularRecomanacio(dades.getAqi()))
                .build();
    }

    private String calcularNivell(int aqi) {
        if (aqi <= 50)  return "Bo";
        if (aqi <= 100) return "Moderat";
        if (aqi <= 150) return "Dolent per a grups sensibles";
        if (aqi <= 200) return "Dolent";
        if (aqi <= 300) return "Molt dolent";
        return "Perillós";
    }

    private String calcularRecomanacio(int aqi) {
        if (aqi <= 50)  return "L'aire és net. Ideal per a activitats a l'exterior.";
        if (aqi <= 100) return "Qualitat acceptable. Les persones sensibles poden notar molèsties.";
        if (aqi <= 150) return "Grups sensibles han d'evitar l'exposició prolongada a l'exterior.";
        if (aqi <= 200) return "Tothom pot notar efectes. Limiteu les activitats a l'exterior.";
        if (aqi <= 300) return "Alerta sanitària. Eviteu sortir a l'exterior.";
        return "Emergència sanitària. Quedeu-vos a casa.";
    }
}