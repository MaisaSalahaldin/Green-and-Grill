package Green.and.Grill.Data;

import Green.and.Grill.models.chef;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends CrudRepository<chef, Integer> {
@Query
    chef findByEmail(String email);
    @Query
    chef findByResturentName(String restaurantName);
}
