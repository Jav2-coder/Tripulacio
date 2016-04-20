package net.javierjimenez.Tripulacio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Surrui
 *
 */
public class Dades {

	/**
	 * Variable int que defineix el valor minim del id.
	 */
	private static final int MIN = 10000000;

	/**
	 * Variable int que defineix el valor maxim del id.
	 */
	private static final int MAX = 99999999;

	/**
	 * Variable int que defineix el id dels objectes vaixell i tripulant
	 */
	private int id;

	/**
	 * Objecte Random que farem servir per generar valors aleatoris.
	 */
	private Random rnd = new Random();

	/**
	 * Array de String que conte els valors per l'atribut rang de Tripulant.
	 */
	private String[] rang = { "capita", "tripulant", "cap de colla" };

	/**
	 * Constructor principal de l'objecte Dades.
	 */
	public Dades() {
	}

	/**
	 * Metode que genera 100 objectes Vaixell
	 * 
	 * @return
	 */
	public List<Vaixell> generarVaixells() {

		List<Vaixell> llista = new ArrayList<Vaixell>();

		int j = 0;

		while (j < 100) {

			Vaixell v = new Vaixell();
			llista.add(v);
			j++;
		}

		return llista;

	}

	/**
	 * Metode que genera els valors pels atributs de l'objecte Tripulant
	 * 
	 * @param t Objecte Tripulant
	 * @param matricula Objecte Integer
	 */
	public void persistirTripulant(Tripulant t, Integer matricula) {

		id = rnd.nextInt(MAX - MIN) + MIN;

		t.setDni(id);
		t.setNom("Nom " + id);
		t.setId_vaixell(matricula);
		t.setRang(rang[rnd.nextInt(rang.length)]);

	}

	/**
	 * Metode que genera els valors pels atributs de l'objecte Vaixell
	 * 
	 * @param v Objecte Tripulant
	 */
	public void persistirVaixell(Vaixell v) {

		id = rnd.nextInt(MAX - MIN) + MIN;

		v.setMatricula(id);
		v.setNom("Nom " + id);

	}

	/**
	 * Metode que retorna un objecte ObservableList que conte els noms dels
	 * objectes Tripulant i genera una llista per cada objecte Vaixell.
	 * 
	 * @param tripulacio  Objecte List
	 * @return
	 */
	public ObservableList<String> emplenarLlista(List<Tripulant> tripulacio) {

		ObservableList<String> llista_tripulacio = FXCollections.observableArrayList();

		if (tripulacio.isEmpty()) {

			llista_tripulacio.add("Sense tripulació");

			return llista_tripulacio;

		} else {

			for (Tripulant t : tripulacio) {
				llista_tripulacio.add(t.getNom() + " - " + t.getRang());
			}

			return llista_tripulacio;
		}
	}

	/**
	 * Metode que comproba si el String esta format per numeros o no.
	 * 
	 * @param cadena Objecte String
	 * @return
	 */
	public boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
