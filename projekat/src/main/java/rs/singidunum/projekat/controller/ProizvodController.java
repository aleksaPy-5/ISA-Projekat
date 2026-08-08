package rs.singidunum.projekat.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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



	// prikaz svih proizvoda
	@GetMapping
	public List<Proizvod> getAllProizvodi() {

		return proizvodService.findAll();
	}



	// prikaz proizvoda po id
	@GetMapping("/{id}")
	public Proizvod getProizvodById(
			@PathVariable Long id) {


		return proizvodService.findById(id);
	}



	// kreiranje proizvoda
	@PostMapping
	public Proizvod createProizvod(
			@RequestBody Proizvod proizvod) {


		return proizvodService.save(proizvod);
	}



	// izmena proizvoda
	@PutMapping("/{id}")
	public Proizvod updateProizvod(
			@PathVariable Long id,
			@RequestBody Proizvod proizvod) {


		return proizvodService.update(id, proizvod);
	}



	// brisanje proizvoda
	@DeleteMapping("/{id}")
	public void deleteProizvod(
			@PathVariable Long id) {


		proizvodService.delete(id);
	}



	// pretraga po nazivu
	@GetMapping("/pretraga")
	public List<Proizvod> pretraga(
			@RequestParam String naziv) {


		return proizvodService.pretraga(naziv);
	}



	// filtriranje po ceni
	@GetMapping("/cena")
	public List<Proizvod> filtrirajPoCeni(
			@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {


		return proizvodService.filtrirajPoCeni(min, max);
	}



	// dopuna lagera
	@PutMapping("/{id}/lager")
	public Proizvod dopuniLager(
			@PathVariable Long id,
			@RequestParam int kolicina) {


		return proizvodService.dopuniLager(id, kolicina);
	}



	// kupovina proizvoda
	@PutMapping("/{id}/kupi")
	public Proizvod kupiProizvod(
			@PathVariable Long id,
			@RequestParam int kolicina) {


		return proizvodService.kupiProizvod(id, kolicina);
	}


}