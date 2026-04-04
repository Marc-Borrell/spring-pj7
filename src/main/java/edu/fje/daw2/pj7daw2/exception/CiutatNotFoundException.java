package edu.fje.daw2.pj7daw2.exception;

/**
 * Excepció llançada quan no es troben dades per a una ciutat a l'API d'Aqicn.
 * Retorna un error HTTP 404.
 *
 * @author Grup1
 * @version 1.0
 */
public class CiutatNotFoundException extends RuntimeException {
    /**
     * Constructor amb el nom de la ciutat no trobada.
     *
     * @param ciutat nom de la ciutat que no s'ha trobat
     */
    public CiutatNotFoundException(String ciutat) {
        super("No s'han trobat dades per a la ciutat: " + ciutat);
    }
}