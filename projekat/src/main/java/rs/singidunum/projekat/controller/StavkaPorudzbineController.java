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
@RequestMapping("/stavkePorudzbine")
public class StavkaPorudzbineController {
	
	public final StavkaPorudzbineService stavkaPorudzbineService;
	
	public StavkaPorudzbineController(StavkaPorudzbineService stavkaPorudzbineService) {
		this.stavkaPorudzbineService = stavkaPorudzbineService;
	}
	
	@GetMapping
	public List<StavkaPorudzbine> findAll() {
		return stavkaPorudzbineService.findAll();
	}
	
	@GetMapping("/{id}")
	public StavkaPorudzbine findById(@PathVariable Long id) {
		return stavkaPorudzbineService.findById(id);
	}
	
	@PostMapping
	public StavkaPorudzbine save(@RequestBody StavkaPorudzbine stavkaPorudzbine) {
		return stavkaPorudzbineService.save(stavkaPorudzbine);
	}
	
	@PutMapping("/{id}")
	public StavkaPorudzbine update(@PathVariable Long id, @RequestBody StavkaPorudzbine stavkaPorudzbine) {
		return stavkaPorudzbineService.update(id, stavkaPorudzbine);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		stavkaPorudzbineService.delete(id);
	}
}
