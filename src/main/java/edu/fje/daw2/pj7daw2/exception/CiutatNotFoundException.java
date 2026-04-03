package edu.fje.daw2.pj7daw2.exception;

public class CiutatNotFoundException extends RuntimeException {
    public CiutatNotFoundException(String ciutat) {
        super("No s'han trobat dades per a la ciutat: " + ciutat);
    }
}