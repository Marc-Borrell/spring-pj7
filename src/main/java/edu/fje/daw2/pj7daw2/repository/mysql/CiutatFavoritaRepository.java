package edu.fje.daw2.pj7daw2.repository.mysql;

import edu.fje.daw2.pj7daw2.model.mysql.CiutatFavorita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CiutatFavoritaRepository extends JpaRepository<CiutatFavorita, Long> {
    List<CiutatFavorita> findByUsuariId(Long usuariId);
}
