package com.noblens.odn.forest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		Iterable<Forest> forests = this.forestRepository.findAll();
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
		Iterable<Forest> forests = this.forestRepository.findAll();
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
		parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);
		Iterable<ParcelleCadastrale> parcellecadastrales = this.parcellecadastraleRepository.findAll();
		return new ModelAndView("forest/parcellecadastralelist" );
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
    	BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		//Workbook workbook = new XSSFWorkbook(file);

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug(sb.toString());
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/forest/dataloader";
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
