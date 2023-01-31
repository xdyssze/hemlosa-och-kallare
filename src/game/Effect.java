package game;

public class Effect {

	public static void main(String[] s) {
		// TODO Auto-generated constructor stub
	}
	
	
	// Klasser
    static public class StatEffect { 	
    	byte state;
    	boolean neg, proc; 
    	String efOn, name, desc, img, value;
    	public StatEffect(String nam, String des, String efO, String v, boolean ne, boolean pro) {
    		if(!nam.isEmpty()) {
    			this.value = v;
    			this.proc = pro;
    			this.neg = ne;
        		this.efOn = efO;
        		this.name = nam;
        		this.desc = des;
        		this.state = 1;
    		} else {
    			this.state = 0;
    		}
    		
    		
    	}
    }
}
