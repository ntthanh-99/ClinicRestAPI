package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Doctor;
@EnableJpaRepositories
public interface DoctorRepository extends CrudRepository<Doctor, Integer>{
	@Query(value="SELECT p FROM doctor p WHERE p.person.name LIKE %?1%")
    public List<Doctor> searchbyName(@Param("keyword") String keyword);
}
