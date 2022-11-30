package gfxproc;


import org.w3c.dom.Node;

import game.Game;

public class Maphandler {	
	public static byte nmap, cmap;
	int xm, ym;
	static Map[] mp;
	xmlHandler.XmlHandler xHand;
    public Maphandler(byte ns, byte cs) {  
       nmap = ns;
       cmap = cs;
       xHand = new xmlHandler.XmlHandler("maps", false);
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
    
    public class Map {
    	String m, mp, mn;
    	int ms;
    	public Map(byte id) {

    		try {		
    			Node mN = xHand.NR("map" + id, 0);
    			m = xHand.getNodeText("mapstring", mN);
    			mp = xHand.getNodeText("mappathmap", mN);
    			mn = xHand.getNodeText("mapname", mN);
    			ms = Integer.valueOf(xHand.getNodeText("mapsize", mN));
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    }
    public static String Mappas(int x, int y) {
    	// jag vill köra en forloop och läsa av alla värden av ny pos på kartan, kolla skillnaden från gamla värde till nya
    	// Retarded funktion som gör att jag slipper läsa hela nya spelarpositionen utan bara den lilla biten som har ngt med pos att göra.
    	String f = "";
    	// KOLLAR om x är denna riktning, åt höger då x + 1, notera att den nya tpposx har inte bluvit satt ännu
        if(x == (Game.tpposx+1)) {
        	// går igenom alla världen på högra sidan av karaktären, om den innehåller någon av de sakerna som specifieras innan så körs specifika funtkoiner.
    		for(int i = 0; i < 8; i++) {
    			// kollar från kartstringen vad som finns på den positionen.
    			f += String.valueOf(mp[cmap].mp.charAt(320*(y+i)+(x+7)));
    			
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
    			f += String.valueOf(mp[cmap].mp.charAt(320*(y+7)+x+i));
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
    }
    
}
