package edu.fje.daw2.pj7daw2.controller;

import edu.fje.daw2.pj7daw2.model.mysql.Usuari;
import edu.fje.daw2.pj7daw2.service.UsuariService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador del panell d'administració.
 * Gestiona les ciutats favorites de cada usuari autenticat via OAuth2.
 *
 * @author Grup1
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuariService usuariService;
    //private final UsuariRepository usuariRepository;

    /**
     * Mostra el panell d'administració amb el perfil i les ciutats favorites de l'usuari.
     *
     * @param oauth2User usuari autenticat via OAuth2
     * @param model      model de Thymeleaf
     * @return nom de la vista admin
     */
    @GetMapping
    public String panell(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        String email   = oauth2User.getAttribute("email");
        String nom     = oauth2User.getAttribute("name");
        String fotoUrl = oauth2User.getAttribute("picture");

        // Crea o obté l'usuari — mai llança excepció
        Usuari usuari = usuariService.obtenirOCrearUsuari(email, nom, fotoUrl);

        model.addAttribute("usuari", usuari);
        model.addAttribute("favorites", usuariService.obtenirFavorites(usuari.getId()));
        return "admin";
    }

    /**
     * Afegeix una ciutat als favorites de l'usuari autenticat.
     *
     * @param oauth2User usuari autenticat via OAuth2
     * @param nomCiutat  nom de la ciutat a afegir
     * @return redirecció al panell admin
     */
    @PostMapping("/afegir")
    public String afegirFavorita(@AuthenticationPrincipal OAuth2User oauth2User,
                                 @RequestParam String nomCiutat) {
        String email   = oauth2User.getAttribute("email");
        String nom     = oauth2User.getAttribute("name");
        String fotoUrl = oauth2User.getAttribute("picture");

        Usuari usuari = usuariService.obtenirOCrearUsuari(email, nom, fotoUrl);
        usuariService.afegirFavorita(usuari, nomCiutat);
        return "redirect:/admin";
    }

    /**
     * Elimina una ciutat dels favorites de l'usuari.
     *
     * @param id identificador de la ciutat favorita a eliminar
     * @return redirecció al panell admin
     */
    @PostMapping("/eliminar/{id}")
    public String eliminarFavorita(@PathVariable Long id) {
        usuariService.eliminarFavorita(id);
        return "redirect:/admin";
    }
}