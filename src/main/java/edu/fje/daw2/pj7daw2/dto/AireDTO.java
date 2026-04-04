package edu.fje.daw2.pj7daw2.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO que representa la resposta de qualitat de l'aire retornada al client.
 * Conté el valor AQI, el nivell de perillositat i la recomanació de salut.
 *
 * @author Grup1
 * @version 1.0
 */
@Data
@Builder
public class AireDTO {
    private String ciutat;
    private int aqi;
    private String nivell;
    private String horaUltimaMesura;
    private String recomanacio;
}
