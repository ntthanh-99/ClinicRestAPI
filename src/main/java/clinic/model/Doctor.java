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
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String position;
	@Column
	private int exp;
	@Column
	private String level;
	@Column
	private String specialty;
	@Column
	private int salary;
	@OneToOne
    @JoinColumn(name = "personcmt")//name="tên cột khóa ngoại"
    Person person;
    
}
