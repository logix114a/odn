package com.noblens.odn.forest.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PeuplementRepository extends CrudRepository<Peuplement, Long>{

	/*  @Query("select p.id from peuplement p inner join parcelle_cadastrale pc on p.parcellecadastrale_id = pc.id\r\n" + 
	  		"inner join parcelle_forestiere_parcellecadastrales pfp on pfp.parcellecadastrales_id = pc.id\r\n" + 
	  		"where p.unite_forestiere=:unite_forestiere and parcelle_forestiere_id=:parcelle_forestiere")
	    List<String> fetchArticles(@Param("unite_forestiere") String unite_forestiere, @Param("parcelle_forestiere") String parcelle_forestiere);
*/
	
	
	List<Peuplement> findByCommentaire(String commentaire);
	List<Peuplement> findByDescription(String description);
	List<Peuplement> findByParcellecadastrale(ParcelleCadastrale parcellecadastrale);
	List<Peuplement> findByParcellecadastraleAndEssence(ParcelleCadastrale parcellecadastrale, Essence essence);
	List<Peuplement> findByUniteforestiere(String unite_forestiere);
	Peuplement findByUniteforestiereAndParcellecadastrale(String unite_forestiere,ParcelleCadastrale parcellecadastrale);
}

