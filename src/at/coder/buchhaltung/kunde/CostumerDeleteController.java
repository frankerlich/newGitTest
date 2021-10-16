package at.coder.buchhaltung.kunde;

import java.sql.Statement;

import at.coder.buchhaltung.databaseUtil.Database;
import at.coder.buchhaltung.leistung.Leistung;
import at.coder.buchhaltung.main.MainController;
import at.coder.buchhaltung.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CostumerDeleteController {
	private Costumer c;
	private Database db;
	private MainController mainController;
	private TableView tView;
	
	@FXML
	private Button buttonCostumerDeleteYes;

	@FXML
	private Button buttonCostumerDeleteNo;

	public void setDataBase(Database database, Costumer c, MainController mainController, TableView<Costumer> view) {
		this.mainController = mainController;
		this.db = database;
		this.c = c;
		this.tView = view;
	}

	@FXML
	void onActionCostumerDeleteNo(ActionEvent event) {
		Utils.closeWindow(buttonCostumerDeleteNo);
	}

	@FXML
	void onActionCostumerDeleteYes(ActionEvent event) {
		try {
			Statement statement = db.getConnection().createStatement();
			db.deleteCostumer(statement, c.getId());
			System.out.println("Kunde gelöscht!");
			statement.close();
			
		} catch (Exception e) {
			System.out.println("Exception in Methode onActionCostumerDelete: " + e);
		}
		mainController.showAllCostumers(db, mainController.getTableViewAnzeige());
		Utils.closeWindow(buttonCostumerDeleteNo);
	}

}