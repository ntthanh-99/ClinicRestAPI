package clinic.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinic.Person;
import clinic.data.PersonRepository;


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
}
