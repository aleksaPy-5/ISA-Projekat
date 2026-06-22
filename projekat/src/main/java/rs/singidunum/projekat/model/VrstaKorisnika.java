package rs.singidunum.projekat.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class VrstaKorisnika {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String naziv;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "vrstaKorisnika")
	private List<Korisnik>korisnici = new ArrayList<Korisnik>();

	public VrstaKorisnika() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VrstaKorisnika(Long id, String naziv, List<Korisnik> korisnici) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.korisnici = korisnici;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	
	
	
}
