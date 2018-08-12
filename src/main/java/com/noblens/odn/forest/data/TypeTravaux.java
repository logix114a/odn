package com.noblens.odn.forest.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TypeTravaux {
	 @Id
	    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "type_travaux")
	    @SequenceGenerator(name="type_travaux", sequenceName = "type_trav", allocationSize=50)
	    @Column(name = "id", updatable = false, nullable = false)
		private Long id;
		private String nom;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
}
