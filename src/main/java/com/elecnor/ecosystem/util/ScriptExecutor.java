package com.elecnor.ecosystem.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ScriptExecutor {
	
	@Autowired
	@Qualifier("masterDBSessionFactory")
	SessionFactory sessionFactory;
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;  
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	public void runScript(){
		String textToEdit1 = "applicationdb";
		String to = "MASTER_123";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Resource resource = new ClassPathResource("D:\\write.sql");
		try {
			readnwrite(textToEdit1, to);
//			JdbcTestUtils.executeSqlScript(jdbcTemplate, resource, true);
//			String aSQLScriptFilePath = "D:\\write.sql";
			// Initialize object for ScripRunner
					 
					
			// Give the input file to Reader
//			Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));

			// Exctute script
//			sr.runScript(reader);


		} catch (Exception e) {
			System.err.println("Failed to Execute" + " The error is " + e.getMessage());
		}
	}
	
	
	
	
	public void readnwrite(String textToEdit1, String to) {
		File readFromFile = new File("D:\\ApplicationDB.sql");
		File writeToFile = new File("D:\\write.sql");
		
		FileInputStream fs = null;
		InputStreamReader in = null;
		BufferedReader br = null;
		FileWriter fstream = null;
		BufferedWriter outobj = null;
		String textinLine;
		
		try {
			fstream = new FileWriter(writeToFile);
		    outobj = new BufferedWriter(fstream);
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		try {
			fs = new FileInputStream(readFromFile);
			in = new InputStreamReader(fs);
			br = new BufferedReader(in);

			while (true) {
				textinLine = br.readLine();
				if (textinLine == null) {
					break;
				}
				if (textinLine.contains(textToEdit1)) {
					textinLine = textinLine.replace(textToEdit1, to);
				}
				outobj.write(textinLine);
				outobj.write(System.getProperty("line.separator"));
			}

			outobj.close();
			fs.close();
			in.close();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

/*		try {
			FileWriter fstream = new FileWriter(f);
			BufferedWriter outobj = new BufferedWriter(fstream);
			outobj.write(sb.toString());
			outobj.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}*/
	}

}
