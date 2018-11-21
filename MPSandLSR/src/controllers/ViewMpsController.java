package controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ViewMpsController implements Initializable{
	
	public static final String[] LOT_SIZING_METHODS = {"Lot for Lot", "Economic Order Quantity", "Periods Of Suply", 
			"Period Order Quantity", "Least Unit Cost", "Least Total Cost"};
	
@FXML private BorderPane borderLayou;
@FXML private Pane centerPanel;
@FXML private GridPane gpCenterPanel;
@FXML private Label labTecnica;
@FXML private Label labLeadTime;
@FXML private Label labAvailableInventory;
@FXML private Label labSecurityInventory;
@FXML private Label labCode;
@FXML private Label labItemName;
@FXML private TextField TfLeadTime;
@FXML private TextField TfAvalibleInvetary;
@FXML private TextField TfSecurityInventory;
@FXML private TextField TfCode;
@FXML private TextField TfOrderCost;
@FXML private Label labMaintenanceCost;
@FXML private Label labPreparationCost;
@FXML private Label labItemCost;
@FXML private TextField TfMaintenanceCost;
@FXML private TextField TfItemCost;
@FXML private TextField TfItemName;
@FXML private ChoiceBox<String> CbLotTec;
@FXML private Pane centralPanel;
@FXML private ScrollPane spCentralPanel;
@FXML private GridPane gpSpCentralPanel;
@FXML private Label labGrossRequirements;
@FXML private Label labScheduledReceptions;
@FXML private Label lavAvalibeSchudeleInventory;
@FXML private Label labNetRequeriments;
@FXML private Label labScheduledOrders;
@FXML private Label labReleaseOrders;
@FXML private Label labTime;
@FXML private TextField tfGrossRequeriment;
@FXML private TextField tfScheduledReceptions;
@FXML private Pane topPanel;
@FXML private Label labMpsTittle;
@FXML private Pane underPanel;
@FXML private GridPane gpUnderPanel;
@FXML private Button butUpdateData;
@FXML private Button butAddTimes;
@FXML private Button butDeleteTimes;
@FXML private ArrayList<TextField> textFRequest;
	
	@FXML
	void pressendButAddTime(ActionEvent event) {
			spCentralPanel = new ScrollPane();
			Label newTime = new Label("T = "+(textFRequest.size()+1));
			TextField txtAux = new TextField();
			txtAux.setPrefSize(96.0, 30.0);
			gpSpCentralPanel.setMaxWidth(gpSpCentralPanel.getMaxWidth()+96);
			gpSpCentralPanel.setPrefSize(gpSpCentralPanel.getMaxWidth(), 417);
			gpSpCentralPanel.add(newTime, textFRequest.size()+2, 0, 1, 1);
			gpSpCentralPanel.add(txtAux, textFRequest.size()+2, 1, 1, 1);
			gpSpCentralPanel.resize(textFRequest.size()*96, gpSpCentralPanel.getHeight());
			textFRequest.add(txtAux);
			spCentralPanel.setContent(gpSpCentralPanel);
		}

	    @FXML
	    void pressendButDeleteTime(ActionEvent event) {

	    }

	    @FXML
	    void pressendButUpdateData(ActionEvent event) {

	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			textFRequest = new ArrayList<TextField>();
			CbLotTec.setItems(FXCollections.observableArrayList(LOT_SIZING_METHODS));
		}

	}


