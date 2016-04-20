package net.javierjimenez.Tripulacio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author Surrui
 *
 */
public class TripulacioController implements Initializable {

	/**
	 * Objecte ListView que conte la llista de tripulants de cada Vaixell.
	 */
	@FXML
	private ListView<String> tripulacio = new ListView<String>();
	
	/**
	 * Objecte Label que informa si el Vaixell pot navegar o no.
	 */
	@FXML
	private Label navegar;
	
	/**
	 * Objecte Button que genera els Vaixells i la seva
	 * tripulacio de forma aleatoria.
	 */
	@FXML
	private Button crearVaixellsRandom;
	
	/**
	 * Objecte Button que genera els Vaixells i la seva
	 * tripulacio a partir d'un arxiu txt.
	 */
	@FXML
	private Button crearVaixellsArxiu;
	
	/**
	 * Objecte ComboBox que conte els noms dels Vaixells generats.
	 */
	@FXML
	private ComboBox<String> llistaVaixells = new ComboBox<>();

	/**
	 * Objecte EntityManager encarregat de persistir les dades a la base de dades.
	 */
	private EntityManager e;

	/**
	 * Objecte EntityManagerFactory encarregat de generar la base de dades.
	 */
	private EntityManagerFactory emf;

	/**
	 * Objecte List que conte objectes Vaixell
	 */
	private List<Vaixell> vaixells = new ArrayList<Vaixell>();

	/**
	 * Objecte List que conte objectes Tripulant.
	 */
	private List<Tripulant> tripulants;

	/**
	 * Variable int que indica el minim d'objectes Tripulant
	 * que contindra cada objecte Vaixell
	 */
	private static final int MIN_TRIPULACIO = 3;

	/**
	 * Variable int que indica el maxim d'objectes Tripulant
	 * que contindra cada objecte Vaixell
	 */
	private static final int MAX_TRIPULACIO = 10;

	/**
	 * Objecte Random que farem servir per generar valors aleatoris.
	 */
	private Random rnd = new Random();

	/**
	 * Objecte Dades que farem servir per generar els valors
	 * dels atributs dels objectes Tripulant i Vaixell
	 */
	private Dades d = new Dades();

	/**
	 * Variable int que conte el total de Tripulant que
	 * contindran cada Vaixell en la BD.
	 */
	private int rndTripulacio;
	
	/**
	 * Objecte List que conte les dades de cada Tripulant
	 * que s'agafaran de l'arxiu TXT
	 */
	private List<String> dades = new ArrayList<String>();

	/**
	 * Metode que inicialitza la connexio amb la Base de Dades.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		emf = Persistence.createEntityManagerFactory("Tripulants");
		e = emf.createEntityManager();

	}

	/**
	 * Metode per generar els objectes Vaixell i Tripulant
	 * a partir d'un arxiu TXT que escollim nosaltres.
	 * 
	 * @param event Objecte ActionEvent
	 * @throws IOException
	 */
	public void generarVaixellsArxiu(ActionEvent event) throws IOException {

		e.getTransaction().begin();

		int i = 0;

		vaixells = d.generarVaixells();

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TXT Files", "*.txt"));

		File selectedFile = fileChooser.showOpenDialog(null);

		BufferedReader br = new BufferedReader(new FileReader(selectedFile));

		String linia = br.readLine();

		while (linia != null) {

			if (Pattern.matches("([a-z\\s]+), (\\d{1,8}), (.+)", linia)) {
				dades.add(linia);
				linia = br.readLine();
			} else {
				linia = br.readLine();
				System.out.println("La linia no coincideix amb les especificacions desitjades.");
			}
		}

		br.close();

		while (i < vaixells.size()) {

			tripulants = new ArrayList<Tripulant>();

			rndTripulacio = rnd.nextInt(MAX_TRIPULACIO - MIN_TRIPULACIO) + MIN_TRIPULACIO;

			d.persistirVaixell(vaixells.get(i));

			if (e.find(Vaixell.class, vaixells.get(i).getMatricula()) == null) {

				for (int k = 0; k < rndTripulacio;) {

					Tripulant t = new Tripulant();

					if (!dades.isEmpty()) {

						int pos = rnd.nextInt(dades.size());
						String data = dades.get(pos);
						String[] dadesTripulant = data.split(", ");

						if (d.isNumeric(dadesTripulant[1])) {

							t.setNom(dadesTripulant[0]);
							t.setDni(Integer.parseInt(dadesTripulant[1]));
							t.setRang(dadesTripulant[2]);

							if (e.find(Tripulant.class, t.getDni()) == null) {
								dades.remove(pos);
								e.persist(t);
								tripulants.add(t);
							}
						} else {
							dades.remove(pos);
						}
						k++;
					} else {
						k++;
					}
				}

				vaixells.get(i).setTripulacio(tripulants);
				llistaVaixells.getItems().add(vaixells.get(i).getNom());

				e.persist(vaixells.get(i));
				i++;
			}
		}

		e.getTransaction().commit();
		e.close();

		tripulants.clear();
		crearVaixellsRandom.setDisable(true);
		crearVaixellsArxiu.setDisable(true);

	}

	/**
	 * Metode per generar els objectes Vaixell i Tripulant aleatoriament.
	 * 
	 * @param event Objecte ActionEvent
	 */
	public void generarVaixellsRandom(ActionEvent event) {

		e.getTransaction().begin();

		int i = 0;

		vaixells = d.generarVaixells();

		while (i < vaixells.size()) {

			tripulants = new ArrayList<Tripulant>();

			rndTripulacio = rnd.nextInt(MAX_TRIPULACIO - MIN_TRIPULACIO) + MIN_TRIPULACIO;

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
			}
		}

		e.getTransaction().commit();
		e.close();

		tripulants.clear();
		crearVaixellsRandom.setDisable(true);
		crearVaixellsArxiu.setDisable(true);

	}

	/**
	 * Metode encarregat de buscar en la BD els objectes Tripulant
	 * i retornar els que estan lligats al objecte Vaixell que hem
	 * seleccionat del ComboBox.
	 * 
	 * @param event Objecte ActionEvent
	 */
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

		for (int i = 0; i < tripulacioVaixell.size(); i++) {
			if (tripulacioVaixell.get(i).getRang().equals("capita")) {
				capita++;
			} else if (tripulacioVaixell.get(i).getRang().equals("cap de colla")) {
				cap_colla++;
			}
			
			if (tripulacioVaixell.get(i).getRang().equals("tripulant"))
				tripulant = true;
		}

		tripulacio.setItems(d.emplenarLlista(tripulacioVaixell));

		if (capita == 1 && tripulant && cap_colla >= 1) {
			navegar.setText("Pot navegar");
		} else {
			navegar.setText("No pot navegar");
		}

		e.close();

	}
}
