package game;

import java.nio.file.Files;
import java.nio.file.Paths;

import gfxproc.Gfx;
import logger.Logcreator;


public class Game {
	public volatile static int state;
	public static int tpposx, tpposy;
	public static char[] keyq;
	static String ascii;
    static int f, locp;
    private static Update update;
    public static Thread up1;
    
    public static Logcreator lg;
	public static void main(String[] args) throws Exception {		
		
		
	    lg = new Logcreator();
		
		ascii = fileread(System.getProperty("user.dir") + "\\src\\steffe.txt");
		
		
		startup();
	}
	
	public static void startup() {				
		Gfx.CLS();		
		System.out.println(Game.ascii);
		Sleep(2000);
		game.Keyboard_handler.main(null);
		tpposx = 160;
		tpposy = 160;
	    gfxproc.Maphandler.nmap = 1;
		gfxproc.Maphandler.cmap = 0;
		update = new Update();
		state = 0;
		up1 = new Thread(update, "t1");
		up1.start();	
		
		
		String[] argu = {"set", "81", "81"};
		Gfx.main(argu);		
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

