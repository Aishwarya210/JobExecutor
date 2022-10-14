package org.kp.poc.csv;

import java.util.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ReadandConvert {
	
	private static Logger log = LoggerFactory.getLogger(ReadandConvert.class);
	
	
	 @Value("${directory.path}")
	 String localdirectorypath;
	 
	 @Value("${archive.path}")
	 String archivepath;
	
		
	 	@Scheduled(fixedRateString ="${fixedRate}")
	 	//@Scheduled(cron="${cronExpression}")
		public void test() throws IOException {
			readFiles();
			moveFile();
			
		}
	 	
		 

		public void readFiles(){
			
			log.debug("ReadandConvert.readFiles - start");
			
			File directoryPath;
			
			try {
			//Creating a File object for directory
		      directoryPath = new File(localdirectorypath);
		      
		      //List of all files and directories
		      File filesList[] = directoryPath.listFiles();
		      System.out.println("List of files and directories in the specified directory:");
		     
		      for(File file : filesList) {
		        // System.out.println("File name: "+file.getName());
		         csvTojsonConverter(file); 
		      }
		      
			}catch (Exception e) {
				
				log.debug("ReadandConvert.readFiles - {}", e);
				
			}
			
			log.debug("ReadandConvert.readFiles - end");
		   }
		
	
		
		 public  void csvTojsonConverter(File file){
			 
			 log.debug("ReadandConvert.csvTojsonConverter - start");
			 
			 List<Map<?, ?>> list;
			 CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
		     CsvMapper csvMapper = new CsvMapper();
		       
		      
		        try {
		        	
		          
		          MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader()
		                    .forType(Map.class)
		                    .with(csvSchema)
		                    .readValues(file);
		          
		           list = mappingIterator.readAll();
		            
			 
		        System.out.println("List Is::");
		        System.out.println(list);
		        
		        ObjectMapper objectMapper = new ObjectMapper();
	            String jsonPretty = objectMapper.writerWithDefaultPrettyPrinter()
	                    .writeValueAsString(list);
	            
	            System.out.println("Json Object Is::");
	            System.out.println(jsonPretty);
		        
		        
		      } catch(Exception e) {
		    	  log.debug("ReadandConvert.csvTojsonConverter - {}", e);
		      }
		        
		     log.debug("ReadandConvert.csvTojsonConverter - end"); 
			 
}
			public void moveFile(){
				  
				log.debug("ReadandConvert.moveFile - start");
				
				File destinationDir;
				File directory;
				File[] subfiles;
				
				try {
			        destinationDir = new File(archivepath);
			        directory = new File(localdirectorypath);
			        subfiles = directory.listFiles();
				        for(File file:subfiles){
				            File previous = new File(destinationDir.getAbsolutePath()+file.getName());
				            if(!previous.exists()){
				                FileUtils.moveFileToDirectory(file, destinationDir, true);
				            }else{
				                FileUtils.moveFile(file,new File(destinationDir.getAbsolutePath()+"_"+System.currentTimeMillis()+file.getName()));
				            }
				            System.out.println("Moved Successfully..");
				        }
				        
				}catch (Exception e) {
					
					log.debug("ReadandConvert.movefile - {}", e);
				}
				log.debug("ReadandConvert.moveFile - end");
				
			}
			
}
		 
