package menugui;
// ska hantera en menyseqvens, inv osvs

import  gfxproc.Gfx;
/// ska rita på en bit av skärmen, nedifrånn, pausa spelet, 
public class EscGUI {
	byte height, width, sh, sw, iw, ih;
    public String[] menuItems;
    char[] keyEnabled;
	public EscGUI() {
		// ställer in lite or värden
		menuItems = new String[4];
		menuItems[0] = game.Game.player.name;
		menuItems[1] = " Invetory";
		menuItems[2] = "Save Game";
		menuItems[3] = "Exit Game";
		height = 5;
		width = 80;
		sh = 3;
		sw = 19;
		iw = 4;
		ih = 1;
		
	}
	
	public void drawMenu(int currentlySelected) {
		// ritar alla lådor baserat på vad som är selected
	     Gfx.drawBox(0, 35, width-1, (34+height), "solid", true);  
	     Gfx.drawBox((currentlySelected*sw)+2, 36,(currentlySelected*sw)+21 , 38, "solid", true);
	    for(int i = 0; i < 4; i++) {
	    	int margin = ((19-menuItems[i].length())/2);
	    	Gfx.text(((i*sw)+2+margin), 37, menuItems[i]);
	    }	   
	    // i princip uppdaterar skärmen enskildt från allt annat så alla processer kan vara stoppade
		String r = Gfx.segToString();
		game.Update.CLS();
		System.out.print(r);	
	}
	// action ba
	public void action(int currentlySelected) {
		switch(currentlySelected) {
		case(0): {
			game.Game.menhand.setGUI("char");
			game.Game.menhand.currentlySelected = 0;
			game.Game.menhand.drawGUI();
			break;
		}
		case(1): {
			game.Game.menhand.setGUI("inv");
			game.Game.menhand.currentlySelected = 0;
			game.Game.menhand.drawGUI();
			break;
		}
		case(3): {
			System.exit(0);
			break;
		}
		}
	}

}


// unused
//Gfx.drawBox((i*sw+4), 35, ((i*sw+3)+sw), 38, "dotted", true );
//int padding = Math.round((16-menuItems[i].length())/2);
//int padding = 0;