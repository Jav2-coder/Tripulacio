package net.javierjimenez.Tripulacio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * 
 * @author alumne1daw
 *
 */
@Entity
@Table(name = "vaixells")
public class Vaixell implements Serializable {

	/**
	 * Variable Integer que conte la matricula de l'objecte Vaixell, el qual es la variable id de l'objecte.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Matrícula")
	private Integer matricula;
	
	/**
	 * variable String que conte el nom de l'objecte Vaixell.
	 */
	@Column(name = "Nom")
	private String nom;
	
	/**
	 * Objecte List que conte els objectes Tripulant lligats a l'objecte Vaixell. Es la variable que genera la relacio OneToMany entre Vaixell i Tripulant.
	 */
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_vaixell")
	private List<Tripulant> tripulacio;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor principal de l'objecte Vaixell
	 */
	public Vaixell() {
		super();
	}
	
	/**
	 * Metode que retorna l'atribut matricula de l'objecte Vaixell.
	 * 
	 * @return
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * Metode per afegir un valor a l'atribut matricula de l'objecte Vaixell.
	 * 
	 * @param matricula Objecte Integer
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * Metode que retorna l'atribut nom de l'objecte Vaixell
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Metode per afegir un valor a l'atribut nom de l'objecte Vaixell
	 * 
	 * @param nom Variable String
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Metode que retorna l'atribut tripulacio de l'objecte Vaixell
	 * 
	 * @return
	 */
	public List<Tripulant> getTripulacio() {
		return tripulacio;
	}

	/**
	 * Metode per afegir un valor a l'atribut tripulacio de l'objecte Vaixell
	 * 
	 * @param tripulacio Objecte List
	 */
	public void setTripulacio(List<Tripulant> tripulacio) {
		this.tripulacio = tripulacio;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Vaixell [getMatricula()=" + getMatricula() + ", getNom()=" + getNom() + ", getTripulacio()="
				+ getTripulacio() + "]";
	}
}
