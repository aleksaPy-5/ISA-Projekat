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

import rs.singidunum.projekat.model.Porudzbina;
import rs.singidunum.projekat.service.PorudzbinaService;

@RestController
@RequestMapping("/porudzbine")
public class PorudzbinaController {
	
	private final PorudzbinaService porudzbinaService;
	
	public PorudzbinaController(PorudzbinaService porudzbinaService) {
		this.porudzbinaService = porudzbinaService;
	}
	
	@GetMapping
	public List<Porudzbina> findAll() {
		return porudzbinaService.findAll();
	}
	
	@GetMapping("/{id}")
	public Porudzbina findById(@PathVariable Long id) {
		return porudzbinaService.findById(id);
	}
	
	@PostMapping
	public Porudzbina save(@RequestBody Porudzbina porudzbina) {
		return porudzbinaService.save(porudzbina);
	}
	
	@PutMapping("{id}")
	public Porudzbina update(@PathVariable Long id, @RequestBody Porudzbina porudzbina) {
		return porudzbinaService.update(id, porudzbina);
	}
	
	@DeleteMapping
	public void delete(@PathVariable Long id) {
		porudzbinaService.delete(id);
	}
}
