package net.javierjimenez.Tripulacio;

import java.util.Random;

public class Dades {
	
	Random rnd = new Random();
	
	String [] rang = {"capita", "tripulant", "cap de colla"};
	
	public Dades() {
		
	}
	
	public Vaixell persistirDatos(){
		
		int j = rnd.nextInt(10000);
			
			Vaixell v = new Vaixell();
			
			v.setMatricula(j);
			v.setNom("Nom " + j);
			
			return v;
	}
}
