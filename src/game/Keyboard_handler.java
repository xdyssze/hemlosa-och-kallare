package game;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

// Använder mig här av jnativehook, typ det enda bibloteket jag hittade för cmd som kan lyssna på keyevents. 
// annars skulle jag behövt skriva eget eller mer trogligen implementera något bootleg system för att gå i text.
public class Keyboard_handler implements NativeKeyListener {
	static char[] keyq;
	static byte ciq;
	static boolean cK;
    static game.Timer tim;
    public static void main(String[] args) {
    	// initialiserar lite värden
    	ciq = 0;
    	keyq = new char[5];
        // skapar ny timer ich sätter den till icke-igång.
    	tim = new game.Timer();
    	tim.running = false;
    	// nullar hela keyq-en
    	for(int i = 0; i < 5; i++ ){keyq[i] = '*';}
    	try {
    		// registrerar hake in i hela skärmen, läser av hela skärmens keyinputs.
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			// vid provlem så gör den följande
			Game.lg.Logbuilder("There was a problem registering the native hook.");
			Game.lg.Logbuilder(ex.getMessage());
			Game.lg.Logwriter();
			System.exit(1);
		}
        // assignar denna klass som avlyssnare och vid en keypress så kommer den söka upp specifikt denna klass
		GlobalScreen.addNativeKeyListener(new Keyboard_handler());        
    }
    
    
    
    // keyhandler faktiskt
    // körs varje update, i princip kollar om en tangent är nertryckt och kör dess funktion.
    public static void Kupdate() {
    	// kollar vilken tangent
    	switch(keyq[0]) {
    	// alla tangenter funkar ungefär på samma sätt när det kommer till movement. Om en timer inte körs, skapas en ny, väntar tills nästa uppdatering, om nog tid paserat rör sig karaktären en pos, jadijada
    	case('W'): {		
    		if(tim.running) {
    			if(tim.t1 == 0) {
    				tim.t1 = tim.t0;
    			}
    			if(tim.mil(tim.intervall()) >= 250/game.Game.player.sp) {
    				String f = gfxproc.Maphandler.Mappas(Game.tpposx, Game.tpposy-1);
    	    		if(f == "do") {
    	    			game.Game.player.pls.WLAE(keyq[0]);
    	    			Game.tpposy -= 1;
    	    			tim.setTimer();
    	    			
    	    		} else {
    				tim.setTimer();
    	    		}
    			}
    		} else {
    			tim = new game.Timer();
    			tim.setTimer();
    		}    		
    		break;
    	}
    	case('A'): {  		
    		if(tim.running) {
    			if(tim.t1 == 0) {
    				tim.t1 = tim.t0;
    			}
    			if(tim.mil(tim.intervall()) >= 250/game.Game.player.sp) {
    				String f = gfxproc.Maphandler.Mappas(Game.tpposx-1, Game.tpposy);
    	    		if(f == "do") {
    	    			game.Game.player.pls.WLAE(keyq[0]);
    	    			Game.tpposx -= 1;
    	    			tim.setTimer();
    	    			
    	    		} else {
    				tim.setTimer();
    	    		}
    			}
    		} else {
    			tim = new game.Timer();
    			tim.setTimer();
    		}
    		break;
    	}
    	case('S'): { 		
    		if(tim.running) {
    			if(tim.t1 == 0) {
    				tim.t1 = tim.t0;
    			}
    			if(tim.mil(tim.intervall()) >= 250/game.Game.player.sp) {
    				String f = gfxproc.Maphandler.Mappas(Game.tpposx, Game.tpposy+1);
    	    		if(f == "do") {
    	    			game.Game.player.pls.WLAE(keyq[0]);
    	    			Game.tpposy += 1;
    	    			
    	    			tim.setTimer();
    	    			
    	    		} else {
    				tim.setTimer();
    	    		}
    			}
    		} else {
    			tim = new game.Timer();
    			tim.setTimer();
    		}	
    		break;
    	}
    	case('D'): {
    		if(tim.running) {
    			if(tim.t1 == 0) {
    				tim.t1 = tim.t0;
    			}
    			if(tim.mil(tim.intervall()) >= 250/game.Game.player.sp) {
    				String f = gfxproc.Maphandler.Mappas(Game.tpposx+1, Game.tpposy);
    	    		if(f == "do") {
    	    			game.Game.player.pls.WLAE(keyq[0]);
    	    			Game.tpposx += 1;
    	    		
    	    			tim.setTimer();
    	    			
    	    		} else {
    				tim.setTimer();
    	    		}
    			}
    		} else {
    			tim = new game.Timer();
    			tim.setTimer();
    		}	
    		break;
    	}
    	// denna startar en meny som kan interageras och gås igenom.
    	case('I'): {
    		game.Game.menhand.setGUI("esc");
    		game.Game.menhand.drawGUI();
            clearQue();
    		break;
    	}
    	case(' '): {
    		
    		break;
    	}
    	default: {
    		
    		break;
    	}
    	
    	}
    }
    
    // samma som kUpdate fast för menyer, lite andra funktioner
    public static void kMenu() {
    	switch(keyq[0]) {
    	case('W'): {
    			int f = (game.Game.menhand.gui.currentlySelected)-(game.Game.menhand.gui.iw);
    			game.Game.menhand.mvPos('w', f);
    			game.Game.menhand.drawGUI();
    			clearQue();
    		break;
    	}
    	case('A'): {
    		int f = (game.Game.menhand.gui.currentlySelected)-1;
			game.Game.menhand.mvPos('a', f);
			game.Game.menhand.drawGUI();
			clearQue();
    		break;
    	}
    	case('S'): {
    		int f = (game.Game.menhand.gui.currentlySelected)+(game.Game.menhand.gui.iw);
            game.Game.menhand.mvPos('s', f);
			game.Game.menhand.drawGUI();
			clearQue();
    		break;
    	}
    	case('D'): {
    		int f = (game.Game.menhand.gui.currentlySelected)+1;
			game.Game.menhand.mvPos('d', f);
			game.Game.menhand.drawGUI();
			clearQue();
    		break;
    	}
    	case('I'): {
    		game.Game.state = 1;
    		clearQue();
    		break;
    	}
    	case(' '): {
    		Game.lg.Logbuilder("mellanslag");
    		game.Game.menhand.action();
    		clearQue();
    		break;
    	}
    	default: {
    		
    		break;
    	}
    	
    	}
    }
    
    // Key nertryckt
	public void nativeKeyPressed(NativeKeyEvent e) {
		boolean alreadypressed = false;
		Game.lg.Logbuilder("KEY: " +  NativeKeyEvent.getKeyText(e.getKeyCode()) + " Has been pressed");
		char p;
		p = NativeKeyEvent.getKeyText(e.getKeyCode()).charAt(0);
		if(e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
			p = ' ';
		}
		for(int i = 0; i < 5; i++) {
			if(keyq[i] ==  p ) {
				alreadypressed = true;
			}
			
		}
		if(!alreadypressed) {
		char[] temp = new char[5];		
		if(ciq == 0) {
			keyq[0] = p;
		} else {
			if (ciq == 5) {
				keyq[4] = '*';
				ciq--;
			}
			temp[0] = p;
			
		    for(int i = 0; i < 4; i++) {
			    if(keyq[i] != '*') {
			    	temp[i+1] = keyq[i];
			    } else {		        
			    	temp[i+1] = '*';
			    }
		    }		
		}
		keyq = temp;
		ciq++;
		}
	}
	public void nativeKeyReleased(NativeKeyEvent e) {
		char[] temp = new char[5];
		int p = 0;
		char kr = NativeKeyEvent.getKeyText(e.getKeyCode()).charAt(0);
		if(e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
			kr = ' ';
		}
		//logger.Logcreator.Logbuilder("\r\n FUNNY STRING:    " + String.valueOf(keyq[0]) + String.valueOf(keyq[1]) + String.valueOf(keyq[2]) + String.valueOf(keyq[3]) + String.valueOf(keyq[4]));
		while(p < 5 && keyq[p] != kr) {
			
			temp[p] = keyq[p];
			p++;
		}
		if(p != 4) {
			if(p == 0) {
				tim.running = false;
				cK = true;
			}
		    for(int i = p; i < 4; i++) {
		    	
			    temp[i] = keyq[i+1];
		    }
		} else {
			temp[4] = '*';
		}				
		keyq = temp;
		Game.lg.Logbuilder("\r\n FUNNY STRING 2:    " + String.valueOf(keyq[0]) + String.valueOf(keyq[1]) + String.valueOf(keyq[2]) + String.valueOf(keyq[3]) + String.valueOf(keyq[4]));
		ciq--;
	}	
	
	
	public static void clearQue() {
		keyq = new char[5];
	}
}