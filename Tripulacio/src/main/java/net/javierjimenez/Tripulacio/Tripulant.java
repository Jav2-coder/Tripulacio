package net.javierjimenez.Tripulacio;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Tripulant implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int dni;
	private String nom;
	private String rang;
	private static final long serialVersionUID = 1L;

	public Tripulant() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}
}
