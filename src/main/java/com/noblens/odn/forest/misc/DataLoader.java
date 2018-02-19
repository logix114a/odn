package com.noblens.odn.forest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.noblens.odn.forest.ForestController;
import com.noblens.odn.forest.data.Forest;
import com.noblens.odn.forest.data.ForestRepository;
import com.noblens.odn.forest.data.Objectif_Sylvicole;
import com.noblens.odn.forest.data.ParcelleCadastrale;
import com.noblens.odn.forest.data.ParcelleCadastraleRepository;
import com.noblens.odn.forest.data.ParcelleForestiere;
import com.noblens.odn.forest.data.ParcelleForestiereRepository;
import com.noblens.odn.forest.data.StationForestiere;
import com.noblens.odn.forest.data.StationForestiereRepository;
import com.noblens.odn.forest.data.TypePeuplement;
import com.noblens.odn.forest.data.TypePeuplementRepository;

@Service
public class DataLoader {
	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
	@Autowired
	private ForestRepository forestRepository1;
	
	@Autowired
	private ParcelleCadastraleRepository parcellecadastraleRepository;
	@Autowired
	private StationForestiereRepository stationforestiereRepository;
	@Autowired
	private ParcelleForestiereRepository parcelleforestiereRepository;
	@Autowired
	private TypePeuplementRepository typepeuplementRepository;
	public List<Forest> loaddataloaderforest(MultipartFile file) {
		 List<Forest> listeforest = new ArrayList<Forest>();
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			logger.debug("--Test OsssssDN : get id--");
			logger.debug(file.getName());
			// INTEGRATION FOREST
			Sheet datatypeSheet1 = workbook.getSheet("forest");
			Iterator<Row> iterator1 = datatypeSheet1.iterator();
			iterator1.next();
			while (iterator1.hasNext()) {
				logger.debug("--Test OyretretreDN : get id--");
				logger.debug(file.getName());
				Row currentRow1 = iterator1.next();
				Forest forest = new Forest();
				forest.setName(currentRow1.getCell(1).getStringCellValue());
				forest.setProprietaire(currentRow1.getCell(2).getStringCellValue());
				//forest = this.forestRepository1.save(forest);
				listeforest.add(forest);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listeforest;
	}

	public List<StationForestiere> loaddataloaderStationForestiere(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		logger.debug("--Test ODN : get id--");
		logger.debug(file.getName());
		 List<StationForestiere> listestationforestiere = new ArrayList<StationForestiere>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("stationforestiere");
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				StationForestiere stationforestiere = new StationForestiere();
				stationforestiere.setNom(currentRow.getCell(1).getStringCellValue());
				//stationforestiere.setDescription(currentRow.getCell(2).getStringCellValue());
				//stationforestiere.setCaracteristique_sol(currentRow.getCell(3).getStringCellValue());
				//	stationforestiere.setPeuplement_naturel(currentRow.getCell(4).getStringCellValue());
				//stationforestiere = this.stationforestiereRepository.save(stationforestiere);
				listestationforestiere.add(stationforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listestationforestiere;
	}

	
	public List<ParcelleCadastrale> loaddataloaderParcelleCadastrale(MultipartFile file) {
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		 List<ParcelleCadastrale> listeparcellecadastrale = new ArrayList<ParcelleCadastrale>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("parcelle_cadastrale");
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				ParcelleCadastrale parcellecadastrale = new ParcelleCadastrale();
				parcellecadastrale.setCommune(currentRow.getCell(1).getStringCellValue());
				//parcellecadastrale.setSection(currentRow.getCell(2).getStringCellValue());
				//parcellecadastrale.setSection(currentRow.getCell(3).getStringCellValue());
				parcellecadastrale.setLieu_dit(currentRow.getCell(4).getStringCellValue());
				parcellecadastrale.setSurface(currentRow.getCell(5).getNumericCellValue());
				
				//parcellecadastrale.setSurface((currentRow.getCell(5).getgetStringCellValue().to);
				//parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);
				listeparcellecadastrale.add(parcellecadastrale);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listeparcellecadastrale;
	}
	
	public List<ParcelleForestiere> loaddataloaderParcelleForestiere(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		 List<ParcelleForestiere> listeparcelleforestiere = new ArrayList<ParcelleForestiere>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("parcelle_forestiere");
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				ParcelleForestiere parcelleforestiere = new ParcelleForestiere();
				parcelleforestiere.setNumero(currentRow.getCell(2).getStringCellValue());
				listeparcelleforestiere.add(parcelleforestiere);
				//parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listeparcelleforestiere;
	}
	
	
	public void loaddataloaderOperationSylvicole(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("oeprationsylvicole");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Objectif_Sylvicole operationsylvicole = new Objectif_Sylvicole();
			
				//operationsylvicole = this.parcelleforestiereRepository.save(operationsylvicole);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public List<TypePeuplement> loaddataloaderTypePeuplement(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		 List<TypePeuplement> listeypepeuplement = new ArrayList<TypePeuplement>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("type_peuplement");
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				TypePeuplement typepeuplement = new TypePeuplement();
				typepeuplement.setNom(currentRow.getCell(1).getStringCellValue());
				listeypepeuplement.add(typepeuplement);
				//typepeuplemet = this.typepeuplementRepository.save(typepeuplemet);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listeypepeuplement;
	}

	public void loaddataloaderAssignParcadtoParfor(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("assignparcadtoparfor");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				ParcelleForestiere parcelleforestiere = new ParcelleForestiere();
				parcelleforestiere.setNumero(currentRow.getCell(1).getStringCellValue());
				parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void loaddataloaderAssignParfortoForest(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("assignparfortoforest");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				ParcelleForestiere parcelleforestiere = new ParcelleForestiere();
				parcelleforestiere.setNumero(currentRow.getCell(1).getStringCellValue());
				parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void loaddataloaderAssignStaFortoParFor(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("assignstafortoparfor");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				ParcelleForestiere parcelleforestiere = new ParcelleForestiere();
				parcelleforestiere.setNumero(currentRow.getCell(1).getStringCellValue());
				parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void loaddataloaderPeuplement(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("peuplement");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				ParcelleForestiere parcelleforestiere = new ParcelleForestiere();
				parcelleforestiere.setNumero(currentRow.getCell(1).getStringCellValue());
				parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public List<ParcelleForestiere> loaddataloaderRepartition(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		 List<ParcelleForestiere> listeparcelleforestiere = new ArrayList<ParcelleForestiere>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("Repartition_peuplement");
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				//TODO
				// FIRST STEP - Link parcelle cadastrale to parcelle forestiere	
				//forestRepository1.findById((long) 1);
				Forest forest = null;
				forest.setName("test");
				forestRepository1.save(forest);
				// GET each row the parcelle cadastrale
				// GET each row the parcelle forestiere
				// SECOND STEP - Create for each row the Repartition peuplement
				//  GET for each each row the parcelle cadastrale
				// Get for each row the type peuplement
				// Create the new recor in reparation peuplement
				ParcelleForestiere parcelleforestiere = new ParcelleForestiere();
				parcelleforestiere.setNumero(currentRow.getCell(2).getStringCellValue());
				listeparcelleforestiere.add(parcelleforestiere);
				//parcelleforestiere = this.parcelleforestiereRepository.save(parcelleforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listeparcelleforestiere;
	}
	
}
