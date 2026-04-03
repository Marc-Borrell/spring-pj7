package edu.fje.daw2.pj7daw2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CiutatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCiutatNotFound(CiutatNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("codi", 404);
        return "error";
    }

    @ExceptionHandler(TokenInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleTokenInvalid(TokenInvalidException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("codi", 401);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericError(Exception ex, Model model) {
        model.addAttribute("error", "S'ha produït un error inesperat: " + ex.getMessage());
        model.addAttribute("codi", 500);
        return "error";
    }
}