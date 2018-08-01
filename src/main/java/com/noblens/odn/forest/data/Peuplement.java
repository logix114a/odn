package com.noblens.odn.forest.data;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Peuplement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    private Boolean status;

    public Date getCreated_dttm() {
		return Created_dttm;
	}

	public void setCreated_dttm(Date created_dttm) {
		Created_dttm = created_dttm;
	}
	
	private String essence;
    private String commentaire;
    private String Created_source;
    private Date Created_dttm;
    private Date Last_updated_dttm;
    private String Last_updated_source;
  
    
   // private Set<Programmation_travaux> programmation_travaux;
    @ManyToOne (cascade=CascadeType.ALL) 
    private ParcelleCadastrale parcellecadastrale;
    public ParcelleCadastrale getParcellecadastrale() {
		return parcellecadastrale;
	}

	public void setParcellecadastrale(ParcelleCadastrale parcellecadastrale) {
		this.parcellecadastrale = parcellecadastrale;
	}

	public String getEssence() {
		return essence;
	}

	public void setEssence(String essence) {
		this.essence = essence;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

/*	public Set<Programmation_travaux> getProgrammation_travaux() {
		return programmation_travaux;
	}

	public void setProgrammation_travaux(Set<Programmation_travaux> programmation_travaux) {
		this.programmation_travaux = programmation_travaux;
	}

	public Objectif_Sylvicole getObjectif_sylvicole() {
		return objectif_sylvicole;
	}

	public void setObjectif_sylvicole(Objectif_Sylvicole objectif_sylvicole) {
		this.objectif_sylvicole = objectif_sylvicole;
	}
	private Objectif_Sylvicole objectif_sylvicole;*/
    private Date plantation_dttm;
   
    public Date getPlantation_dttm() {
		return plantation_dttm;
	}

	public void setPlantation_dttm(Date plantation_dttm) {
		this.plantation_dttm = plantation_dttm;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne (cascade={CascadeType.PERSIST}) 
	private TypePeuplement typepeuplement;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public TypePeuplement getTypepeuplements() {
		return typepeuplement;
	}
	public void setTypepeuplements(TypePeuplement typepeuplement) {
		this.typepeuplement = typepeuplement;
	}
	
}
