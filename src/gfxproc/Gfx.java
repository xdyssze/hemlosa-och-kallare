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
		        
		        String[] ar = {"cr"};
		        Seghand(ar);
                break;
		    }
		    default: {
		    	
		    	break;
		    }
		
	    }
		
		}
	}
	
	public static void Seghand(String[] args) {
		switch (args[0]) {
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
		    	game.Game.lg.Logbuilder("chng " + Integer.parseInt(args[1]) + " " + Integer.parseInt(args[2]));
		    	int id = sizex*(Integer.parseInt(args[2]))+Integer.parseInt(args[1]);
		    	pix[id].symbol = args[3].charAt(0);
		    	
		    	break;
		    }
		    case ("clr"): {
		    	int id = sizex*(Integer.parseInt(args[2]))+Integer.parseInt(args[1]);
		    	pix[id].symbol = ' ';
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
}


