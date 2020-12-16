package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Medicineused;

public interface MedicineUsedRepository extends CrudRepository<Medicineused, Integer>{
	@Query(value="SELECT * FROM medicineused WHERE prescriptionid=:id ;",nativeQuery = true)
    public List<Medicineused> statisticMedicineUsedbyPrescriptionId(@Param("id") String id);
}
