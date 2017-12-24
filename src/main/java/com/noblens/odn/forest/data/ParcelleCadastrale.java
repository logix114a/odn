package com.noblens.odn.forest.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParcelleCadastrale {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String commune;
	private String section;
	private Long numero_parcelle;
	private String lieu_dit;
	private Double surface;
	
	 @ManyToOne
	@JoinColumn(name="forest_id")
	private Forest forest;


	 public ParcelleCadastrale(String commune, Forest forest) {
		 this.commune = commune;
		 this.forest = forest;	 
	 }

	public Forest getForest() {
		return forest;
	}
	public void setForest(Forest forest) {
		this.forest = forest;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Long getNumero_parcelle() {
		return numero_parcelle;
	}
	public void setNumero_parcelle(Long numero_parcelle) {
		this.numero_parcelle = numero_parcelle;
	}
	public String getLieu_dit() {
		return lieu_dit;
	}
	public void setLieu_dit(String lieu_dit) {
		this.lieu_dit = lieu_dit;
	}
	public Double getSurface() {
		return surface;
	}
	public void setSurface(Double surface) {
		this.surface = surface;
	}

	
}