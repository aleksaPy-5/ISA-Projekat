package rs.singidunum.projekat.model;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Proizvod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String naziv;
	@Column(length = 150)
	private String opis;
	private BigDecimal cena;
	private int kolicinaNaLageru;
	
	@OneToMany(mappedBy = "proizvod")
	private List<StavkaPorudzbine>stavkePorudzbine = new ArrayList<StavkaPorudzbine>();
	
	@ManyToOne
	@JoinColumn(name = "kategorija_id")
	private Kategorija kategorija;

	public Proizvod() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proizvod(Long id, String naziv, String opis, BigDecimal cena, int kolicinaNaLageru,
			List<StavkaPorudzbine> stavkePorudzbine, Kategorija kategorija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.kolicinaNaLageru = kolicinaNaLageru;
		this.stavkePorudzbine = stavkePorudzbine;
		this.kategorija = kategorija;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public int getKolicinaNaLageru() {
		return kolicinaNaLageru;
	}

	public void setKolicinaNaLageru(int kolicinaNaLageru) {
		this.kolicinaNaLageru = kolicinaNaLageru;
	}

	public List<StavkaPorudzbine> getStavkePorudzbine() {
		return stavkePorudzbine;
	}

	public void setStavkePorudzbine(List<StavkaPorudzbine> stavkePorudzbine) {
		this.stavkePorudzbine = stavkePorudzbine;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	
	
}
