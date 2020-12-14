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

import clinic.data.TestRepository;
import clinic.model.Test;


@RestController
@RequestMapping(path = "/test", produces = "application/json")
@CrossOrigin(origins = "*")
public class TestController {
	private TestRepository testRepo;
	@Autowired
	EntityLinks entityLinks;

	public TestController(TestRepository testRepo) {
		this.testRepo = testRepo;
	}

	@GetMapping
	public Iterable<Test> getTestAll() {
		return testRepo.findAll();
	}

	@GetMapping("/{id}")
	public Test getTestById(@PathVariable("id") int id) {
		Optional<Test> optTest = testRepo.findById(id);
		if (optTest.isPresent()) {
			return optTest.get();
		}
		return null;
	}
	@GetMapping("/statistic/disease/{keyword}")
	public Iterable<Test> statisticDisease(@PathVariable("keyword") String keyword) {
		return testRepo.statisticDiseasebyMonth(keyword);
	}
	@GetMapping("/statistic/doctor/{keyword}/{id}")
	public Iterable<Test> statisticTestbyDoctor(@PathVariable("keyword") String keyword,@PathVariable("id") String id) {
		return testRepo.payRollDoctorbyMonth(keyword,id);
	}
	@GetMapping("/statistic/patient/{id}")
	public Iterable<Test> statisticTestbyPatient(@PathVariable("id") String id) {
		return testRepo.statisticPatient(id);
	}
	@GetMapping("/statistic/income/{keyword}")
	public Iterable<Test> statisticIncome(@PathVariable("keyword") String keyword) {
		return testRepo.statisticIncomebyMonth(keyword);
	}
	@PostMapping(consumes = "application/json")
	public Test postTest(@RequestBody Test test) {
		return testRepo.save(test);
	}

	@PutMapping("/{id}")
	public Test putTest(@RequestBody Test test) {
		return testRepo.save(test);
	}

	@DeleteMapping("/{id}")
	public void deleteTest(@PathVariable("id") Integer id) {
		try {
			testRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
