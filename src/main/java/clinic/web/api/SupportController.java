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

import clinic.data.SupportRepository;
import clinic.model.Support;

@RestController
@RequestMapping(path = "/support", produces = "application/json")
@CrossOrigin(origins = "*")
public class SupportController {
	private SupportRepository supportRepo;
	@Autowired
	EntityLinks entityLinks;

	public SupportController(SupportRepository supportRepo) {
		this.supportRepo = supportRepo;
	}

	@GetMapping
	public Iterable<Support> getSupportAll() {
		return supportRepo.findAll();
	}

	@GetMapping("/{id}")
	public Support getSupportById(@PathVariable("id") int id) {
		Optional<Support> optSupport = supportRepo.findById(id);
		if (optSupport.isPresent()) {
			return optSupport.get();
		}
		return null;
	}
	@GetMapping("/statistic/nurse/{keyword}/{id}")
	public Iterable<Support> statisticTestbyDoctor(@PathVariable("keyword") String keyword,@PathVariable("id") String id) {
		return supportRepo.payRollNursebyMonth(keyword, id);
	}
	
	@PostMapping(consumes = "application/json")
	public Support postSupport(@RequestBody Support support) {
		return supportRepo.save(support);
	}

	@PutMapping("/{id}")
	public Support putSupport(@RequestBody Support support) {
		return supportRepo.save(support);
	}

	@DeleteMapping("/{id}")
	public void deleteTest(@PathVariable("id") Integer id) {
		try {
			supportRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
