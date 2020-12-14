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

import clinic.data.NurseRepository;
import clinic.model.Nurse;


@RestController
@RequestMapping(path = "/nurse", produces = "application/json")
@CrossOrigin(origins = "*")
public class NurseController {
	private NurseRepository nurseRepo;
	@Autowired
	EntityLinks entityLinks;

	public NurseController(NurseRepository nurseRepo) {
		this.nurseRepo = nurseRepo;
	}

	@GetMapping
	public Iterable<Nurse> getNurseAll() {
		return nurseRepo.findAll();
	}

	@GetMapping("/{id}")
	public Nurse getNurseById(@PathVariable("id") int id) {
		Optional<Nurse> optNurse = nurseRepo.findById(id);
		if (optNurse.isPresent()) {
			return optNurse.get();
		}
		return null;
	}
	@GetMapping("/search/{keyword}")
	public Iterable<Nurse> searchNurse(@PathVariable("keyword") String keyword) {
		return nurseRepo.searchbyName(keyword);
	}
	@PostMapping(consumes = "application/json")
	public Nurse postDoctor(@RequestBody Nurse nurse) {
		return nurseRepo.save(nurse);
	}

	@PutMapping("/{id}")
	public Nurse putDoctor(@RequestBody Nurse nurse) {
		return nurseRepo.save(nurse);
	}

	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable("id") Integer id) {
		try {
			nurseRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
