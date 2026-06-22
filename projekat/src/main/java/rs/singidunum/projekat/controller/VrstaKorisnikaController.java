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

import rs.singidunum.projekat.model.VrstaKorisnika;
import rs.singidunum.projekat.service.VrstaKorisnikaService;

@RestController
@RequestMapping("/vrsteKorisnika")
public class VrstaKorisnikaController {
	
	public final VrstaKorisnikaService vrstaKorisnikaService;
	
	public VrstaKorisnikaController(VrstaKorisnikaService vrstaKorisnikaService) {
		this.vrstaKorisnikaService = vrstaKorisnikaService;
	}
	
	@GetMapping
	public List<VrstaKorisnika> findAll() {
		return vrstaKorisnikaService.findAll();
	}
	
	@GetMapping("/{id}")
	public VrstaKorisnika findById(@PathVariable Long id) {
		return vrstaKorisnikaService.findById(id);
	}
	
	@PostMapping
	public VrstaKorisnika save(@RequestBody VrstaKorisnika vrstaKorisnika) {
		return vrstaKorisnikaService.save(vrstaKorisnika);
	}
	
	@PutMapping("/{id}")
	public VrstaKorisnika update(@PathVariable Long id, @RequestBody VrstaKorisnika vrstaKorisnika) {
		return vrstaKorisnikaService.update(id, vrstaKorisnika);
	}
	
	@DeleteMapping
	public void delete(@PathVariable Long id) {
		vrstaKorisnikaService.delete(id);
	}
}
