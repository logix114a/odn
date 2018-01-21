package com.noblens.odn.forest.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Programmation{
	 @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
}
