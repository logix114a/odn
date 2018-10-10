package com.noblens.odn.forest.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParcelleForestiereRepository extends PagingAndSortingRepository <ParcelleForestiere, Long>{

	public List<ParcelleForestiere> findAllByOrderByIdAsc();

}
