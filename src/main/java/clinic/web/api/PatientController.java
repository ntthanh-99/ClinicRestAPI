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

import clinic.data.PatientRepository;
import clinic.model.Patient;

@RestController
@RequestMapping(path = "/patient", produces = "application/json")
@CrossOrigin(origins = "*")
public class PatientController {
	private PatientRepository patientRepo;
	@Autowired
	EntityLinks entityLinks;

	public PatientController(PatientRepository patientRepo) {
		this.patientRepo = patientRepo;
	}

	@GetMapping
	public Iterable<Patient> getPatientAll() {
		return patientRepo.findAll();
	}

	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable("id") int id) {
		Optional<Patient> optPatient = patientRepo.findById(id);
		if (optPatient.isPresent()) {
			return optPatient.get();
		}
		return null;
	}
	@GetMapping("/search/{keyword}")
	public Iterable<Patient> searchPatient(@PathVariable("keyword") String keyword) {
		return patientRepo.searchbyName(keyword);
	}
	@PostMapping(consumes = "application/json")
	public Patient postPatient(@RequestBody Patient patient) {
		return patientRepo.save(patient);
	}

	@PutMapping("/{id}")
	public Patient putPatient(@RequestBody Patient patient) {
		return patientRepo.save(patient);
	}

	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable("id") Integer id) {
		try {
			patientRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
