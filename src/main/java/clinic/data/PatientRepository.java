package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Patient;
@EnableJpaRepositories
public interface PatientRepository extends CrudRepository<Patient, Integer>{
	@Query(value="SELECT p FROM Patient p WHERE p.person.name LIKE %?1%")
   public List<Patient> searchbyName(@Param("keyword") String keyword);
}