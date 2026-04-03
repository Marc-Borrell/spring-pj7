package edu.fje.daw2.pj7daw2.repository.mysql;
import edu.fje.daw2.pj7daw2.model.mysql.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariRepository extends JpaRepository<Usuari, Long> {
    Optional<Usuari> findByEmail(String email);
}
