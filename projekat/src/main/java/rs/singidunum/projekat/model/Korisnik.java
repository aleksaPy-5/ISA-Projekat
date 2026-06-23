package rs.singidunum.projekat.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="korisnik")
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String ime;
	@Column(nullable = false)
	private String prezime;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(length = 150)
	private String lozinka;
	@Column(length = 50)
	private String telefon;
	@Column(length = 250)
	private String adresa;
	
	@ManyToOne
	@JoinColumn(name ="vrsta_korisnika_id")
	private VrstaKorisnika vrstaKorisnika;
	
	
	@OneToMany(mappedBy = "korisnik")
	@JsonIgnore
	private List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(Long id, String ime, String prezime, String email, String lozinka, String telefon, String adresa,
			VrstaKorisnika vrstaKorisnika, List<Porudzbina> porudzbine) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.telefon = telefon;
		this.adresa = adresa;
		this.vrstaKorisnika = vrstaKorisnika;
		this.porudzbine = porudzbine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public VrstaKorisnika getVrstaKorisnika() {
		return vrstaKorisnika;
	}

	public void setVrstaKorisnika(VrstaKorisnika vrstaKorisnika) {
		this.vrstaKorisnika = vrstaKorisnika;
	}

	public List<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}

	
}
