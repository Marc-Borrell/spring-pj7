package edu.fje.daw2.pj7daw2.exception;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException() {
        super("El token de l'API no és vàlid o ha expirat.");
    }
}