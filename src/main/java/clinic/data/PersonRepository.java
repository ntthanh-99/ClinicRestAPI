package clinic.data;

import org.springframework.data.repository.CrudRepository;

import clinic.Person;

public interface PersonRepository extends CrudRepository<Person, String>{

}
