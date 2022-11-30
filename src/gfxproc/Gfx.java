package gfxproc;

import java.io.IOException;
public class Gfx {
	public static int sizex, sizey;
	public static Segment[] pix;
	public static void main(String[] args) {
		if (args.length >= 1) {
		switch(args[0]) {
		    case ("set"): {
		        sizex = Integer.parseInt(args[1]);
		        sizey = Integer.parseInt(args[2]);
		        pix = new Segment[sizex*sizey];
		        
		        
		        Seghand("cr", 0, 0, ' ');
                break;
		    }
		    default: {
		    	
		    	break;
		    }
		
	    }
		
		}
	}
	
	public static void Seghand(String ar1, int ar2, int ar3, char ar4) {
		switch (ar1) {
		    case ("cr"): {
		    	for(int i = 0; i < sizex*sizey; i++) {
		    		try {
		    			
		    		pix[i] = new Segment(i);
		    		
		    		} catch(Exception e) {
		    			System.out.println(e);
		    		}
		    	}		    		
		    	break;
		    }
		    case ("chng"): {
		    	game.Game.lg.Logbuilder("  ** CHNG SEGHAND: X: " + ar2 + " Y: " + ar3 + " **  ");
		    	int id = sizex*(ar3)+ar2;
		    	pix[id].symbol = ar4;
		    	
		    	break;
		    }
		    case ("clr"): {
		    	int id = sizex*(ar3)+ar2;
		    	pix[id].symbol = ' ';
		    	break;
		    }
		    default: {
		    	game.Game.lg.Logbuilder(" **  AT SEGHAND:  INVALID SEGHAND AR1 PASSED TO SWITCH  **  ");
				break;
			}   	
		}		
	}
	public static void CLS(){

	    
	    try {
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (IOException | InterruptedException ex) {}

	    }

	public static class Segment {
		public char symbol;		
		int id;
		public Segment(int id) {			
			this.id = id;
			this.symbol = ' ';
		}				
		}
	public class Graph {
		
	}
}


