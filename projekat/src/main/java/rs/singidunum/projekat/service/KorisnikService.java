package rs.singidunum.projekat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.singidunum.projekat.model.Korisnik;
import rs.singidunum.projekat.repository.KorisnikRepository;
import rs.singidunum.projekat.repository.VrstaKorisnikaRepository;

@Service
public class KorisnikService {


private final KorisnikRepository korisnikRepository;

private final VrstaKorisnikaRepository vrstaKorisnikaRepository;



public KorisnikService(
        KorisnikRepository korisnikRepository,
        VrstaKorisnikaRepository vrstaKorisnikaRepository) {

    this.korisnikRepository = korisnikRepository;
    this.vrstaKorisnikaRepository = vrstaKorisnikaRepository;

}



// prikaz svih korisnika
public List<Korisnik> findAll() {

    return korisnikRepository.findAll();

}



// pronalazak korisnika po id-u
public Korisnik findById(Long id) {

    return korisnikRepository.findById(id)
            .orElseThrow(() ->
            new RuntimeException(
                    "Korisnik ne postoji"));

}



// pretraga korisnika po imenu
public List<Korisnik> pretraga(String ime) {

    return korisnikRepository
            .findByImeContainingIgnoreCase(ime);

}



// validacija korisnika
private void validiraj(Korisnik korisnik) {


    if(korisnik.getIme() == null ||
            korisnik.getIme().trim().isEmpty()) {


        throw new RuntimeException(
                "Ime je obavezno");

    }



    if(korisnik.getPrezime() == null ||
            korisnik.getPrezime().trim().isEmpty()) {


        throw new RuntimeException(
                "Prezime je obavezno");

    }



    if(korisnik.getEmail() == null ||
            korisnik.getEmail().trim().isEmpty()) {


        throw new RuntimeException(
                "Email je obavezan");

    }



    if(korisnik.getLozinka() == null ||
            korisnik.getLozinka().length() < 6) {


        throw new RuntimeException(
                "Lozinka mora imati najmanje 6 karaktera");

    }



    if(korisnik.getVrstaKorisnika() == null) {


        throw new RuntimeException(
                "Vrsta korisnika je obavezna");

    }

}



// dodavanje korisnika
public Korisnik save(Korisnik korisnik) {


    validiraj(korisnik);



    if(korisnikRepository.existsByEmail(
            korisnik.getEmail())) {


        throw new RuntimeException(
                "Email vec postoji");

    }



    return korisnikRepository.save(korisnik);

}







// izmena korisnika
public Korisnik update(
        Long id,
        Korisnik izmeniKorisnika) {


    Korisnik postojeciKorisnik =
            findById(id);



    validiraj(izmeniKorisnika);



    if(!postojeciKorisnik.getEmail()
            .equalsIgnoreCase(
                    izmeniKorisnika.getEmail())
            &&
            korisnikRepository.existsByEmail(
                    izmeniKorisnika.getEmail())) {


        throw new RuntimeException(
                "Email vec postoji");

    }



    postojeciKorisnik.setIme(
            izmeniKorisnika.getIme());


    postojeciKorisnik.setPrezime(
            izmeniKorisnika.getPrezime());


    postojeciKorisnik.setEmail(
            izmeniKorisnika.getEmail());


    postojeciKorisnik.setLozinka(
            izmeniKorisnika.getLozinka());


    postojeciKorisnik.setTelefon(
            izmeniKorisnika.getTelefon());


    postojeciKorisnik.setAdresa(
            izmeniKorisnika.getAdresa());


    postojeciKorisnik.setVrstaKorisnika(
            izmeniKorisnika.getVrstaKorisnika());



    return korisnikRepository.save(
            postojeciKorisnik);

}



// brisanje korisnika
public void delete(Long id) {


    Korisnik korisnik =
            findById(id);


    korisnikRepository.delete(korisnik);

}


}
