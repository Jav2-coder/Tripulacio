package net.javierjimenez.Tripulacio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "vaixells")
public class Vaixell implements Serializable {

	@Id
	@Column(name = "Matrícula")
	private int matricula;
	
	@Column(name = "Nom")
	private String nom;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_vaixell")
	private List<Tripulant> tripulacio;
	
	private static final long serialVersionUID = 1L;

	public Vaixell() {
		super();
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
