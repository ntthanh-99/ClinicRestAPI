package clinic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Support {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
    @JoinColumn(name = "tblnurseid")//name="tên cột khóa ngoại"
    Nurse nurse;
	@OneToOne
    @JoinColumn(name = "tbltestid")//name="tên cột khóa ngoại"
    Test test;
}
