package menugui;

import game.Player;
import gfxproc.Gfx;

public class CarachterGUI extends menugui.GuiI{
	
	
	public CarachterGUI() {
		super();

		ih = 3;
		iw = 1;
		
		 
	}
	@Override
    public void drawMenu() {
    	Gfx.clearSec();
    	
    	//ritar lådor på hörn med items i
        Gfx.drawBox(0, 0, 15, 7, "solid", true);
        Gfx.drawBox(0, 8, 15, 15, "solid", true);
        Gfx.drawBox(0, 16, 15, 23, "solid", true);
        for(int i = 0; i < 3; i++) {
        	if(game.Game.player.equipped[i] != null) {
        	Gfx.text(1, 1+(i*8), game.Game.player.equipped[i].getName());
        	}
        }
        
        //Charaktär
        Gfx.drawBox(16, 0, 35, 23, "solid", true);
        
        //Ritar stats:
        Gfx.text(36, 1, "DMG:" + game.Game.player.dmg);
        
        // ritar text
        Gfx.text(52, 1, "Quests");
        
        // Questboxes, varje låda är 24 px bred,
        for(int i = 0; i < game.Game.player.aQ; i++) {
        	Gfx.drawBox(39, 3+(i*8), 71, 9+(i*8), "solid", true);
        }
    	// uppdaterar skrämen
    	String r = Gfx.segToString();
		game.Update.CLS();
		System.out.print(r);	
		
		
		// ritar beskrivningsmenyn
    }
    @Override
    public void action() {
    	
    	
    }
    
}
