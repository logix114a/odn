package com.noblens.odn.forest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

public class DataLoader {
	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
	@Autowired
	private ForestRepository forestRepository;
	@Autowired
	private ParcelleCadastraleRepository parcellecadastraleRepository;
	@Autowired
	private StationForestiereRepository stationforestiereRepository;
	@Autowired
	private ParcelleForestiereRepository parcelleforestiereRepository;
	@Autowired
	private TypePeuplementRepository typepeuplementRepository;
	public Boolean loaddataloaderforest(MultipartFile file) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			logger.debug("--Test OsssssDN : get id--");
			logger.debug(file.getName());
			// INTEGRATION FOREST
			Sheet datatypeSheet1 = workbook.getSheet("forest");
			Iterator<Row> iterator1 = datatypeSheet1.iterator();
			while (iterator1.hasNext()) {
				logger.debug("--Test OyretretreDN : get id--");
				logger.debug(file.getName());
				Row currentRow1 = iterator1.next();
				Forest forest = new Forest();
				forest.setName(currentRow1.getCell(1).getStringCellValue());
				forest.setProprietaire(currentRow1.getCell(2).getStringCellValue());
				//forest = this.forestRepository.save(forest);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}

	public Boolean loaddataloaderStationForestiere(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		logger.debug("--Test ODN : get id--");
		logger.debug(file.getName());
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("stationforestiere");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				StationForestiere stationforestiere = new StationForestiere();
				stationforestiere.setNom(currentRow.getCell(1).getStringCellValue());
				stationforestiere.setDescription(currentRow.getCell(2).getStringCellValue());
				stationforestiere.setCaracteristique_sol(currentRow.getCell(3).getStringCellValue());
				stationforestiere.setPeuplement_naturel(currentRow.getCell(4).getStringCellValue());
				//stationforestiere = this.stationforestiereRepository.save(stationforestiere);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}

	
	public void loaddataloaderParcelleCadastrale(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("parcellecadastrale");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				ParcelleCadastrale parcellecadastrale = new ParcelleCadastrale();
				parcellecadastrale.setCommune(currentRow.getCell(1).getStringCellValue());
				parcellecadastrale = this.parcellecadastraleRepository.save(parcellecadastrale);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void loaddataloaderParcelleForestiere(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("parcelleforestiere");
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
	
	public void loaddataloaderTypePeuplement(MultipartFile file) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());

			// INTEGRATION STATION FORESTIERE
			Sheet datatypeSheet = workbook.getSheet("typepeuplement");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				TypePeuplement typepeuplemet = new TypePeuplement();
			
				typepeuplemet = this.typepeuplementRepository.save(typepeuplemet);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	
}
