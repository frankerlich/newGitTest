package at.coder.buchhaltung.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.coder.buchhaltung.databaseUtil.Database;
import at.coder.buchhaltung.kunde.Costumer;
import at.coder.buchhaltung.kunde.CostumerController;
import at.coder.buchhaltung.kunde.CostumerDeleteController;
import at.coder.buchhaltung.leistung.Leistung;
import at.coder.buchhaltung.leistung.LeistungHinzufuegenController;
import at.coder.buchhaltung.rechnung.NeueRechnungController;
import at.coder.buchhaltung.rechnung.Rechnung;
import at.coder.buchhaltung.unternehmen.Unternehmen;
import at.coder.buchhaltung.unternehmen.UnternehmenController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainController {

	// Attribute
	private Unternehmen unternehmen = null;
	private Database database = new Database();
	private ObservableList<Costumer> costumerList;
	private ObservableList<Leistung> leistungenList;
	private ObservableList<Rechnung> rechnungenList;
	private boolean showRechnungFromCostumerActive = false;
	private boolean showKundeActive = false;

	@FXML
	private MenuItem neuerKundeMenueItem;
	@FXML
	private Tab tabKunde;
	@FXML
	private Button buttonAenderungenUebernehmen;
	@FXML
	private Button buttonAbbrechenAendern;
	@FXML
	private Circle circleDatenbankStatus;
	@FXML
	private Label labelDatenbankStatus;
	@FXML
	private TableView<Costumer> tableViewAnzeige;
	@FXML
	private TableColumn<Costumer, String> colID;
	@FXML
	private TableColumn<Costumer, String> colBezeichnung;
	@FXML
	private TableColumn<Costumer, String> colAnrede;
	@FXML
	private TableColumn<Costumer, String> colKontaktperson;
	@FXML
	private TableColumn<Costumer, String> colStrasse;
	@FXML
	private TableColumn<Costumer, String> colHausnummer;
	@FXML
	private TableColumn<Costumer, String> colPLZ;
	@FXML
	private TableColumn<Costumer, String> colOrt;
	@FXML
	private TableColumn<Costumer, String> colLand;
	@FXML
	private TableColumn<Costumer, String> colAnzahlRechnungen;
	@FXML
	private TableColumn<Costumer, String> colUid;
	@FXML
	private TableColumn<Costumer, String> colEmail;
	@FXML
	private TableColumn<Costumer, String> colTelefon;
	@FXML
	private TableColumn<Costumer, String> colBeschreibung;
	@FXML
	private TabPane tabPaneTabBar;
	@FXML
	private TextField textFieldBezeichnungMain;
	@FXML
	private TextField textFieldKontaktpersonMain;
	@FXML
	private MenuItem menuItemProgrammBeenden;
	@FXML
	private TextField textFieldStrasseMain;
	@FXML
	private TextField textFieldHausnummerMain;
	@FXML
	private TextField textFieldPlzMain;
	@FXML
	private TextField textFieldOrtMain;
	@FXML
	private TextField textFieldUidMain;

	// *************************************************
	// TAB LEISTUNG*************************************
	// *************************************************
	@FXML
	private TableView<Leistung> tableViewLeistungen;
	@FXML
	private TableColumn<Leistung, String> colLeistungID;
	@FXML
	private TableColumn<Leistung, String> colLeistungBezeichnung;
	@FXML
	private TableColumn<Leistung, String> colLeistungMenge;
	@FXML
	private TableColumn<Leistung, String> colLeistungPreis;

	// *************************************************
	// TAB RECHNUNGEN***********************************
	// *************************************************
	@FXML
	private TableView<Rechnung> tableViewRechnungen;
	@FXML
	private TableColumn<Rechnung, String> colRechnungRechnNr;
	@FXML
	private TableColumn<Rechnung, String> colRechnungKundenname;
	@FXML
	private TableColumn<Rechnung, String> colRechnungNettoGesamt;
	@FXML
	private TableColumn<Rechnung, String> colRechnungBruttoGesamt;
	@FXML
	private TableColumn<Rechnung, String> colRechnungRechnungsdatum;
	@FXML
	private TableColumn<Rechnung, String> colRechnungErstellungsdatum;
	@FXML
	private TableColumn<Rechnung, String> colRechnungBeglichen;
	@FXML
	private TextField textFieldRechnungSuchen;
	@FXML
	private Button buttonRechnungStornieren;
	@FXML
	private Button buttonRechnungAlleAnzeigen;

	public void initialize() {
		// System.out.println("INIT");
		ladeUnternehmen();
		unternehmen.unternehmenAusgeben();

		// CHANGELISTENER TABS:
		tabPaneTabBar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> arg0, Tab oldTab, Tab newTab) {
				// TODO Auto-generated method stub
				System.out.println("Tab geändert! --> " + newTab.getText());
				if (newTab.getText().equals("Kunde")) {
					// Kunden laden
					if(showKundeActive == false) {
						//showAllCostumers(database, tableViewAnzeige);
					}
				} else if (newTab.getText().equals("Leistung")) {
					// Leistungen laden
					showAllLeistungen(database, tableViewLeistungen);
				} else if (newTab.getText().equals("Rechnungen")) {
					// Rechnungen laden
					System.out.println("DRINN");
					if (showRechnungFromCostumerActive == false) {
						showAllRechnungen(database, tableViewRechnungen);
					}
				}
			}
		});

		colBezeichnung.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {
			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				System.out.println("Bezeichnung editieren");
			}
		});

		boolean opened = database.open();
		if (opened) {
			labelDatenbankStatus.setText("OK");
			circleDatenbankStatus.setFill(Color.GREEN);
			System.out.println("Datenbank verbunden!");
			;
		} else {
			labelDatenbankStatus.setText("Getrennt!");
			circleDatenbankStatus.setFill(Color.RED);
			System.out.println("Datenbank getrennt!");
			;
		}

		// Initialisierung für das TableView KUNDEN
		colBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
		colAnrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));
		colKontaktperson.setCellValueFactory(new PropertyValueFactory<>("kontaktperson"));
		colStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
		colHausnummer.setCellValueFactory(new PropertyValueFactory<>("hausnummer"));
		colPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));
		colOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));
		colLand.setCellValueFactory(new PropertyValueFactory<>("land"));
		colAnzahlRechnungen.setCellValueFactory(new PropertyValueFactory<>("anzahlRechnungen"));
		colUid.setCellValueFactory(new PropertyValueFactory<>("uid"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
		colBeschreibung.setCellValueFactory(new PropertyValueFactory<>("beschreibung"));
		colID.setCellValueFactory(new PropertyValueFactory<>("id"));

		// Table View Einstellungen --> Bearbeiten möglich machen
		ObservableList<String> listAnrede = FXCollections.observableArrayList("Herr", "Frau");
		ObservableList<String> listLand = FXCollections.observableArrayList("Österreich", "Deutschland", "Schweiz");
		tableViewAnzeige.setEditable(true); // Zellen sollen bearbeitbar sein.
		colBezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
		colAnrede.setCellFactory(ComboBoxTableCell.forTableColumn(listAnrede)); // TextField für Änderung;
		colKontaktperson.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colStrasse.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colHausnummer.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colPLZ.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colOrt.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colLand.setCellFactory(ComboBoxTableCell.forTableColumn(listLand)); // TextField für Änderung;
		// colAnzahlRechnungen.setCellFactory(TextFieldTableCell.forTableColumn());
		// //TextField für Änderung;
		colUid.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colEmail.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colTelefon.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;
		colBeschreibung.setCellFactory(TextFieldTableCell.forTableColumn()); // TextField für Änderung;

		// Initialisierung für das TableView Leistung
		tableViewLeistungen.setEditable(true); // Zellen sollen bearbeitbar sein.
		colLeistungID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colLeistungBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
		colLeistungMenge.setCellValueFactory(new PropertyValueFactory<>("menge"));
		colLeistungPreis.setCellValueFactory(new PropertyValueFactory<>("preis"));
		// Bearbeiten möglich machen:
		colLeistungID.setCellFactory(TextFieldTableCell.forTableColumn());
		colLeistungBezeichnung.setCellFactory(TextFieldTableCell.forTableColumn());
		colLeistungMenge.setCellFactory(TextFieldTableCell.forTableColumn());
		colLeistungPreis.setCellFactory(TextFieldTableCell.forTableColumn());

		// Initialisierung für das TableView Rechnung
		colRechnungRechnNr.setCellValueFactory(new PropertyValueFactory<>("id"));
		colRechnungKundenname.setCellValueFactory(new PropertyValueFactory<>("kundenname"));
		colRechnungRechnungsdatum.setCellValueFactory(new PropertyValueFactory<>("rechnungsdatum"));
		colRechnungErstellungsdatum.setCellValueFactory(new PropertyValueFactory<>("erstellungsdatum"));
		colRechnungBeglichen.setCellValueFactory(new PropertyValueFactory<>("rechnungBereitsBeglichen"));
		colRechnungBruttoGesamt.setCellValueFactory(new PropertyValueFactory<>("bruttoSumme"));
		colRechnungNettoGesamt.setCellValueFactory(new PropertyValueFactory<>("nettoSumme"));

		tableViewRechnungen.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getClickCount() == 2) {
					System.out.println("Doppelclick on Element");
					Rechnung r = tableViewRechnungen.getSelectionModel().getSelectedItem();
					String rechnungsId = r.getId();
					r = getRechnungFromRechnungId(rechnungsId);
					System.out.println("Id geholt!");;
					Stage stage = new Stage();
					try {
						FXMLLoader fxmlLaoder = new FXMLLoader();
						Parent root = fxmlLaoder
								.load(getClass().getResource("/at/coder/buchhaltung/rechnung/NeueRechnung.fxml").openStream());
						Scene scene = new Scene(root);

						System.out.println("setDataBase setten");;
						NeueRechnungController neueRechnungController = fxmlLaoder.getController();
						neueRechnungController.setDataBase(database, null, scene, unternehmen, r);
						System.out.println("database gesettzt");;
						stage.getIcons().add(new Image(getClass().getResourceAsStream("/at/coder/buchhaltung/images/user.png")));
						stage.setTitle(" Rechnung hinzufügen");
						stage.setScene(scene);
					} catch (IOException e) {
						System.out.println("Fehler in Handler tableViewRechnungen.setOnMouseClicked: " + e.getMessage());
					}
					stage.show();
				}
			}
		});
		
		zellenBearbeitenHandlerKunden();
		zellenBearbeitenHandlerLeistungen();

		showAllCostumers(database, tableViewAnzeige);

		colBezeichnung.getStyleClass().add("column_style");

		// Wenn Textfeld fokusiert ist --> ändere Farben der Spalten.
		
		textFieldBezeichnungMain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String text = textFieldBezeichnungMain.getText();
				loeschenOnClickMain(null);
				textFieldBezeichnungMain.setText(text);
				if(text.trim().equals("")) {
					showAllCostumers(database, tableViewAnzeige);
				}
				colBezeichnung.getStyleClass().add("table-cell-searched");
			}
		});
		
		
		textFieldKontaktpersonMain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String text = textFieldKontaktpersonMain.getText();
				loeschenOnClickMain(null);
				textFieldKontaktpersonMain.setText(text);
				if(text.trim().equals("")) {
					showAllCostumers(database, tableViewAnzeige);
				}
				colKontaktperson.getStyleClass().add("table-cell-searched");
			}
		});
		
		textFieldStrasseMain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String text = textFieldStrasseMain.getText();
				loeschenOnClickMain(null);
				textFieldStrasseMain.setText(text);
				if(text.trim().equals("")) {
					showAllCostumers(database, tableViewAnzeige);
				}
				colStrasse.getStyleClass().add("table-cell-searched");
			}
		});


		textFieldPlzMain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String text = textFieldPlzMain.getText();
				loeschenOnClickMain(null);
				textFieldPlzMain.setText(text);
				if(text.trim().equals("")) {
					showAllCostumers(database, tableViewAnzeige);
				}
				colPLZ.getStyleClass().add("table-cell-searched");
			}
		});
		
		textFieldOrtMain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String text = textFieldOrtMain.getText();
				loeschenOnClickMain(null);
				textFieldOrtMain.setText(text);
				if(text.trim().equals("")) {
					showAllCostumers(database, tableViewAnzeige);
				}
				colOrt.getStyleClass().add("table-cell-searched");
			}
		});
		
		textFieldUidMain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String text = textFieldUidMain.getText();
				loeschenOnClickMain(null);
				textFieldUidMain.setText(text);
				if(text.trim().equals("")) {
					showAllCostumers(database, tableViewAnzeige);
				}
				colUid.getStyleClass().add("table-cell-searched");
			}
		});

	}

	// *****************************************************************
	// ************************************
	// TAB Kunde***************************
	// ************************************
	// *****************************************************************

	@FXML
	void neuerKundeMenueItemClick(ActionEvent event) {
		System.out.println("Neuer Kunde wird erstellt!");
		Stage stage = new Stage();
		try {
			FXMLLoader fxmlLaoder = new FXMLLoader();
			Parent root = fxmlLaoder
					.load(getClass().getResource("/at/coder/buchhaltung/kunde/NewCostumer.fxml").openStream());
			Scene scene = new Scene(root);

			CostumerController coController = fxmlLaoder.getController();

			coController.setDataBase(database, tableViewAnzeige, colBezeichnung, colAnrede, colKontaktperson,
					colStrasse, colHausnummer, colPLZ, colOrt, colLand, colUid, colEmail, colTelefon, colBeschreibung);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/at/coder/buchhaltung/images/user.png")));
			stage.setTitle(" Neuen Kunden erstellen");
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}

	public TableView<Costumer> getTableViewAnzeige() {
		return tableViewAnzeige;
	}

	public void showAllCostumers(Database database, TableView<Costumer> tableView) {
		try {
			Statement statement = database.getConnection().createStatement(); // Statement starten
			ResultSet resultSet = database.loadAllCostumers(statement);

			costumerList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				Costumer costumer = new Costumer(resultSet.getString("bezeichnung"), resultSet.getString("anrede"),
						resultSet.getString("kontaktperson"), resultSet.getString("strasse"),
						resultSet.getString("hausnummer"), resultSet.getString("plz"), resultSet.getString("ort"),
						resultSet.getString("land"), String.valueOf(resultSet.getInt("rechnungen")),
						resultSet.getString("uid"), resultSet.getString("email"), resultSet.getString("telefon"),
						resultSet.getString("beschreibung"), resultSet.getString("id"));

				costumerList.add(costumer);
			}
			// Table View Daten laden
			tableView.setItems(costumerList);

			statement.close(); // Statement schließen.

		} catch (SQLException e) {
			System.out.println("Fehler in Methode showAllCostumers():" + e.getMessage());
		}
	}

	@FXML
	void loeschenOnClickMain(ActionEvent event) {
		if(event != null) {
			showAllCostumers(database, tableViewAnzeige);
		}
		textFieldBezeichnungMain.setText("");
		textFieldKontaktpersonMain.setText("");
		textFieldPlzMain.setText("");
		textFieldOrtMain.setText("");
		textFieldUidMain.setText("");
		textFieldStrasseMain.setText("");
		System.out.println("Neue Suche gestartet!");
		// Zeige alle Kunden wieder an:
		showRechnungFromCostumerActive = false;
		colKontaktperson.getStyleClass().remove("table-cell-searched");
		colBezeichnung.getStyleClass().remove("table-cell-searched");
		colStrasse.getStyleClass().remove("table-cell-searched");
		colPLZ.getStyleClass().remove("table-cell-searched");
		colOrt.getStyleClass().remove("table-cell-searched");
		colUid.getStyleClass().remove("table-cell-searched");
		
	}

	@FXML
	void aendernOnClick(ActionEvent event) {

	}

	@FXML
	void programmBeendenOnClick(ActionEvent event) {
		Platform.exit();
	}

	// MENU RECHTSKLICK

	@FXML
	void onActionNeueRechnung(ActionEvent event) {
		Costumer costumer = tableViewAnzeige.getSelectionModel().getSelectedItem();
		System.out.println("Rechnung hinzufügen zu Kunde " + costumer.getId());
		Stage stage = new Stage();
		try {
			FXMLLoader fxmlLaoder = new FXMLLoader();
			Parent root = fxmlLaoder
					.load(getClass().getResource("/at/coder/buchhaltung/rechnung/NeueRechnung.fxml").openStream());
			Scene scene = new Scene(root);

			NeueRechnungController neueRechnungController = fxmlLaoder.getController();
			neueRechnungController.setDataBase(database, costumer, scene, unternehmen, null);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/at/coder/buchhaltung/images/user.png")));
			stage.setTitle(" Rechnung hinzufügen");
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println("##Fehler in Methode onActionNeueRechnung: " + e.getMessage());
		}
		stage.show();
	}

	@FXML
	void onActionKundeLoeschen(ActionEvent event) {
		Costumer costumer = tableViewAnzeige.getSelectionModel().getSelectedItem();
		System.out.println("Kunde löschen mit ID: " + costumer.getId());

		Stage stage = new Stage();
		try {
			FXMLLoader fxmlLaoder = new FXMLLoader();
			Parent root = fxmlLaoder
					.load(getClass().getResource("/at/coder/buchhaltung/kunde/CostumerDelete.fxml").openStream());
			Scene scene = new Scene(root);

			CostumerDeleteController neuerCostumerDeleteController = fxmlLaoder.getController();
			neuerCostumerDeleteController.setDataBase(database, costumer, this, tableViewAnzeige);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/at/coder/buchhaltung/images/user.png")));
			stage.setTitle(" Kunde Löschen");
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println("Fehler in Methode onActionKundeLöschen: " + e.getMessage());
		}
		stage.show();
	}

	@FXML
	void onActionRechnungenAnzeigen(ActionEvent event) {
		Costumer costumer = tableViewAnzeige.getSelectionModel().getSelectedItem();
		System.out.println("Rechnungen anzeigen von Kunde mit ID = : " + costumer.getId());
		SingleSelectionModel<Tab> selectionModel = tabPaneTabBar.getSelectionModel();
		selectionModel.select(2); // Switchen zum Tab Rechnungen mit diesem Kunden.
		showRechnungenWhere(database, tableViewRechnungen, costumer.getId());
		showRechnungFromCostumerActive = true; // Damit bei TabChange nicht alle Rechnungen von allen Kunden angezeigt
												// werden --> Kennzeichnung.
	}

	/////////////////////////////////////////////
	// Hier sind die ganzen Suchmöglichkeiten:
	/////////////////////////////////////////////
	@FXML
	void sucheOnKeyPressed(KeyEvent event) {
		System.out.println("Suche!");
		showRechnungFromCostumerActive = true;

		try {
			Statement statement = database.getConnection().createStatement(); // Statement starten
			// Prüfe nach was gesucht wird:
			TextField textFieldChanged = (TextField) event.getSource();
			ResultSet resultSet;
			if (textFieldChanged.getId() == textFieldBezeichnungMain.getId()) {
				System.out.println("suche nach Bezeichnung.");
				resultSet = database.loadAllCostumersColumn(statement, textFieldBezeichnungMain.getText(),
						"bezeichnung");

			} else if (textFieldChanged.getId() == textFieldKontaktpersonMain.getId()) {
				System.out.println("suche nach Kontaktperson");
				resultSet = database.loadAllCostumersColumn(statement, textFieldKontaktpersonMain.getText(),
						"kontaktperson");

			} else if (textFieldChanged.getId() == textFieldStrasseMain.getId()) {
				System.out.println("suche nach Straße");
				resultSet = database.loadAllCostumersColumn(statement, textFieldStrasseMain.getText(), "strasse");

			} else if (textFieldChanged.getId() == textFieldPlzMain.getId()) {
				System.out.println("suche nach Plz");
				resultSet = database.loadAllCostumersColumn(statement, textFieldPlzMain.getText(), "plz");

			} else if (textFieldChanged.getId() == textFieldOrtMain.getId()) {
				System.out.println("suche nach Ort");
				resultSet = database.loadAllCostumersColumn(statement, textFieldOrtMain.getText(), "ort");

			} else if (textFieldChanged.getId() == textFieldUidMain.getId()) {
				System.out.println("suche nach UID");
				resultSet = database.loadAllCostumersColumn(statement, textFieldUidMain.getText(), "uid");

			} else {
				resultSet = null;
			}

			ObservableList<Costumer> costumerSearchedList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				Costumer costumer = new Costumer(resultSet.getString("bezeichnung"), resultSet.getString("anrede"),
						resultSet.getString("kontaktperson"), resultSet.getString("strasse"),
						resultSet.getString("hausnummer"), resultSet.getString("plz"), resultSet.getString("ort"),
						resultSet.getString("land"), String.valueOf(resultSet.getInt("rechnungen")),
						resultSet.getString("uid"), resultSet.getString("email"), resultSet.getString("telefon"),
						resultSet.getString("beschreibung"), resultSet.getString("id"));

				costumerSearchedList.add(costumer);
			}
			// Table View Daten laden
			tableViewAnzeige.setItems(costumerSearchedList);

			statement.close(); // Statement schließen.

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/////////////////////////////////////////////
	// Handler für die Bearbeitung der Kundendaten.
	/////////////////////////////////////////////
	void zellenBearbeitenHandlerKunden() {
		colBezeichnung.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "bezeichnung", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colAnrede.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "anrede", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colKontaktperson.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "kontaktperson", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colStrasse.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "strasse", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colHausnummer.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "hausnummer", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colPLZ.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "plz", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colOrt.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "ort", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colLand.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "land", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colUid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "uid", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "email", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colTelefon.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "telefon", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});

		colBeschreibung.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Costumer, String>>() {

			@Override
			public void handle(CellEditEvent<Costumer, String> event) {
				// TODO Auto-generated method stub
				String newValue = event.getNewValue();
				Costumer c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateCostumer(statement, "beschreibung", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} // Statement starten

			}
		});
	}

	void zellenBearbeitenHandlerLeistungen() {
		colLeistungBezeichnung.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leistung, String>>() {

			@Override
			public void handle(CellEditEvent<Leistung, String> event) {
				String newValue = event.getNewValue();
				Leistung c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateLeistung(statement, "bezeichnung", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			}
		});

		colLeistungMenge.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leistung, String>>() {

			@Override
			public void handle(CellEditEvent<Leistung, String> event) {
				String newValue = event.getNewValue();
				Leistung c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateLeistung(statement, "menge", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			}
		});

		colLeistungPreis.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Leistung, String>>() {

			@Override
			public void handle(CellEditEvent<Leistung, String> event) {
				String newValue = event.getNewValue();
				Leistung c = event.getRowValue();

				Statement statement;
				try {
					statement = database.getConnection().createStatement();
					database.updateLeistung(statement, "preis", newValue, Integer.valueOf(c.getId()));
					statement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			}
		});
	}

	@FXML
	void onActionUnternehmensDatenAendern(ActionEvent event) {
		Stage stage = new Stage();
		try {
			FXMLLoader fxmlLaoder = new FXMLLoader();
			Parent root = fxmlLaoder
					.load(getClass().getResource("/at/coder/buchhaltung/unternehmen/Unternehmen.fxml").openStream());
			Scene scene = new Scene(root);

			UnternehmenController unternehmenController = fxmlLaoder.getController();
			unternehmenController.setDataBase(database, unternehmen, scene, this);
			System.out.println("TableView = ..." + tableViewAnzeige == null);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/at/coder/buchhaltung/images/user.png")));
			stage.setTitle(" Unternehmensdaten ändern");
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		stage.showAndWait();
	}

	public void ladeUnternehmen() {
		System.out.println("lade gerade Unternehmen...");
		// Unternehmen laden
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("Unternehmen.txt"));
			Object o = in.readObject();
			System.out.println("Laden Unternehmensdaten erfolgreich!");
			unternehmen = (Unternehmen) o;
			unternehmen.unternehmenAusgeben();
			in.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Unternehmen existiert noch nicht! Es muss ein neues Unternehmen erstellt werden.");
			onActionUnternehmensDatenAendern(null);
		}
	}

	// *****************************************************************
	// ************************************
	// TAB LEISTUNG LOGIK******************
	// ************************************
	// *****************************************************************
	public void showAllLeistungen(Database database, TableView<Leistung> tableView) {
		try {
			Statement statement = database.getConnection().createStatement(); // Statement starten
			ResultSet resultSet = database.loadAllLeistungen(statement);

			leistungenList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				Leistung leistung = new Leistung(resultSet.getString("bezeichnung"), resultSet.getString("id"),
						resultSet.getString("menge"), resultSet.getString("preis"));

				leistungenList.add(leistung);
			}
			// Table View Daten laden
			tableView.setItems(leistungenList);

			statement.close(); // Statement schließen.

		} catch (SQLException e) {
			System.out.println("Fehler in Methode showAllLeistungen():" + e.getMessage());
		}
	}

	@FXML
	void onActionNeueLeistung(ActionEvent event) {
		Stage stage = new Stage();
		try {
			FXMLLoader fxmlLaoder = new FXMLLoader();
			Parent root = fxmlLaoder.load(
					getClass().getResource("/at/coder/buchhaltung/leistung/LeistungHinzufuegen.fxml").openStream());
			Scene scene = new Scene(root);

			LeistungHinzufuegenController leistungHinzufuegenController = fxmlLaoder.getController();
			leistungHinzufuegenController.setDataBase(database, scene, this, tableViewLeistungen);
			stage.getIcons()
					.add(new Image(getClass().getResourceAsStream("/at/coder/buchhaltung/images/leistung.png")));
			stage.setTitle(" Leistung hinzufügen");
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		stage.showAndWait();
	}

	@FXML
	void sucheLeistungOnKeyPressed(KeyEvent event) {

	}

	// *****************************************************************
	// ************************************
	// TAB RECHNUNG LOGIK******************
	// ************************************
	// *****************************************************************

	//////////////////////////////////////////
	// Rechnung stornieren
	//////////////////////////////////////////
	@FXML
	void onActionRechnungStornieren(ActionEvent event) {

	}

	@FXML
	void onActionRechnungAlleAnzeigen(ActionEvent event) {
		showRechnungFromCostumerActive = false;
		showAllRechnungen(database, tableViewRechnungen);
	}

	public void showAllRechnungen(Database database, TableView<Rechnung> tableView) {
		try {
			Statement statement = database.getConnection().createStatement(); // Statement starten
			ResultSet resultSet = database.loadAllRechnungen(statement);

			rechnungenList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				Rechnung rechnung = new Rechnung("", "", "", "", "", "", "", "", "", "", "",
						resultSet.getString("kunden_name"), "", "", "", "", resultSet.getString("id_kunde"),
						resultSet.getString("id"), resultSet.getString("rechnungsdatum"),
						resultSet.getString("erstellungsdatum"), resultSet.getString("rechnung_beglichen"), "",
						resultSet.getString("brutto_gesamt"), resultSet.getString("netto_gesamt"));

				rechnungenList.add(rechnung);
			}
			// Table View Daten laden
			tableView.setItems(rechnungenList);

			statement.close(); // Statement schließen.

		} catch (SQLException e) {
			System.out.println("Fehler in Methode showAllRechnungen():" + e.getMessage());
		}
	}

	public void showRechnungenWhere(Database database, TableView<Rechnung> tableView, String id) {
		try {
			Statement statement = database.getConnection().createStatement(); // Statement starten
			ResultSet resultSet = database.loadRechnungenFromCostumer(statement, id);

			rechnungenList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				Rechnung rechnung = new Rechnung("", "", "", "", "", "", "", "", "", "", "",
						resultSet.getString("kunden_name"), "", "", "", "", resultSet.getString("id_kunde"),
						resultSet.getString("id"), resultSet.getString("rechnungsdatum"),
						resultSet.getString("erstellungsdatum"), resultSet.getString("rechnung_beglichen"), "",
						resultSet.getString("brutto_gesamt"), resultSet.getString("netto_gesamt"));

				System.out.println("RechnungsID = " + rechnung.getId());
				rechnungenList.add(rechnung);
			}
			// Table View Daten laden
			tableView.setItems(rechnungenList);

			statement.close(); // Statement schließen.

		} catch (SQLException e) {
			System.out.println("Fehler in Methode showRechnungenWhere():" + e.getMessage());
		}

	}
	
	public Rechnung getRechnungFromRechnungId(String id) {
		try {
			Statement statement = database.getConnection().createStatement(); // Statement starten
			ResultSet resultSet = database.loadRechnung(statement, id);
			Rechnung rechnung = null;
			rechnungenList = FXCollections.observableArrayList();

			while (resultSet.next()) {
				rechnung = new Rechnung(
						resultSet.getString("unternehmen"), 
						resultSet.getString("unternehmen_strasse"), 
						resultSet.getString("unternehmen_hausnummer"), 
						resultSet.getString("unternehmen_plz"), 
						resultSet.getString("unternehmen_ort"), 
						resultSet.getString("unternehmen_land"), 
						resultSet.getString("standort_gewerbeberechtigung_strasse"), 
						resultSet.getString("standort_gewerbeberechtigung_hausnummer"), 
						resultSet.getString("standort_gewerbeberechtigung_plz"), 
						resultSet.getString("standort_gewerbeberechtigung_ort"), 
						resultSet.getString("standort_gewerbeberechtigung_land"),
						resultSet.getString("kunden_name"), 
						resultSet.getString("kunden_strasse"), 
						resultSet.getString("kunden_hausnummer"), 
						resultSet.getString("kunden_plz"), 
						resultSet.getString("kunden_land"), 
						resultSet.getString("id_kunde"),
						resultSet.getString("id"), 
						resultSet.getString("rechnungsdatum"),
						resultSet.getString("erstellungsdatum"), 
						resultSet.getString("rechnung_beglichen"), 
						resultSet.getString("zahlungsziel"),
						resultSet.getString("brutto_gesamt"), 
						resultSet.getString("netto_gesamt"));

				System.out.println("RechnungsID = " + rechnung.getId());
				rechnungenList.add(rechnung);
			}
			// Table View Daten laden

			statement.close(); // Statement schließen.
			return rechnung;

		} catch (SQLException e) {
			System.out.println("Fehler in Methode showRechnungenWhere():" + e.getMessage());
			return null;
		}
	}
	

}
