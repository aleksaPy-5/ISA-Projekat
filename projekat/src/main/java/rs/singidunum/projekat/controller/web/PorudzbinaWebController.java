package rs.singidunum.projekat.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import rs.singidunum.projekat.model.Porudzbina;
import rs.singidunum.projekat.service.KorisnikService;
import rs.singidunum.projekat.service.PorudzbinaService;

@Controller
@RequestMapping("/web/porudzbine")
public class PorudzbinaWebController {


    private final PorudzbinaService porudzbinaService;
    private final KorisnikService korisnikService;


    public PorudzbinaWebController(
            PorudzbinaService porudzbinaService,
            KorisnikService korisnikService) {

        this.porudzbinaService = porudzbinaService;
        this.korisnikService = korisnikService;
    }



    // prikaz svih porudzbina
    @GetMapping
    public String prikaziPorudzbine(Model model) {


        model.addAttribute(
                "porudzbina",
                new Porudzbina()
        );


        model.addAttribute(
                "porudzbine",
                porudzbinaService.findAll()
        );


        model.addAttribute(
                "korisnici",
                korisnikService.findAll()
        );


        return "porudzbine";
    }



    // kreiranje porudzbine
    @PostMapping
    public String sacuvajPorudzbinu(
            @ModelAttribute Porudzbina porudzbina) {


        porudzbinaService.napraviPorudzbinu(
                porudzbina,
                porudzbina.getKorisnik()
        );


        return "redirect:/web/porudzbine";
    }



    // prikaz jedne porudzbine
    @GetMapping("/{id}")
    public String prikaziPorudzbinu(
            @PathVariable Long id,
            Model model) {


        model.addAttribute(
                "porudzbina",
                porudzbinaService.findById(id)
        );


        return "porudzbina";
    }



    // promena statusa porudzbine
    @PostMapping("/{id}/status")
    public String promeniStatus(
            @PathVariable Long id,
            @RequestParam String status) {


        Porudzbina porudzbina = new Porudzbina();

        porudzbina.setStatus(status);


        porudzbinaService.update(
                id,
                porudzbina
        );


        return "redirect:/web/porudzbine";
    }



    // otkazivanje porudzbine
    @PostMapping("/{id}/otkazi")
    public String otkazi(
            @PathVariable Long id) {


        porudzbinaService.otkaziPorudzbinu(id);


        return "redirect:/web/porudzbine";
    }



    // prikaz porudzbina jednog korisnika
    @GetMapping("/korisnik/{korisnikId}")
    public String poKorisniku(
            @PathVariable Long korisnikId,
            Model model) {


        model.addAttribute(
                "porudzbine",
                porudzbinaService.findByKorisnikId(korisnikId)
        );


        model.addAttribute(
                "porudzbina",
                new Porudzbina()
        );


        return "porudzbine";
    }

}