package net.javierjimenez.Tripulacio;

import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dades {

	Random rnd = new Random();

	String[] rang = { "capita", "tripulant", "cap de colla" };

	public Dades() {

	}

	public void persistirTripulant(Tripulant t, Integer id) {

		int j = rnd.nextInt(10000);

		t.setDni(j);
		t.setNom("Nom " + j);
		t.setId_vaixell(id);
		t.setRang(rang[rnd.nextInt(rang.length)]);

	}

	public void persistirVaixell(Vaixell v) {

		int j = rnd.nextInt(10000);

		v.setMatricula(j);
		v.setNom("Nom " + j);

	}
	
	public ObservableList<String> emplenarLlista(List<Vaixell> vaixells){
		
		ObservableList<String> llista_vaixells = FXCollections.observableArrayList();

		for (Vaixell v : vaixells){
			llista_vaixells.add(v.getNom());
		}
		
		return llista_vaixells;
	}
}
