package edu.fje.daw2.pj7daw2.controller;

import edu.fje.daw2.pj7daw2.model.mysql.Usuari;
import edu.fje.daw2.pj7daw2.service.UsuariService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UsuariService usuariService;
    //private final UsuariRepository usuariRepository;

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

    @PostMapping("/eliminar/{id}")
    public String eliminarFavorita(@PathVariable Long id) {
        usuariService.eliminarFavorita(id);
        return "redirect:/admin";
    }
}