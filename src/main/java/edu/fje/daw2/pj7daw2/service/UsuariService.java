package edu.fje.daw2.pj7daw2.service;

import edu.fje.daw2.pj7daw2.model.mysql.CiutatFavorita;
import edu.fje.daw2.pj7daw2.model.mysql.Usuari;
import edu.fje.daw2.pj7daw2.repository.mysql.CiutatFavoritaRepository;
import edu.fje.daw2.pj7daw2.repository.mysql.UsuariRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servei per a la gestió d'usuaris i ciutats favorites.
 * Interactua amb la base de dades MySQL via Spring Data JPA.
 *
 * @author Grup1
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UsuariService {

    private final UsuariRepository usuariRepository;
    private final CiutatFavoritaRepository ciutatFavoritaRepository;

    /**
     * Cerca un usuari per email o el crea si no existeix.
     * S'executa cada vegada que un usuari inicia sessió amb OAuth2.
     *
     * @param email    adreça de correu electrònic de l'usuari
     * @param nom      nom complet de l'usuari
     * @param fotoUrl  URL de la foto de perfil de l'usuari
     * @return Usuari existent o nou usuari creat
     */
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

    /**
     * Retorna totes les ciutats favorites d'un usuari.
     *
     * @param usuariId identificador de l'usuari
     * @return llista de ciutats favorites
     */
    public List<CiutatFavorita> obtenirFavorites(Long usuariId) {
        return ciutatFavoritaRepository.findByUsuariId(usuariId);
    }

    /**
     * Afegeix una ciutat als favorites d'un usuari.
     *
     * @param usuari    usuari propietari de la ciutat
     * @param nomCiutat nom de la ciutat a afegir
     */
    public void afegirFavorita(Usuari usuari, String nomCiutat) {
        CiutatFavorita ciutat = new CiutatFavorita();
        ciutat.setNomCiutat(nomCiutat);
        ciutat.setUsuari(usuari);
        ciutatFavoritaRepository.save(ciutat);
    }

    /**
     * Elimina una ciutat dels favorites pel seu identificador.
     *
     * @param ciutatId identificador de la ciutat favorita a eliminar
     */
    public void eliminarFavorita(Long ciutatId) {
        ciutatFavoritaRepository.deleteById(ciutatId);
    }
}