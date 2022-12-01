package game;

import java.io.IOException;
import gfxproc.Gfx;

public class Update implements Runnable{
	
	public boolean running;
	public int prevposx, prevposy;
	
	
	
	
	
	// 
	// Att göra, Binda framerate till 24fps, detta genom att räkna hur lång tid varje frame tar, och justera en sleep utifrån det.
	// 30 fps är 1/30 sekund per frame, om framen tar mindre tid än detta för att kalkulera och göra allt, ställ då in så att den sover i lämplig tid.
	// därav lägg till timer. System.nanoTime();
	
	
	
	//
	public void run() {

        switch(game.Game.state) {
            case (0): {
            	Game.lg.Logbuilder("State changed to 0");
            	
            	prgbar();
            	break;
            }
            case (1): {
            	Game.lg.Logbuilder("State changed to 1");
            	up();
            	break;
            }
            case (2): {
            	Game.lg.Logbuilder("State changed to 2");
            	while(game.Game.state == 2) {
            	try {
            		Sleep(100);
            	} catch(Exception e) {
            		System.out.println(e);
            	}
            	}
            	break;
            }
            case (3): {
            	Game.lg.Logbuilder("State changed to 3");
            	menu();
            	break;
            }
        }
	}
	public void prgbar() {
		
		var i = 0;
		while(game.Game.state == 0) {
			
			i++;
			if(i == 81) {
				System.out.print("\r\n");
				i = 0;
				
			}
			System.out.print("#");
			
			Sleep(20);
			
		}
		Game.lg.Logbuilder("PROGRESSBAR STOPPED");
		Game.lg.Logwriter();
		run();
	}
	public void up() {		
		
		while(game.Game.state == 1) {	
		game.Keyboard_handler.Kupdate();	
		if(game.Game.state == 1) {
		String r  = Gfx.Segproc();
	    CLS();	   
		System.out.print(r);
		
		
		prevposx = game.Game.tpposx;
		prevposy = game.Game.tpposy;
		Game.lg.Logwriter();
		
		}
		}
		Game.lg.Logbuilder("UPDATE PROCESS STOPPED");
		run();
		
	}
    public void menu() {
    	//String r = Gfx.segToString();
    	while(game.Game.state == 3) {	
    		game.Keyboard_handler.Kmenu();
    		//game.Game.cMenu.drawMenu();
    		//String r = Gfx.segToString();
    		//CLS();
    		//System.out.print(r);	
    		Sleep(100);
    		Game.lg.Logwriter();
    		
    	}
    	run();
    }
    
    
    
    
    // timing functions
    
   
	public static void CLS() {
    	
	    
	    try {
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (IOException | InterruptedException e) {e.printStackTrace();}
            
	    }
	private static void Sleep(int to) {
		try {
									
			Thread.sleep(to);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}