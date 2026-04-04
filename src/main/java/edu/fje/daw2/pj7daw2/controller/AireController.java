package edu.fje.daw2.pj7daw2.controller;

import edu.fje.daw2.pj7daw2.dto.AireDTO;
import edu.fje.daw2.pj7daw2.service.ContaminacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST per a consultar la qualitat de l'aire per ciutat.
 */
@RestController
@RequestMapping("/api/v1/aire")
@RequiredArgsConstructor
public class AireController {

    private final ContaminacioService contaminacioService;

    /**
     * Retorna les dades de qualitat de l'aire d'una ciutat.
     *
     * @param ciutat nom de la ciutat a consultar
     * @return ResponseEntity amb AireDTO que conté AQI, nivell i recomanació
     */
    @GetMapping("/{ciutat}")
    public ResponseEntity<AireDTO> obtenirAire(@PathVariable String ciutat) {
        AireDTO resposta = contaminacioService.obtenirContaminacio(ciutat);
        return ResponseEntity.ok(resposta);
    }
}