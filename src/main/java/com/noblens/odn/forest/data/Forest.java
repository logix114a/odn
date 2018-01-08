package com.noblens.odn.forest.data;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Forest {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String proprietaire;
	private String situation_geographique;
	private String zonage_reglementaire;
	private String droit_usage;


	private String region_forestiere;
	private String relief;
	private String climat;
	private String temperature;
	private String geologie;
	private Boolean manage_parcelle_forestiere;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getManage_parcelle_forestiere() {
		return manage_parcelle_forestiere;
	}
	public void setManage_parcelle_forestiere(Boolean manage_parcelle_forestiere) {
		this.manage_parcelle_forestiere = manage_parcelle_forestiere;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getSituation_geographique() {
		return situation_geographique;
	}
	public void setSituation_geographique(String situation_geographique) {
		this.situation_geographique = situation_geographique;
	}
	public String getZonage_reglementaire() {
		return zonage_reglementaire;
	}
	public void setZonage_reglementaire(String zonage_reglementaire) {
		this.zonage_reglementaire = zonage_reglementaire;
	}
	public String getDroit_usage() {
		return droit_usage;
	}
	public void setDroit_usage(String droit_usage) {
		this.droit_usage = droit_usage;
	}
	public String getRegion_forestiere() {
		return region_forestiere;
	}
	public void setRegion_forestiere(String region_forestiere) {
		this.region_forestiere = region_forestiere;
	}
	public String getRelief() {
		return relief;
	}
	public void setRelief(String relief) {
		this.relief = relief;
	}
	public String getClimat() {
		return climat;
	}
	public void setClimat(String climat) {
		this.climat = climat;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getGeologie() {
		return geologie;
	}
	public void setGeologie(String geologie) {
		this.geologie = geologie;
	}
	
	   @OneToMany 	  
	
	private Set<ParcelleForestiere> parcelleforestieres;


	public Set<ParcelleForestiere> getParcelleforestieres() {
		return parcelleforestieres;
	}
	public void setParcelleforestieres(Set<ParcelleForestiere> parcelleforestieres) {
		this.parcelleforestieres = parcelleforestieres;
	}
	
	
}
