package edu.fje.daw2.pj7daw2.service;

import edu.fje.daw2.pj7daw2.model.mysql.CiutatFavorita;
import edu.fje.daw2.pj7daw2.model.mysql.Usuari;
import edu.fje.daw2.pj7daw2.repository.mysql.CiutatFavoritaRepository;
import edu.fje.daw2.pj7daw2.repository.mysql.UsuariRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuariService {

    private final UsuariRepository usuariRepository;
    private final CiutatFavoritaRepository ciutatFavoritaRepository;

    public Usuari obtenirOCrearUsuari(String email, String nom, String fotoUrl) {
        return usuariRepository.findByEmail(email)
                .orElseGet(() -> {
                    Usuari nouUsuari = new Usuari();
                    nouUsuari.setEmail(email);
                    nouUsuari.setNom(nom);
                    nouUsuari.setFotoUrl(fotoUrl);
                    return usuariRepository.save(nouUsuari);
                });
    }

    public List<CiutatFavorita> obtenirFavorites(Long usuariId) {
        return ciutatFavoritaRepository.findByUsuariId(usuariId);
    }

    public void afegirFavorita(Usuari usuari, String nomCiutat) {
        CiutatFavorita ciutat = new CiutatFavorita();
        ciutat.setNomCiutat(nomCiutat);
        ciutat.setUsuari(usuari);
        ciutatFavoritaRepository.save(ciutat);
    }

    public void eliminarFavorita(Long ciutatId) {
        ciutatFavoritaRepository.deleteById(ciutatId);
    }
}