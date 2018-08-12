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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
public class Peuplement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    private Boolean status;
    @ManyToOne (cascade={CascadeType.PERSIST}) 
	private Essence essence;
	@ManyToOne (cascade={CascadeType.PERSIST}) 
	private TypePeuplement typepeuplement;
    @ManyToOne  
    private ParcelleCadastrale parcellecadastrale;
    @ManyToMany
    private Set<Programmation> programmation = new HashSet<Programmation>();;
	private String unite_forestiere;
    private String commentaire;
    private String description;
    private String Created_source;
	private Date Created_dttm;
    private Date Last_updated_dttm;
    private String Last_updated_source;
    private Date plantation_dttm;
    private String Close_source;
    private Date close_dttm;
    private Double surface;
	public Double getSurface() {
		return surface;
	}

	public void setSurface(Double surface) {
		this.surface = surface;
	}

	public Set<Programmation> getProgrammation() {
		return programmation;
	}

	public void setProgrammation(Set<Programmation> programmation) {
		this.programmation = programmation;
	}

	public Date getClose_dttm() {
		return close_dttm;
	}

	public void setClose_dttm(Date close_dttm) {
		this.close_dttm = close_dttm;
	}

	public TypePeuplement getTypepeuplement() {
		return typepeuplement;
	}
	
	
    public void setEssence(Essence essence) {
		this.essence = essence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClose_source() {
		return Close_source;
	}

	public String getUnite_forestiere() {
		return unite_forestiere;
	}

	public void setUnite_forestiere(String unite_forestiere) {
		this.unite_forestiere = unite_forestiere;
	}

	public void setClose_source(String close_source) {
		Close_source = close_source;
	}
	
	public void setTypepeuplement(TypePeuplement typepeuplement) {
		this.typepeuplement = typepeuplement;
	}
    
    public Date getCreated_dttm() {
		return Created_dttm;
	}

	public void setCreated_dttm(Date created_dttm) {
		Created_dttm = created_dttm;
	}
	
    public ParcelleCadastrale getParcellecadastrale() {
		return parcellecadastrale;
	}

	public void setParcellecadastrale(ParcelleCadastrale parcellecadastrale) {
		this.parcellecadastrale = parcellecadastrale;
	}

	public Essence getEssence() {
		return essence;
	}

	public void Essence(Essence essence) {
		this.essence = essence;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
   
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
