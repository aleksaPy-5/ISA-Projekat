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

import rs.singidunum.projekat.model.Proizvod;
import rs.singidunum.projekat.service.ProizvodService;

@RestController
@RequestMapping("/proizvodi")
public class ProizvodController {

	
	private final ProizvodService proizvodService;
	
	public ProizvodController(ProizvodService proizvodService) {
		this.proizvodService = proizvodService;
	}
	
	@GetMapping
	public List<Proizvod> findAll() {
		return proizvodService.findAll();
	}
	
	@GetMapping("/{id}")
	public Proizvod findByIdProizvod (@PathVariable Long id) {
		return proizvodService.findById(id);
	}
	
	@PostMapping
	public Proizvod save(@RequestBody Proizvod proizvod) {
		return proizvodService.save(proizvod);
	}
	
	@PutMapping("/{id}")
	public Proizvod update(@PathVariable Long id, @RequestBody Proizvod proizvod) {
		return proizvodService.update(id, proizvod);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		proizvodService.delete(id);
	}
}
