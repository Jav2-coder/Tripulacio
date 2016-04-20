package net.javierjimenez.Tripulacio;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 
 * @author Surrui
 *
 */
@Entity
@Table(name = "tripulants")
public class Tripulant implements Serializable {

	/**
	 * Variable Integer que conte el DNI de l'objecte Tripulant, el qual es la variable id de l'objecte.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DNI")
	private Integer dni;
	
	/**
	 * Variable String que conte el nom de l'objecte Tripulant.
	 */
	@Column(name= "Nom")
	private String nom;
	
	/**
	 * Variable String que conte el rang de l'objecte Tripulant.
	 */
	@Column(name = "Rang")
	private String rang;
	
	/**
	 * Variable Integer que conte la matrícula del Vaixell que te vinculat aquest Tripulant.
	 */
	@Column(name = "id_vaixell")
	private Integer id_vaixell;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor principal de l'objecte Tripulant.
	 */
	public Tripulant() {
		super();
	}

	/**
	 * Metode que retorna l'atribut DNI de l'objecte Tripulant.
	 * 
	 * @return
	 */
	public Integer getDni() {
		return dni;
	}

	/**
	 * Metode per afegir un valor a l'atribut DNI de l'objecte Tripulant.
	 * 
	 * @param dni Objecte Integer
	 */
	public void setDni(Integer dni) {
		this.dni = dni;
	}

	/**
	 * Metode que retorna l'atribut nom de l'objecte Tripulant.
	 * 
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Metode per afegir un valor a l'atribut nom de l'objecte Tripulant.
	 * 
	 * @param nom Objecte String
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Metode que retorna l'atribut rang de l'objecte Tripulant.
	 * 
	 * @return
	 */
	public String getRang() {
		return rang;
	}

	/**
	 * Metode per afegir un valor a l'atribut rang de l'objecte Tripulant.
	 * 
	 * @param nom Objecte String
	 */
	public void setRang(String rang) {
		this.rang = rang;
	}

	/**
	 * Metode que retorna l'atribut id_vaixell de l'objecte Tripulant.
	 * 
	 * @return
	 */
	public Integer getId_vaixell() {
		return id_vaixell;
	}

	/**
	 * Metode per afegir un valor a l'atribut id_vaixell de l'objecte Tripulant.
	 * 
	 * @param nom Objecte Integer
	 */
	public void setId_vaixell(Integer id_vaixell) {
		this.id_vaixell = id_vaixell;
	}
}
