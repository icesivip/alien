package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import model.OldLotSizingMethods;

public class ContainerController implements Initializable{

@FXML private BorderPane borderLayout;
@FXML private Pane centralPanel;
@FXML private Label lbCostArticle;
@FXML private Label lbCostPrepare;
@FXML private Label lbCostMaintenance;
@FXML private Label lbNetRequirements;
@FXML private Button butAddRequirement;
@FXML private Button butModifyData;
@FXML private Button butRemoveRequeriment;
@FXML private TextField textFCostArticle;
@FXML private TextField textFCostMaintenance;
@FXML private TextField textFCostPrepare;
@FXML private ArrayList<TextField> textFRequest;
@FXML private Label lbTime;
@FXML private ScrollPane scrollCentralPanel;
@FXML private Pane leftPanel;
@FXML private Label lbTechniques;
@FXML private Button butLXL;
@FXML private Button butPOS;
@FXML private Button butPOQ;
@FXML private Button butLTC;
@FXML private Button butEOQ;
@FXML private Button butLUC;
@FXML private Label lbResult;
@FXML private Label lbAnswer;
@FXML private GridPane gpOrdersInTime;
	
    private OldLotSizingMethods lotSizingRules;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	textFRequest = new ArrayList<TextField>();
    	
    }
    
    @FXML
    void PressendAddRequirement(ActionEvent event) {
    	
    	Label newTime = new Label("T = "+(textFRequest.size()+1));
    	TextField txtAux = new TextField();
    	txtAux.setPrefSize(96.0, 30.0);
    	gpOrdersInTime.add(newTime, textFRequest.size(), 0, 1, 1);
    	gpOrdersInTime.add(txtAux, textFRequest.size(), 1, 1, 1);
    	gpOrdersInTime.resize(textFRequest.size()*96, gpOrdersInTime.getHeight());
    	textFRequest.add(txtAux);
    	
    }
    
    void inicializarSistema() {
    	double costoArticulo = Double.parseDouble(textFCostArticle.getText());
		double costoPreparacion = Double.parseDouble(textFCostPrepare.getText());
		double costoMantenimiento = Double.parseDouble(textFCostMaintenance.getText());
		lotSizingRules = new OldLotSizingMethods(costoArticulo, costoPreparacion, costoMantenimiento, OldLotSizingMethods.UNKNOWN);
		for(int i = 0; i < textFRequest.size(); i++) {
			lotSizingRules.agregarPedido(Integer.parseInt(textFRequest.get(i).getText()));
		}
    }

    /*
     * Costo total mínimo
     */
    @FXML
    void PressedLTC(ActionEvent event) {
    	inicializarSistema();
    	lotSizingRules.sistemaPorCostoTotalMinimo();
    	Alert dialogAlert = new Alert(AlertType.INFORMATION);
    	dialogAlert.setTitle("Planificación de pedidos por costo total mínimo");
    	dialogAlert.setHeaderText(null);
    	dialogAlert.setContentText(lotSizingRules.toString());
    	dialogAlert.initStyle(StageStyle.UTILITY);
    	dialogAlert.showAndWait();
    }
    
    /*
     * Lote por lote
     */
    @FXML
    void PressendLXL(ActionEvent event) {
    	inicializarSistema();
    	lotSizingRules.sistemaLotePorLote();
    	Alert dialogAlert = new Alert(AlertType.INFORMATION);
    	dialogAlert.setTitle("Planificación de pedidos por lote por lote");
    	dialogAlert.setHeaderText(null);
    	dialogAlert.setContentText(lotSizingRules.toString());
    	dialogAlert.initStyle(StageStyle.UTILITY);
    	dialogAlert.showAndWait();
    }

    @FXML
    void PressendModifyData(ActionEvent event) {
    	
    }
    
    /*
     * Cantidad de orden periodo
     */
    @FXML
    void PressendPOQ(ActionEvent event) {
    	inicializarSistema();
    	lotSizingRules.sistemaCantidadDeOrdenDePeriodo();
    	Alert dialogAlert = new Alert(AlertType.INFORMATION);
    	dialogAlert.setTitle("Planificación de pedidos por cantidad de orden periodo");
    	dialogAlert.setHeaderText(null);
    	dialogAlert.setContentText(lotSizingRules.toString());
    	dialogAlert.initStyle(StageStyle.UTILITY);
    	dialogAlert.showAndWait();
    }
    
    /*
     * Periodos de suministro
     */
    @FXML
    void PressendPOS(ActionEvent event) {
    	inicializarSistema();
    	//agregar opción de ingreso
    	lotSizingRules.sistemaPeriodoDeSuministro(1);
    	Alert dialogAlert = new Alert(AlertType.INFORMATION);
    	dialogAlert.setTitle("Planificación de pedidos por periodos de suministro");
    	dialogAlert.setHeaderText(null);
    	dialogAlert.setContentText(lotSizingRules.toString());
    	dialogAlert.initStyle(StageStyle.UTILITY);
    	dialogAlert.showAndWait();
    }
    
    /*
     * Cantidad de orden económica
     */
    @FXML
    void PressedEOQ(ActionEvent event) {
    	inicializarSistema();
    	lotSizingRules.sistemaCantidadDeOrdenEconomica();
    	Alert dialogAlert = new Alert(AlertType.INFORMATION);
    	dialogAlert.setTitle("Planificación de pedidos por cantidad de orden económica");
    	dialogAlert.setHeaderText(null);
    	dialogAlert.setContentText(lotSizingRules.toString());
    	dialogAlert.initStyle(StageStyle.UTILITY);
    	dialogAlert.showAndWait();
    }
    
    /*
     * Menor costo unitario
     */
    @FXML
    void PressedLUC(ActionEvent event) {
    	inicializarSistema();
    	lotSizingRules.sistemaPorMenorCostoUnitario();
    	Alert dialogAlert = new Alert(AlertType.INFORMATION);
    	dialogAlert.setTitle("Planificación de pedidos por menor costo unitario");
    	dialogAlert.setHeaderText(null);
    	dialogAlert.setContentText(lotSizingRules.toString());
    	dialogAlert.initStyle(StageStyle.UTILITY);
    	dialogAlert.showAndWait();
    }

    @FXML
    void PressendRemoveRequeriment(ActionEvent event) {
    	if(!textFRequest.isEmpty()) {
    		textFRequest.remove(textFRequest.get(textFRequest.size()-1));
    	}
    }


}
