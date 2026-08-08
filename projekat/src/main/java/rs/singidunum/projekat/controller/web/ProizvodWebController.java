package rs.singidunum.projekat.controller.web;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import rs.singidunum.projekat.model.Proizvod;
import rs.singidunum.projekat.service.KategorijaService;
import rs.singidunum.projekat.service.KorisnikService;
import rs.singidunum.projekat.service.PorudzbinaService;
import rs.singidunum.projekat.service.ProizvodService;

@Controller
@RequestMapping("/web/proizvodi")
public class ProizvodWebController {


private final ProizvodService proizvodService;
private final KategorijaService kategorijaService;
private final KorisnikService korisnikService;
private final PorudzbinaService porudzbinaService;



public ProizvodWebController(
        ProizvodService proizvodService,
        KategorijaService kategorijaService,
        KorisnikService korisnikService,
        PorudzbinaService porudzbinaService) {

    this.proizvodService = proizvodService;
    this.kategorijaService = kategorijaService;
    this.korisnikService = korisnikService;
    this.porudzbinaService = porudzbinaService;
}




// prikaz svih proizvoda

@GetMapping
public String prikaziProizvode(Model model) {

    pripremiModel(model, new Proizvod());

    model.addAttribute(
            "proizvodi",
            proizvodService.findAll()
    );

    return "proizvodi";
}




// dodavanje proizvoda

@PostMapping
public String sacuvajProizvod(
        @ModelAttribute Proizvod proizvod) {

    proizvodService.save(proizvod);

    return "redirect:/web/proizvodi";
}




// prikaz forme za izmenu

@GetMapping("/edit/{id}")
public String izmeniProizvod(
        @PathVariable Long id,
        Model model) {

    Proizvod proizvod =
            proizvodService.findById(id);

    pripremiModel(model, proizvod);

    model.addAttribute(
            "proizvodi",
            proizvodService.findAll()
    );

    return "proizvodi";
}




// izmena proizvoda

@PostMapping("/update/{id}")
public String updateProizvod(
        @PathVariable Long id,
        @ModelAttribute Proizvod proizvod) {

    proizvodService.update(id, proizvod);

    return "redirect:/web/proizvodi";
}




// brisanje proizvoda

@PostMapping("/{id}/obrisi")
public String obrisiProizvod(
        @PathVariable Long id) {

    proizvodService.delete(id);

    return "redirect:/web/proizvodi";
}




// pretraga po nazivu

@GetMapping("/pretraga")
public String pretraga(
        @RequestParam String naziv,
        Model model) {

    pripremiModel(model, new Proizvod());

    model.addAttribute(
            "proizvodi",
            proizvodService.pretraga(naziv)
    );

    return "proizvodi";
}




// filtriranje po ceni

@GetMapping("/cena")
public String filtrirajPoCeni(
        @RequestParam BigDecimal min,
        @RequestParam BigDecimal max,
        Model model) {

    pripremiModel(model, new Proizvod());

    model.addAttribute(
            "proizvodi",
            proizvodService.filtrirajPoCeni(min, max)
    );

    return "proizvodi";
}




// dopuna lagera

@PostMapping("/{id}/lager")
public String dopuniLager(
        @PathVariable Long id,
        @RequestParam int kolicina) {

    proizvodService.dopuniLager(
            id,
            kolicina
    );

    return "redirect:/web/proizvodi";
}




// kupovina proizvoda

@PostMapping("/{id}/kupi")
public String kupiProizvod(
        @PathVariable Long id,
        @RequestParam Long korisnikId,
        @RequestParam int kolicina) {

    porudzbinaService.kupi(
            id,
            korisnikId,
            kolicina
    );

    return "redirect:/web/proizvodi";
}




// priprema podataka za HTML

private void pripremiModel(
        Model model,
        Proizvod proizvod) {

    model.addAttribute(
            "proizvod",
            proizvod
    );

    model.addAttribute(
            "kategorije",
            kategorijaService.findAll()
    );

    model.addAttribute(
            "korisnici",
            korisnikService.findAll()
    );
}


}
