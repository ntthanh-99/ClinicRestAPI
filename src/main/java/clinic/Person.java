package clinic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Person {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String cmt;
	@Column
	private String name;
	@Column
	private String birth;
	@Column
	private String address;
	@Column
	private String phone;
}
