package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Integer>{
	@Query(value="SELECT s.* FROM prescription s INNER JOIN test t on t.id=s.tbltestid WHERE t.datein LIKE %:keyword% ;",nativeQuery = true)
    public List<Prescription> statisticPrescriptionbyMonth(@Param("keyword") String keyword);
}
