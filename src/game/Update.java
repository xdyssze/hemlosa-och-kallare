package game;

import java.io.IOException;
import gfxproc.Gfx;

public class Update implements Runnable{
	
	public boolean running;
	public int prevposx, prevposy;
	
	
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
			Sleep(2);
			
		}
		Game.lg.Logbuilder("PROGRESSBAR STOPPED");
		Game.lg.Logwriter();
		run();
	}
	public void up() {		
		
		while(game.Game.state == 1) {	
		game.Keyboard_handler.Kupdate();	
		String r  = Gfx.Segproc();
	    CLS();	   
		System.out.print(r);
		
		
		prevposx = game.Game.tpposx;
		prevposy = game.Game.tpposy;
		Game.lg.Logwriter();
		// faster sleeeep 1500
		Sleep(200);
		}
		
		Game.lg.Logbuilder("UPDATE PROCESS STOPPED");
		run();
		
	}
    
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