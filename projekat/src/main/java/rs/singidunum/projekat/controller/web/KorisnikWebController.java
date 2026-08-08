package rs.singidunum.projekat.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import rs.singidunum.projekat.model.Korisnik;
import rs.singidunum.projekat.service.KorisnikService;
import rs.singidunum.projekat.service.VrstaKorisnikaService;

@Controller
@RequestMapping("/web/korisnici")
public class KorisnikWebController {


private final KorisnikService korisnikService;
private final VrstaKorisnikaService vrstaKorisnikaService;


public KorisnikWebController(
        KorisnikService korisnikService,
        VrstaKorisnikaService vrstaKorisnikaService) {

    this.korisnikService = korisnikService;
    this.vrstaKorisnikaService = vrstaKorisnikaService;
}



// prikaz svih korisnika
@GetMapping
public String prikaziKorisnike(Model model) {


    pripremiModel(
            model,
            new Korisnik()
    );


    model.addAttribute(
            "korisnici",
            korisnikService.findAll()
    );


    return "korisnici";
}



// dodavanje korisnika
@PostMapping
public String sacuvajKorisnika(
        @ModelAttribute Korisnik korisnik,
        @RequestParam Long vrstaKorisnikaId) {


    korisnik.setVrstaKorisnika(
            vrstaKorisnikaService.findById(
                    vrstaKorisnikaId
            )
    );


    korisnikService.save(korisnik);


    return "redirect:/web/korisnici";
}



// brisanje korisnika
@PostMapping("/{id}/obrisi")
public String obrisiKorisnika(
        @PathVariable Long id) {


    korisnikService.delete(id);


    return "redirect:/web/korisnici";
}



// prikaz korisnika po id
@GetMapping("/{id}")
public String prikaziKorisnika(
        @PathVariable Long id,
        Model model) {


    model.addAttribute(
            "korisnik",
            korisnikService.findById(id)
    );


    model.addAttribute(
            "vrsteKorisnika",
            vrstaKorisnikaService.findAll()
    );


    return "korisnik";
}



// pretraga po imenu
@GetMapping("/pretraga")
public String pretraga(
        @RequestParam String ime,
        Model model) {


    pripremiModel(
            model,
            new Korisnik()
    );


    model.addAttribute(
            "korisnici",
            korisnikService.pretraga(ime)
    );


    return "korisnici";
}



// izmena korisnika
@PostMapping("/{id}/izmeni")
public String izmeniKorisnika(
        @PathVariable Long id,
        @ModelAttribute Korisnik korisnik,
        @RequestParam Long vrstaKorisnikaId) {


    korisnik.setVrstaKorisnika(
            vrstaKorisnikaService.findById(
                    vrstaKorisnikaId
            )
    );


    korisnikService.update(
            id,
            korisnik
    );


    return "redirect:/web/korisnici";
}



// priprema podataka za HTML
private void pripremiModel(
        Model model,
        Korisnik korisnik) {


    model.addAttribute(
            "korisnik",
            korisnik
    );


    model.addAttribute(
            "vrsteKorisnika",
            vrstaKorisnikaService.findAll()
    );

}


}
