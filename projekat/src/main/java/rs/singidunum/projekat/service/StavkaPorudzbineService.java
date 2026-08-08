package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.StavkaPorudzbine;
import rs.singidunum.projekat.repository.StavkaPorudzbineRepository;


@Service
public class StavkaPorudzbineService {


    private final StavkaPorudzbineRepository stavkaPorudzbineRepository;



    public StavkaPorudzbineService(
            StavkaPorudzbineRepository stavkaPorudzbineRepository) {

        this.stavkaPorudzbineRepository = stavkaPorudzbineRepository;

    }




    // validacija stavke
    private void validiraj(StavkaPorudzbine stavkaPorudzbine) {


        if(stavkaPorudzbine.getProizvod() == null) {

            throw new RuntimeException(
                    "Proizvod je obavezan");

        }



        if(stavkaPorudzbine.getKolicina() <= 0) {

            throw new RuntimeException(
                    "Kolicina mora biti veca od 0");

        }



        if(stavkaPorudzbine.getCena() == null) {

            throw new RuntimeException(
                    "Cena proizvoda je obavezna");

        }

    }





    // prikaz svih stavki
    public List<StavkaPorudzbine> findAll() {

        return stavkaPorudzbineRepository.findAll();

    }





    // stavke jedne porudzbine
    public List<StavkaPorudzbine> findByPorudzbinaId(Long porudzbinaId) {

        return stavkaPorudzbineRepository
                .findByPorudzbinaId(porudzbinaId);

    }





    // pronalazak po id-u
    public StavkaPorudzbine findById(Long id) {


        return stavkaPorudzbineRepository.findById(id)
                .orElseThrow(() ->
                new RuntimeException(
                        "Stavka porudzbine ne postoji"));

    }





    // dodavanje stavke
    public StavkaPorudzbine save(
            StavkaPorudzbine stavkaPorudzbine) {


        validiraj(stavkaPorudzbine);


        return stavkaPorudzbineRepository.save(
                stavkaPorudzbine);

    }





    // izmena stavke
    public StavkaPorudzbine update(
            Long id,
            StavkaPorudzbine izmeniStavku) {


        StavkaPorudzbine postojecaStavka =
                findById(id);



        validiraj(izmeniStavku);



        postojecaStavka.setKolicina(
                izmeniStavku.getKolicina());



        return stavkaPorudzbineRepository.save(
                postojecaStavka);

    }





    // brisanje stavke
    public void delete(Long id) {


        StavkaPorudzbine stavka =
                findById(id);


        stavkaPorudzbineRepository.delete(stavka);

    }


}