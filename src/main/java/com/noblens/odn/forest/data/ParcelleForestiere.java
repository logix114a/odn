package com.noblens.odn.forest.data;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ParcelleForestiere {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String numero;
	   public Set<ParcelleCadastrale> getParcellecadastrales() {
		return parcellecadastrales;
	}
	public void setParcellecadastrales(Set<ParcelleCadastrale> parcellecadastrales) {
		this.parcellecadastrales = parcellecadastrales;
	}
	@OneToMany 
	private Set<ParcelleCadastrale> parcellecadastrales;
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
