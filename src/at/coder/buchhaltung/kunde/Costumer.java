package at.coder.buchhaltung.kunde;

import javafx.beans.property.SimpleStringProperty;

public class Costumer {
	private SimpleStringProperty bezeichnung = new SimpleStringProperty("");
	private SimpleStringProperty anrede = new SimpleStringProperty("");
	private SimpleStringProperty kontaktperson = new SimpleStringProperty("");
	private SimpleStringProperty strasse = new SimpleStringProperty("");
	private SimpleStringProperty hausnummer = new SimpleStringProperty("");
	private SimpleStringProperty plz = new SimpleStringProperty("");
	private SimpleStringProperty ort = new SimpleStringProperty("");
	private SimpleStringProperty land = new SimpleStringProperty("");
	private SimpleStringProperty anzahlRechnungen = new SimpleStringProperty("");
	private SimpleStringProperty uid = new SimpleStringProperty("");
	private SimpleStringProperty email = new SimpleStringProperty("");
	private SimpleStringProperty telefon = new SimpleStringProperty("");
	private SimpleStringProperty beschreibung = new SimpleStringProperty("");
	private SimpleStringProperty id = new SimpleStringProperty("");
	
	

	public Costumer(String bezeichnung, String anrede, String kontaktperson, String strasse, String hausnummer, String plz, String ort,
			String land, String anzahlRechnungen, String uid, String email, String telefon, String beschreibung, String id) {
		
		this.bezeichnung = new SimpleStringProperty(bezeichnung);
		this.anrede = new SimpleStringProperty(anrede);
		this.kontaktperson = new SimpleStringProperty(kontaktperson);
		this.strasse = new SimpleStringProperty(strasse);
		this.hausnummer = new SimpleStringProperty(hausnummer);
		this.plz = new SimpleStringProperty(plz);
		this.ort = new SimpleStringProperty(ort);
		this.land = new SimpleStringProperty(land);
		this.anzahlRechnungen = new SimpleStringProperty(anzahlRechnungen);
		this.uid = new SimpleStringProperty(uid);
		this.email = new SimpleStringProperty(email);
		this.telefon = new SimpleStringProperty(telefon);
		this.beschreibung = new SimpleStringProperty(beschreibung);
		this.id = new SimpleStringProperty(id);
	}

	public Costumer() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}
	
	public String getAnzahlRechnungen() {
		return anzahlRechnungen.get();
	}

	public void setAnzahlRechnungen(String anzahlRechnungen) {
		this.anzahlRechnungen = new SimpleStringProperty(anzahlRechnungen);
	}

	public String getBezeichnung() {
		return bezeichnung.get();
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = new SimpleStringProperty(bezeichnung);
	}

	public String getKontaktperson() {
		return kontaktperson.get();
	}

	public void setKontaktperson(String kontaktperson) {
		this.kontaktperson = new SimpleStringProperty(kontaktperson);
	}

	public String getStrasse() {
		return strasse.get();
	}

	public void setStrasse(String strasse) {
		this.strasse = new SimpleStringProperty(strasse);
	}

	public String getHausnummer() {
		return hausnummer.get();
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = new SimpleStringProperty(hausnummer);
	}

	public String getPlz() {
		return plz.get();
	}

	public void setPlz(String plz) {
		this.plz = new SimpleStringProperty(plz);
	}

	public String getOrt() {
		return ort.get();
	}

	public void setOrt(String ort) {
		this.ort = new SimpleStringProperty(ort);
	}

	public String getLand() {
		return land.get();
	}

	public void setLand(String land) {
		this.land = new SimpleStringProperty(land);
	}

	public String getUid() {
		return uid.get();
	}

	public void setUid(String uid) {
		this.uid = new SimpleStringProperty(uid);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

	public String getTelefon() {
		return telefon.get();
	}

	public void setTelefon(String telefon) {
		this.telefon = new SimpleStringProperty(telefon);
	}

	public String getBeschreibung() {
		return beschreibung.get();
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = new SimpleStringProperty(beschreibung);
	}

	public String getAnrede() {
		return anrede.get();
	}

	public void setAnrede(String anrede) {
		this.anrede = new SimpleStringProperty(anrede);
	}
}
