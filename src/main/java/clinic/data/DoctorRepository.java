package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.Doctor;
@EnableJpaRepositories
public interface DoctorRepository extends CrudRepository<Doctor, Integer>{
	@Query(value="SELECT d.* FROM qlphongkham.doctor d INNER JOIN qlphongkham.person p where p.cmt=d.tblpersoncmt;")
    public List<Doctor> search();
}
