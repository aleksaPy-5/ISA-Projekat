package rs.singidunum.projekat.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Porudzbina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal ukupnaCena;
	private String status;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	public Porudzbina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Porudzbina(Long id, BigDecimal ukupnaCena, String status, Korisnik korisnik) {
		super();
		this.id = id;
		this.ukupnaCena = ukupnaCena;
		this.status = status;
		this.korisnik = korisnik;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(BigDecimal ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
