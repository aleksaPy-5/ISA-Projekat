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
@RequestMapping("/vrste-korisnika")
public class VrstaKorisnikaController {


    private final VrstaKorisnikaService vrstaKorisnikaService;


    public VrstaKorisnikaController(
            VrstaKorisnikaService vrstaKorisnikaService) {

        this.vrstaKorisnikaService = vrstaKorisnikaService;
    }



    // sve vrste korisnika
    @GetMapping
    public List<VrstaKorisnika> getAllVrste() {

        return vrstaKorisnikaService.findAll();
    }



    // vrsta po id
    @GetMapping("/{id}")
    public VrstaKorisnika getById(
            @PathVariable Long id) {

        return vrstaKorisnikaService.findById(id);
    }



    // dodavanje vrste
    @PostMapping
    public VrstaKorisnika create(
            @RequestBody VrstaKorisnika vrsta) {

        return vrstaKorisnikaService.save(vrsta);
    }



    // izmena
    @PutMapping("/{id}")
    public VrstaKorisnika update(
            @PathVariable Long id,
            @RequestBody VrstaKorisnika vrsta) {

        return vrstaKorisnikaService.update(id, vrsta);
    }



    // brisanje
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        vrstaKorisnikaService.delete(id);
    }

}