package net.javierjimenez.Tripulacio;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tripulant")
public class Tripulant implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DNI")
	private Integer dni;
	
	@Column(name= "Nom")
	private String nom;
	
	@Column(name = "Rang")
	private String rang;
	
	@Column(name = "id_vaixell")
	private Integer id_vaixell;
	
	private static final long serialVersionUID = 1L;

	public Tripulant() {
		super();
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
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

	public Integer getId_vaixell() {
		return id_vaixell;
	}

	public void setId_vaixell(Integer id_vaixell) {
		this.id_vaixell = id_vaixell;
	}

	@Override
	public String toString() {
		return "Tripulant [getDni()=" + getDni() + ", getNom()=" + getNom() + ", getRang()=" + getRang()
				+ ", getId_vaixell()=" + getId_vaixell() + "]";
	}
}
