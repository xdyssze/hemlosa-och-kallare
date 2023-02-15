package game;

import java.nio.file.Files;

import java.nio.file.Paths;

import gfxproc.Gfx;
import gfxproc.Maphandler;
import logger.Logcreator;



public class Game {
	
	// definierar lite variabler här
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
    public static game.Homeless_handler homHand;
    public static menugui.GUIManager menhand;
	public static void main(String[] args) throws Exception {	
		// i princip endast visar asciiarten i början och startar logcreatorn, detta då logcreatorn ska dokumentera och göra så jag kan debugga under hela startprocessen, därför är denna först igång innan allt annat så den faktiskt kan logga
	    lg = new Logcreator();		
	    // läser in steffe.txt
		ascii = fileread(System.getProperty("user.dir") + "\\src\\steffe.txt");	
		// Startar startup funlktionen
		startup();
	}
	
	public static void startup() {		
		// Ascii art show i princip, rensar skärmen och visar steffe, sedan sover i 2000 milisec
		Gfx.CLS();	
		System.out.println(Game.ascii);
		Sleep(8000);
		// Thread Start, progressbar
		// startar update som i princip är en klocka och bestämmer allt som händer i en ny tråd.
		state = 0;					
		update = new Update();
		up1 = new Thread(update, "Update Thread");
		up1.start();
		
		// Initerar flertalet variabler, objekt osv som används i spele.et
		menhand = new menugui.GUIManager();
		tpposx = 20;
		tpposy = 190;	
		// använde en array för flexibilitet på gfx main, så jag kan stoppa in lite vad som i framtiden om det skulle behövas, därav definieras arrayen här
		String[] argu = {"set", "80", "40"};
		// Classes start / init
		maph = new Maphandler((byte)9, (byte)5);		
		game.Keyboard_handler.main(null);
		Gfx.main(argu);	
		// kan ändras till false i framtiden om jag vill lägga till sparfiler, säger till i princip att detta är den första gången spelet startas och allt ska skapas p ånytt
		player = new Player(true);
		player.iH.debugFillInv();
		// SKapar hemlösa
		//homHand = new game.Homeless_handler();
		// Start, bestämmer statet som att spelet är färdigt och ska börja köra vanliga spelrutiner.
	    state = 1;			    
		}
	// Game functions
	
	    
	
	
	/// RANDOM STUFF

	
	
	// Läser in en fil, med filnamnet fn. Eller specifikt texten i en textfil
	public static String fileread(String fn)throws Exception {	    				
		
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fn)));
		return data;
		
	    
	}
	// tvingar tråden att sova.
	private static void Sleep(int to) {
		
		try {
									
			Thread.sleep(to);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}

