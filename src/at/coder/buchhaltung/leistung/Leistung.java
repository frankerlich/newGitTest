package at.coder.buchhaltung.leistung;

import javafx.beans.property.SimpleStringProperty;

public class Leistung {
	
	SimpleStringProperty bezeichnung;
	SimpleStringProperty id;
	SimpleStringProperty menge;
	SimpleStringProperty preis;
	
	public Leistung(String bezeichnung, String id, String menge,
			String preis) {
		super();
		this.bezeichnung = new SimpleStringProperty(bezeichnung);
		this.id = new SimpleStringProperty(id);
		this.menge = new SimpleStringProperty(menge);
		this.preis = new SimpleStringProperty(preis);
	}
	
	public Leistung() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}
	public String getBezeichnung() {
		return bezeichnung.get();
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = new SimpleStringProperty(bezeichnung);
	}
	public String getMenge() {
		return menge.get();
	}
	public void setMenge(String menge) {
		this.menge = new SimpleStringProperty(menge);
	}
	public String getPreis() {
		return preis.get();
	}
	public void setPreis(String preis) {
		this.preis = new SimpleStringProperty(preis);
	}
	
}
