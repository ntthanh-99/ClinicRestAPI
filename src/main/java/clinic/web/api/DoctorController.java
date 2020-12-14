package clinic.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinic.data.DoctorRepository;
import clinic.model.Doctor;

@RestController
@RequestMapping(path = "/doctor", produces = "application/json")
@CrossOrigin(origins = "*")
public class DoctorController {
	private DoctorRepository doctorRepo;
	@Autowired
	EntityLinks entityLinks;

	public DoctorController(DoctorRepository doctorRepo) {
		this.doctorRepo = doctorRepo;
	}

	@GetMapping
	public Iterable<Doctor> getDoctorAll() {
		return doctorRepo.findAll();
	}

	@GetMapping("/{id}")
	public Doctor getDoctorById(@PathVariable("id") int id) {
		Optional<Doctor> optDoctor = doctorRepo.findById(id);
		if (optDoctor.isPresent()) {
			return optDoctor.get();
		}
		return null;
	}
	@GetMapping("/search/{keyword}")
	public Iterable<Doctor> searchDoctor(@PathVariable("keyword") String keyword) {
		return doctorRepo.searchbyName(keyword);
	}
	@PostMapping(consumes = "application/json")
	public Doctor postDoctor(@RequestBody Doctor doctor) {
		return doctorRepo.save(doctor);
	}

	@PutMapping("/{id}")
	public Doctor putDoctor(@RequestBody Doctor doctor) {
		return doctorRepo.save(doctor);
	}

	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable("id") Integer id) {
		try {
			doctorRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
