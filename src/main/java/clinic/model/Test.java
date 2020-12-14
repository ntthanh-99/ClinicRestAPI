package clinic.model;

import javax.persistence.Column;
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
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String datein;
	@Column
	private String dateout;
	@Column
	private String type;
	@Column
	private String status;
	@Column
	private String total;
	@OneToOne
    @JoinColumn(name = "tbldoctorid")//name="tên cột khóa ngoại"
    Doctor doctor;
	@OneToOne
    @JoinColumn(name = "tblpatientid")//name="tên cột khóa ngoại"
    Patient patient;
	@OneToOne
    @JoinColumn(name = "tbldiseaseid")//name="tên cột khóa ngoại"
    Disease disease;
}
