package edu.fje.daw2.pj7daw2.exception;

/**
 * Excepció llançada quan el token de l'API d'Aqicn és invàlid o ha expirat.
 * Retorna un error HTTP 401.
 *
 * @author Grup1
 * @version 1.0
 */
public class TokenInvalidException extends RuntimeException {
    /**
     * Constructor per defecte amb missatge d'error estàndard.
     */
    public TokenInvalidException() {
        super("El token de l'API no és vàlid o ha expirat.");
    }
}