package game;

import java.io.IOException;
import gfxproc.Gfx;

public class Update implements Runnable{
	
	public boolean running;
	public int prevposx, prevposy;
	public String prevs;
	Process proces;
	
	
	
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
            	CLS();
            	System.out.print(Gfx.Segproc());
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
		
		run();
	}
	public void up() {		
		long t1, t2;
		while(game.Game.state == 1) {	
	    t1 = System.nanoTime();
		game.Keyboard_handler.Kupdate();	
		if(game.Game.state == 1) {
		Gfx.updateScreen();
		}
		prevposx = game.Game.tpposx;
		prevposy = game.Game.tpposy;
		
		t2 = System.nanoTime();
		if(((t2-t1)) < ((1/10)*1000000000)) {
		    try {
		    	Thread.sleep(((1/10)*100)-(t2-t1)/1000000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		}
		
		Game.lg.Logbuilder("UPDATE PROCESS STOPPED");
		run();
		
	}
    public void menu() {
    	//String r = Gfx.segToString();
    	while(game.Game.state == 3) {	
    		game.Keyboard_handler.kMenu();
    		//game.Game.cMenu.drawMenu();
    		//String r = Gfx.segToString();
    		//CLS();
    		//System.out.print(r);	
    		Sleep(100);
    		
    		
    	}
    	run();
    }
    
    
    
    
    // timing functions
    
    
	public static void CLS() {
		System.out.print("\033[H\033[2J");  
		System.out.flush();
		
	    /*
	    try {
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (IOException | InterruptedException e) {e.printStackTrace();}
            
	    }
	    */
	}
	private static void Sleep(int to) {
		try {
									
			Thread.sleep(to);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}