package edu.fje.daw2.pj7daw2.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "contaminacio")
public class ContaminacioData {
    @Id
    private String id;

    private String ciutat;
    private int aqi;
    private String nivell;
    private String horaUltimaMesura;
    private LocalDateTime dataConsulta;
}
