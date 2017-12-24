package com.noblens.odn.forest.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ForestArea {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
 
	
}
