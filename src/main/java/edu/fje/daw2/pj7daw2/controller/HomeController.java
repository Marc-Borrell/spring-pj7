package edu.fje.daw2.pj7daw2.controller;

import edu.fje.daw2.pj7daw2.dto.AireDTO;
import edu.fje.daw2.pj7daw2.service.ContaminacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ContaminacioService contaminacioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

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