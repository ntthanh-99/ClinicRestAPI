package clinic.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import clinic.model.Support;
import clinic.model.Test;

public interface SupportRepository extends CrudRepository<Support, Integer>{
	@Query(value="SELECT s.* FROM Support s INNER JOIN Test t on t.id=s.tbltestid WHERE t.datein LIKE %:keyword% AND t.status='Mắc bệnh' AND s.tblnurseid = :id ;",nativeQuery = true)
    public List<Support> payRollNursebyMonth(@Param("keyword") String keyword, @Param("id") String id);
}
