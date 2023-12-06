package Green.and.Grill.Data;

import Green.and.Grill.models.client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<client, Integer> {
    @Query
    client findByEmail(String email);
}
