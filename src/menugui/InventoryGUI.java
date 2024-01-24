package menugui;

import gfxproc.Gfx;

public class InventoryGUI extends GuiI {
	
	int itemCount;
	
	public InventoryGUI() {
		super();
		calculateItemWidth();
	    game.Game.lg.Logbuilder("\r\n Mängd föremål enligt gui:" + itemCount);
	    game.Game.lg.Logwriter();
		this.ih = 5;
		// 16 px bred, 8 px hög e varje låda
		// TODO Auto-generated constructor stub
	}
	// egentligen är detta svinretarded och jag ska bara flytta rutan men eh, jag orkar inte programera något som tar bort rutorna utan att refresha skärmen.
	@Override
    public void drawMenu() {
		Gfx.clearSec();
		String[] currentString = {};
    	// ritar föremål i inventory, varje objekt 16 px bred, 8 px hög.
		try {
    	currentString = game.Game.player.inventory[currentlySelected].getInfo();
    	Gfx.drawBox(16*(currentlySelected%iw), 8*((currentlySelected-(currentlySelected%iw))/iw), (16*(currentlySelected%iw))+15, (8*((currentlySelected-(currentlySelected%iw))/iw))+7, "solid", true);
    	for(int z = 0; z < 4; z++) {
    	    for(int i = 0; i < 5; i++) {    

    	    	if(i+(z*5) < itemCount) {
    	    		try {
    		    Gfx.text(1+(i*16), 1+(z*8), game.Game.player.inventory[i+(z*5)].getName());
    		    for(items.Item obj : game.Game.player.equipped) {
    		    	
    		    	if(obj != null && obj.equals(game.Game.player.inventory[i+(z*5)])) {
    		    		Gfx.text(1+(i*16), 6+(z*8), "#");
    		    		break;
    		    	};
    		    	
    		    }
    		    
    	    	} catch(Exception e) {
    	    		e.printStackTrace();
    	    	}
    	    	}
    	    }
    	}
		} catch(Exception e) {
			e.printStackTrace();
		}
    	// ritar desc ruta
    	try {
    	Gfx.drawBox(0, 32, 79, 39, "solid", true);
    	if(game.Game.player.inventory[currentlySelected] != null) {
    		// desc
    	Gfx.text(2, 34, currentString[3]);
    	// effect
    	if(currentString[0] == "true") {
    		Gfx.text(16, 36, ((int)((Double.valueOf(currentString[4])*100)) + "%"));
    	} else {
    		Gfx.text(16, 36, currentString[4]);
    	}
    	// effecttyp
    	
    	//Gfx.text(2, 36, (game.Game.player.inventory[currentlySelected].effecttype + ":"));
    	// föremålstyp
    	Gfx.text(32, 36, "type: " + currentString[6]);
    	
    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	String r = Gfx.segToString();
    	
		game.Update.CLS();
		System.out.print(r);	
		
    }
	
	@Override
    public void action() {
    	boolean eq = false;
    	
    	for(items.Item obj : game.Game.player.equipped ) {
    		if(obj != null && obj.equals(game.Game.player.inventory[this.currentlySelected])) {
    			
    			obj.dequip();
    			eq = true;
    			break;
    		}
    	}
    	if(!eq) {
    		game.Game.player.inventory[currentlySelected].equip();
    	}
    	calculateItemWidth();
    	drawMenu();
    }
	public void calculateItemWidth() {
		this.itemCount = game.Game.player.itemCounter();
		if(this.itemCount <= 4) {
			this.iw = (byte) this.itemCount;
		} else {
			this.iw = 4;
		}
	}
}
