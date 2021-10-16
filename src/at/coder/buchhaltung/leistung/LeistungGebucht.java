package at.coder.buchhaltung.leistung;

import javafx.beans.property.SimpleStringProperty;

public class LeistungGebucht {
	
	SimpleStringProperty bezeichnung;
	SimpleStringProperty menge;
	SimpleStringProperty preis;
	SimpleStringProperty netto;
	SimpleStringProperty nettoGesamt;

	SimpleStringProperty bruttoGesamt;
	SimpleStringProperty ust;
	SimpleStringProperty steuersatz;
	SimpleStringProperty bezahlt;
	SimpleStringProperty idLeistung;
	SimpleStringProperty idKunde;
	SimpleStringProperty idRechnung;
	SimpleStringProperty id;
	
	
	public LeistungGebucht(SimpleStringProperty bezeichnung, SimpleStringProperty menge, SimpleStringProperty preis,
			SimpleStringProperty netto, SimpleStringProperty nettoGesamt, 
			SimpleStringProperty bruttoGesamt, SimpleStringProperty ust, SimpleStringProperty steuersatz,
			SimpleStringProperty bezahlt, SimpleStringProperty idLeistung, SimpleStringProperty idKunde,
			SimpleStringProperty id, SimpleStringProperty idRechnung) {
		super();
		this.bezeichnung = bezeichnung;
		this.menge = menge;
		this.preis = preis;
		this.netto = netto;
		this.nettoGesamt = nettoGesamt;
		
		this.bruttoGesamt = bruttoGesamt;
		this.ust = ust;
		this.steuersatz = steuersatz;
		this.bezahlt = bezahlt;
		this.idLeistung = idLeistung;
		this.idKunde = idKunde;
		this.id = id;
		this.idRechnung = idRechnung;
	}
	
	public LeistungGebucht() {
		// TODO Auto-generated constructor stub
		this.bezeichnung = new SimpleStringProperty("");
		this.menge = new SimpleStringProperty("1");
		this.preis = new SimpleStringProperty("-");
		this.netto = new SimpleStringProperty("0");
		this.nettoGesamt = new SimpleStringProperty("0");
		this.bruttoGesamt = new SimpleStringProperty("0");
		this.ust = new SimpleStringProperty("-");
		this.steuersatz = new SimpleStringProperty("20");
		this.bezahlt = new SimpleStringProperty("Nein");
		this.idLeistung = new SimpleStringProperty("-");
		this.idKunde = new SimpleStringProperty("-");
		this.id = new SimpleStringProperty("-");
		this.idRechnung = new SimpleStringProperty("-");
	}

	public String getIdLeistung() {
		System.out.println("TEST ID: " + idLeistung.get());
		return idLeistung.get();
	}
	public void setIdLeistung(String idLeistung) {
		this.idLeistung = new SimpleStringProperty(idLeistung);
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

	public String getNetto() {
		return netto.get();
	}

	public void setNetto(String netto) {
		this.netto = new SimpleStringProperty(netto);
	}

	public String getNettoGesamt() {
		return nettoGesamt.get();
	}

	public void setNettoGesamt(String nettoGesamt) {
		this.nettoGesamt = new SimpleStringProperty(nettoGesamt);
	}

	public String getBruttoGesamt() {
		return bruttoGesamt.get();
	}

	public void setBruttoGesamt(String bruttoGesamt) {
		this.bruttoGesamt = new SimpleStringProperty(bruttoGesamt);
	}

	public String getUst() {
		return ust.get();
	}

	public void setUst(String ust) {
		this.ust = new SimpleStringProperty(ust);
	}

	public String getSteuersatz() {
		return steuersatz.get();
	}

	public void setSteuersatz(String steuersatz) {
		this.steuersatz = new SimpleStringProperty(steuersatz);
	}

	public String getBezahlt() {
		return bezahlt.get();
	}

	public void setBezahlt(String bezahlt) {
		this.bezahlt = new SimpleStringProperty(bezahlt);
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

	public String getIdRechnung() {
		return idRechnung.get();
	}

	public void setIdRechnung(String idRechnung) {
		this.idRechnung = new SimpleStringProperty(idRechnung);
	}
	
	public void leistungGebuchtAusgeben() {
		System.out.println(bezeichnung + " " + menge + " " + preis + " " + netto + " " + nettoGesamt + " " + bruttoGesamt + " " + 
	ust + " " + steuersatz + " " + bezahlt + " " + idLeistung + " " + idKunde + " " + idRechnung);
	}
	
}
