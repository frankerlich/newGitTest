package at.coder.buchhaltung.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/at/coder/buchhaltung/images/costumer.png")));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(this.getClass().getResource("mainCSS.css").toExternalForm());
		primaryStage.setTitle(" Buchhaltung Kleinunternehmer");
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		//primaryStage.setMaximized(true);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static int getIndexComboBoxLand(String selected) {
		if(selected.equals("Deutschland")) {
			return 0;
		}
		else if(selected.equals("Österreich")) {
			return 1;
		}
		else if(selected.equals("Schweiz")) {
			return 2;
		}
		else {
			return -1;
		}
	}
	
	public static int getIndexComboBoxAnrede(String selected) {
		if(selected.equals("Herr")) {
			return 0;
		}
		else if(selected.equals("Frau")) {
			return 1;
		}
		else {
			return -1;
		}
	}

	
	
}
