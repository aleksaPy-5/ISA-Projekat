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


    // prikaz svih kategorija
    @GetMapping
    public List<Kategorija> getAllKategorije() {
        return kategorijaService.findAll();
    }


    // prikaz kategorije po id
    @GetMapping("/{id}")
    public Kategorija getKategorijaById(
            @PathVariable Long id) {

        return kategorijaService.findById(id);
    }


    // kreiranje kategorije
    @PostMapping
    public Kategorija createKategorija(
            @RequestBody Kategorija kategorija) {

        return kategorijaService.save(kategorija);
    }


    // izmena kategorije
    @PutMapping("/{id}")
    public Kategorija updateKategorija(
            @PathVariable Long id,
            @RequestBody Kategorija kategorija) {

        return kategorijaService.update(id, kategorija);
    }


    // brisanje kategorije
    @DeleteMapping("/{id}")
    public void deleteKategorija(
            @PathVariable Long id) {

        kategorijaService.delete(id);
    }


    // pretraga kategorija po nazivu
    @GetMapping("/pretraga")
    public List<Kategorija> pretragaKategorija(
            @RequestParam String naziv) {

        return kategorijaService.pretraga(naziv);
    }


    // sortiranje po nazivu
    @GetMapping("/sortirano")
    public List<Kategorija> sortirajPoNazivu() {

        return kategorijaService.sortirajPoNazivu();
    }

}