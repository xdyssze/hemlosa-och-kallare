package game;

public class Player {
	int x, y;
	byte cs;
	String name;
	String[] standardSprites;
    public Player() {
    	// Fyra positioner, upp, ner, vänster, höger. Alla med 3 stycken stegtyper
    	standardSprites = new String[12];
        
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
    	
    	return("");
    }
}
