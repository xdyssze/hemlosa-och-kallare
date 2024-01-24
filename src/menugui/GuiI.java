package menugui;
// bestämmer funktionerna av de olika menyklasserna
public abstract class GuiI {
	public byte height, width, sh, sw, iw, ih;
	public int currentlySelected;
	
	GuiI() {
		game.Game.state = 3;
		this.currentlySelected = 0;
	}
	
    abstract void drawMenu();
    abstract void action();   
    // kollar om det går att röra på sig åt ett håll ,så inte saker går out of bounds.
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
}
