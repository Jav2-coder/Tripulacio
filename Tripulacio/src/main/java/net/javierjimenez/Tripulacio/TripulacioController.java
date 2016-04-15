package net.javierjimenez.Tripulacio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
	
	private List<Vaixell> vaixells = new ArrayList<Vaixell>();
	
	private List<Tripulant> tripulants;
	
	private static final int MIN_TRIPULACIO = 3;
	
	private static final int MAX_TRIPULACIO = 10;
	
	private Random rnd = new Random();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tripulants");
		e = emf.createEntityManager();

	}

	public void generarVaixells(ActionEvent event) {

		Dades d = new Dades();
		
		e.getTransaction().begin();
		
		int i = 0;
		int j = 0;
		
		while (j < 2){
			
			Vaixell v = new Vaixell();
			vaixells.add(v);
			System.out.println("Total " + j);
			e.persist(v);
			j++;
		}
		
		while(i < vaixells.size()){
			
			tripulants = new ArrayList<Tripulant>();
			
			int rndTripulacio = rnd.nextInt(MAX_TRIPULACIO - MIN_TRIPULACIO) + MIN_TRIPULACIO;
			
			d.persistirVaixell(vaixells.get(i));
			
			for(int k = 0; k < rndTripulacio;){
				
				System.out.println("Posicio " +  k);
				
				Tripulant t = new Tripulant();
				d.persistirTripulant(t, vaixells.get(i).getMatricula());
				
				if(e.find(Tripulant.class, t.getDni()) == null) {
					e.persist(t);
					k++;
					tripulants.add(t);
				}
			}
			
			vaixells.get(i).setTripulacio(tripulants);
			
			e.persist(vaixells.get(i));
			i++;
		}
		
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
