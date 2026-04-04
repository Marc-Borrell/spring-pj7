package edu.fje.daw2.pj7daw2.controller;

import edu.fje.daw2.pj7daw2.dto.AireDTO;
import edu.fje.daw2.pj7daw2.service.ContaminacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC per a les vistes Thymeleaf de l'aplicació.
 * Gestiona la pàgina principal i la cerca de ciutats.
 *
 * @author Grup1
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ContaminacioService contaminacioService;

    /**
     * Mostra la pàgina principal amb el formulari de cerca.
     *
     * @return nom de la vista index
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Processa la cerca d'una ciutat i mostra els resultats de qualitat de l'aire.
     *
     * @param ciutat nom de la ciutat cercada
     * @param model  model de Thymeleaf per passar dades a la vista
     * @return nom de la vista resultat o error
     */
    @GetMapping("/cerca")
    public String cercarCiutat(@RequestParam String ciutat, Model model) {
        try {
            AireDTO dades = contaminacioService.obtenirContaminacio(ciutat);
            model.addAttribute("dades", dades);
            return "resultat";
        } catch (Exception e) {
            model.addAttribute("error", "No s'han pogut obtenir dades per a: " + ciutat);
            return "error";
        }
    }
}