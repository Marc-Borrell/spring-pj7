package edu.fje.daw2.pj7daw2.model.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuaris")
public class Usuari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nom;

    private String fotoUrl;
}
