package clinic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Doctor extends Person{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String positon;
	@Column
	private int exp;
	@Column
	private String level;
	@Column
	private String specialty;
	@Column
	private int salary;
	
}
