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

import clinic.data.PersonRepository;
import clinic.model.Person;

@RestController
@RequestMapping(path = "/person", produces = "application/json")
@CrossOrigin(origins = "*")
public class PersonController {
	private PersonRepository personRepo;
	@Autowired
	EntityLinks entityLinks;

	public PersonController(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}
	
	@GetMapping
	public Iterable<Person> getPersonAll() {
		return personRepo.findAll();
	}

	@GetMapping("/{id}")
	public Person getPersonById(@PathVariable("id") String id) {
		Optional<Person> optPerson = personRepo.findById(id);
		if (optPerson.isPresent()) {
			return optPerson.get();
		}
		return null;
	}
	@GetMapping("/search/{keyword}")
	public Iterable<Person> searchPerson(@PathVariable("keyword") String keyword) {
		return personRepo.search(keyword);
	}	
	@PostMapping(consumes = "application/json")
	public Person postPerson(@RequestBody Person person) {
		return personRepo.save(person);
	}

	@PutMapping("/{id}")
	public Person putPerson(@RequestBody Person person) {
		return personRepo.save(person);
	}

	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable("id") String id) {
		try {
			personRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
