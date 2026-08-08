package rs.singidunum.projekat.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import rs.singidunum.projekat.model.Kategorija;
import rs.singidunum.projekat.service.KategorijaService;

@Controller
@RequestMapping("/web/kategorije")
public class KategorijaWebController {


    private final KategorijaService kategorijaService;


    public KategorijaWebController(
            KategorijaService kategorijaService) {

        this.kategorijaService = kategorijaService;
    }



    // prikaz svih kategorija
    @GetMapping
    public String prikaziKategorije(Model model) {


        model.addAttribute(
                "kategorija",
                new Kategorija()
        );


        model.addAttribute(
                "kategorije",
                kategorijaService.findAll()
        );


        return "kategorije";
    }



    // dodavanje kategorije
    @PostMapping
    public String sacuvajKategoriju(
            @ModelAttribute Kategorija kategorija) {


        kategorijaService.save(kategorija);


        return "redirect:/web/kategorije";
    }



    // brisanje kategorije
    @PostMapping("/{id}/obrisi")
    public String obrisiKategoriju(
            @PathVariable Long id) {


        kategorijaService.delete(id);


        return "redirect:/web/kategorije";
    }



    // pretraga kategorija
    @GetMapping("/pretraga")
    public String pretraga(
            @RequestParam String naziv,
            Model model) {


        model.addAttribute(
                "kategorija",
                new Kategorija()
        );


        model.addAttribute(
                "kategorije",
                kategorijaService.pretraga(naziv)
        );


        return "kategorije";
    }



    // sortiranje po nazivu
    @GetMapping("/sortirano")
    public String sortirano(Model model) {


        model.addAttribute(
                "kategorija",
                new Kategorija()
        );


        model.addAttribute(
                "kategorije",
                kategorijaService.sortirajPoNazivu()
        );


        return "kategorije";
    }

}