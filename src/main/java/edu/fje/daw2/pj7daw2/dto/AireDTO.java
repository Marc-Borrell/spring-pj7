package edu.fje.daw2.pj7daw2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AireDTO {
    private String ciutat;
    private int aqi;
    private String nivell;
    private String horaUltimaMesura;
    private String recomanacio;
}
