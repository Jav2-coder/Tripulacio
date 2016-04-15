package net.javierjimenez.Tripulacio;

import java.util.Random;

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
}
