package game;
import gfxproc.Gfx;
public class Player {
	int x, y;
	byte cs;
	String name;
	String[] standardSprites;
    public Player() {
    	// Fyra positioner, upp, ner, vänster, höger. Alla med 3 stycken stegtyper
    	standardSprites = new String[12];
        for(int i = 0; i < 12; i++) {
        	String[] a = {String.valueOf((i+1)), "player"};
        	standardSprites[i] = Gfx.SpriteHandler.SpriteS(a);
        }
        
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
    public String getSprite(byte c) {
    	return(standardSprites[c]);
    }
    public class PlayerSprite
}
