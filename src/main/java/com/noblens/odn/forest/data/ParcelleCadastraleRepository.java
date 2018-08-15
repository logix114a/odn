package com.noblens.odn.forest.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ParcelleCadastraleRepository  extends CrudRepository<ParcelleCadastrale, Long>{

	ParcelleCadastrale findByNumeroparcelle(String numeroparcelle);
	
}
