package com.noblens.odn.forest.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Programmation{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    private String type;
    private Boolean status;
    private Date Prevision;
    private String Created_source;
	private Date Created_dttm;
    private Date Last_updated_dttm;
    private String Last_updated_source;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPrevision() {
		return Prevision;
	}

	public void setPrevision(Date prevision) {
		Prevision = prevision;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
