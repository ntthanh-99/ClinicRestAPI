package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Integer>{
	@Query(value="SELECT p FROM medicine p WHERE p.name LIKE %?1%")
    public List<Medicine> searchbyName(@Param("keyword") String keyword);
}
