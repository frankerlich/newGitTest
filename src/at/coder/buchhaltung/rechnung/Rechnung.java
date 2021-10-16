package at.coder.buchhaltung.rechnung;

import at.coder.buchhaltung.main.MainController;
import javafx.beans.property.SimpleStringProperty;

public class Rechnung {
	private SimpleStringProperty UNTERNEHMEN;
	
	private SimpleStringProperty UNTERNEHMEN_STRASSE;
	private SimpleStringProperty UNTERNEHMEN_HAUSNUMMER;
	private SimpleStringProperty UNTERNHEMEN_PLZ;
	private SimpleStringProperty UNTERNEHMEN_ORT;
	private SimpleStringProperty UNTERNEHMEN_LAND;
	
	private SimpleStringProperty STANDORD_GEWERBEBERECHTIGUNG_STRASSE;
	private SimpleStringProperty STANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER;
	private SimpleStringProperty STANDORD_GEWERBEBERECHTIGUNG_PLZ;
	private SimpleStringProperty STANDORD_GEWERBEBERECHTIGUNG_ORT;
	private SimpleStringProperty STANDORD_GEWERBEBERECHTIGUNG_LAND;
	
	private SimpleStringProperty kundenname;
	private SimpleStringProperty KUNDEN_STRASSE;
	private SimpleStringProperty KUNDEN_HAUSNUMMER;
	private SimpleStringProperty KUNDEN_PLZ;
	private SimpleStringProperty KUNDEN_LAND;
	
	private SimpleStringProperty idKunde;
	private SimpleStringProperty id;
	private SimpleStringProperty rechnungsdatum;
	private SimpleStringProperty erstellungsdatum;
	private SimpleStringProperty rechnungBereitsBeglichen = new SimpleStringProperty("Nein");
	private SimpleStringProperty zahlungsziel = new SimpleStringProperty("14");
	private SimpleStringProperty bruttoSumme = new SimpleStringProperty("0.00");
	private SimpleStringProperty nettoSumme = new SimpleStringProperty("0.00");
	
	
	public Rechnung(String uNTERNEHMEN, 
			String uNTERNEHMEN_STRASSE,
			String uNTERNEHMEN_HAUSNUMMER, 
			String uNTERNHEMEN_PLZ, 
			String uNTERNEHMEN_ORT,
			String uNTERNEHMEN_LAND,
			String sTANDORD_GEWERBEBERECHTIGUNG_STRASSE,
			String sTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER,
			String sTANDORD_GEWERBEBERECHTIGUNG_PLZ,
			String sTANDORD_GEWERBEBERECHTIGUNG_ORT, 
			String sTANDORD_GEWERBEBERECHTIGUNG_LAND, 
			String kundenname,
			String kUNDEN_STRASSE, 
			String kUNDEN_HAUSNUMMER,
			String kUNDEN_PLZ, 
			String kUNDEN_LAND, 
			String idKunde,
			String id, 
			String rechnungsdatum,
			String erstellungsdatum, 
			String rechnungBereitsBeglichen,
			String zahlungsziel, 
			String bruttoSumme, 
			String nettoSumme) {

		UNTERNEHMEN = new SimpleStringProperty(uNTERNEHMEN);
		UNTERNEHMEN_STRASSE = new SimpleStringProperty(uNTERNEHMEN_STRASSE);
		UNTERNEHMEN_HAUSNUMMER = new SimpleStringProperty(uNTERNEHMEN_HAUSNUMMER);
		UNTERNHEMEN_PLZ = new SimpleStringProperty(uNTERNHEMEN_PLZ);
		UNTERNEHMEN_ORT = new SimpleStringProperty(uNTERNEHMEN_ORT);
		UNTERNEHMEN_LAND = new SimpleStringProperty(uNTERNEHMEN_LAND);
		STANDORD_GEWERBEBERECHTIGUNG_STRASSE = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_STRASSE);
		STANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER);
		STANDORD_GEWERBEBERECHTIGUNG_PLZ = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_PLZ);
		STANDORD_GEWERBEBERECHTIGUNG_ORT = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_ORT);
		STANDORD_GEWERBEBERECHTIGUNG_LAND = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_LAND);
		this.kundenname = new SimpleStringProperty(kundenname);
		KUNDEN_STRASSE = new SimpleStringProperty(kUNDEN_STRASSE);
		KUNDEN_HAUSNUMMER = new SimpleStringProperty(kUNDEN_HAUSNUMMER);
		KUNDEN_PLZ = new SimpleStringProperty(kUNDEN_PLZ);
		KUNDEN_LAND = new SimpleStringProperty(kUNDEN_LAND);
		this.idKunde = new SimpleStringProperty(idKunde);
		this.id = new SimpleStringProperty(id);
		this.rechnungsdatum = new SimpleStringProperty(rechnungsdatum);
		this.erstellungsdatum = new SimpleStringProperty(erstellungsdatum);
		this.rechnungBereitsBeglichen = new SimpleStringProperty(rechnungBereitsBeglichen);
		this.zahlungsziel = new SimpleStringProperty(zahlungsziel);
		this.bruttoSumme = new SimpleStringProperty(bruttoSumme);
		this.nettoSumme = new SimpleStringProperty(nettoSumme);
	}
	
	
	public String getUNTERNEHMEN() {
		return UNTERNEHMEN.get();
	}
	public void setUNTERNEHMEN(String uNTERNEHMEN) {
		UNTERNEHMEN = new SimpleStringProperty(uNTERNEHMEN);
	}
	public String getUNTERNEHMEN_STRASSE() {
		return UNTERNEHMEN_STRASSE.get();
	}
	public void setUNTERNEHMEN_STRASSE(String uNTERNEHMEN_STRASSE) {
		UNTERNEHMEN_STRASSE = new SimpleStringProperty(uNTERNEHMEN_STRASSE);
	}
	public String getUNTERNEHMEN_HAUSNUMMER() {
		return UNTERNEHMEN_HAUSNUMMER.get();
	}
	public void setUNTERNEHMEN_HAUSNUMMER(String uNTERNEHMEN_HAUSNUMMER) {
		UNTERNEHMEN_HAUSNUMMER = new SimpleStringProperty(uNTERNEHMEN_HAUSNUMMER);
	}
	public String getUNTERNHEMEN_PLZ() {
		return UNTERNHEMEN_PLZ.get();
	}
	public void setUNTERNHEMEN_PLZ(String uNTERNHEMEN_PLZ) {
		UNTERNHEMEN_PLZ = new SimpleStringProperty(uNTERNHEMEN_PLZ);
	}
	public String getUNTERNHEMEN_ORT() {
		return UNTERNEHMEN_ORT.get();
	}
	public void setUNTERNHEMEN_ORT(String uNTERNHEMEN_ORT) {
		UNTERNEHMEN_ORT = new SimpleStringProperty(uNTERNHEMEN_ORT);
	}
	public String getUNTERNEHMEN_LAND() {
		return UNTERNEHMEN_LAND.get();
	}
	public void setUNTERNEHMEN_LAND(String uNTERNEHMEN_LAND) {
		UNTERNEHMEN_LAND = new SimpleStringProperty(uNTERNEHMEN_LAND);
	}
	public String getSTANDORD_GEWERBEBERECHTIGUNG_STRASSE() {
		return STANDORD_GEWERBEBERECHTIGUNG_STRASSE.get();
	}
	public void setSTANDORD_GEWERBEBERECHTIGUNG_STRASSE(String sTANDORD_GEWERBEBERECHTIGUNG_STRASSE) {
		STANDORD_GEWERBEBERECHTIGUNG_STRASSE = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_STRASSE);
	}
	public String getSTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER() {
		return STANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER.get();
	}
	public void setSTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER(String sTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER) {
		STANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER);
	}
	public String getSTANDORD_GEWERBEBERECHTIGUNG_PLZ() {
		return STANDORD_GEWERBEBERECHTIGUNG_PLZ.get();
	}
	public void setSTANDORD_GEWERBEBERECHTIGUNG_PLZ(
			String sTANDORD_GEWERBEBERECHTIGUNG_UNTERNHEMEN_PLZ) {
		STANDORD_GEWERBEBERECHTIGUNG_PLZ = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_UNTERNHEMEN_PLZ);
	}
	public String getSTANDORD_GEWERBEBERECHTIGUNG_LAND() {
		return STANDORD_GEWERBEBERECHTIGUNG_LAND.get();
	}
	public void setSTANDORD_GEWERBEBERECHTIGUNG_LAND(
			String sTANDORD_GEWERBEBERECHTIGUNG_UNTERNEHMEN_LAND) {
		STANDORD_GEWERBEBERECHTIGUNG_LAND = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_UNTERNEHMEN_LAND);
	}
	public String getSTANDORD_GEWERBEBERECHTIGUNG_ORT() {
		return STANDORD_GEWERBEBERECHTIGUNG_ORT.get();
	}
	public void setSTANDORD_GEWERBEBERECHTIGUNG_ORT(
			String sTANDORD_GEWERBEBERECHTIGUNG_UNTERNEHMEN_ORT) {
		STANDORD_GEWERBEBERECHTIGUNG_ORT = new SimpleStringProperty(sTANDORD_GEWERBEBERECHTIGUNG_UNTERNEHMEN_ORT);
	}
	public String getKundenname() {
		return kundenname.get();
	}
	public void setKundenname(String kUNDEN_NAME) {
		kundenname = new SimpleStringProperty(kUNDEN_NAME);
	}
	public String getKUNDEN_STRASSE() {
		return KUNDEN_STRASSE.get();
	}
	public void setKUNDEN_STRASSE(String kUNDEN_STRASSE) {
		KUNDEN_STRASSE = new SimpleStringProperty(kUNDEN_STRASSE);
	}
	public String getKUNDEN_HAUSNUMMER() {
		return KUNDEN_HAUSNUMMER.get();
	}
	public void setKUNDEN_HAUSNUMMER(String kUNDEN_HAUSNUMMER) {
		KUNDEN_HAUSNUMMER = new SimpleStringProperty(kUNDEN_HAUSNUMMER);
	}
	public String getKUNDEN_PLZ() {
		return KUNDEN_PLZ.get();
	}
	public void setKUNDEN_PLZ(String kUNDEN_PLZ) {
		KUNDEN_PLZ = new SimpleStringProperty(kUNDEN_PLZ);
	}
	public String getKUNDEN_LAND() {
		return KUNDEN_LAND.get();
	}
	public void setKUNDEN_LAND(String kUNDEN_LAND) {
		KUNDEN_LAND = new SimpleStringProperty(kUNDEN_LAND);
	}
	public String getIdKunde() {
		return idKunde.get();
	}
	public void setIdKunde(String idKunde) {
		this.idKunde = new SimpleStringProperty(idKunde);
	}
	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}
	public String getRechnungsdatum() {
		return rechnungsdatum.get();
	}
	public void setRechnungsdatum(String rechnungsdatum) {
		this.rechnungsdatum = new SimpleStringProperty(rechnungsdatum);
	}
	public String getErstellungsdatum() {
		return erstellungsdatum.get();
	}
	public void setErstellungsdatum(String erstellungsdatum) {
		this.erstellungsdatum = new SimpleStringProperty(erstellungsdatum);
	}
	public String getRechnungBereitsBeglichen() {
		return rechnungBereitsBeglichen.get();
	}
	public void setRechnungBereitsBeglichen(String rechnungBereitsBeglichen) {
		this.rechnungBereitsBeglichen = new SimpleStringProperty(rechnungBereitsBeglichen);
	}
	public String getZahlungsziel() {
		return zahlungsziel.get();
	}
	public void setZahlungsziel(String zahlungsziel) {
		this.zahlungsziel = new SimpleStringProperty(zahlungsziel);
	}
	public String getBruttoSumme() {
		return bruttoSumme.get();
	}
	public void setBruttoSumme(String bruttoSumme) {
		this.bruttoSumme = new SimpleStringProperty(bruttoSumme);
	}
	public String getNettoSumme() {
		return nettoSumme.get();
	}
	public void setNettoSumme(String nettoSumme) {
		this.nettoSumme = new SimpleStringProperty(nettoSumme);
	}
	
	public void rechnungAusgeben() {
		System.out.println("Rechnung unternehmen: " + this.getUNTERNEHMEN());
		System.out.println("Rechnung unternehmen straﬂe: " + this.getUNTERNEHMEN_STRASSE());
		System.out.println("Rechnung unternehmen hausnummer: " + this.getUNTERNEHMEN_HAUSNUMMER());
		System.out.println("Rechnung unternehmen: plz " + this.getUNTERNHEMEN_PLZ());
		System.out.println("Rechnung unternehmen: ort " + this.getUNTERNHEMEN_ORT());
		System.out.println("Rechnung unternehmen: land " + this.getUNTERNEHMEN_LAND());
	}
}
