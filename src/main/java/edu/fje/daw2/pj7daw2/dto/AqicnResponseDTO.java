package edu.fje.daw2.pj7daw2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AqicnResponseDTO {

    private String status;
    private DadesAire data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DadesAire {
        private int aqi;

        @JsonProperty("time")
        private Temps time;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Temps {
            private String s;
        }
    }
}