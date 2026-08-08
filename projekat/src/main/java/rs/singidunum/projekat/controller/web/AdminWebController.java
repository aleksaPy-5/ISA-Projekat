package rs.singidunum.projekat.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.singidunum.projekat.service.KategorijaService;
import rs.singidunum.projekat.service.KorisnikService;
import rs.singidunum.projekat.service.PorudzbinaService;
import rs.singidunum.projekat.service.ProizvodService;

@Controller
@RequestMapping("/web/admin")
public class AdminWebController {


    private final ProizvodService proizvodService;
    private final KategorijaService kategorijaService;
    private final KorisnikService korisnikService;
    private final PorudzbinaService porudzbinaService;


    public AdminWebController(
            ProizvodService proizvodService,
            KategorijaService kategorijaService,
            KorisnikService korisnikService,
            PorudzbinaService porudzbinaService) {

        this.proizvodService = proizvodService;
        this.kategorijaService = kategorijaService;
        this.korisnikService = korisnikService;
        this.porudzbinaService = porudzbinaService;
    }



    @GetMapping
    public String adminPanel(Model model) {


        model.addAttribute(
                "brojProizvoda",
                proizvodService.findAll().size()
        );


        model.addAttribute(
                "brojKategorija",
                kategorijaService.findAll().size()
        );


        model.addAttribute(
                "brojKorisnika",
                korisnikService.findAll().size()
        );


        model.addAttribute(
                "brojPorudzbina",
                porudzbinaService.findAll().size()
        );


        return "admin";
    }

}