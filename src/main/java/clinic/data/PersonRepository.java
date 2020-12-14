package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Person;

public interface PersonRepository extends CrudRepository<Person, String>{
	@Query(value="SELECT p FROM Person p WHERE p.name LIKE %?1%" )
    public List<Person> search(@Param("keyword") String keyword);
}
