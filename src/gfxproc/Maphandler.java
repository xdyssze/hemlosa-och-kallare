package gfxproc;

import game.Game;

public class Maphandler {	
	public static byte nmap, cmap;
	int xm, ym;
	static Map[] mp;
    public Maphandler(byte ns, byte cs) {  
       nmap = ns;
       cmap = cs;
 
       mp = new Map[nmap];
       Game.lg.Logbuilder("  ** Map begin init **  ");
       for(byte i = 1; i <= nmap; i++) {
    	   try {
    	       mp[i] = new Map(i);
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   }
       }
       
    }
    
    
    public static  String Mappas(int x, int y) {
    	// jag vill köra en forloop och läsa av alla värden av ny pos på kartan, kolla skillnaden från gamla värde till nya
    	// Retarded funktion som gör att jag slipper läsa hela nya spelarpositionen utan bara den lilla biten som har ngt med pos att göra.
    	String f = "";
        if(x == (Game.tpposx+1)) {
    		for(int i = 0; i < 8; i++) {
    			f += String.valueOf(mp[cmap].mp.charAt(320*(y+i)+(x+8)));
    			
    		}
    		logger.Logcreator.Logbuilder(" \r\n     CHAR AT:    " + f + "    END OF:    ");
            if(f.contains("1")) {
            	return("no");
            } else {
            	if(f.contains("2")) {
            		return("up");
            	} else if(f.contains("3")) {
            		return("down");
            	} else if(f.contains("4")) {
            		return("left");
            	} else if (f.contains("5")){
            		return("right");
            	}
            }
    		
      	    } else if (x == (Game.tpposx-1)){
      	    	for(int i = 0; i < 8; i++) {
        			f += String.valueOf(mp[cmap].mp.charAt(320*(y+i)+x));
        		}
      	    	logger.Logcreator.Logbuilder(" \r\n     CHAR AT:    " + f + "    END OF:    ");
                if(f.contains("1")) {
                	return("no");
                } else {
                	if(f.contains("2")) {
                		return("up");
                	} else if(f.contains("3")) {
                		return("down");
                	} else if(f.contains("4")) {
                		return("left");
                	} else if (f.contains("5")){
                		return("right");
                	}
                }
        		
    		
    	} else if (y == (Game.tpposy+1)) {
    		for(int i = 0; i < 8; i++) {
    			f += String.valueOf(mp[cmap].mp.charAt(320*(y)+x+i));
    		}
    		logger.Logcreator.Logbuilder(" \r\n     CHAR AT:    " + f + "    END OF:    ");
            if(f.contains("1")) {
            	return("no");
            } else {
            	if(f.contains("2")) {
            		return("up");
            	} else if(f.contains("3")) {
            		return("down");
            	} else if(f.contains("4")) {
            		return("left");
            	} else if (f.contains("5")){
            		return("right");
            	}
            }
    		
    		
    	} else if (y == (Game.tpposy-1)) {
    		for(int i = 0; i < 8; i++) {
    			f += String.valueOf(mp[cmap].mp.charAt(320*(y)+x+i));
    		}
    		logger.Logcreator.Logbuilder(" \r\n     CHAR AT:    " + f + "    END OF:    ");
            if(f.contains("1")) {
            	return("no");
            } else {
            	if(f.contains("2")) {
            		return("up");
            	} else if(f.contains("3")) {
            		return("down");
            	} else if(f.contains("4")) {
            		return("left");
            	} else if (f.contains("5")){
            		return("right");
            	}
            }
    		
    	} 
    	return("do");
    }
	public char maprender(int x, int y) {
		
		ym = (int)(Game.tpposy-16);
        xm = (int)(Game.tpposx-36);
		 if(ym+y < 0 || xm+x < 0 || xm+x > 319 || ym+y > 319) { 
			 return('*');
		 } else {
			 return(mp[cmap].m.charAt(mp[cmap].ms*(ym+y)+(xm+x))); 
		 }
       
        /*
    	for(int y = 0; y < Gfx.sizey; y++) {
    		for(int x = 0; x < Gfx.sizex; x++) {
    		    if(ym+y < 0 || xm+x < 0 || xm+x > 319 || ym+y > 319) { 
    		    	Gfx.Seghand("chng", x, y, '*');
    		    } else {
    		    	// 320*(y+ym)
    		    	// y = ym x = xm
    		    	//(320*y)+x
    			Gfx.Seghand("chng", x, y, mp[cmap].m.charAt(mp[cmap].ms*(ym+y)+(xm+x)));
    		    }
    		}
    	}   	 
        **/
    }
    
}
