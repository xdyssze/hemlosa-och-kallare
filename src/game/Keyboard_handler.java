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
    public static void main(String[] args) {
    	ciq = 0;
    	keyq = new char[5];
    	for(int i = 0; i < 5; i++ ){keyq[i] = '*';}
    	try {
    		// registrerar hake in i hela skärmen, läser av hela skärmens keyinputs.
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			Game.lg.Logbuilder("There was a problem registering the native hook.");
			Game.lg.Logbuilder(ex.getMessage());
			System.exit(1);
		}
        // assignar denna klass som avlyssnare och vid en keypress så kommer den söka upp specifikt denna klass
		GlobalScreen.addNativeKeyListener(new Keyboard_handler());        
    }
    
    
    
    // keyhandler faktiskt
    
    public static void Kupdate() {
    	switch(keyq[0]) {
    	case('w'): {
    		Game.tpposy -= 1;
    		break;
    	}
    	case('a'): {
    		Game.tpposx -= 1;
    		break;
    	}
    	case('s'): {
    		Game.tpposy += 1;
    		break;
    	}
    	case('d'): {
    		Game.tpposx += 1;
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
    // Key nertryckt
	public void nativeKeyPressed(NativeKeyEvent e) {		
		char[] temp = new char[5];		
		if(ciq == 0) {
			keyq[0] = NativeKeyEvent.getKeyText(e.getKeyCode()).charAt(0);
		} else {
			if (ciq == 5) {
				keyq[4] = '*';
				ciq--;
			}
			temp[0] = NativeKeyEvent.getKeyText(e.getKeyCode()).charAt(0);
			
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
	public void nativeKeyReleased(NativeKeyEvent e) {
		char[] temp = new char[5];
		byte p = 0;
		try {
		while(keyq[p] != NativeKeyEvent.getKeyText(e.getKeyCode()).charAt(0) && p < 5) {
			logger.Logcreator.Logbuilder(String.valueOf(keyq[p]));
			logger.Logcreator.Logwriter();
			temp[p] = keyq[p];
			p++;
		}
		if(p != 4) {
		    for(byte i = p; i <= 3; i++) {
		    	System.out.println(i);
			    temp[i] = keyq[i+1];
		    }
		} else {
			temp[4] = '*';
		}		
		} catch(Exception es) {
			es.printStackTrace();
		}
	}	
}