package edu.fje.daw2.pj7daw2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Gestor global d'excepcions per a tota l'aplicació.
 * Intercepta els errors i retorna respostes personalitzades a l'usuari.
 *
 * @author Grup1
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestiona el cas en què la ciutat no es troba a l'API (404).
     *
     * @param ex    excepció llançada
     * @param model model de Thymeleaf
     * @return vista d'error amb missatge personalitzat
     */
    @ExceptionHandler(CiutatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCiutatNotFound(CiutatNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("codi", 404);
        return "error";
    }

    /**
     * Gestiona el cas en què el token de l'API és invàlid (401).
     *
     * @param ex    excepció llançada
     * @param model model de Thymeleaf
     * @return vista d'error amb missatge personalitzat
     */
    @ExceptionHandler(TokenInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleTokenInvalid(TokenInvalidException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("codi", 401);
        return "error";
    }

    /**
     * Gestiona qualsevol altre error inesperat (500).
     *
     * @param ex    excepció llançada
     * @param model model de Thymeleaf
     * @return vista d'error amb missatge personalitzat
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericError(Exception ex, Model model) {
        model.addAttribute("error", "S'ha produït un error inesperat: " + ex.getMessage());
        model.addAttribute("codi", 500);
        return "error";
    }
}