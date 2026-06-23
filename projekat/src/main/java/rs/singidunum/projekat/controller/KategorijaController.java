package rs.singidunum.projekat.controller;

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

import rs.singidunum.projekat.model.Kategorija;
import rs.singidunum.projekat.service.KategorijaService;

@RestController
@RequestMapping("/kategorije")
public class KategorijaController {
	
	private final KategorijaService kategorijaService;
	
	public KategorijaController(KategorijaService kategorijaService) {
        this.kategorijaService = kategorijaService;
    }

    @GetMapping
    public List<Kategorija> findAll() {
        return kategorijaService.findAll();
    }

    @GetMapping("/{id}")
    public Kategorija findById(@PathVariable Long id) {
        return kategorijaService.findById(id);
    }

    @PostMapping
    public Kategorija save(@RequestBody Kategorija kategorija) {
        return kategorijaService.save(kategorija);
    }

    @PutMapping("/{id}")
    public Kategorija update(@PathVariable Long id,
                             @RequestBody Kategorija kategorija) {
        return kategorijaService.update(id, kategorija);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        kategorijaService.delete(id);
    }
    
    @GetMapping("/pretraga-kategorija")
    public List<Kategorija> pretragaKategorija(@RequestParam String naziv) {
    	return kategorijaService.pretraga(naziv);
    }
    
}
