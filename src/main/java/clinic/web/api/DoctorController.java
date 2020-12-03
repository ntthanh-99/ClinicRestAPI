package clinic.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinic.Doctor;
import clinic.data.DoctorRepository;

@RestController
@RequestMapping(path = "/doctor", produces = "application/json")
@CrossOrigin(origins = "*")
public class DoctorController {
	private DoctorRepository doctorRepo;
	@Autowired
	EntityLinks entityLinks;
	
	public DoctorController(DoctorRepository doctorRepo) {
		this.doctorRepo=doctorRepo;
	}
	@GetMapping
	public List<Doctor> getDoctorAll() {
		return doctorRepo.search();
	}
	@GetMapping("/{id}")
	public Doctor getDoctorById(@PathVariable("id") int id) {
	  Optional<Doctor> optDoctor = doctorRepo.findById(id);
	  if (optDoctor.isPresent()) {
	    return optDoctor.get();
	  }
	return null;
	}
}
