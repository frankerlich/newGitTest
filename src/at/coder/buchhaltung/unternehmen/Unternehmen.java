package at.coder.buchhaltung.unternehmen;

import java.io.Serializable;

public class Unternehmen implements Serializable { //Serializeable ist notwendig um das Objekt in ein File zu schreiben.
	// Unternehmensdaten
	public String unternehmen = "";
	public String unternehmen_strasse = "";
	public String unternehmen_hausnummer = "";
	public String unternehmen_plz = "";
	public String unternehmen_ort = "";
	public String unternehmen_land = "";

	public String standort_gewerbeberechtigung_strasse = "";
	public String standort_gewerbeberechtigung_hausnummer = "";
	public String standort_gewerbeberechtigung_plz = "";
	public String standort_gewerbeberechtigung_ort = "";
	public String standort_gewerbeberechtigung_land = "";

	public String getUnternehmen() {
		return unternehmen;
	}

	public void setUnternehmen(String unternehmen) {
		this.unternehmen = unternehmen;
	}

	public String getUnternehmen_strasse() {
		return unternehmen_strasse;
	}

	public void setUnternehmen_strasse(String unternehmen_strasse) {
		this.unternehmen_strasse = unternehmen_strasse;
	}

	public String getUnternehmen_hausnummer() {
		return unternehmen_hausnummer;
	}

	public void setUnternehmen_hausnummer(String unternehmen_hausnummer) {
		this.unternehmen_hausnummer = unternehmen_hausnummer;
	}

	public String getUnternehmen_plz() {
		return unternehmen_plz;
	}

	public void setUnternehmen_plz(String unternehmen_plz) {
		this.unternehmen_plz = unternehmen_plz;
	}

	public String getUnternehmen_ort() {
		return unternehmen_ort;
	}

	public void setUnternehmen_ort(String unternehmen_ort) {
		this.unternehmen_ort = unternehmen_ort;
	}
	
	public String getUnternehmen_land() {
		return unternehmen_land;
	}

	public void setUnternehmen_land(String unternehmen_land) {
		this.unternehmen_land = unternehmen_land;
	}

	public String getStandort_gewerbeberechtigung_strasse() {
		return standort_gewerbeberechtigung_strasse;
	}

	public void setStandort_gewerbeberechtigung_strasse(String standort_gewerbeberechtigung_strasse) {
		this.standort_gewerbeberechtigung_strasse = standort_gewerbeberechtigung_strasse;
	}

	public String getStandort_gewerbeberechtigung_hausnummer() {
		return standort_gewerbeberechtigung_hausnummer;
	}

	public void setStandort_gewerbeberechtigung_hausnummer(String standort_gewerbeberechtigung_hausnummer) {
		this.standort_gewerbeberechtigung_hausnummer = standort_gewerbeberechtigung_hausnummer;
	}

	public String getStandort_gewerbeberechtigung_plz() {
		return standort_gewerbeberechtigung_plz;
	}

	public void setStandort_gewerbeberechtigung_plz(String standort_gewerbeberechtigung_plz) {
		this.standort_gewerbeberechtigung_plz = standort_gewerbeberechtigung_plz;
	}

	public String getStandort_gewerbeberechtigung_land() {
		return standort_gewerbeberechtigung_land;
	}

	public void setStandort_gewerbeberechtigung_land(String standort_gewerbeberechtigung_land) {
		this.standort_gewerbeberechtigung_land = standort_gewerbeberechtigung_land;
	}

	
	public String getStandort_gewerbeberechtigung_ort() {
		return standort_gewerbeberechtigung_ort;
	}

	public void setStandort_gewerbeberechtigung_ort(String standort_gewerbeberechtigung_ort) {
		this.standort_gewerbeberechtigung_ort = standort_gewerbeberechtigung_ort;
	}
	
	public void unternehmenAusgeben() {
		System.out.println("Unternehmensbezeichnung: " + this.getUnternehmen());
		System.out.println("Unternehmen Straße: " + this.getUnternehmen_strasse());
		System.out.println("Unternehmen Hausnummer: " + this.getUnternehmen_hausnummer());
		System.out.println("Unternehmen Plz: " + this.getUnternehmen_plz());
		System.out.println("Unternehmen Ort: " + this.getUnternehmen_ort());
		System.out.println("Unternehmen Land: " + this.getUnternehmen_land());
	}
}
