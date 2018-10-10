package com.noblens.odn.forest.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProgrammationRepository extends CrudRepository<Programmation, Long>{

	//List<Programmation> findByPrevision(Date Prevision);

}
