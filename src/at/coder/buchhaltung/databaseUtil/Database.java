package at.coder.buchhaltung.databaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.coder.buchhaltung.kunde.Costumer;
import at.coder.buchhaltung.leistung.Leistung;
import at.coder.buchhaltung.leistung.LeistungGebucht;
import at.coder.buchhaltung.rechnung.Rechnung;
import at.coder.buchhaltung.utils.Utils;

public class Database {
	public static final String DB_NAME = "buchhaltung.db";
	public static final String DB_PATH = "D:\\Eisenbahn Daten\\Java\\Eclipse Workbench\\Buchhaltungssoftware\\src\\";
	public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_PATH + DB_NAME;

	public Connection connection;

	public boolean open() {
		try {
			connection = DriverManager.getConnection(CONNECTION_STRING);
			System.out.println("true");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("false");
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	// FUNKTIONEN FÜR KUNDENTABELLE
	public boolean createCostumer(Statement statement, Costumer costumer) {
		try {
			String sql = "INSERT INTO costumers (bezeichnung, " + "anrede, " + "kontaktperson, " + "strasse, "
					+ "hausnummer, " + "plz, " + "ort, " + "land, " + "rechnungen,  " + "uid, " + "email, "
					+ "telefon, " + "beschreibung) VALUES ('" + costumer.getBezeichnung() + "', '"
					+ costumer.getAnrede() + "', '" + costumer.getKontaktperson() + "', '" + costumer.getStrasse()
					+ "', '" + costumer.getHausnummer() + "', '" + costumer.getPlz() + "', '" + costumer.getOrt()
					+ "', '" + costumer.getLand() + "', '" + costumer.getAnzahlRechnungen() + "', '" + costumer.getUid()
					+ "', '" + costumer.getEmail() + "', '" + costumer.getTelefon() + "', '"
					+ costumer.getBeschreibung() + "')";
			statement.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public ResultSet loadAllCostumers(Statement statement) {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM costumers");// Abfrage an Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ResultSet loadAllCostumersColumn(Statement statement, String suchstring, String column) {
		try {
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM costumers WHERE " + column + " LIKE " + "'%" + suchstring + "%'");// Abfrage
																													// an
																													// Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void updateCostumer(Statement statement, String column, String newValue, int id) {
		try {
			statement.execute("UPDATE costumers SET " + column + " = " + "'" + newValue + "' WHERE id=" + id);// Abfrage
																												// an
																												// Datenbank
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void deleteCostumer(Statement statement, String id) {
		try {
			statement.execute("DELETE FROM costumers WHERE id=" + id);// Abfrage
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// FUNKTIONEN FÜR LEISTUNG
	public ResultSet loadAllLeistungen(Statement statement) {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM leistungen");// Abfrage an Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean createLeistung(Statement statement, Leistung leistung) {
		try {
			String sql = "INSERT INTO leistungen (bezeichnung, " + "menge, " + "preis) VALUES ('"
					+ leistung.getBezeichnung() + "', '" + leistung.getMenge() + "', '" + leistung.getPreis() + "')";
			statement.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getMessage());
			return false;
		}
	}

	public void updateLeistung(Statement statement, String column, String newValue, int id) {
		try {
			statement.execute("UPDATE leistungen SET " + column + " = " + "'" + newValue + "' WHERE id=" + id);// Abfrage
																												// an
																												// Datenbank
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// FUNKTIONEN FÜR LEISTUNGGEBUCHTTABELLE
	public ResultSet loadAllLeistungenGebucht(Statement statement) {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM gebuchte_leistungen");// Abfrage an Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ResultSet loadAllLeistungenGebuchtFromRechnung(Statement statement, String rechnungsnummer) { 
		try {
//			String sql = "SELECT * FROM gebuchte_leistungen WHERE id_rechnung = " + "'0000000049'";
			String sql2 = "SELECT * FROM gebuchte_leistungen WHERE id_rechnung = " + "'"+ Utils.getRechnungsnummerFromId(Integer.valueOf(rechnungsnummer)) +"'";
//			System.out.println(sql);
			System.out.println(sql2);
			ResultSet resultSet = statement.executeQuery(sql2);// Abfrage an Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean createLeistungGebucht(Statement statement, LeistungGebucht leistungGebucht) {
		try {
			leistungGebucht.leistungGebuchtAusgeben();
			String sql = "INSERT INTO gebuchte_leistungen (bezeichnung, " + "menge, " + "preis, " + "netto, "
					+ "netto_gesamt, " + "brutto_gesamt," + "ust, " + "steuersatz, " + "bezahlt, " + "id_leistung, "
					+ "id_kunde, " + "id_rechnung) VALUES ('" + leistungGebucht.getBezeichnung() + "', '"
					+ leistungGebucht.getMenge() + "', '" + leistungGebucht.getPreis() + "', '"
					+ leistungGebucht.getNetto() + "', '" + leistungGebucht.getNettoGesamt() + "', '"
					+ leistungGebucht.getBruttoGesamt() + "', '" + leistungGebucht.getUst() + "', '"
					+ leistungGebucht.getSteuersatz() + "', '" + leistungGebucht.getBezahlt() + "', '"
					+ leistungGebucht.getIdLeistung() + "', '" + leistungGebucht.getIdKunde() + "', '"
					+ leistungGebucht.getIdRechnung() + "')";
			statement.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println("Fehler in Methode createLeistungGebucht: " + e);
			return false;
		}
	}

	public void updateLeistungGebucht(Statement statement, String column, String newValue, int id) {
		try {
			statement.execute("UPDATE gebuchte_leistungen SET " + column + " = " + "'" + newValue + "' WHERE id=" + id);// Abfrage
			// an
			// Datenbank
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// FUNKTIONEN FÜR RECHNUNGSTABELLE
	public ResultSet loadAllRechnungen(Statement statement) {
		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM rechnungen");// Abfrage an Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ResultSet loadRechnungenFromCostumer(Statement statement, String id) {
		try {
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM rechnungen WHERE id_kunde ='" + id + "'");// Abfrage
																													// an
																													// Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ResultSet loadRechnung(Statement statement, String id) {
		try {
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM rechnungen WHERE id ='" + id + "'");// Abfrage
																													// an
																													// Datenbank
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int createRechnung(Statement statement, Rechnung rechnung) {
		try {
			String sql = "INSERT INTO rechnungen (unternehmen, " + "unternehmen_strasse, " + "unternehmen_hausnummer, "
					+ "unternehmen_plz, unternehmen_ort, unternehmen_land, " + "standort_gewerbeberechtigung_strasse, "
					+ "standort_gewerbeberechtigung_hausnummer, " + "standort_gewerbeberechtigung_plz, "
					+ "standort_gewerbeberechtigung_ort, " + "standort_gewerbeberechtigung_land,  " + "kunden_name, "
					+ "kunden_strasse, " + "kunden_hausnummer, " + "kunden_plz, " + "kunden_land, " + "id_kunde, "
					+ "rechnungsdatum, " + "erstellungsdatum, " + "rechnung_beglichen, " + "zahlungsziel, "
					+ "brutto_gesamt, " + "netto_gesamt) VALUES ('" + rechnung.getUNTERNEHMEN() + "', '"
					+ rechnung.getUNTERNEHMEN_STRASSE() + "', '" + rechnung.getUNTERNEHMEN_HAUSNUMMER() + "', '"
					+ rechnung.getUNTERNHEMEN_PLZ() + "', '" + rechnung.getUNTERNHEMEN_ORT() + "', '"
					+ rechnung.getUNTERNEHMEN_LAND() + "', '" + rechnung.getSTANDORD_GEWERBEBERECHTIGUNG_STRASSE()
					+ "', '" + rechnung.getSTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER() + "', '"
					+ rechnung.getSTANDORD_GEWERBEBERECHTIGUNG_PLZ() + "', '"
					+ rechnung.getSTANDORD_GEWERBEBERECHTIGUNG_ORT() + "', '"
					+ rechnung.getSTANDORD_GEWERBEBERECHTIGUNG_LAND() + "', '" + rechnung.getKundenname() + "', '"
					+ rechnung.getKUNDEN_STRASSE() + "', '" + rechnung.getKUNDEN_HAUSNUMMER() + "', '"
					+ rechnung.getKUNDEN_PLZ() + "', '" + rechnung.getKUNDEN_LAND() + "', '" + rechnung.getIdKunde()
					+ "', '" + rechnung.getRechnungsdatum() + "', '" + rechnung.getErstellungsdatum() + "', '"
					+ rechnung.getRechnungBereitsBeglichen() + "', '" + rechnung.getZahlungsziel() + "', '"
					+ rechnung.getBruttoSumme() + "', '" + rechnung.getNettoSumme() + "')";
			statement.execute(sql);
			ResultSet resSet = statement.getGeneratedKeys();
			int insertedKey = -5;
			if (resSet.next()) {
				insertedKey = resSet.getInt(1);
				System.out.println("ID: " + resSet.getInt(1));
			}
			return insertedKey;
		} catch (Exception e) {
			System.out.println("Fehler: " + e.getMessage());
			return -1;
		}
	}

}
