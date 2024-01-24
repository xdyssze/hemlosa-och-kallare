package logger;

import java.io.IOException;


import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

// egna generiska klass, skapar loggar i en log folder.
public class Logcreator {
	
	public static String date;

	static Path fname;
	private String logBuffer;
	// initierar klassen
    public Logcreator () {
    	    logBuffer = "";
    		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
    		LocalDateTime da = LocalDateTime.now();
    		date = d.format(da);
    		File funny = new File(System.getProperty("user.dir") + "\\log\\" + date + ".txt");
    		
    		fname = Path.of(System.getProperty("user.dir") + "\\log\\" + date + ".txt");
    		try {
    		funny.createNewFile();
    		
    		
    		} catch (IOException e) {
    			System.out.print(e);
    		}
    	
    }
    // lägger till en text till loggbuffren
    public void Logbuilder(String ev) {
    	logBuffer += LocalDateTime.now() + ":   " + ev + "\r\n ";
    }
    // skriver ut logbuffern till logfilen, om den ej finns skapas den. Logbuffer clearas.
    public boolean Logwriter() {
    	
    	try {
    	  Files.writeString(fname, logBuffer, StandardOpenOption.APPEND);
          logBuffer ="";
    	  return(true);
    	} catch(IOException e) {
    		e.printStackTrace();
    		logBuffer = "";
    		return(false);
    	}
    	
    }
    
}
