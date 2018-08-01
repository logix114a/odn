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
public class ParcelleCadastrale {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "parcelle_cadastrale")
    @SequenceGenerator(name="parcelle_cadastrale", sequenceName = "par_cad", allocationSize=50)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String commune;
	private String section;
	private String numero_parcelle;
	private String lieu_dit;
	private Double surface;

	
	/* @ManyToOne
	@JoinColumn(name="forest_id")
	private Forest forest;
*/

	@OneToMany(mappedBy="parcellecadastrale")
	private Set<Peuplement> peuplements = new HashSet<Peuplement>();
;
	public Set<Peuplement> getPeuplements() {
		return peuplements;
	}

	public void setPeuplements(Set<Peuplement> peuplements) {
		this.peuplements = peuplements;
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
	public String getNumero_parcelle() {
		return numero_parcelle;
	}
	public void setNumero_parcelle(String numero_parcelle) {
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
