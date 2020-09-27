package test.cybercube.consumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.cybercube.consumer.dto.PeopleDTO;

@Repository
public interface PeopleDTORepository extends CrudRepository<PeopleDTO, String> {
}
