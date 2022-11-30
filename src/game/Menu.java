package game;
// ska hantera en menyseqvens, inv osvs

import  gfxproc.Gfx;
/// ska rita på en bit av skärmen, nedifrånn, pausa spelet, 
public class Menu {
    byte height, width, sh, sw;
    String[] menuItems;
    int currentlySelected;
	public Menu() {
		height = 5;
		width = 80;
		sh = 3;
		sw = 19;
		
		menuItems = new String[4];
		menuItems[0] = game.Game.player.name;
		menuItems[1] = " Invetory";
		menuItems[2] = "Save Game";
		menuItems[3] = "Exit Game";
		game.Game.state = 3;
	}
	
	public void drawMenu() {
	     Gfx.drawBox(0, 35, width-1, (34+height), "solid", true);    
	    for(int i = 0; i < 4; i++) {
	    	int margin = ((19-menuItems[i].length())/2);
	    	Gfx.text(((i*sw)+2+margin), 37, menuItems[i]);
	    }
	   
	   
	   
	   
	 //game.Game.cMenu.drawMenu();
		String r = Gfx.segToString();
		game.Update.CLS();
		System.out.print(r);	
	}

}



// unused
//Gfx.drawBox((i*sw+4), 35, ((i*sw+3)+sw), 38, "dotted", true );
//int padding = Math.round((16-menuItems[i].length())/2);
//int padding = 0;