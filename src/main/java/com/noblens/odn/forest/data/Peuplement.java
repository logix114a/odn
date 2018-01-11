package com.noblens.odn.forest.data;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Peuplement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    private Boolean status;
    private String essence;
    private String commentaire;
    private Set<Programmation_travaux> programmation_travaux;
    private Objectif_Sylvicole objectif_sylvicole;
    private Date plantation_dttm;
    private Date Created_dttm;
    public Date getPlantation_dttm() {
		return plantation_dttm;
	}

	public void setPlantation_dttm(Date plantation_dttm) {
		this.plantation_dttm = plantation_dttm;
	}

	public Date getCreated_dttm() {
		return Created_dttm;
	}

	public void setCreated_dttm(Date created_dttm) {
		Created_dttm = created_dttm;
	}

	public String getCreated_source() {
		return Created_source;
	}

	public void setCreated_source(String created_source) {
		Created_source = created_source;
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
	private String Created_source;
    private Date Last_updated_dttm;
    private String Last_updated_source;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@OneToMany 
	private Set<TypePeuplement> typepeuplements;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<TypePeuplement> getTypepeuplements() {
		return typepeuplements;
	}
	public void setTypepeuplements(Set<TypePeuplement> typepeuplements) {
		this.typepeuplements = typepeuplements;
	}
	
}
