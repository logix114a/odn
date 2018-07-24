package com.noblens.odn.forest.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class ParcelleForestiere {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "parcelle_forestiere")
    @SequenceGenerator(name="parcelle_forestiere", sequenceName = "par_for", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String numero;
	   public Set<ParcelleCadastrale> getParcellecadastrales() {
		return parcellecadastrales;
	}
	public void setParcellecadastrales(Set<ParcelleCadastrale> parcellecadastrales) {
		this.parcellecadastrales = parcellecadastrales;
	}
	@OneToMany(cascade={CascadeType.PERSIST}) 
	private Set<ParcelleCadastrale> parcellecadastrales = new HashSet<ParcelleCadastrale>();

	@OneToMany 
	private Set<StationForestiere> stationforestieres;
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
	
}
