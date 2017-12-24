package com.noblens.odn.forest;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noblens.odn.forest.data.Forest;
import com.noblens.odn.forest.data.ForestArea;
import com.noblens.odn.forest.data.ForestRepository;
import com.noblens.odn.forest.data.ParcelleCadastrale;
import com.noblens.odn.forest.data.ParcelleCadastraleRepository;
import com.noblens.odn.forest.data.ParcelleForestiere;
import com.noblens.odn.forest.data.ParcelleForestiereRepository;
import com.noblens.odn.forest.data.TypePeuplement;
import com.noblens.odn.forest.data.TypePeuplementRepository;


@Controller
@RequestMapping("/forest")
public class ForestController {
	@Autowired
	private ForestRepository forestRepository;
	@Autowired
	private ParcelleCadastraleRepository parcellecadastraleRepository;
	@Autowired
	private ParcelleForestiereRepository parcelleforestiereRepository;	
	@Autowired
	private TypePeuplementRepository typepeuplementRepository;	
	
	@GetMapping("")
	public ModelAndView home() {
		return new ModelAndView("forest/home");
	}
	
	@GetMapping(path="all")
	public @ResponseBody Iterable<Forest> getAllForests() {
		// This returns a JSON or XML with the users
		return forestRepository.findAll();
	}
	
	
	
	@GetMapping(path="listall")
public ModelAndView listall() {
	Iterable<Forest> forests = this.forestRepository.findAll();
	return new ModelAndView("forest/forestlist", "forests", forests);
}	
	@GetMapping(path="add") // Map ONLY GET Requests
	public @ResponseBody String addNewForest (@RequestParam String name)
			 {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Forest n = new Forest();
		n.setName(name);
		forestRepository.save(n);
		return "Saved";
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
		return new ModelAndView("forest/forestlist", "forests", forests);
	}
	

	
	@GetMapping(path="forestview/{id}")
public ModelAndView viewforest(@PathVariable("id") Forest forest) {
	return new ModelAndView("forest/forestview", "forest", forest);
}

	@GetMapping(path="forestview1/{id}")
public ModelAndView viewforest1() {
	
		Forest forest = new Forest();
		forest.setId((long) 2);
		forest.setName("test olivier");
		
		Set parcad1 = new HashSet <ParcelleCadastrale>() {{
			add(new ParcelleCadastrale("olivier 12345",forest));
			add(new ParcelleCadastrale("olivier",forest));
		}};
		
		forest.setParcelleadastrales(parcad1);
		//forestRepository.save(forest);
		
	return new ModelAndView("forest/forestview1", "forest", forest);
}	
	@GetMapping(path="forestview2/{id}")
public ModelAndView viewforest2(@PathVariable("id") Forest forest) {
		//forestRepository.save(forest);
		
	return new ModelAndView("forest/forestview2", "forest", forest);
}		
	
	@GetMapping(path="parcellecadastraleadd") // Map ONLY GET Requests
	public ModelAndView parcellecadastraleadd1(ParcelleCadastrale parcellecadastrale) {
		return new ModelAndView("forest/parcellecadastraleadd");
	}	

	@PostMapping(path="parcellecadastraleadd") 
	public ModelAndView parcellecadastraleadd(@Valid ParcelleCadastrale parcellecadastrale, BindingResult result,
			RedirectAttributes redirect) {
		parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);
		Iterable<ParcelleCadastrale> parcellescadastrales = this.parcellecadastraleRepository.findAll();
		return new ModelAndView("forest/parcellecadastralelist", "parcellecadastrales", parcellescadastrales);
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
	
	
	@GetMapping(path="forestareaassignparcad") // Map ONLY GET Requests
	public ModelAndView forestareaassignparcad(Forest forest) {
		
		Iterable<ParcelleCadastrale> parcellecadastrales = this.parcellecadastraleRepository.findAll();
		return new ModelAndView("forest/forestareaassignparcad", "parcellecadastrales", parcellecadastrales);
	}	

	@PostMapping(path="forestareaassignparcad") // Map ONLY GET Requests
	public ModelAndView forestareaassignparcad1(@Valid ParcelleCadastrale parcellecadastrale, BindingResult result,
			RedirectAttributes redirect) {
		
		parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);
		Iterable<ParcelleCadastrale> parcellescadastrales = this.parcellecadastraleRepository.findAll();
		return new ModelAndView("forest/parcellecadastralelist", "parcellecadastrales", parcellescadastrales);
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
