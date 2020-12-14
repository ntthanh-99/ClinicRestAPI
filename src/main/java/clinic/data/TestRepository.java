package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Test;

public interface TestRepository extends CrudRepository<Test, Integer>{
	@Query(value="SELECT * FROM Test WHERE datein LIKE %?1% AND status='Mắc bệnh'",nativeQuery = true)
    public List<Test> statisticDiseasebyMonth(@Param("keyword") String keyword);
	@Query(value="SELECT * FROM Test WHERE datein LIKE %:keyword% AND status='Mắc bệnh' AND tbldoctorid = :id ;",nativeQuery = true)
    public List<Test> payRollDoctorbyMonth(@Param("keyword") String keyword, @Param("id") String id);
	@Query(value="SELECT * FROM Test WHERE tblpatientid=:id",nativeQuery = true)
    public List<Test> statisticPatient(@Param("id") String id);
	@Query(value="SELECT * FROM Test WHERE datein LIKE %?1%",nativeQuery = true)
    public List<Test> statisticIncomebyMonth(@Param("keyword") String keyword);
}
