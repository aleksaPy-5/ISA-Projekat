package rs.singidunum.projekat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.singidunum.projekat.model.StavkaPorudzbine;
import rs.singidunum.projekat.service.StavkaPorudzbineService;


@RestController
@RequestMapping("/stavke-porudzbine")
public class StavkaPorudzbineController {


	private final StavkaPorudzbineService stavkaPorudzbineService;


	public StavkaPorudzbineController(
			StavkaPorudzbineService stavkaPorudzbineService) {

		this.stavkaPorudzbineService = stavkaPorudzbineService;
	}



	// sve stavke
	@GetMapping
	public List<StavkaPorudzbine> getAllStavke() {

		return stavkaPorudzbineService.findAll();
	}



	// stavka po id
	@GetMapping("/{id}")
	public StavkaPorudzbine getStavkaById(
			@PathVariable Long id) {


		return stavkaPorudzbineService.findById(id);
	}



	// stavke jedne porudzbine
	@GetMapping("/porudzbina/{porudzbinaId}")
	public List<StavkaPorudzbine> getByPorudzbina(
			@PathVariable Long porudzbinaId) {


		return stavkaPorudzbineService.findByPorudzbinaId(porudzbinaId);
	}



	// dodavanje stavke
	@PostMapping
	public StavkaPorudzbine createStavka(
			@RequestBody StavkaPorudzbine stavka) {


		return stavkaPorudzbineService.save(stavka);
	}



	// izmena stavke
	@PutMapping("/{id}")
	public StavkaPorudzbine updateStavka(
			@PathVariable Long id,
			@RequestBody StavkaPorudzbine stavka) {


		return stavkaPorudzbineService.update(id, stavka);
	}



	// brisanje stavke
	@DeleteMapping("/{id}")
	public void deleteStavka(
			@PathVariable Long id) {


		stavkaPorudzbineService.delete(id);
	}

}