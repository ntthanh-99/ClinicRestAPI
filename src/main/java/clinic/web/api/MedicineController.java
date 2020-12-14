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

import clinic.data.MedicineRepository;
import clinic.model.Medicine;



@RestController
@RequestMapping(path = "/medicine", produces = "application/json")
@CrossOrigin(origins = "*")
public class MedicineController {
	private MedicineRepository medicineRepo;
	@Autowired
	EntityLinks entityLinks;

	public MedicineController(MedicineRepository medicineRepo) {
		this.medicineRepo = medicineRepo;
	}

	@GetMapping
	public Iterable<Medicine> getMedicineAll() {
		return medicineRepo.findAll();
	}

	@GetMapping("/{id}")
	public Medicine getMedicineById(@PathVariable("id") int id) {
		Optional<Medicine> optMedicine = medicineRepo.findById(id);
		if (optMedicine.isPresent()) {
			return optMedicine.get();
		}
		return null;
	}
	@GetMapping("/search/{keyword}")
	public Iterable<Medicine> searchMedicine(@PathVariable("keyword") String keyword) {
		return medicineRepo.searchbyName(keyword);
	}
	@PostMapping(consumes = "application/json")
	public Medicine postMedicine(@RequestBody Medicine medicine) {
		return medicineRepo.save(medicine);
	}

	@PutMapping("/{id}")
	public Medicine putMedicine(@RequestBody Medicine medicine) {
		return medicineRepo.save(medicine);
	}

	@DeleteMapping("/{id}")
	public void deleteMedicine(@PathVariable("id") Integer id) {
		try {
			medicineRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
