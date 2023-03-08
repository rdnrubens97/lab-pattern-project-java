package dio.labpatternprojectjava.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dio.labpatternprojectjava.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
