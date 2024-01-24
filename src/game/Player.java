package game;
import gfxproc.Gfx;
import xmlHandler.XmlHandler;

import org.w3c.dom.Node;

import items.Item;
// föräldrarklassen, i princip innehåller alla små funktioner och klasser / attribut en spelare kan ha. Tex en spelare kan ha stats (altså en ny klass stats), samt kan en spelare ha en sprite (klassen playersprite).
public class Player {
	int x, y;
    public PlayerSprite pls;
	public String name;
	public int aQ;
	public Item[] equipped;
	public double hp;
	public double dmg;
	public double sp, bhp;
	public double bdmg;
	public double bsp;
	
	public static XmlHandler xHand;
    
	public Item[] inventory;
	int itemAmount;
	int nW, nC, nM;
	Item[] itemA;
    public Player(boolean init) {
    	// STILLA NERÅT POs
    	xHand = new XmlHandler("items", false);
    	// Denna ska var temp TODO göra denna dynamisk
    	
    	
    	// sex
    	pls = new PlayerSprite();
    	pls.cs = 3;
    	aQ = 2;
    	
    	if(init) {
    	// värden av init
    		// Arrays så jag kan bestämma mer än siffror, egentligen bör jag ha 
    		name = "Jonas";
    		bhp = 20;
    		bdmg = 4;
    		bsp = 1;
    		hp = bhp;
    		dmg = bdmg;
    		sp = bsp;
    		// detta är för items
    		LoadItemsFromXML();
    		this.nW = xHand.NodeCounter(null, "wearable");
    		this.nC = xHand.NodeCounter(null, "consumable");
    		this.nM = xHand.NodeCounter(null, "quest");
    		// Itemarray är en array av alla items vi har, som kan kommas åt vid behov.
    		itemA = new Item[(this.nW+this.nC+this.nM)];
    		
    		inventory = new Item[99];
    		createItems();
    		itemAmount = itemCounter();
    		// plats 0 = dräkt,1 = amulet, 2 = vapen
    		equipped = new Item[3];

    	} else {
    		
    		inventory = new Item[99];
    	}
    	
    	
    }
    
    // FUNKTIONER AV FÖRÄLDRAKLASSEN
    // tar bort ett föremål från inv och updaterar counten av föremål i inv
    public void removeItem(int item) {
    	int marker = -1;
    	itemAmount = itemCounter();
      for(int i = 0; i < itemAmount; i++) {
    	  if(inventory[i].getId() == item) {
    		  marker = i;
    		  inventory[i] = null;
    		  i = itemAmount;
    	  }
    	  
      }
      if(marker != -1) {
          for(int i = marker; i < itemAmount-1; i++) {
    	      inventory[i] = inventory[i+1];
    	      inventory[i+1] = null;
          }
          
      }
      this.itemAmount = itemCounter();
      
      
    }
    
    
    // debug funktion som fyller inventoryt med alla möjliga föremål
    	public void debugFillInv() {
		int is = 0;
		for(Item obj : itemA) {
			game.Game.player.inventory[is] = obj;
			is++;
		};
	}
    	
    // läser in alla föremål som kan finnas in i minne från xml fil.
	public void createItems() {
		int i1 = 0;
		for(int i = 0; i < this.nW; i++) {
			Node w = xHand.NR("wearable", i);
			switch(w.getAttributes().getNamedItem("class").getTextContent()) {
			case("amulet"): {
				itemA[i1] = new items.Amulet(w);
				break;
			}
			case("suit"): {
				itemA[i1] = new items.Suit(w);
				break;
			}
			case("weapon"): {
				itemA[i1] = new items.Weapon(w);
				break;
			}
			
			}
			i1++;
		}
		for(int i = 0; i < this.nC; i++) {
			Node w = xHand.NR("consumable", i);
			switch(w.getAttributes().getNamedItem("class").getTextContent()) {
			case("health"): {
				itemA[i1] = new items.HealthPotion(w);
				i1++;
				break;
			}
			case("strength"): {
				itemA[i1] = new items.DamagePotion(w);
				i1++;
				break;
			}
			
			
			
			}
			
		}
	}
	// räknar mängden föremål i inventoryt
	public int itemCounter() {
		int count = 0;
		for(Item ob : this.inventory) {
			if(ob != null) {
				count++;
			}
		}
		return(count);
	}
	// läser in items.xml via xHand.
    public void LoadItemsFromXML() {
    	xHand = new XmlHandler("items", false);
    }	
    // KLASSER
    
    // Hanterar spelarspriten, vilken sprite som ska visas
    public class PlayerSprite {
    	String[] standardSprites;
    	byte cmPos;
        byte cs;
        // Laddar in alla spelarsprites från xml
    	public PlayerSprite() {
    		
    		standardSprites = new String[12];
            for(int i = 0; i < 12; i++) {
            	String[] a = {String.valueOf((i+1)), "player"};
            	standardSprites[i] = Gfx.SpriteHandler.SpriteS(a);
            	game.Game.lg.Logbuilder(standardSprites[i]);
            }          
    	}
    	// Skcikar spriten som är den nuvarande.
    	public String cSprite() {
    		//game.Game.lg.Logbuilder(" CSPRITE: " + cs + " * " + cmPos + " * ") ;
    		return(standardSprites[cs]);
    	}
    	// i princip det som byter spelarsprites mellan varje steg, och riktning. Fungerar på att det bestämt finns 12 sprites.
    	// När 
    	public void WLAE(char s) {
    		if(game.Keyboard_handler.cK) {
    			cmPos = 0;
    			game.Keyboard_handler.cK = false;
    		}
    		switch(s) {
    		case('W'): {
    			if(cmPos >= 3) {
    				cmPos = 0;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		
    		case('A'): {
    			if(cmPos < 6 || cmPos >= 8) {
    				cmPos = 6;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		
    		case('S'): {	
    			if(cmPos < 3 || cmPos >= 5) {
    				cmPos = 3;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    		    break;
    		}
    		
    		case('D'): {
    			if(cmPos < 9 || cmPos >= 11) {
    				cmPos = 9;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		
    		
    	}
    	}
    	
    }
    
    
    // Misc
    
}
