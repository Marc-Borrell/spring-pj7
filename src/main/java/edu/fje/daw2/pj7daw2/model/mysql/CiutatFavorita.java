package edu.fje.daw2.pj7daw2.model.mysql;

import jakarta.persistence.*;
import lombok.Data;
import edu.fje.daw2.pj7daw2.model.mysql.Usuari;

/**
 * Entitat JPA que representa una ciutat guardada com a favorita per un usuari.
 * Relacionada amb {@link Usuari} mitjançant clau forana.
 *
 * @author Grup1
 * @version 1.0
 */
@Data
@Entity
@Table(name = "ciutats_favorites")
public class CiutatFavorita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomCiutat;

    @ManyToOne
    @JoinColumn(name = "usuari_id", nullable = false)
    private Usuari usuari;
}
