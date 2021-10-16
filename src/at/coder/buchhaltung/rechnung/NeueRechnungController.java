package at.coder.buchhaltung.rechnung;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import at.coder.buchhaltung.databaseUtil.Database;
import at.coder.buchhaltung.kunde.Costumer;
import at.coder.buchhaltung.leistung.Leistung;
import at.coder.buchhaltung.leistung.LeistungGebucht;
import at.coder.buchhaltung.unternehmen.Unternehmen;
import at.coder.buchhaltung.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NeueRechnungController {
	// Attribute
	private Costumer costumer;
	private Unternehmen unternehmen;
	private Database database = new Database();
	private Rechnung rechnung = new Rechnung("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "");
	private Rechnung rechnungGeladen;
	private ObservableList<LeistungGebucht> leistgb = FXCollections.observableArrayList();
	private ObservableList<LeistungGebucht> listLeistunggebuchtKopie = FXCollections.observableArrayList();

	@FXML
	private TableView<Leistung> tableViewLeistungsSuche;
	@FXML
	private Label labelRechnungsnummer;
	@FXML
	private DatePicker datePickerRechnungsdatum;
	@FXML
	private CheckBox checkBoxBereitsBeglichen;
	@FXML
	private DatePicker datePickerErstellungsdatum;
	@FXML
	private Spinner<Integer> spinnerZahlungsziel;
	@FXML
	private Button buttonRechnungErstellen;
	@FXML
	private TableColumn<Leistung, String> colIDleistungssuche;
	@FXML
	private TableColumn<Leistung, String> colLeistungsbezeichnungSuche;
	@FXML
	private TableColumn<Leistung, String> colLeistungsPreisSuche;
	@FXML
	private TableView<LeistungGebucht> tableViewLeistungenFuerRechnung;
	@FXML
	private TableColumn<LeistungGebucht, String> colLeistung;
	@FXML
	private TableColumn<LeistungGebucht, String> colMenge;
	@FXML
	private TableColumn<LeistungGebucht, String> colEinheit;
	@FXML
	private TableColumn<LeistungGebucht, String> colNetto;
	@FXML
	private TableColumn<LeistungGebucht, String> colNettoGesamt;
	@FXML
	private TableColumn<LeistungGebucht, String> colBrutto;
	@FXML
	private TableColumn<LeistungGebucht, String> colBruttoGesamt;
	@FXML
	private TableColumn<LeistungGebucht, String> colUst;
	@FXML
	private TableColumn<LeistungGebucht, String> colSteuersatz;
	@FXML
	private TableColumn<LeistungGebucht, String> colBezahlt;
	@FXML
	private TableColumn<LeistungGebucht, String> colIdLeistung;
	@FXML
	private TableColumn<LeistungGebucht, String> colIdKunde;
	@FXML
	private Label labelSummeNetto;
	@FXML
	private Label labelSummeBrutto;
	@FXML
	private Label labelKunde;
	@FXML
	private TextField textFieldLeistungSuchen;
	@FXML
	private Button buttonLeistungHinzufuegen;
	@FXML
	private ListView<String> listViewLeistungen;

	// TAB PDF ERSTELLEN
	@FXML
	private Button buttonPdfErstellen;

	public void initialize() {
		try {
			// Rechnungsdatum/Erstellungsdatum:
			datePickerErstellungsdatum.setValue(LocalDate.now());
			datePickerRechnungsdatum.setValue(LocalDate.now());

			// Spinner Grenzen festlegen fï¿½r das Zahlungsziel.
			spinnerZahlungsziel.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 30, 14));

			// Wenn schon gezahlt, dann Spinner deaktivieren.
			checkBoxBereitsBeglichen.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					if (checkBoxBereitsBeglichen.isSelected()) {
						System.out.println("checked!");
						spinnerZahlungsziel.setDisable(true);
					} else {
						System.out.println("Not Checked!");
						spinnerZahlungsziel.setDisable(false);
					}
				}
			});

			// Initialisierung von Haupt tableView:
			colLeistung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
			colEinheit.setCellValueFactory(new PropertyValueFactory<>("preis"));
			colMenge.setCellValueFactory(new PropertyValueFactory<>("menge"));
			colNetto.setCellValueFactory(new PropertyValueFactory<>("netto"));
			colNettoGesamt.setCellValueFactory(new PropertyValueFactory<>("nettoGesamt"));
			colBruttoGesamt.setCellValueFactory(new PropertyValueFactory<>("bruttoGesamt"));
			colUst.setCellValueFactory(new PropertyValueFactory<>("ust"));
			colSteuersatz.setCellValueFactory(new PropertyValueFactory<>("steuersatz"));
			colBezahlt.setCellValueFactory(new PropertyValueFactory<>("bezahlt"));
			colIdLeistung.setCellValueFactory(new PropertyValueFactory<>("idLeistung"));
			colIdKunde.setCellValueFactory(new PropertyValueFactory<>("idKunde"));

			tableViewLeistungenFuerRechnung.setEditable(true);
			ObservableList<String> listBezahlt = FXCollections.observableArrayList("Ja", "Nein");
			colLeistung.setCellFactory(TextFieldTableCell.forTableColumn());
			colMenge.setCellFactory(TextFieldTableCell.forTableColumn());
			colEinheit.setCellFactory(TextFieldTableCell.forTableColumn());
			colNetto.setCellFactory(TextFieldTableCell.forTableColumn());
			colNettoGesamt.setCellFactory(TextFieldTableCell.forTableColumn());
			colBruttoGesamt.setCellFactory(TextFieldTableCell.forTableColumn());
			colSteuersatz.setCellFactory(TextFieldTableCell.forTableColumn());
			colBezahlt.setCellFactory(ComboBoxTableCell.forTableColumn(listBezahlt));

			handlerOnEditCommit();

			// Lade alle Leistungen
			colIDleistungssuche.setCellValueFactory(new PropertyValueFactory<>("id"));
			colLeistungsbezeichnungSuche.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
			colLeistungsPreisSuche.setCellValueFactory(new PropertyValueFactory<>("preis"));

			database.open();
			ObservableList<Leistung> observableList = FXCollections.observableArrayList();

			Statement statement = database.getConnection().createStatement();
			ResultSet resultSet = database.loadAllLeistungen(statement);

			while (resultSet.next()) {
				Leistung leistung = new Leistung();

				leistung.setBezeichnung(resultSet.getString("bezeichnung"));
				leistung.setId(String.valueOf(resultSet.getInt("id")));
				leistung.setPreis(resultSet.getString("preis").toString());
				leistung.setMenge("1");

				observableList.add(leistung);
			}
			tableViewLeistungsSuche.setItems(observableList);
			statement.close();

			// Initialisiere Columns fï¿½r TableViewFï¿½rRechnungen
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void setDataBase(Database db, Costumer costumer, Scene scene, Unternehmen unternehmen, Rechnung r) {
		this.unternehmen = unternehmen;
		this.costumer = costumer;
		database = db;
		if (costumer == null) {
			// Zeige Rechnung nur an.
			ladeLeistungenVonVorhandenerRechnung(r.getId());
			disableRechnungChange(Integer.valueOf(r.getId()));
			this.rechnungGeladen = r;

		} else {

			labelKunde.setText("Kunden ID: " + costumer.getId() + "\nKundenbezeichnung: " + costumer.getBezeichnung());
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent arg0) {
					// TODO Auto-generated method stub
					if (arg0.getCode() == KeyCode.DELETE) {
						// Löschen
						System.out.println("LÖSCHEN");
						onActionGebuchteRechnungLoeschen();
					}
				}
			});
		}
	}

	@FXML
	void onActionRechnungErstellen(ActionEvent event) {
		// Rechnung vorbereiten:
		System.out.println("Rechnung erstellen");
		rechnung.setBruttoSumme(labelSummeBrutto.getText());
		rechnung.setErstellungsdatum(datePickerErstellungsdatum.getValue().toString());
		rechnung.setIdKunde(costumer.getId());
		rechnung.setKUNDEN_HAUSNUMMER(costumer.getHausnummer());
		rechnung.setKUNDEN_LAND(costumer.getLand());
		rechnung.setKundenname(costumer.getKontaktperson());
		rechnung.setKUNDEN_PLZ(costumer.getPlz());
		rechnung.setKUNDEN_STRASSE(costumer.getStrasse());
		rechnung.setNettoSumme(labelSummeNetto.getText());
		rechnung.setRechnungBereitsBeglichen(checkBoxBereitsBeglichen.getText());
		rechnung.setRechnungsdatum(datePickerRechnungsdatum.getValue().toString());
		// rechnung.setRechnungsnummer(null);
		rechnung.setSTANDORD_GEWERBEBERECHTIGUNG_HAUSNUMMER(unternehmen.getStandort_gewerbeberechtigung_hausnummer());
		rechnung.setSTANDORD_GEWERBEBERECHTIGUNG_STRASSE(unternehmen.getStandort_gewerbeberechtigung_strasse());
		rechnung.setSTANDORD_GEWERBEBERECHTIGUNG_PLZ(unternehmen.getStandort_gewerbeberechtigung_plz());
		rechnung.setSTANDORD_GEWERBEBERECHTIGUNG_ORT(unternehmen.getStandort_gewerbeberechtigung_ort());
		rechnung.setSTANDORD_GEWERBEBERECHTIGUNG_LAND(unternehmen.getStandort_gewerbeberechtigung_land());

		rechnung.setUNTERNEHMEN(unternehmen.getUnternehmen());
		rechnung.setUNTERNEHMEN_STRASSE(unternehmen.getUnternehmen_strasse());
		rechnung.setUNTERNEHMEN_HAUSNUMMER(unternehmen.getUnternehmen_hausnummer());
		rechnung.setUNTERNHEMEN_PLZ(unternehmen.getUnternehmen_plz());
		rechnung.setUNTERNHEMEN_ORT(unternehmen.getUnternehmen_ort());
		rechnung.setUNTERNEHMEN_LAND(unternehmen.getUnternehmen_land());

		int rechnungsnummer = -5;
		Statement statement;
		try {
			statement = database.getConnection().createStatement();
			rechnungsnummer = database.createRechnung(statement, rechnung);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		disableRechnungChange(rechnungsnummer);

		// Leistung Gebucht hinzufügen:
		Statement statement2;
		try {
			statement2 = database.getConnection().createStatement();
			for (LeistungGebucht lG : leistgb) {
				lG.setIdRechnung(Utils.getRechnungsnummerFromId(rechnungsnummer));
				database.createLeistungGebucht(statement2, lG);
				System.out.println(lG);
			}
			statement2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in Methode onActionRechnungErstellen: " + e);
		}

	}

	@FXML
	void onActionLeistungHinzufuegen(ActionEvent event) { // Leistungen hinzufügen zur Rechnung.

		// Hole die selektierte LEistung und schreibe sie in Leistung
		Leistung leistung = tableViewLeistungsSuche.getSelectionModel().getSelectedItem();

		if (leistung != null) { // Wenn Leistung ausgewählt
			LeistungGebucht leistungGebucht = new LeistungGebucht(); // Erstelle neue gebuchte Leistung und lade in
																		// gebuchte Leistung hinein.
			leistungGebucht.setBezeichnung(leistung.getBezeichnung());
			leistungGebucht.setIdKunde(costumer.getId());
			leistungGebucht.setIdLeistung(leistung.getId());
			leistungGebucht.setMenge(leistung.getMenge());
			leistungGebucht.setPreis(leistung.getPreis().replace(",", "."));
			leistungGebucht.setBezahlt("nein");
			leistungGebucht.setNetto(leistung.getPreis().replace(",", "."));
			leistungGebucht.setNettoGesamt(leistung.getPreis().replace(",", "."));

			double preis = Double.valueOf(leistungGebucht.getPreis());
			double steuersatz = Double.valueOf(leistungGebucht.getSteuersatz());
			leistungGebucht.setBruttoGesamt(String.valueOf(preis + preis * steuersatz / 100));
			leistungGebucht.setUst(String.valueOf(preis * steuersatz / 100));
			leistungGebucht.setSteuersatz("20");

			leistgb.add(leistungGebucht);
			listeAusgeben(leistgb);

			// INIT WERTE:
			tableViewLeistungenFuerRechnung.getItems().add(leistungGebucht);
			tableViewLeistungenFuerRechnung.refresh();

			aktualisiereSummen(); // Rechnungssumme aktualisieren und anzeigen.
			System.out.println("FERTIG hinzugefügt");
		} else {

		}
	}

	// Leistungssuche*********************************************
	@FXML
	void onKeyPressedLeistungSuchen(KeyEvent event) {
		String suchstring = textFieldLeistungSuchen.getText();
		System.out.println("Suchen nach " + suchstring);

	}

	// Lade Leistungen (NUR Für Rechnung Anzeigen)
	void ladeLeistungenVonVorhandenerRechnung(String rechnungsnummer) {
		System.out.println("LadeLeistungenvonVorhandener Rechnung drinnen mit Rechnungsnummer: " + rechnungsnummer);
		ObservableList<LeistungGebucht> listLeistungG = FXCollections.observableArrayList();

		try {
			Statement statement = database.getConnection().createStatement();
			ResultSet resultSet = database.loadAllLeistungenGebuchtFromRechnung(statement, rechnungsnummer);
//			System.out.println("ResultSet = " + resultSet.next());

			while (resultSet.next()) {
				System.out.println("in While Schleife Drinn");
				LeistungGebucht leistungG = new LeistungGebucht();

				leistungG.setBezeichnung(resultSet.getString("bezeichnung"));
				leistungG.setMenge(resultSet.getString("menge"));
				leistungG.setPreis(resultSet.getString("preis"));
				leistungG.setNetto(resultSet.getString("netto"));
				leistungG.setNettoGesamt(resultSet.getString("netto_gesamt"));
				leistungG.setBruttoGesamt(resultSet.getString("brutto_gesamt"));
				System.out.println("BEZ BEZ BEZ" + leistungG.getBezeichnung());

				listLeistungG.add(leistungG);
				
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Fehler in Methode: ladeLeistungenVonRechnung: " + e);
		}
		tableViewLeistungenFuerRechnung.setItems(listLeistungG);
		listLeistunggebuchtKopie = listLeistungG;
	}

	@FXML
	void onActionGebuchteRechnungLoeschen() {
		LeistungGebucht lG = tableViewLeistungenFuerRechnung.getSelectionModel().getSelectedItem();
		if (lG != null) {
			int leistungGebuchtSelectedIndex = tableViewLeistungenFuerRechnung.getSelectionModel().getSelectedIndex();
			System.out.println("Zu löschende Leistung: " + leistungGebuchtSelectedIndex);
			tableViewLeistungenFuerRechnung.getItems().remove(leistungGebuchtSelectedIndex);
			tableViewLeistungenFuerRechnung.refresh();
			aktualisiereSummen();
		}
	}

	void handlerOnEditCommit() { // Handler für Bearbeiten einer Zelle in der Tabelle.
		colLeistung.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<LeistungGebucht, String>>() {
			@Override
			public void handle(CellEditEvent<LeistungGebucht, String> event) {
				System.out.println("leistung Bezeichnung changed");
				String newValue = event.getNewValue();
				tableViewLeistungenFuerRechnung.getSelectionModel().getSelectedItem().setBezeichnung(newValue);
				listeAusgeben(tableViewLeistungenFuerRechnung.getItems());
			}
		});

		colMenge.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<LeistungGebucht, String>>() {

			@Override
			public void handle(CellEditEvent<LeistungGebucht, String> event) {
				// TODO Auto-generated method stub
				System.out.println("leistung Bezeichnung changed");
				String newValue = event.getNewValue();
				LeistungGebucht lG = tableViewLeistungenFuerRechnung.getSelectionModel().getSelectedItem();
				lG.setMenge(newValue);
				aktualisiereTabellenZeile(lG);
				aktualisiereSummen();
			}
		});

		colEinheit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<LeistungGebucht, String>>() {
			@Override
			public void handle(CellEditEvent<LeistungGebucht, String> event) {
				System.out.println("leistung Bezeichnung changed");
				String newValue = event.getNewValue();
				LeistungGebucht lG = tableViewLeistungenFuerRechnung.getSelectionModel().getSelectedItem();
				lG.setPreis(newValue);
				aktualisiereTabellenZeile(lG);
				aktualisiereSummen();
			}
		});
	}

	void listeAusgeben(ObservableList<LeistungGebucht> list) { // Liste mit Gebrauchten Leistungen anzeigen.
		for (LeistungGebucht l : list) {
			showLeistung(l);
		}
	}

	void showLeistung(LeistungGebucht l) { // Eine Leistung anzeigen.
		System.out.println(l.getBezeichnung() + " " + l.getMenge() + " " + l.getPreis() + " " + l.getNetto() + " "
				+ l.getNettoGesamt() + " " + l.getBruttoGesamt() + " " + l.getUst() + " " + l.getSteuersatz() + " "
				+ l.getBezahlt() + " " + l.getIdLeistung() + " " + l.getIdKunde());
	}

	void aktualisiereSummen() {
		double summeBrutto = 0;
		for (LeistungGebucht lg : tableViewLeistungenFuerRechnung.getItems()) {
			double brutto = Double.valueOf(lg.getBruttoGesamt());
			summeBrutto += brutto;
		}
		labelSummeBrutto.setText(String.format("%1.2f", summeBrutto));

		double summeNetto = 0;
		for (LeistungGebucht lg : tableViewLeistungenFuerRechnung.getItems()) {
			double netto = Double.valueOf(lg.getNettoGesamt());
			summeNetto += netto;
		}
		labelSummeNetto.setText(String.format("%1.2f", summeNetto));
	}

	void aktualisiereTabellenZeile(LeistungGebucht lG) {
		double menge = Double.valueOf(lG.getMenge());
		double preis = Double.valueOf(lG.getPreis());
		double steuersatz = Double.valueOf(lG.getSteuersatz());
		lG.setNetto(String.valueOf(preis * menge));
		lG.setNettoGesamt(String.valueOf(preis * menge));
		lG.setBruttoGesamt(String.valueOf(preis * menge + (preis * menge) * steuersatz / 100));
		double umsatzsteuer = (preis * menge) * steuersatz / 100;
		lG.setUst(String.valueOf(umsatzsteuer));
		tableViewLeistungenFuerRechnung.refresh();
	}

	void disableRechnungChange(int rechnungsnummer) {
//		Stage s = (Stage) buttonRechnungErstellen.getScene().getWindow();
//		s.setTitle(" Rechnung: " + Utils.getRechnungsnummerFromId(rechnungsnummer) + "[Kunde: "
//				+ costumer.getBezeichnung() + "]");

		labelRechnungsnummer.setText(Utils.getRechnungsnummerFromId(rechnungsnummer));
		buttonRechnungErstellen.setDisable(true);
		checkBoxBereitsBeglichen.setDisable(true);
		datePickerRechnungsdatum.setDisable(true);
		spinnerZahlungsziel.setDisable(true);
		buttonLeistungHinzufuegen.setDisable(true);
		tableViewLeistungenFuerRechnung.setEditable(false);
	}

	// TAB PDF ERSTELLEN
	@FXML
	void onActionPdfErstellen(ActionEvent event) {
		System.out.println("Pdf erstellen");
		try {
			Document document = new Document();
			String fileName = Utils.getRechnungsnummerFromId(Integer.valueOf(rechnungGeladen.getId())) + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			
			
			String unternehmensDaten = unternehmen.getUnternehmen() + "\n" + unternehmen.getUnternehmen_strasse()
					+ unternehmen.getUnternehmen_hausnummer() + "\n" + unternehmen.getUnternehmen_plz() + " "
					+ unternehmen.getUnternehmen_ort() + "\n" + unternehmen.getUnternehmen_land() + "\n\n\n\n\n\n";
			

			String kundenDaten = rechnungGeladen.getKundenname() + "\n" + rechnungGeladen.getKUNDEN_STRASSE() + " "
					+ rechnungGeladen.getKUNDEN_HAUSNUMMER() + "\n" + rechnungGeladen.getKUNDEN_PLZ() + "\n"
					+ rechnungGeladen.getKUNDEN_LAND();
			
			String rechnungsNummerUndDatum = "Rechn. Datum.: " + rechnungGeladen.getRechnungsdatum() + "\n" + "Rechn. Nr.: " + 
					Utils.getRechnungsnummerFromId(Integer.valueOf(rechnungGeladen.getId()));
		    
		    Paragraph paragraphFirmendaten = new Paragraph(unternehmensDaten);             
		    paragraphFirmendaten.setLeading(14);; //Line Spacing
		    Paragraph paragraph2Kundendaten = new Paragraph(kundenDaten);              
		    paragraph2Kundendaten.setLeading(14);; //Line Spacing
		    Paragraph paragraph3 = new Paragraph(rechnungsNummerUndDatum);
		    paragraph3.setLeading(17);; //Line Spacing
		    
		    //Tabelle für Überschrift und Rechnungsdatum
		    PdfPTable tableUeberschriftUndRechnungsdaten = new PdfPTable(2);
		    tableUeberschriftUndRechnungsdaten.setWidthPercentage(100); //Tabellenbreitebreite 100%
		    tableUeberschriftUndRechnungsdaten.setPaddingTop(100); //Padding nach oben
		    Font f=new Font(FontFamily.TIMES_ROMAN, 30.0f ,Font.UNDERLINE,BaseColor.BLACK);
		    PdfPCell cell1 = new PdfPCell(new Phrase("Rechnung", f));
		    cell1.setBorder(Rectangle.NO_BORDER); //Kein Rand
		    cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		    PdfPCell cell2 = new PdfPCell(paragraph3);
		    cell2.setHorizontalAlignment(Element.ALIGN_RIGHT); // Text rechtsbündig in Cell
		    cell2.setBorder(Rectangle.NO_BORDER); //Kein Rand
		    
		    tableUeberschriftUndRechnungsdaten.addCell(cell1);
		    tableUeberschriftUndRechnungsdaten.addCell(cell2);
		    
		    //Tabelle Leistungen gebucht
		    PdfPTable tableLeistungen = new PdfPTable(4);
		    tableLeistungen.setWidthPercentage(100); //Tabellenbreitebreite 100%
		    PdfPCell cellLeistungenHeaderBezeichnung = new PdfPCell(new Phrase("Bezeichnung"));
		    PdfPCell cellLeistungenHeaderMenge = new PdfPCell(new Phrase("Menge"));
		    PdfPCell cellLeistungenHeaderEinzelpreis = new PdfPCell(new Phrase("Einzel"));
		    PdfPCell cellLeistungenHeaderGesamtpreis = new PdfPCell(new Phrase("Gesamt"));
		    
		    tableLeistungen.addCell(cellLeistungenHeaderBezeichnung);
		    tableLeistungen.addCell(cellLeistungenHeaderMenge);
		    tableLeistungen.addCell(cellLeistungenHeaderEinzelpreis);
		    tableLeistungen.addCell(cellLeistungenHeaderGesamtpreis);
		    System.out.println("Kurz davot!");
		    double gesamtsumme = 0;
		    leereTabellenZeile(tableLeistungen, document);
		    for(LeistungGebucht lG : listLeistunggebuchtKopie) {
		    	System.out.println("Menge = " + lG.getMenge());
		    	double gesamtpreis = Double.valueOf(lG.getMenge()) * Double.valueOf(lG.getPreis());
		    	gesamtsumme += gesamtpreis;
		    	PdfPCell cellLeistungenBezeichnung = new PdfPCell(new Phrase(lG.getBezeichnung()));
		    	PdfPCell cellLeistungenMenge = new PdfPCell(new Phrase(lG.getMenge()));
		    	PdfPCell cellLeistungenEinzelpreis = new PdfPCell(new Phrase(lG.getPreis()));
		    	PdfPCell cellLeistungenGesamtpreis = new PdfPCell(new Phrase(String.valueOf(gesamtpreis)));
			    
		    	tableLeistungen.addCell(cellLeistungenBezeichnung);
		    	tableLeistungen.addCell(cellLeistungenMenge);
		    	tableLeistungen.addCell(cellLeistungenEinzelpreis);
		    	tableLeistungen.addCell(cellLeistungenGesamtpreis);
		    }
		    leereTabellenZeile(tableLeistungen, document);
	    	PdfPCell cellLeistungenBezeichnung = new PdfPCell(new Phrase(" "));
	    	PdfPCell cellLeistungenMenge = new PdfPCell(new Phrase(" "));
	    	PdfPCell cellLeistungenEinzelpreis = new PdfPCell(new Phrase("Summe: "));
	    	PdfPCell cellLeistungenGesamtpreis = new PdfPCell(new Phrase(String.valueOf(gesamtsumme)));
		    
	    	tableLeistungen.addCell(cellLeistungenBezeichnung);
	    	tableLeistungen.addCell(cellLeistungenMenge);
	    	tableLeistungen.addCell(cellLeistungenEinzelpreis);
	    	tableLeistungen.addCell(cellLeistungenGesamtpreis);
		    
	    	Paragraph paragraphZahlungsauftrag = new Paragraph("Wir bitten Sie die Rechnung innherhalb von " + rechnungGeladen.getZahlungsziel() + "zu begleichen!");             
		    paragraphFirmendaten.setLeading(14);; //Line Spacing
		    
		    //Dokumentenstruktur:
			document.add(paragraphFirmendaten);
			document.add(paragraph2Kundendaten);
			leereZeile(document);
			document.add(tableUeberschriftUndRechnungsdaten); 
			leereZeile(document);
			leereZeile(document);
			document.add(new Paragraph("Vielen Dank für Ihr Vertrauen! Wir erlauben uns folgende Rechnungsstellung: "));
			leereZeile(document);
			leereZeile(document);
			document.add(tableLeistungen); 
			leereZeile(document);
			document.add(paragraphZahlungsauftrag);
			leereZeile(document);
			document.add(new Paragraph("Mit freundlichen Grüßen"));
			document.add(new Paragraph(unternehmen.getUnternehmen()));

			document.close();

			File pdfFile = new File(fileName);
			Desktop desktop = Desktop.getDesktop();
			desktop.open(pdfFile);
		} catch (Exception e) {
			System.out.println("Fehler in Methode onActionPdfErstellen: " + e);
		}
	}
	
	public void leereZeile(Document doc) {
		try {
			doc.add(new Paragraph(" "));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Leere Zeile

	}
	
	public void leereTabellenZeile(PdfPTable tableLeistungen, Document doc) {
		PdfPCell cellLeistungenBezeichnung = new PdfPCell(new Phrase(" "));
    	PdfPCell cellLeistungenMenge = new PdfPCell(new Phrase(" "));
    	PdfPCell cellLeistungenEinzelpreis = new PdfPCell(new Phrase(" "));
    	PdfPCell cellLeistungenGesamtpreis = new PdfPCell(new Phrase(" "));
	    
    	tableLeistungen.addCell(cellLeistungenBezeichnung);
    	tableLeistungen.addCell(cellLeistungenMenge);
    	tableLeistungen.addCell(cellLeistungenEinzelpreis);
    	tableLeistungen.addCell(cellLeistungenGesamtpreis);
	}
	
}