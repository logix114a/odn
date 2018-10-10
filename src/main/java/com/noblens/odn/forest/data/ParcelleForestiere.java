package com.noblens.odn.forest.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.OrderBy;

@Entity
public class ParcelleForestiere {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcelle_forestiere")
	@SequenceGenerator(name = "parcelle_forestiere", sequenceName = "par_for", allocationSize = 50)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String numero;
	private String description;
	private String pente;
	private String exposition;
	private String position;
	private String roche;
	private String texture;
	private String profondeur;
	private String Created_source;
	private Date Created_dttm;
    private Date Last_updated_dttm;
    private String Last_updated_source;

	@OneToMany(cascade = { CascadeType.PERSIST })
	@OrderBy(clause = "parcellecadastrales_id asc")  
	private Set<ParcelleCadastrale> parcellecadastrales = new HashSet<ParcelleCadastrale>();

	@ManyToMany(cascade = { CascadeType.PERSIST })
	private Set<StationForestiere> stationforestieres = new HashSet<StationForestiere>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ParcelleCadastrale> getParcellecadastrales() {
		return parcellecadastrales;
	}

	public void setParcellecadastrales(Set<ParcelleCadastrale> parcellecadastrales) {
		this.parcellecadastrales = parcellecadastrales;
	}

	public Set<StationForestiere> getStationforestieres() {
		return stationforestieres;
	}

	public void setStationforestieres(Set<StationForestiere> stationforestieres) {
		this.stationforestieres = stationforestieres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPente() {
		return pente;
	}

	public void setPente(String pente) {
		this.pente = pente;
	}

	public String getExposition() {
		return exposition;
	}

	public void setExposition(String exposition) {
		this.exposition = exposition;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRoche() {
		return roche;
	}

	public void setRoche(String roche) {
		this.roche = roche;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(String profondeur) {
		this.profondeur = profondeur;
	}

	public String getCreated_source() {
		return Created_source;
	}

	public void setCreated_source(String created_source) {
		Created_source = created_source;
	}

	public Date getCreated_dttm() {
		return Created_dttm;
	}

	public void setCreated_dttm(Date created_dttm) {
		Created_dttm = created_dttm;
	}

	public Date getLast_updated_dttm() {
		return Last_updated_dttm;
	}

	public void setLast_updated_dttm(Date last_updated_dttm) {
		Last_updated_dttm = last_updated_dttm;
	}

	public String getLast_updated_source() {
		return Last_updated_source;
	}

	public void setLast_updated_source(String last_updated_source) {
		Last_updated_source = last_updated_source;
	}

}
