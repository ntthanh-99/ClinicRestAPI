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

import clinic.data.MedicineUsedRepository;
import clinic.model.Medicineused;


@RestController
@RequestMapping(path = "/medicineused", produces = "application/json")
@CrossOrigin(origins = "*")
public class MedicineUsedController {
	private MedicineUsedRepository medicineUsedRepo;
	@Autowired
	EntityLinks entityLinks;

	public MedicineUsedController(MedicineUsedRepository medicineUsedRepo) {
		this.medicineUsedRepo = medicineUsedRepo;
	}

	@GetMapping
	public Iterable<Medicineused> getMedicinUsedAll() {
		return medicineUsedRepo.findAll();
	}

	@GetMapping("/{id}")
	public Medicineused getMedicineUsedById(@PathVariable("id") int id) {
		Optional<Medicineused> optMedicineUsed = medicineUsedRepo.findById(id);
		if (optMedicineUsed.isPresent()) {
			return optMedicineUsed.get();
		}
		return null;
	}
	@GetMapping("/statistic/income/{id}")
	public Iterable<Medicineused> statisticIncomebyPrescription(@PathVariable("id") String id) {
		return medicineUsedRepo.statisticMedicineUsedbyPrescriptionId(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Medicineused postMedicineUsed(@RequestBody Medicineused medicineUsed) {
		return medicineUsedRepo.save(medicineUsed);
	}

	@PutMapping("/{id}")
	public Medicineused putMedicineUsed(@RequestBody Medicineused medicineUsed) {
		return medicineUsedRepo.save(medicineUsed);
	}

	@DeleteMapping("/{id}")
	public void deleteMedicineUsed(@PathVariable("id") Integer id) {
		try {
			medicineUsedRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
