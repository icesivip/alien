package controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.Main;



public class MainView implements Initializable{

@FXML private BorderPane borderLayout;
@FXML private Pane principalPanel;
@FXML private Button butLSR;
@FXML private Button butMPS;

	    @FXML
	    void pressendButMps(ActionEvent event) {
	    	try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("MPSView.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.setTitle("Master Plan Schedule");
				window.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void pressendBut(ActionEvent event) {
	    	try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("LSRView.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.setTitle("Lot Sizing Techniques");
				window.show();
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
	    }
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
		}
	    
}

