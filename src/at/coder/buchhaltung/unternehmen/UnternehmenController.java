package at.coder.buchhaltung.unternehmen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import at.coder.buchhaltung.databaseUtil.Database;
import at.coder.buchhaltung.kunde.Costumer;
import at.coder.buchhaltung.main.Main;
import at.coder.buchhaltung.main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class UnternehmenController {
	Unternehmen unternehmen;
	MainController mainController;
	Database db;
	Scene scene;
	
	@FXML
	private TextField textFieldBezeichnung;

	@FXML
	private ComboBox<String> comboBoxAnrede;

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
	private Button buttonUnternehmenSpeichern;

	
	public void initialize() {
		comboBoxAnrede.getItems().add("Frau");
		comboBoxAnrede.getItems().add("Herr");
		comboBoxLand.getItems().add("Deutschland");
		comboBoxLand.getItems().add("Österreich");
		comboBoxLand.getItems().add("Schweiz");
	}
	
	public void setDataBase(Database database, Unternehmen unternehmen, Scene scene, MainController mainController) {
		this.scene = scene;
		this.mainController = mainController;
		
		if(unternehmen == null)	{
			System.out.println("Es muss ein neues Unternehmen erstellt werden!");
			unternehmen = new Unternehmen();
			this.unternehmen = unternehmen;
		}
		else {
			this.unternehmen = unternehmen;
			ladeDatenInFelder();
		}
		this.db = database;
	}
	
	
	@FXML
	void onClickUnternehmenSpeichern(ActionEvent event) {
		unternehmen.setUnternehmen(textFieldBezeichnung.getText());
		unternehmen.setUnternehmen_strasse(textFieldStrasse.getText());
		unternehmen.setUnternehmen_hausnummer(textFieldHausnummerStiegeTuernummer.getText());
		unternehmen.setUnternehmen_plz(textFieldPLZ.getText());
		unternehmen.setUnternehmen_ort(textFieldOrt.getText());
		unternehmen.setUnternehmen_land(comboBoxLand.getValue());
		
		unternehmen.setStandort_gewerbeberechtigung_strasse(textFieldStrasse.getText());
		unternehmen.setStandort_gewerbeberechtigung_hausnummer(textFieldHausnummerStiegeTuernummer.getText());
		unternehmen.setStandort_gewerbeberechtigung_plz(textFieldPLZ.getText());
		unternehmen.setStandort_gewerbeberechtigung_ort(textFieldOrt.getText());
		unternehmen.setStandort_gewerbeberechtigung_land(comboBoxLand.getValue());
		unternehmen.unternehmenAusgeben();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("Unternehmen.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(unternehmen);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		mainController.ladeUnternehmen();
		Stage s = (Stage) buttonAbbrechen.getScene().getWindow();
		s.close();
	}

	@FXML
	void onClickbuttonAbbrechen(ActionEvent event) {
		
	}

	@FXML
	void onKeyTypedBezeichnung(KeyEvent event) {
		
	}

	@FXML
	void onKeyTypedKontaktperson(KeyEvent event) {
		
	}
	
	void ladeDatenInFelder() {
		textFieldBezeichnung.setText(unternehmen.getUnternehmen());
		textFieldStrasse.setText(unternehmen.getUnternehmen_strasse());
		textFieldHausnummerStiegeTuernummer.setText(unternehmen.getUnternehmen_hausnummer());
		textFieldPLZ.setText(unternehmen.getUnternehmen_plz());
		textFieldOrt.setText(unternehmen.getUnternehmen_ort());
		comboBoxLand.getSelectionModel().select(Main.getIndexComboBoxLand(unternehmen.getUnternehmen_land()));
	}

}
