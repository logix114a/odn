package com.noblens.odn.forest.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TypePeuplement {
	 @Id
	    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "type_peuplement")
	    @SequenceGenerator(name="type_peuplement", sequenceName = "type_peup", allocationSize=50)
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
