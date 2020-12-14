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
public class Medicineused {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int amount;
	@OneToOne
    @JoinColumn(name = "medicineid")//name="tên cột khóa ngoại"
    Medicine medicine;
	@OneToOne
    @JoinColumn(name = "prescriptionid")//name="tên cột khóa ngoại"
    Prescription prescription;
    
}
