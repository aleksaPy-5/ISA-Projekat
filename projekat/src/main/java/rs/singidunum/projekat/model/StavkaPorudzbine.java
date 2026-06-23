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
public class StavkaPorudzbine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int kolicina;
	private BigDecimal cena;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="porudzbina_id")
	private Porudzbina porudzbina;
	
	@ManyToOne
	@JoinColumn(name ="proizvod_id")
	private Proizvod proizvod;

	public StavkaPorudzbine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StavkaPorudzbine(Long id, int kolicina, BigDecimal cena, Porudzbina porudzbina, Proizvod proizvod) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.cena = cena;
		this.porudzbina = porudzbina;
		this.proizvod = proizvod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}


	
	
}
