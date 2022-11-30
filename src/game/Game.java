package game;

import java.nio.file.Files;

import java.nio.file.Paths;

import gfxproc.Gfx;
import gfxproc.Maphandler;
import logger.Logcreator;
import game.Player;


public class Game {
	public volatile static int state;
	public static int tpposx, tpposy;
	public static char[] keyq;
	public static Player player;
	static String ascii;
    static int f, locp;
    private static Update update;
    public static Thread up1, maint;
    public static Maphandler maph;
    public static Logcreator lg;
	public static void main(String[] args) throws Exception {			
	    lg = new Logcreator();		
		ascii = fileread(System.getProperty("user.dir") + "\\src\\steffe.txt");			
		startup();
	}
	
	public static void startup() {		
		// Ascii art show
		Gfx.CLS();	
		System.out.println(Game.ascii);
		Sleep(2000);
		// Thread Start, progressbar
		
		state = 0;					
		update = new Update();
		up1 = new Thread(update, "Update Thread");
		up1.start();
		
		// variables init
		tpposx = 20;
		tpposy = 190;	
		String[] argu = {"set", "80", "40"};
		// Classes start / init
		maph = new Maphandler((byte)9, (byte)5);		
		game.Keyboard_handler.main(null);
		Gfx.main(argu);	
		// kan ändras till false i framtiden om jag vill lägga till sparfiler
		player = new Player(true);
		// Start
	    state = 1;			    
		}
	// Game functions
	
	
	
	/// RANDOM STUFF

	
	
	//
	public static String fileread(String fn)throws Exception {	    				
		
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fn)));
		return data;
		
	    
	}
	
	private static void Sleep(int to) {
		try {
									
			Thread.sleep(to);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}

