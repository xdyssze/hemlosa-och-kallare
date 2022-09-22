package game;

import java.nio.file.Files;
import java.nio.file.Paths;

import gfxproc.Gfx;
import gfxproc.Maphandler;
import logger.Logcreator;


public class Game {
	public volatile static int state;
	public static int tpposx, tpposy;
	public static char[] keyq;
	static String ascii;
    static int f, locp;
    private static Update update;
    public static Thread up1;
    static Maphandler maph;
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
		up1 = new Thread(update, "t1");
		up1.start();
		// variables init
		tpposx = 10;
		tpposy = 160;	
		String[] argu = {"set", "40", "40"};
		// Classes start / init
		maph = new Maphandler((byte)9, (byte)5);		
		game.Keyboard_handler.main(null);
		Gfx.main(argu);		
		// Start
	    state = 1;			    
		}
	
	
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

