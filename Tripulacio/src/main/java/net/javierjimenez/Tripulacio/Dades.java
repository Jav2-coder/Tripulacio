package net.javierjimenez.Tripulacio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dades {

	private static final int MIN = 10000000;

	private static final int MAX = 99999999;
	
	private int id;
	
	Random rnd = new Random();

	String[] rang = { "capita", "tripulant", "cap de colla" };

	public Dades() {

	}

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

	public void persistirTripulant(Tripulant t, Integer matricula) {

		id = rnd.nextInt(MAX - MIN) + MIN;

		t.setDni(id);
		t.setNom("Nom " + id);
		t.setId_vaixell(matricula);
		t.setRang(rang[rnd.nextInt(rang.length)]);

	}

	public void persistirVaixell(Vaixell v) {

		id = rnd.nextInt(MAX - MIN) + MIN;

		v.setMatricula(id);
		v.setNom("Nom " + id);

	}

	public ObservableList<String> emplenarLlista(List<Tripulant> tripulacio) {

		ObservableList<String> llista_tripulacio = FXCollections.observableArrayList();

		for (Tripulant t : tripulacio) {
			llista_tripulacio.add(t.getNom() + " - " + t.getRang());
		}

		return llista_tripulacio;
	}
}
