package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, Integer>{
	@Query(value="SELECT p FROM nurse p WHERE p.person.name LIKE %?1%")
    public List<Nurse> searchbyName(@Param("keyword") String keyword);
}
