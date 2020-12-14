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

import clinic.data.PrescriptionRepository;
import clinic.model.Prescription;


@RestController
@RequestMapping(path = "/prescription", produces = "application/json")
@CrossOrigin(origins = "*")
public class PrescriptionController {
	private PrescriptionRepository prescriptionRepo;
	@Autowired
	EntityLinks entityLinks;

	public PrescriptionController(PrescriptionRepository prescriptionRepo) {
		this.prescriptionRepo = prescriptionRepo;
	}

	@GetMapping
	public Iterable<Prescription> getPrescriptionAll() {
		return prescriptionRepo.findAll();
	}

	@GetMapping("/{id}")
	public Prescription getPrescriptionById(@PathVariable("id") int id) {
		Optional<Prescription> optPrescription = prescriptionRepo.findById(id);
		if (optPrescription.isPresent()) {
			return optPrescription.get();
		}
		return null;
	}
	@GetMapping("/statistic/income/{keyword}")
	public Iterable<Prescription> statisticIncomebyPrescription(@PathVariable("keyword") String keyword) {
		return prescriptionRepo.statisticPrescriptionbyMonth(keyword);
	}
	
	@PostMapping(consumes = "application/json")
	public Prescription postPrescription(@RequestBody Prescription prescription) {
		return prescriptionRepo.save(prescription);
	}

	@PutMapping("/{id}")
	public Prescription putPrescription(@RequestBody Prescription prescription) {
		return prescriptionRepo.save(prescription);
	}

	@DeleteMapping("/{id}")
	public void deletePrescription(@PathVariable("id") Integer id) {
		try {
			prescriptionRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
