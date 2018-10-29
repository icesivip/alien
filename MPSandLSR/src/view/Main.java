package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	
	public void start(Stage PrimaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Container.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			PrimaryStage.setTitle("Tecnicas de loteo");
			PrimaryStage.setScene(scene);
			PrimaryStage.show();
			PrimaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
				
				
}