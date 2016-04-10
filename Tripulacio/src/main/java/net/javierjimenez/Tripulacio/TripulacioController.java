package net.javierjimenez.Tripulacio;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TripulacioController implements Initializable {

	@FXML
	private ListView<String> tripulacio = new ListView<String>();

	@FXML
	private Label navegar;

	@FXML
	private ComboBox<String> llistaVaixells = new ComboBox<>();

	private EntityManager e;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tripulants");
		e = emf.createEntityManager();

	}

	public void generarVaixells(ActionEvent event) {

		e.getTransaction().begin();
		// e.persist(alumne);
		e.getTransaction().commit();

		e.close();

	}

	public void seleccionarVaixell(ActionEvent event) {

		ObservableList<String> prueba = FXCollections.observableArrayList();

		int i = 0;

		while (i < 10) {
			i++;
			prueba.add("Prueba");
		}

		tripulacio.setItems(prueba);

	}
}
