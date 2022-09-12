package game;

import java.io.IOException;
import gfxproc.Gfx;

public class Update implements Runnable{
	
	public boolean running;
	
	
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
			if(i == 40) {
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
			
        for(int i = 0; i <= Game.points; i++) {
			// System.out.print(" nn " + i + " ss ");
			Game.thand[i].update();
		}
        
	    CLS();
		
		for(int i = 0; i < Gfx.sizey; i++) {
		    for(int z = 0; z < Gfx.sizex; z++) {
		    	int id = Gfx.sizex*(i)+z;
		    	System.out.print(Gfx.pix[id].symbol);
		    	if((Gfx.pix[id].symbol) == '*') {
		    			System.out.println("             // " + z + " //                // " + i + " //   ");
		    	}
		    }
		    System.out.print("\r\n");
		}
		Game.lg.Logwriter();
		// faster sleeeep 1500
		Sleep(400);

		}
		Game.lg.Logbuilder("UPDATE PROCESS STOPPED");
		run();
	}
    
	public static void CLS() {
    	
	    
	    try {
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (IOException | InterruptedException ex) {}

	    }
	private static void Sleep(int to) {
		try {
									
			Thread.sleep(to);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}