package at.coder.buchhaltung.kunde;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.coder.buchhaltung.databaseUtil.Database;
import at.coder.buchhaltung.main.MainController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CostumerController {
	Costumer costumer;
	Database database;
	private TableView<Costumer> tableView;
	private TableColumn colBezeichnung;
	private TableColumn colAnrede;
	private TableColumn colKontaktperson;
	private TableColumn colStrasse;
	private TableColumn colHausnummer;
	private TableColumn colPLZ;
	private TableColumn colOrt;
	private TableColumn colLand;
	private TableColumn colUid;
	private TableColumn colEmail;
	private TableColumn colTelefon;
	private TableColumn colBeschreibung;

	@FXML
	private ComboBox<String> comboBoxAnrede;
	@FXML
	private TextField textFieldBezeichnung;
	@FXML
	private TextField textFieldKontaktperson;
	@FXML
	private TextField textFieldStrasse;
	@FXML
	private TextField textFieldHausnummerStiegeTuernummer;
	@FXML
	private TextField textFieldPLZ;
	@FXML
	private TextField textFieldOrt;
	@FXML
	private ComboBox<String> comboBoxLand;
	@FXML
	private TextField textFieldUID;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldTelefon;
	@FXML
	private TextArea textAreaBeschreibung;
	@FXML
	private Button buttonAbbrechen;
	@FXML
	private Button buttonKundeErstellen;

	public void setDataBase(Database db, TableView<Costumer> tableView1, TableColumn<Costumer, String> colBezeichnung,
			TableColumn<Costumer, String> colAnrede, TableColumn<Costumer, String> colKontaktperson,
			TableColumn<Costumer, String> colStrasse, TableColumn<Costumer, String> colHausnummer,
			TableColumn<Costumer, String> colPLZ, TableColumn<Costumer, String> colOrt,
			TableColumn<Costumer, String> colLand, TableColumn<Costumer, String> colUid,
			TableColumn<Costumer, String> colEmail, TableColumn<Costumer, String> colTelefon,
			TableColumn<Costumer, String> colBeschreibung) {
		database = db;
		this.tableView = tableView1;
		// System.out.println("NEUESFTableView = ..." + tableView == null);

		this.colBezeichnung = colBezeichnung;
		this.colAnrede = colAnrede;
		this.colKontaktperson = colKontaktperson;
		this.colStrasse = colStrasse;
		this.colHausnummer = colHausnummer;
		this.colPLZ = colPLZ;
		this.colOrt = colOrt;
		this.colLand = colLand;
		this.colUid = colUid;
		this.colEmail = colEmail;
		this.colTelefon = colTelefon;
		this.colBeschreibung = colBeschreibung;
	}

	public void initialize() {
		comboBoxAnrede.getItems().add("Frau");
		comboBoxAnrede.getItems().add("Herr");

		comboBoxLand.getItems().add("Deutschland");
		comboBoxLand.getItems().add("÷sterreich");
		comboBoxLand.getItems().add("Schweiz");
	}

	@FXML
	void onKeyTypedKontaktperson(KeyEvent event) {
		String bezeichnung;
		String kontaktperson;
		bezeichnung = textFieldBezeichnung.getText();
		kontaktperson = textFieldKontaktperson.getText();
		if (!bezeichnung.trim().isEmpty() || !kontaktperson.trim().isEmpty()) {
			buttonKundeErstellen.setDisable(false);
		} else {
			buttonKundeErstellen.setDisable(true);
		}
	}

	@FXML
	void onKeyTypedBezeichnung(KeyEvent event) {
		String bezeichnung;
		String kontaktperson;
		bezeichnung = textFieldBezeichnung.getText();
		kontaktperson = textFieldKontaktperson.getText();
		if (!bezeichnung.trim().isEmpty() || !kontaktperson.trim().isEmpty()) {
			buttonKundeErstellen.setDisable(false);
		} else {
			buttonKundeErstellen.setDisable(true);
		}
	}

	@FXML
	void onClickKundeErstellen(ActionEvent event) {
		costumer = new Costumer();
		costumer.setBezeichnung(textFieldBezeichnung.getText());
		costumer.setKontaktperson(textFieldKontaktperson.getText());
		costumer.setAnrede(comboBoxAnrede.getValue());
		costumer.setStrasse(textFieldStrasse.getText());
		costumer.setHausnummer(textFieldHausnummerStiegeTuernummer.getText());
		costumer.setPlz(textFieldPLZ.getText());
		costumer.setOrt(textFieldOrt.getText());
		costumer.setLand(comboBoxLand.getValue());
		costumer.setAnzahlRechnungen("0");
		costumer.setUid(textFieldUID.getText());
		costumer.setEmail(textFieldEmail.getText());
		costumer.setBeschreibung(textAreaBeschreibung.getText());
		try {
			Statement statement = database.getConnection().createStatement();// Statement starten
			database.createCostumer(statement, costumer);
			statement.close(); // Statement schlieﬂen.
			MainController mainController = new MainController();
			mainController.showAllCostumers(database, tableView);
			Stage s = (Stage) buttonAbbrechen.getScene().getWindow();
			s.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@FXML
	void onClickbuttonAbbrechen(ActionEvent event) {
		Stage stage = (Stage) textFieldBezeichnung.getScene().getWindow();
		stage.close();
	}

}