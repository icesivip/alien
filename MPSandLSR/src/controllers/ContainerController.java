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

@FXML private BorderPane BorderLayout;
@FXML private Pane CentralPanel;
@FXML private Label LbCostArticle;
@FXML private Label LbCostPrepare;
@FXML private Label LbCostMaintenance;
@FXML private Label LbNetRequirements;
@FXML private Button ButAddRequirement;
@FXML private Button ButModifyData;
@FXML private Button ButRemoveRequeriment;
@FXML private TextField TextFCostArticle;
@FXML private TextField TextFCostMaintenance;
@FXML private TextField TextFCostPrepare;
@FXML private ArrayList<TextField> TextFRequest;
@FXML private Label LbTime;
@FXML private ScrollPane ScrollCentralPanel;
@FXML private Pane LeftPanel;
@FXML private Label LbTechniques;
@FXML private Button ButLXL;
@FXML private Button ButPOS;
@FXML private Button ButPOQ;
@FXML private Button ButLTC;
@FXML private Button ButEOQ;
@FXML private Button ButLUC;
@FXML private Label LbResult;
@FXML private Label LbAnswer;
@FXML private GridPane gpOrdersInTime;
	
    private OldLotSizingMethods lotSizingRules;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	TextFRequest = new ArrayList<TextField>();
    	
    }
    
    @FXML
    void PressendAddRequirement(ActionEvent event) {
    	
    	Label newTime = new Label("T = "+(TextFRequest.size()+1));
    	TextField txtAux = new TextField();
    	txtAux.setPrefSize(96.0, 30.0);
    	gpOrdersInTime.add(newTime, TextFRequest.size(), 0, 1, 1);
    	gpOrdersInTime.add(txtAux, TextFRequest.size(), 1, 1, 1);
    	gpOrdersInTime.resize(TextFRequest.size()*96, gpOrdersInTime.getHeight());
    	TextFRequest.add(txtAux);
//    	Label newTime = new Label("T = "+(loteoSystem.getArticulosSemanales().size()+1));
    	
    }
    
    void inicializarSistema() {
    	double costoArticulo = Double.parseDouble(TextFCostArticle.getText());
		double costoPreparacion = Double.parseDouble(TextFCostPrepare.getText());
		
		double costoMantenimiento = Double.parseDouble(TextFCostMaintenance.getText());
		lotSizingRules = new OldLotSizingMethods(costoArticulo, costoPreparacion, costoMantenimiento, OldLotSizingMethods.UNKNOWN);
		for(int i = 0; i < TextFRequest.size(); i++) {
			lotSizingRules.agregarPedido(Integer.parseInt(TextFRequest.get(i).getText()));
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
//    	gpOrdersInTime.re
    	//Poner exception no more salir pl0x papi
//    	gpOrdersInTime.
    	TextFRequest.remove(TextFRequest.get(TextFRequest.size()-1));
    }


}
