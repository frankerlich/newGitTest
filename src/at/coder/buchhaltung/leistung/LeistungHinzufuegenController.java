package at.coder.buchhaltung.leistung;

import java.sql.SQLException;
import java.sql.Statement;

import at.coder.buchhaltung.databaseUtil.Database;
import at.coder.buchhaltung.main.MainController;
import at.coder.buchhaltung.unternehmen.Unternehmen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LeistungHinzufuegenController {

	// Attribute
	private MainController mainController;
	private TableView tableViewLeistungen;
	private Database db;
	private Scene scene;

	@FXML
	private Button buttonLeistungHinzufuegen;

	@FXML
	private TextField textFieldNeueLeistungBezeichnung;

	@FXML
	private TextField textFieldNeueLeistungMenge;

	@FXML
	private TextField textFieldNeueLeistungPreis;

	public void initialize() {
		
	}

	public void setDataBase(Database database, Scene scene, MainController mainController, TableView<Leistung> tView) {
		this.scene = scene;
		this.mainController = mainController;
		this.db = database;
		this.tableViewLeistungen = tView;
	}

	@FXML
    void onActionNeueLeistungHinzufuegen(ActionEvent event) {
		Leistung leistung = new Leistung();
		leistung.setBezeichnung(textFieldNeueLeistungBezeichnung.getText());
		leistung.setMenge(textFieldNeueLeistungMenge.getText());
		leistung.setPreis(textFieldNeueLeistungPreis.getText());
		try {
			Statement statement = db.getConnection().createStatement();// Statement starten
			db.createLeistung(statement, leistung);
			statement.close(); // Statement schlieﬂen.
			mainController.showAllLeistungen(db, tableViewLeistungen);
			Stage s = (Stage) buttonLeistungHinzufuegen.getScene().getWindow();
			s.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
	
}
