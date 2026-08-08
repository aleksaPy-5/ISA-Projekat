package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.VrstaKorisnika;
import rs.singidunum.projekat.repository.VrstaKorisnikaRepository;


@Service
public class VrstaKorisnikaService {


    private final VrstaKorisnikaRepository repository;


    public VrstaKorisnikaService(
            VrstaKorisnikaRepository repository) {

        this.repository = repository;
    }



    public List<VrstaKorisnika> findAll() {
        return repository.findAll();
    }



    public VrstaKorisnika findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                new RuntimeException("Vrsta korisnika ne postoji"));
    }



    public VrstaKorisnika save(VrstaKorisnika vrsta) {

        if(vrsta.getNaziv() == null ||
           vrsta.getNaziv().isBlank()) {

            throw new RuntimeException(
                    "Naziv vrste korisnika je obavezan");
        }

        return repository.save(vrsta);
    }



    public VrstaKorisnika update(
            Long id,
            VrstaKorisnika novaVrsta) {


        VrstaKorisnika postojeca = findById(id);

        postojeca.setNaziv(novaVrsta.getNaziv());

        return repository.save(postojeca);
    }



    public void delete(Long id) {

        VrstaKorisnika vrsta = findById(id);

        repository.delete(vrsta);
    }

}