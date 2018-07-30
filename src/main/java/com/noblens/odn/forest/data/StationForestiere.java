package com.noblens.odn.forest.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class StationForestiere {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "station_forestiere")
    @SequenceGenerator(name="station_forestiere", sequenceName = "sta_for", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String nom;
	@Column(length = 3000) 
	private String description;
	@Column(length = 3000) 
	private String caracteristique_sol;
	@Column(length = 3000) 
	private String peuplement_naturel;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCaracteristique_sol() {
		return caracteristique_sol;
	}
	public void setCaracteristique_sol(String caracteristique_sol) {
		this.caracteristique_sol = caracteristique_sol;
	}
	public String getPeuplement_naturel() {
		return peuplement_naturel;
	}
	public void setPeuplement_naturel(String peuplement_naturel) {
		this.peuplement_naturel = peuplement_naturel;
	}
	
	
}
