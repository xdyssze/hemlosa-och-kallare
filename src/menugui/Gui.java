package menugui;

import gfxproc.Gfx;

public class Gui {
	public byte height, width, sh, sw;
	public byte iw;
	byte ih;
    char[] keyEnabled;
    public int currentlySelected;
    String gType;
    menugui.EscGUI esc;
    menugui.CarachterGUI chr;
    menugui.InventoryGUI inv;
    menugui.MainMenuGUI mmen;
	public Gui(String gTyp) {
		this.gType = gTyp;
		setupGui();
	}
	void setupGui() {
		currentlySelected = 0;
		switch(gType) {
		case("esc"): {
			esc = new menugui.EscGUI();
			iw = esc.iw;
			ih = esc.ih;
			sw = esc.sw;
			sh = esc.sh;
			break;
		}
		case("char"): {
			chr = new menugui.CarachterGUI();
			iw = chr.iw;
			ih = chr.ih;
			sw = chr.sw;
			sh = chr.sh;
			break;
		}
		case("inv"): {
			inv = new menugui.InventoryGUI();
			iw = inv.iw;
			ih = inv.ih;
			sw = inv.sw;
			sh = inv.sh;
			break;
		}
		case("main"): {
			mmen = new menugui.MainMenuGUI();
			iw = mmen.iw;
			ih = mmen.ih;
			sw = mmen.sw;
			sh = mmen.sh;
			break;
		}
		}
		game.Game.state = 3;
	}
	public void action() {
		switch(gType) {
		case("esc"): {
			esc.action(currentlySelected);
			break;
		}
		case("char"): {
			break;
		}
		case("inv"): {
			inv.action(currentlySelected);
			break;
		}
		}
	}
	public boolean checkmv(char dir) {
		switch(dir) {
		case('a'): {
			int f = (currentlySelected)-1;
			if (f >= 0) {
				return(true);
			}
			break;
		}
		case('s'): {
			int f = (currentlySelected)+(iw);
			if (f < iw*ih) {
				return(true);
			}
			break;
		}
		case('d'): {
			int f = (currentlySelected)+1;
			if (f < iw*ih) {
				return(true);
			}
			break;
		}
		case('w'): {
			int f = (currentlySelected)-(iw);
			if (f >= 0) {
				return(true);
			}
			break;
		}
		}
		return(false);
	}
	public void draw() {
		switch(gType) {
		case("esc"): {
			esc.drawMenu(currentlySelected);
			break;
		}
		case("char"): {
			Gfx.clearSec();
			chr.drawMenu(currentlySelected);
			break;
		}
		case("inv"): {
			Gfx.clearSec();
			inv.drawMenu(currentlySelected);
			break;
		}
		}
	}
	
	
	

}
