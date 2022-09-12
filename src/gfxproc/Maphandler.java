package gfxproc;

import game.Game;

public class Maphandler {	
	public static byte nmap, cmap;
	int xm, ym;
	Map[] mp;
    public void main(String[] args) {  
       
 
       mp = new Map[nmap];
       Game.lg.Logbuilder("  ** Map begin init **  ");
       for(byte i = 0; i < nmap; i++) {
    	   mp[i] = new Map(i);
       }
       
    }
    
    
    public void maprender() {
    	ym = (int)(Game.tpposy-((Gfx.sizey/2)-0.5));
        xm = (int)(Game.tpposx-((Gfx.sizex/2)-0.5));
    	for(int y = 0; y < Gfx.sizey; y++) {
    		for(int x = 0; x < Gfx.sizex; x++) {
    		    if(ym+y < 0 || ym+x < 0) {
    		    	Gfx.Seghand("chng", x, y, ' ');
    		    } else {
    			Gfx.Seghand("chng", x, y, mp[cmap].m.charAt(mp[cmap].ms*(ym+y)+ym+x));
    		    }
    		}
    	}   	    	
    }
    
}
