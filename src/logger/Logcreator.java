package logger;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
public class Logcreator {
	
	public static String date;
	public static String log;
	static Path fname;
    public Logcreator () {
    	
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
    public static void Logbuilder(String ev) {
    	log += ev + " ";
    }
    public static boolean Logwriter() {
    	
    	try {
    	  Files.writeString(fname, LocalDateTime.now() + ":    " + log + "\r\n", StandardOpenOption.APPEND);
    	  log = "";
    	  return(true);
    	} catch(IOException e) {
    		e.printStackTrace();
    		log = "";
    		return(false);
    	}
    	
    }
    
}
