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
	private String numeroparcelle;
	private String lieu_dit;
	private Double surface;
	private String Created_source;
	private Date Created_dttm;
    private Date Last_updated_dttm;
    private String Last_updated_source;
	
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

	public String getNumeroparcelle() {
		return numeroparcelle;
	}

	public void setNumeroparcelle(String numeroparcelle) {
		this.numeroparcelle = numeroparcelle;
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
