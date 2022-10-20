package game;
import gfxproc.Gfx;
public class Player {
	int x, y;
    public PlayerSprite pls;
	String name;
    public Player() {
    	// STILLA NERÅT POs
    	pls = new PlayerSprite();
    	pls.cs = 3;
    	// Fyra positioner, upp, ner, vänster, höger. Alla med 3 stycken stegtyper
    }
    public class Stats {
    	public Stats() {
    		
    	}
    	public class Health {
    		double max, current;
    		
    	}
    	public class Damage {
    		double max, current;
    		
    	}
    	public class Speed {
    		double max, current;
    	}
    }
    
    public class PlayerSprite {
    	String[] standardSprites;
    	byte cmPos;
        byte cs;
    	public PlayerSprite() {
    		
    		standardSprites = new String[12];
            for(int i = 0; i < 12; i++) {
            	String[] a = {String.valueOf((i+1)), "player"};
            	standardSprites[i] = Gfx.SpriteHandler.SpriteS(a);
            }          
    	}
    	public String cSprite() {
    		return(standardSprites[cs]);
    	}
    	public void WLAE(char s) {
    		if(game.Keyboard_handler.cK) {
    			cmPos = 0;
    			game.Keyboard_handler.cK = false;
    		}
    		switch(s) {
    		case('W'): {
    			if(cmPos < 3) {
    				cmPos = 0;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		
    		case('A'): {
    			if(cmPos < 9) {
    				cmPos = 6;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		
    		case('S'): {	
    			if(cmPos < 6) {
    				cmPos = 3;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    		    break;
    		}
    		
    		case('D'): {
    			if(cmPos < 12) {
    				cmPos = 9;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		case('*'): {
    			cs = 3;
    			break;
    		}
    		
    	}
    	}
    	
    }
}
