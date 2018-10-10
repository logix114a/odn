package com.noblens.odn.forest.data;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ForestRepository extends CrudRepository<Forest, Long>{

	Iterable<Forest> findAll(Sort test);
	
}
