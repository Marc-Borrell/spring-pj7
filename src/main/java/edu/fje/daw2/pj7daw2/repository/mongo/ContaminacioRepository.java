package edu.fje.daw2.pj7daw2.repository.mongo;

import edu.fje.daw2.pj7daw2.model.mongo.ContaminacioData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContaminacioRepository extends MongoRepository<ContaminacioData, String> {
    Optional<ContaminacioData> findByCiutat(String ciutat);
}
