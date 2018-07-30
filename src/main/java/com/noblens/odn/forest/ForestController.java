package com.noblens.odn.forest;


import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noblens.odn.forest.data.Forest;

import com.noblens.odn.forest.data.ForestRepository;
import com.noblens.odn.forest.data.ParcelleCadastrale;
import com.noblens.odn.forest.data.ParcelleCadastraleRepository;
import com.noblens.odn.forest.data.ParcelleForestiere;
import com.noblens.odn.forest.data.ParcelleForestiereRepository;
import com.noblens.odn.forest.data.Peuplement;
import com.noblens.odn.forest.data.PeuplementRepository;
import com.noblens.odn.forest.data.StationForestiere;
import com.noblens.odn.forest.data.StationForestiereRepository;
import com.noblens.odn.forest.data.TypePeuplement;
import com.noblens.odn.forest.data.TypePeuplementRepository;
import com.noblens.odn.forest.misc.StorageService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.noblens.odn.forest.misc.StorageFileNotFoundException;



@Controller
@RequestMapping("/forest")
public class ForestController {
	   private StorageService storageService;
	   
		@Autowired
	    public void FileUploadController(StorageService storageService) {
	        this.storageService = storageService;
	    }
	
	@Autowired
	private ForestRepository forestRepository;
	@Autowired
	private ParcelleCadastraleRepository parcellecadastraleRepository;
	@Autowired
	private ParcelleForestiereRepository parcelleforestiereRepository;	
	@Autowired
	private TypePeuplementRepository typepeuplementRepository;	
	@Autowired
	private StationForestiereRepository stationforestiereRepository;	
	@Autowired
	private PeuplementRepository peuplementRepository;	
	

	
	    @ExceptionHandler(StorageFileNotFoundException.class)
	    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
	        return ResponseEntity.notFound().build();
	    }
	private static final Logger logger = LoggerFactory.getLogger(ForestController.class);
	@GetMapping("")
	public ModelAndView home() {
		return new ModelAndView("forest/home");
	}
	

	
	@GetMapping(path="forestlist")
public ModelAndView listall() {
	Iterable<Forest> forests = this.forestRepository.findAll();
	return new ModelAndView("forest/forestlist", "forests", forests);
}	

	
	@GetMapping(path="forestadd") // Map ONLY GET Requests
	public ModelAndView forestadd(Forest forest) {
		return new ModelAndView("forest/forestadd");
	}
	
	@PostMapping(path="forestadd") 
	public ModelAndView forestadd1(@Valid Forest forest, BindingResult result,
			RedirectAttributes redirect) {
		//forest.setName("lateteatoto");
		forest = this.forestRepository.save(forest);
		
		return new ModelAndView("redirect:forestlist");
	}
	
	@GetMapping(path="forestmodify/{id}") // Map ONLY GET Requests
	public ModelAndView forestmodify(@PathVariable("id") Forest forest) {
		return new ModelAndView("forest/forestmodify", "forest", forest);
	}
	
	@PostMapping(path="forestmodify") 
	public ModelAndView forestamodify1(@Valid Forest forest, BindingResult result,
			RedirectAttributes redirect) {
		//forest.setName("lateteatoto");
		forest = this.forestRepository.save(forest);
		
		return new ModelAndView("forest/forestlist" );
	}
	
	
	@GetMapping(path="forestview/{id}")
public ModelAndView viewforest(@PathVariable("id") Forest forest) {
	return new ModelAndView("forest/forestview", "forest", forest);
}


	@GetMapping(path="parcellecadastraleadd") // Map ONLY GET Requests
	public ModelAndView parcellecadastraleadd1(ParcelleCadastrale parcellecadastrale) {
		return new ModelAndView("forest/parcellecadastraleadd");
	}	

	@PostMapping(path="parcellecadastraleadd") 
	public ModelAndView parcellecadastraleadd(@Valid ParcelleCadastrale parcellecadastrale, BindingResult result,
			RedirectAttributes redirect) {
		parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);
		return new ModelAndView("redirect:parcellecadastralelist");
	}
	
	@GetMapping(path="parcellecadastralelist")
	public ModelAndView parcellecadastralelist() {
		Iterable<ParcelleCadastrale> parcellecadastrales = this.parcellecadastraleRepository.findAll();
		return new ModelAndView("forest/parcellecadastralelist", "parcellecadastrales", parcellecadastrales);
	}
	
	@GetMapping(path="parcellecadastraleview/{id}")
public ModelAndView parcellecadastraleview(@PathVariable("id") ParcelleCadastrale parcellecadastrale) {
		
	//Forest forest  = new Forest();
	//forest.setId(id);	
		logger.debug(parcellecadastrale.getLieu_dit());
		logger.debug(parcellecadastrale.getCommune());
		
		 for (Iterator<Peuplement> it = parcellecadastrale.getPeuplements().iterator(); it.hasNext(); ) {
			 Peuplement f = it.next();
			/* for (Iterator<TypePeuplement> it1 = f.getTypepeuplements().iterator(); it1.hasNext(); ) {
				 TypePeuplement f1 = it1.next();
				 logger.debug("-- olivier dsqdsqdsqdsq");	
				 logger.debug(f1.getNom());
			 }
		 */
			  logger.debug("-- olivier addd");
		    }
		
		//logger.debug(parcellecadastrale.getPeuplements().);
	return new ModelAndView("forest/parcellecadastraleview", "parcellecadastrale", parcellecadastrale);
}
	
	@GetMapping(path="parcellecadastralemodify/{id}") // Map ONLY GET Requests
	public ModelAndView parcellecadastralemodify(@PathVariable("id") ParcelleCadastrale parcellecadastrale) {
		return new ModelAndView("forest/parcellecadastralemodify", "parcellecadastrale", parcellecadastrale);
	}
	
	@PostMapping(path="parcellecadastralemodify") 
	public ModelAndView parcellecadastralemodify(@Valid ParcelleCadastrale parcellecadastrale, BindingResult result,
			RedirectAttributes redirect) {
		//forest.setName("lateteatoto");
		Optional<ParcelleCadastrale> parcellecadastrales = this.parcellecadastraleRepository.findById(parcellecadastrale.getId());
		ParcelleCadastrale parcellecadastrale1 = parcellecadastrales.get();
		parcellecadastrale.setPeuplements(parcellecadastrale1.getPeuplements());
	 
		
		
		
		parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);
		
		return new ModelAndView("redirect:parcellecadastralelist");
	}	
	@GetMapping(path="parcelleforestiereadd") // Map ONLY GET Requests
	public ModelAndView parcelleforestiereadd1(ParcelleForestiere parcelleforestiere	) {
		return new ModelAndView("forest/parcelleforestiereadd");
	}	

	@PostMapping(path="parcelleforestiereadd") 
	public ModelAndView parcelleforestiereadd(@Valid ParcelleForestiere parcelleforestiere, BindingResult result,
			RedirectAttributes redirect) {
		
		parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
		Iterable<ParcelleForestiere> parcellesforestieres = this.parcelleforestiereRepository.findAll();
		return new ModelAndView("forest/parcelleforestierelist", "parcelleforestieres", parcellesforestieres);
	}
	
	@GetMapping(path="parcelleforestierelist")
	public ModelAndView parcelleforestierelist() {
		Iterable<ParcelleForestiere> parcelleforestieres = this.parcelleforestiereRepository.findAll();
		return new ModelAndView("forest/parcelleforestierelist", "parcelleforestieres", parcelleforestieres);
	}
	
	@GetMapping(path="parcelleforestiereview/{id}")
public ModelAndView parcelleforestiereview(@PathVariable("id") ParcelleForestiere parcelleforestiere) {
		
	return new ModelAndView("forest/parcelleforestiereview", "parcelleforestiere", parcelleforestiere);
}	
	@GetMapping(path="parcelleforestieremodify/{id}") // Map ONLY GET Requests
	public ModelAndView parcelleforestieremodify(@PathVariable("id") ParcelleForestiere parcelleforestiere) {
		return new ModelAndView("forest/parcelleforestieremodify", "parcelleforestiere", parcelleforestiere);
	}
	
	@PostMapping(path="parcelleforestieremodify") 
	public ModelAndView parcelleforestieremodify(@Valid ParcelleForestiere parcelleforestiere, BindingResult result,
			RedirectAttributes redirect) {
		//forest.setName("lateteatoto");
		Optional<ParcelleForestiere> parcelleforestieres = this.parcelleforestiereRepository.findById(parcelleforestiere.getId());
		ParcelleForestiere parcelleforestier1 = parcelleforestieres.get();
		parcelleforestiere.setParcellecadastrales(parcelleforestier1.getParcellecadastrales());
		parcelleforestiere.setStationforestieres(parcelleforestier1.getStationforestieres());
	 
		
		parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
		
		return new ModelAndView("redirect:parcelleforestierelist");
	}	
	
	@GetMapping(path="typepeuplementadd") // Map ONLY GET Requests
	public ModelAndView typepeuplementadd1(TypePeuplement typepeuplement	) {
		return new ModelAndView("forest/typepeuplementadd");
	}	

	@PostMapping(path="typepeuplementadd") 
	public ModelAndView typepeuplementadd(@Valid TypePeuplement typepeuplement, BindingResult result,
			RedirectAttributes redirect) {
		typepeuplement = this.typepeuplementRepository.save(typepeuplement);
		Iterable<TypePeuplement> typepeuplements = this.typepeuplementRepository.findAll();
		return new ModelAndView("forest/typepeuplementlist", "typepeuplements", typepeuplements);
	}
	
	@GetMapping(path="typepeuplementlist")
	public ModelAndView typepeuplementlist() {
		Iterable<TypePeuplement> typepeuplements = this.typepeuplementRepository.findAll();
		return new ModelAndView("forest/typepeuplementlist", "typepeuplements", typepeuplements);
	}
	
	@GetMapping(path="typepeuplementview/{id}")
public ModelAndView typepeuplementview(@PathVariable("id") TypePeuplement typepeuplement) {
		
	//Forest forest  = new Forest();
	//forest.setId(id);	
	return new ModelAndView("forest/typepeuplementview", "typepeuplement", typepeuplement);
}	

	
	@GetMapping(path="stationforestiereadd") // Map ONLY GET Requests
	public ModelAndView stationforestiereadd1(StationForestiere stationforestiere	) {
		return new ModelAndView("forest/stationforestiereadd");
	}	

	@PostMapping(path="stationforestiereadd") 
	public ModelAndView stationforestiereadd(@Valid StationForestiere stationForestiere, BindingResult result,
			RedirectAttributes redirect) {
		logger.debug("--Test ODN ODN ODN ODN --");
		logger.debug(stationForestiere.getNom());	
		stationForestiere = this.stationforestiereRepository.save(stationForestiere);
	return new ModelAndView("redirect:stationforestierelist");
	}
	
	@GetMapping(path="stationforestierelist")
	public ModelAndView stationforestierelist() {
		Iterable<StationForestiere> stationforestieres = this.stationforestiereRepository.findAll();
		return new ModelAndView("forest/stationforestierelist", "stationforestieres", stationforestieres);
	}
	
	@GetMapping(path="stationforestiereview/{id}")
public ModelAndView stationforestiereview(@PathVariable("id") StationForestiere stationforestiere) {
		
	//Forest forest  = new Forest();
	//forest.setId(id);	
	return new ModelAndView("forest/stationforestiereview", "stationforestiere", stationforestiere);
}	
	
	
	@GetMapping(path="forestareaassignparfor/{id}") // Map ONLY GET Requests
	public ModelAndView forestareaassignparcad(@PathVariable("id") Forest forest) {	
			ModelAndView test = new ModelAndView("forest/forestareaassignparfor");
			test.addObject("forest", forest);
			Iterable<ParcelleForestiere> parcellesforestieress = this.parcelleforestiereRepository.findAll();
			test.addObject("parcellesforestieress", parcellesforestieress);
		return  test;
	}	

	@PostMapping(path="forestareaassignparfor") // Map ONLY GET Requests
	public ModelAndView forestareaassignparcad1(@Valid Forest forest, BindingResult result,
			RedirectAttributes redirect) {
		Optional<Forest> forests = this.forestRepository.findById(forest.getId());
		Forest forest1 = forests.get();
		forest1.setParcelleforestieres(forest.getParcelleforestieres());
		logger.debug("--Test ODN --");
		logger.debug(forest.getName());	
		
		forest = this.forestRepository.save(forest1);

		return new ModelAndView("redirect:forestlist");
	}	
	
	
	@GetMapping(path="parforassignparcad/{id}") // Map ONLY GET Requests
	public ModelAndView forestareaassignparfor(@PathVariable("id") ParcelleForestiere parcelleforestiere) {	
			ModelAndView test = new ModelAndView("forest/parforassignparcad");
			test.addObject("parcelleforestiere", parcelleforestiere);
			Iterable<ParcelleCadastrale> parcellescadastraless = this.parcellecadastraleRepository.findAll();
			test.addObject("parcellescadastraless", parcellescadastraless);
		return  test;
	}	

	@PostMapping(path="parforassignparcad") // Map ONLY GET Requests
	public ModelAndView forestareaassignparfor1(@Valid ParcelleForestiere parcelleforestiere, BindingResult result,
			RedirectAttributes redirect) {
		logger.debug("--Test ODN --");
		logger.debug(parcelleforestiere.getNumero());	
		Optional<ParcelleForestiere> parcelleforestieres = this.parcelleforestiereRepository.findById(parcelleforestiere.getId());
		ParcelleForestiere parcelleforestiere1 = parcelleforestieres.get();
		parcelleforestiere1.setParcellecadastrales(parcelleforestiere.getParcellecadastrales());
		
		parcelleforestiere1 = this.parcelleforestiereRepository.save(parcelleforestiere1);

		return new ModelAndView("redirect:parcelleforestierelist");
	}	

	@GetMapping(path="parforassignstafor/{id}") // Map ONLY GET Requests
	public ModelAndView parforassignstafor(@PathVariable("id") ParcelleForestiere parcelleforestiere) {	
			ModelAndView test = new ModelAndView("forest/parforassignstafor");
			test.addObject("parcelleforestiere", parcelleforestiere);
			Iterable<StationForestiere> stationforestieres = this.stationforestiereRepository.findAll();
			test.addObject("stationforestieres", stationforestieres);
		return  test;
	}	

	@PostMapping(path="parforassignstafor") // Map ONLY GET Requests
	public ModelAndView parforassignstafor(@Valid ParcelleForestiere parcelleforestiere, BindingResult result,
			RedirectAttributes redirect) {
		logger.debug("--Test ODN --");
		logger.debug(parcelleforestiere.getNumero());	
		Optional<ParcelleForestiere> parcelleforestieres = this.parcelleforestiereRepository.findById(parcelleforestiere.getId());
		ParcelleForestiere parcelleforestiere1 = parcelleforestieres.get();
		parcelleforestiere1.setStationforestieres(parcelleforestiere.getStationforestieres());
		
		parcelleforestiere1 = this.parcelleforestiereRepository.save(parcelleforestiere1);
		return new ModelAndView("redirect:parcelleforestierelist");
	}	
	
	@GetMapping(path="peuplementaddtoparcad/{id}") // Map ONLY GET Requests
	public ModelAndView parcadassignpeu(@PathVariable("id") Long id,Peuplement peuplement) {	
			ModelAndView test = new ModelAndView("forest/peuplementaddtoparcad");
			test.addObject("peuplement", peuplement);
			test.addObject("id", id);
		return  test;
	}	

	@PostMapping(path="peuplementaddtoparcad/{id}") // Map ONLY GET Requests
	public ModelAndView parcadassignpeu(@PathVariable("id") Long id,@Valid Peuplement peuplement, BindingResult result,
			RedirectAttributes redirect) {
		logger.debug("--Test ODN : get id--");
		
		logger.debug(Long.toString(id));
		//logger.debug(peuplement.getParcellecadastrale().getId());
		Optional<ParcelleCadastrale> parcellescadastrales = this.parcellecadastraleRepository.findById(id);
		ParcelleCadastrale parcellecadastrale =  parcellescadastrales.get();
		java.util.Date date_util = new java.util.Date();
		peuplement.setCreated_dttm(new java.sql.Date(date_util.getTime()));
		peuplement.setLast_updated_dttm(new java.sql.Date(date_util.getTime()));
		peuplement.setCreated_source("USER_ID");
		peuplement.setLast_updated_source("USER_ID");
		peuplement.setStatus(true);
		peuplement.setParcellecadastrale(parcellecadastrale);
		peuplement = this.peuplementRepository.save(peuplement);
		//TODO: Save the parcelle cadastrale
		/*Optional<ParcelleCadastrale> parcellescadastrales = this.parcellecadastraleRepository.findById(id);
		ParcelleCadastrale parcellecadastrale =  parcellescadastrales.get();
		/*parcellecadastrale.setPeuplement(peuplement);
		parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);*/
		return new ModelAndView("redirect:/forest/parcellecadastralelist");
	}	
	
	@GetMapping(path="dataloader")
	public ModelAndView dataloaderupload() {
		
		
		
		return new ModelAndView("forest/dataloader","test",storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(ForestController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
	}
	
	 @GetMapping("/files/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

	        Resource file = storageService.loadAsResource(filename);
	        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	    }
	
    @PostMapping("dataloader")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
    	logger.debug("--Test ODN : get id--");
    	logger.debug(file.getName());
    	//BufferedReader br = null;
		//StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			
			//INTEGRATION STATION FORESTIERE
			/*Sheet datatypeSheet = workbook.getSheet("stationforestiere");
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
        StationForestiere stationforestiere = new StationForestiere();
        stationforestiere.setNom(currentRow.getCell(1).getStringCellValue());
        stationforestiere.setDescription(currentRow.getCell(2).getStringCellValue());
        stationforestiere.setCaracteristique_sol(currentRow.getCell(3).getStringCellValue());
        stationforestiere.setPeuplement_naturel(currentRow.getCell(4).getStringCellValue());
        stationforestiere = this.stationforestiereRepository.save(stationforestiere);
           }
        */
        
      //INTEGRATION FOREST
      		Sheet datatypeSheet1 = workbook.getSheet("forest");
              Iterator<Row> iterator1 = datatypeSheet1.iterator();
              iterator1.next();
              while (iterator1.hasNext()) {
           Row currentRow1 = iterator1.next();
           logger.debug("--Test ODN 0099");
           logger.debug(currentRow1.getCell(1).getStringCellValue() );
           if (!currentRow1.getCell(1).getStringCellValue().isEmpty()) {
        	   logger.debug("--Test ODN 9998");
        
        	   
          Forest forest = new Forest();
          forest.setName(currentRow1.getCell(1).getStringCellValue()); //Nom
          forest.setProprietaire(currentRow1.getCell(2).getStringCellValue()); //Propriétaire
          forest.setSituation_geographique(currentRow1.getCell(3).getStringCellValue()); //situation geographique
          forest.setZonage_reglementaire(currentRow1.getCell(4).getStringCellValue()); //zonage reglementaire
          forest.setDroit_usage(currentRow1.getCell(5).getStringCellValue()); //droit usage
          forest.setRegion_forestiere(currentRow1.getCell(6).getStringCellValue()); //region forestiere
          forest.setRelief(currentRow1.getCell(7).getStringCellValue()); //relief
          forest.setClimat(currentRow1.getCell(8).getStringCellValue()); //climat
          forest.setTemperature(currentRow1.getCell(9).getStringCellValue()); //temperature
          forest.setGeologie(currentRow1.getCell(10).getStringCellValue()); //geologie
          //forest.setManage_parcelle_forestiere(currentRow1.getCell(11).getBooleanCellValue()); //manage parcelle forestiere
          forest = this.forestRepository.save(forest);
           }
           logger.debug("--Test ODN 9997");
         }
        
           
              
              //INTEGRATION PARCELLE FORESTIERE
        		Sheet datatypeSheet2 = workbook.getSheet("parcelle_forestiere");
                Iterator<Row> iterator2 = datatypeSheet2.iterator();
                iterator2.next();
                while (iterator2.hasNext()) {
             Row currentRow1 = iterator2.next();
             logger.debug("--Test ODN 9966");
             //logger.debug(curre	ntRow1.getCell(1).getStringCellValue() );
             if (!currentRow1.getCell(1).getStringCellValue().isEmpty()) {
          	   
          
          	   
            ParcelleForestiere parcelle_forestiere = new ParcelleForestiere();
            parcelle_forestiere.setNumero(currentRow1.getCell(1).getStringCellValue()); //Numéro
            parcelle_forestiere.setDescription(currentRow1.getCell(2).getStringCellValue());
            //forest.setManage_parcelle_forestiere(currentRow1.getCell(11).getBooleanCellValue()); //manage parcelle forestiere
            parcelle_forestiere = this.parcelleforestiereRepository.save(parcelle_forestiere);
             }
                }
              

                
                
                //INTEGRATION PARCELLE CADASTRALE
          		Sheet datatypeSheet3 = workbook.getSheet("parcelle_cadastrale");
                  Iterator<Row> iterator3 = datatypeSheet3.iterator();
                  iterator3.next();
                  while (iterator3.hasNext()) {
               Row currentRow1 = iterator3.next();
               logger.debug("--Test ODN 9966");
               //logger.debug(currentRow1.getCell(1).getStringCellValue() );
               if (!currentRow1.getCell(1).getStringCellValue().isEmpty()) {
            	   
            
            	   
              ParcelleCadastrale parcelle_cadastrale = new ParcelleCadastrale();
              parcelle_cadastrale.setCommune(currentRow1.getCell(1).getStringCellValue()); //Commune
              parcelle_cadastrale.setSection(currentRow1.getCell(2).getStringCellValue()); //Section
              DataFormatter formatter = new DataFormatter();
              Cell cell = currentRow1.getCell(3);
              String var_name = formatter.formatCellValue(cell);
              
              parcelle_cadastrale.setNumero_parcelle(var_name); //Numero
              parcelle_cadastrale.setLieu_dit(currentRow1.getCell(4).getStringCellValue()); //Lieu-dit
              parcelle_cadastrale.setSurface(currentRow1.getCell(5).getNumericCellValue()); //Surface
              //forest.setManage_parcelle_forestiere(currentRow1.getCell(11).getBooleanCellValue()); //manage parcelle forestiere
              parcelle_cadastrale = this.parcellecadastraleRepository.save(parcelle_cadastrale);
               }
              }
                
                
              
                  
                  //INTEGRATION TYPE PEUPLEMENT
            		Sheet datatypeSheet4 = workbook.getSheet("type_peuplement");
                    Iterator<Row> iterator4 = datatypeSheet4.iterator();
                    iterator4.next();
                    while (iterator4.hasNext()) {
                 Row currentRow1 = iterator4.next();
                 logger.debug("--Test ODN 9966");
                 //logger.debug(currentRow1.getCell(1).getStringCellValue() );
                 if (!currentRow1.getCell(1).getStringCellValue().isEmpty()) {
              	   
              
              	   
                TypePeuplement type_peuplement = new TypePeuplement();

                
                type_peuplement.setNom(currentRow1.getCell(1).getStringCellValue()); //Numero

                type_peuplement = this.typepeuplementRepository.save(type_peuplement);
                 }
                }
                      
  
                    
                    //INTEGRATION STATION 
              		Sheet datatypeSheet5 = workbook.getSheet("station_forestiere");
                      Iterator<Row> iterator5 = datatypeSheet5.iterator();
                      iterator5.next();
                      while (iterator5.hasNext()) {
                   Row currentRow1 = iterator5.next();
                   logger.debug("--Test ODN 9966");
                   //logger.debug(currentRow1.getCell(1).getStringCellValue() );
                   if (!currentRow1.getCell(1).getStringCellValue().isEmpty()) {
                	   
                
                	   
                	   StationForestiere station_forestiere = new StationForestiere();                  
                	   station_forestiere.setNom(currentRow1.getCell(1).getStringCellValue()); //Numero
                	   station_forestiere.setDescription(currentRow1.getCell(2).getStringCellValue()); //Numero
                	   station_forestiere.setCaracteristique_sol(currentRow1.getCell(3).getStringCellValue()); //Numero
                	   station_forestiere.setPeuplement_naturel(currentRow1.getCell(4).getStringCellValue()); //Numero
                	   station_forestiere = this.stationforestiereRepository.save(station_forestiere);
                   }
                  }
  //REPARTITION FORET
               
                      
                      
                      
                      //REPARTITION PEUPLEMENT 
                		Sheet datatypeSheet6 = workbook.getSheet("repartition_peuplement");
                        Iterator<Row> iterator6 = datatypeSheet6.iterator();
                        iterator6.next();
                        while (iterator6.hasNext()) {
                     Row currentRow1 = iterator6.next();
                     logger.debug("--Test ODN 123");
  
                   	//Foret
                	 DataFormatter formatter3 = new DataFormatter();
                   Cell cell3 = currentRow1.getCell(0);
                   String var_name3 = formatter3.formatCellValue(cell3);
                   Long test3 = new Long(var_name3);       
                   logger.debug("-- parcellecadastrales1");
                	 Optional<Forest> forest = this.forestRepository.findById(test3);
                     
//Parcelle forestiere
                  	 DataFormatter formatter1 = new DataFormatter();
                     Cell cell = currentRow1.getCell(2);
                     String var_name = formatter1.formatCellValue(cell);
                     Long test = new Long(var_name);             
                     logger.debug("-- parcelle forestiere");
                  	 Optional<ParcelleForestiere> parcelleforestieres1 = this.parcelleforestiereRepository.findById(test);
        
 
                  	//Parcelle cadastrale
                    	 DataFormatter formatter2 = new DataFormatter();
                       Cell cell2 = currentRow1.getCell(1);
                       String var_name2 = formatter1.formatCellValue(cell2);
                       Long test2 = new Long(var_name2);       
                       logger.debug("-- parcellecadastrales1");
                    	 Optional<ParcelleCadastrale> parcellecadastrales1 = this.parcellecadastraleRepository.findById(test2);
                    	
//SAVE   
                		ParcelleForestiere parcelleforestiere1 = parcelleforestieres1.get();
                		ParcelleCadastrale parcellecadastrale1 = parcellecadastrales1.get();
                		//Set<ParcelleCadastrale> test123 = parcelleforestieres1.get().getParcellecadastrales();
                		Set<ParcelleCadastrale> test123 = new HashSet<ParcelleCadastrale>();
                		
                	
                		  for (Iterator<ParcelleCadastrale> it = parcelleforestieres1.get().getParcellecadastrales().iterator(); it.hasNext(); ) {
                			  ParcelleCadastrale f = it.next();
                			  test123.add(f);
                			  logger.debug("-- olivier addd");
                		    }
                        
                		  
                
                		  Forest forest1 = forest.get();
                		test123.add(parcellecadastrales1.get());     
                		Set<ParcelleForestiere> test31 = new HashSet<ParcelleForestiere>();
                		test31.add(parcelleforestiere1);
              		  for (Iterator<ParcelleForestiere> it = forest1.getParcelleforestieres().iterator(); it.hasNext(); ) {
              			ParcelleForestiere f = it.next();
              			test31.add(f);
            			  logger.debug("-- olivier addd");
            		    }
              		  
              		  // STATION FORESTIERE
              	  	 DataFormatter formatter8 = new DataFormatter();
                     Cell cell8 = currentRow1.getCell(7);
                     String var_name8 = formatter8.formatCellValue(cell8);
                     Long test8 = new Long(var_name8);       
                     logger.debug("-- parcellecadastrales1");
              		Optional<StationForestiere> stationforestieres1 = this.stationforestiereRepository.findById(test8);
                	
              	//SAVE   
              		
              		StationForestiere stationforestiere1 = stationforestieres1.get();
              	                	//	Set<ParcelleCadastrale> test1238 = parcelleforestieres1.get().getParcellecadastrales();
              	  Set<StationForestiere> test1238 = new HashSet<StationForestiere>();
              	                		
              		  for (Iterator<StationForestiere> it = parcelleforestieres1.get().getStationforestieres().iterator(); it.hasNext(); ) {
              			StationForestiere f = it.next();
              			test1238.add(f);
            			  logger.debug("-- olivier addd");
            		    }
              		  test1238.add(stationforestiere1);
                		
                		forest1.setParcelleforestieres(test31);
                		forest1 = this.forestRepository.save(forest1);
                		parcelleforestiere1.setParcellecadastrales(test123);
                		parcelleforestiere1.setStationforestieres(test1238);
                		parcelleforestiere1 = this.parcelleforestiereRepository.save(parcelleforestiere1);
                		 
                		// PEUPLEMENT
                		Peuplement peuplement = new Peuplement();
                		peuplement.setCreated_source("test");
                		//peuplement.setCreated_dttm(Date heure);
                		peuplement.setStatus(true);
                		peuplement.setParcellecadastrale(parcellecadastrale1);
                		parcelleforestiere1 = this.parcelleforestiereRepository.save(parcelleforestiere1);

                		//Type Peuplement
                   	 DataFormatter formatter5 = new DataFormatter();
                      Cell cell5 = currentRow1.getCell(5);
                      String var_name5 = formatter5.formatCellValue(cell5);
                      Long test5 = new Long(var_name5);       
                      logger.debug("-- parcellecadastrales1");
                   	 Optional<TypePeuplement> typepeuplemnt1 = this.typepeuplementRepository.findById(test5);
                   	
                   	TypePeuplement typepeuplemnt2 = typepeuplemnt1.get();
                   	 Set<TypePeuplement> test351 = new HashSet<TypePeuplement>();
                   	test351.add(typepeuplemnt1.get());
                		
                		
                		peuplement.setTypepeuplements(typepeuplemnt2);
                		peuplement = this.peuplementRepository.save(peuplement);
                		/*Set<Peuplement> peuplements = new HashSet<Peuplement>();
                		peuplements.add(peuplement);
                		parcellecadastrale1.setPeuplements(peuplements);
                		parcellecadastrale1 = this.parcellecadastraleRepository.save(parcellecadastrale1);
                    	*/
                		logger.debug("--Test ODN 456");
                     
                    }
                    
     
            
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        logger.debug("--Test ODN 2018");
        return "redirect:/forest/forestlist";
    }
	
	@GetMapping(path="programmationlist")
	public ModelAndView programmationlist() {
		
		return new ModelAndView("forest/programmationlist");
	}
	@GetMapping(path="operationsylvicolelist")
	public ModelAndView operationsylvicolelist() {
		
		return new ModelAndView("forest/operationsylvicolelist");
	}
	
	/*@GetMapping(path="parcadaddtoforest") // Map ONLY GET Requests
	public ModelAndView parcadaddtoforest(TypePeuplement typepeuplement) {
		return new ModelAndView("forest/parcadaddtoforest");
	}	

	@PostMapping(path="parcadaddtoforest") 
	public ModelAndView typepeuplementadd(@Valid TypePeuplement typepeuplement, BindingResult result,
			RedirectAttributes redirect) {
		typepeuplement = this.typepeuplementRepository.save(typepeuplement);
		Iterable<TypePeuplement> typepeuplements = this.typepeuplementRepository.findAll();
		return new ModelAndView("forest/parcadaddtoforest", "typepeuplements", typepeuplements);
	}	*/
}
