package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Marathon;
import com.example.demo.model.Organizer;
import com.example.demo.repo.MarathonRepo;
import com.example.demo.repo.OrganizerRepo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class OrganizerServiceImpl implements OrganizerService{

	@Autowired
	OrganizerRepo organizerRepo;
	@Autowired
	MarathonRepo marathonRepo;
	
	@Override
	public ArrayList<Organizer> selectAll() {
		ArrayList<Organizer> tempList = new ArrayList<Organizer>();
		for (Organizer o:organizerRepo.findAll())
		{
			tempList.add(o);
		}
		return tempList;
	}
 	
	@Override
	public Organizer selectById_org(long id_org) {
		if (id_org >=0) {
		Organizer orgTemp = organizerRepo.findById(id_org).get();
		
		if(orgTemp!=null)
			return orgTemp;
		}
		return null;
	}
	public boolean addNewOrganizer(Organizer organizer) {
		if (organizer==null)
			return false;
		if(organizerRepo.existsById(organizer.getId_org()))
			return false;
			else
		{	
			organizerRepo.save(organizer);
			return true;
		}
	}	
	@Override
	public boolean updateOrganizerById_org(Organizer organizer, long id_org) {
		if (organizerRepo.existsById(id_org) && organizer!=null) {
			Organizer orgTemp = organizerRepo.findById(id_org).get();
			orgTemp.setName(organizer.getName());
			orgTemp.setLogin(organizer.getLogin());
			orgTemp.setPassword(organizer.getPassword());
			organizerRepo.save(organizer);
			return true;
		}
		return false;
	}
		
	@Override
	public boolean deleteOrganizerById_org(long id_org) {
		if (organizerRepo.existsById(id_org))
		{
		Organizer orgTemp = organizerRepo.findById(id_org).get();
			organizerRepo.delete(orgTemp);
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteOrganizerByObject(Organizer organizer) {
		if(organizerRepo.existsById(organizer.getId_org()) && organizer!=null)
		{
			organizerRepo.delete(organizer);
			return true;
		}
	return false;	
	}
	
	
	@Override
	public boolean exportDataExcel()
	{
		final String FILE_NAME = "MarathonExcel.xlsx";

	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Marathon info");
	        
	        
	        ArrayList<Marathon> tempList = new ArrayList<Marathon>();
			for (Marathon m:marathonRepo.findAll())
			{
				tempList.add(m);
			}
			/*
	        ArrayList<Marathon> tempList = new ArrayList<Marathon>();
			for (Marathon m:marathonRepo.findAll())
			{
				tempList.add(m);
			}
			int k=(int) marathonRepo.count();
			Marathon[][] maratoni =new Marathon [k][3];
			for (int i=0; i<k;i++)
			{
				for(int j=0; j<3;j++)
				{
					maratoni[i][j];
				}
			}
			*/
			
			
			/*maratoni = {
	                {maratoni.getName(), maratoni.getPlace(), maratoni.getDistance()},
	                {"Big", "Jelgava", 7},
	                {"Tet", "Riga", 4},
	                {"Kalnu", "Valmiera", 8},
	                {"Ultra", "Sigulda", 20},
	                {"Seb", "Balvi", 40}
	        };
			*/
	        int rowNum = 1;
	        System.out.println("Creating excel");

	        for (int i = 1; i <= tempList.size(); i++) {
				Marathon maratoni1 = tempList.get(i-1);
				Row row = sheet.createRow(rowNum);
	            int colNum = 0;
	            
	                Cell cell = row.createCell(colNum);
	                cell.setCellValue(maratoni1.getName());
	                Cell cell2 = row.createCell(colNum);
	                cell2.setCellValue(maratoni1.getPlace());
	                rowNum++;
	             
			}
	        
	        
	        /*for (Marathon [] maratoni1 : maratoni) {
	            Row row = sheet.createRow(rowNum++);
	            int colNum = 0;
	            for (Object field : maratoni1) {
	                Cell cell = row.createCell(colNum++);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	        }
	        */

	        try {
	            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
	            workbook.write(outputStream);
	            workbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      System.out.println("Done");
		
		
		return false;
	}
	

	
}
