package rs.singidunum.projekat.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Kategorija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naziv;
	
	@OneToMany(mappedBy = "kategorija")
	@JsonIgnore
	private List<Proizvod> proizvodi = new ArrayList<Proizvod>();

	public Kategorija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kategorija(Long id, String naziv, List<Proizvod> proizvodi) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.proizvodi = proizvodi;
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

	public List<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public void setProizvodi(List<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}
	
	
}
