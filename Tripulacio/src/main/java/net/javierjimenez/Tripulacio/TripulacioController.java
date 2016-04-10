package net.javierjimenez.Tripulacio;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TripulacioController implements Initializable {

	@FXML
	private ListView tripulacio;
	
	@FXML
	private Label navegar;
	
	@FXML
	private ComboBox<String> llistaVaixells = new ComboBox<>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void generarVaixells(ActionEvent event){
		
	}
	
	public void seleccionarVaixell(ActionEvent event){
		
	}
}
