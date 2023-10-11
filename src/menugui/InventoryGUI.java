package menugui;

import gfxproc.Gfx;

public class InventoryGUI extends GuiI {
	
	int itemCount;
	
	public InventoryGUI() {
		super();
		itemCount = game.Game.player.iH.itemCounter();
		ih = 4;
		iw = 5;
		// 16 px bred, 8 px hög e varje låda
		// TODO Auto-generated constructor stub
	}
	// egentligen är detta svinretarded och jag ska bara flytta rutan men eh, jag orkar inte programera något som tar bort rutorna utan att refresha skärmen.
	@Override
    public void drawMenu() {
		Gfx.clearSec();
    	// ritar föremål i inventory, varje objekt 16 px bred, 8 px hög.
    	
    	Gfx.drawBox(16*(currentlySelected%iw), 8*((currentlySelected-(currentlySelected%iw))/iw), (16*(currentlySelected%iw))+15, (8*((currentlySelected-(currentlySelected%iw))/iw))+7, "solid", true);
    	for(int z = 0; z < 4; z++) {
    	    for(int i = 0; i < 5; i++) {    	 
    	    	if(i+(z*5) < itemCount) {
    		    Gfx.text(1+(i*16), 1+(z*8), game.Game.player.inventory[i+(z*5)].name);
    		    for(game.Itemhandler.Item obj : game.Game.player.equipped) {
    		    	
    		    	if(obj != null && obj.equals(game.Game.player.inventory[i+(z*5)])) {
    		    		Gfx.text(1+(i*16), 6+(z*8), "#");
    		    		break;
    		    	};
    		    	
    		    }
    		    
    	    	}
    	    }
    	}
    	// ritar desc ruta
    	
    	Gfx.drawBox(0, 32, 79, 39, "solid", true);
    	if(game.Game.player.inventory[currentlySelected] != null) {
    	Gfx.text(2, 34, game.Game.player.inventory[currentlySelected].desc);
    	if(game.Game.player.inventory[currentlySelected].proc) {
    		Gfx.text(16, 36, ((Integer.valueOf(game.Game.player.inventory[currentlySelected].effect)*100) + "%"));
    	} else {
    		Gfx.text(16, 36, game.Game.player.inventory[currentlySelected].effect);
    	}
    	Gfx.text(2, 36, (game.Game.player.inventory[currentlySelected].effecttype + ":"));
    	Gfx.text(32, 36, "type: " + game.Game.player.inventory[currentlySelected].itemtype);
    	
    	}
    	String r = Gfx.segToString();
		game.Update.CLS();
		System.out.print(r);	
    }
	
	@Override
    public void action() {
    	boolean eq = false;
    	for(game.Itemhandler.Item obj : game.Game.player.equipped ) {
    		if(obj != null && obj.equals(game.Game.player.inventory[currentlySelected])) {
    			game.Game.player.iH.dequip(obj);
    			eq = true;
    			break;
    		}
    	}
    	if(!eq) {
    		game.Game.player.iH.equip(game.Game.player.inventory[currentlySelected]);
    	}
    	
    	String r = Gfx.segToString();
		game.Update.CLS();
		System.out.print(r);	
    }
}
