package net.javierjimenez.Tripulacio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Vaixell implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int matricula;
	private String nom;
	private List<Tripulant> tripulacio;
	private static final long serialVersionUID = 1L;

	public Vaixell() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Tripulant> getTripulacio() {
		return tripulacio;
	}

	public void setTripulacio(List<Tripulant> tripulacio) {
		this.tripulacio = tripulacio;
	}
}
