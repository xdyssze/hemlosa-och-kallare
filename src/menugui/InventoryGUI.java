package menugui;

import gfxproc.Gfx;

public class InventoryGUI {
	public byte height, width, sh, sw, ih, iw;
	int itemCount;
	
	public InventoryGUI() {
		itemCount = game.Game.player.iH.itemCounter();
		ih = 4;
		iw = 5;
		// 16 px bred, 8 px hög e varje låda
		// TODO Auto-generated constructor stub
	}
	// egentligen är detta svinretarded och jag ska bara flytta rutan men eh, jag orkar inte programera något som tar bort rutorna utan att refresha skärmen.
    public void drawMenu(int cs) {
    	// ritar föremål i inventory, varje objekt 16 px bred, 8 px hög.
    	
    	Gfx.drawBox(16*(cs%iw), 8*((cs-(cs%iw))/iw), (16*(cs%iw))+15, (8*((cs-(cs%iw))/iw))+7, "solid", true);
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
    	if(game.Game.player.inventory[cs] != null) {
    	Gfx.text(2, 34, game.Game.player.inventory[cs].desc);
    	if(game.Game.player.inventory[cs].proc) {
    		Gfx.text(16, 36, ((Integer.valueOf(game.Game.player.inventory[cs].effect)*100) + "%"));
    	} else {
    		Gfx.text(16, 36, game.Game.player.inventory[cs].effect);
    	}
    	Gfx.text(2, 36, (game.Game.player.inventory[cs].effecttype + ":"));
    	Gfx.text(32, 36, "type: " + game.Game.player.inventory[cs].itemtype);
    	
    	}
    	String r = Gfx.segToString();
		game.Update.CLS();
		System.out.print(r);	
    }
    public void action(int cs) {
    	boolean eq = false;
    	for(game.Itemhandler.Item obj : game.Game.player.equipped ) {
    		if(obj != null && obj.equals(game.Game.player.inventory[cs])) {
    			game.Game.player.iH.dequip(obj);
    			eq = true;
    			break;
    		}
    	}
    	if(!eq) {
    		game.Game.player.iH.equip(game.Game.player.inventory[cs]);
    	}
    	
    	String r = Gfx.segToString();
		game.Update.CLS();
		System.out.print(r);	
    }
}
