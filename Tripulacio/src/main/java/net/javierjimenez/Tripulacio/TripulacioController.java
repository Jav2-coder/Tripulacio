package net.javierjimenez.Tripulacio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TripulacioController implements Initializable {

	@FXML
	private ListView<String> tripulacio = new ListView<String>();
	@FXML
	private Label navegar;
	@FXML
	private Button crearVaixellsRandom;
	@FXML
	private Button crearVaixellsArxiu;
	@FXML
	private ComboBox<String> llistaVaixells = new ComboBox<>();

	private EntityManager e;
	
	EntityManagerFactory emf;
	
	List<Vaixell> vaixells = new ArrayList<Vaixell>();

	List<Tripulant> tripulants;

	private static final int MIN_TRIPULACIO = 3;

	private static final int MAX_TRIPULACIO = 10;

	private Random rnd = new Random();
	
	private Dades d = new Dades();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		emf = Persistence.createEntityManagerFactory("Tripulants");
		e = emf.createEntityManager();

	}

	public void generarVaixellsArxiu(ActionEvent event) {
		
		e.getTransaction().begin();
		
		int i = 0;
		
		vaixells = d.generarVaixells();
		
		while (i < vaixells.size()){
			
		}
		
		e.getTransaction().commit();
		e.close();
		
		tripulants.clear();
		crearVaixellsRandom.setDisable(true);
		crearVaixellsArxiu.setDisable(true);
		
	}
	
	public void generarVaixellsRandom(ActionEvent event) {

		e.getTransaction().begin();

		int i = 0;
		
		vaixells = d.generarVaixells();
		
		while (i < vaixells.size()) {

			tripulants = new ArrayList<Tripulant>();
			
			int rndTripulacio = rnd.nextInt(MAX_TRIPULACIO - MIN_TRIPULACIO) + MIN_TRIPULACIO;

			d.persistirVaixell(vaixells.get(i));
			
			if (e.find(Vaixell.class, vaixells.get(i).getMatricula()) == null) {

				for (int k = 0; k < rndTripulacio;) {

					Tripulant t = new Tripulant();
					d.persistirTripulant(t, vaixells.get(i).getMatricula());

					if (e.find(Tripulant.class, t.getDni()) == null) {
						e.persist(t);
						k++;
						tripulants.add(t);
					}
				}

				vaixells.get(i).setTripulacio(tripulants);
				llistaVaixells.getItems().add(vaixells.get(i).getNom());

				e.persist(vaixells.get(i));
				i++;
				System.out.println("Vaixell " + i);
			}
		}
		
		e.getTransaction().commit();
		e.close();
		
		tripulants.clear();
		crearVaixellsRandom.setDisable(true);
		crearVaixellsArxiu.setDisable(true);

	}

	public void seleccionarVaixell(ActionEvent event) {

		e = emf.createEntityManager();
		
		String g = llistaVaixells.getValue();
		
		TypedQuery<Vaixell> v = e.createQuery("SELECT v FROM Vaixell v WHERE v.nom = ?1", Vaixell.class);
		
		v.setParameter(1, g);
		
		Vaixell ship = v.getSingleResult();
		
		Integer matricula = ship.getMatricula();
		
		TypedQuery<Tripulant> t = e.createQuery("SELECT t FROM Tripulant t WHERE t.id_vaixell = ?1", Tripulant.class);
		
		t.setParameter(1, matricula);
		
		List<Tripulant> tripulacioVaixell = t.getResultList();
		
		int capita = 0;
		int cap_colla = 0;
		boolean tripulant = false;
		
		for (int i = 0; i <tripulacioVaixell.size(); i++){
			if(tripulacioVaixell.get(i).getRang().equals("capita")){
				capita++;
			} else if(tripulacioVaixell.get(i).getRang().equals("cap de colla")) {
				cap_colla++;
			}
			
			if(tripulacioVaixell.get(i).getRang().equals("tripulant")) tripulant = true;
		}
		
		tripulacio.setItems(d.emplenarLlista(tripulacioVaixell));
		
		if(capita == 1 && tripulant && cap_colla >= 1){
			navegar.setText("Pot navegar");
		} else {
			navegar.setText("No pot navegar");
		}
		
		e.close();

	}
}
