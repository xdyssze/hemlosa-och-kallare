package game;
import gfxproc.Gfx;
import xmlHandler.XmlHandler;

import org.w3c.dom.Node;

import items.Item;
// föräldrarklassen, i princip innehåller alla små funktioner och klasser / attribut en spelare kan ha. Tex en spelare kan ha stats (altså en ny klass stats), samt kan en spelare ha en sprite (klassen playersprite).
public class Player {
	int x, y;
    public PlayerSprite pls;
    public Itemhandler iH;
	public String name;
	public int aQ;
	public Item[] equipped;
	public double hp;
	public double dmg;
	public double sp, bhp;
	public double bdmg;
	public double bsp;
	
	public static XmlHandler xHand;
    StatEffect[] activeEffects;
	public Item[] inventory;
	int itemAmount;
	int nW, nC, nM;
	Item[] itemA;
    public Player(boolean init) {
    	// STILLA NERÅT POs
    	xHand = new XmlHandler("items", false);
    	// Denna ska var temp TODO göra denna dynamisk
    	itemAmount = 5;
    	
    	// sex
    	pls = new PlayerSprite();
    	pls.cs = 3;
    	aQ = 2;
    	activeEffects = new StatEffect[20];
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
    		// plats 0 = dräkt,1 = amulet, 2 = vapen
    		equipped = new Item[3];
    	} else {
    		
    		inventory = new Item[99];
    	}
    	
    	
    }
    
    // FUNKTIONER AV FÖRÄLDRAKLASSEN
    public void healthEffect(boolean neg, boolean proc, double effect) {
    	if(proc) {
    		if(neg) {
        	    hp = ((hp/100)*(100-effect));
        	} else {
        		 hp = ((hp/100)*(100+effect));
        	}
	    } else {
	    	if(neg) {
	    		hp -= effect;
	    	} else {
	    		hp += effect;
	    	}
	    }
    	
    }
    
    public void removeItem(int item) {
    	int marker = -1;
      for(int i = 0; i < itemAmount; i++) {
    	  if(inventory[i].getId() == item) {
    		  marker = i;
    		  inventory[i] = null;
    		  i = itemAmount-1;
    	  }
    	  
      }
      if(marker != -1) {
          for(int i = marker; i < itemAmount-1; i++) {
    	      inventory[i] = inventory[i+1];
          }
      }
    }
    
    
    public void damageEffect(boolean neg, boolean proc, double effect) {
    	if(proc) {
    		if(neg) {
        	    dmg = ((dmg/100)*(100-effect));
        	} else {
        		 dmg = ((dmg/100)*(100+effect));
        	}
	    } else {
	    	if(neg) {
	    		dmg -= effect;
	    	} else {
	    		dmg += effect;
	    	}
	    }
    	
    }
    // detta går att förbättras med inhertance och föräldraklasser. Alternativt att en effect är en funktion i princip, med enkel beräkning.
    /*
    public void calculateStat() {
    	double con1, con2, con3;
    	con1 = bhp;
    	con2 = bdmg;
    	con3 = bsp;
    	
    	// SÅ ATT VI RÄKNAR PROC FÖRST
    	for(StatEffect obj : activeEffects) {
    		if(obj != null) {
    		    if(obj.proc) {
        	    switch(obj.efOn) {
        	        case("dmg"): {
            		    if(obj.neg) {
                	        con2 = ((bdmg/100)*(100-(Double.valueOf(obj.value))));
                	    } else {
                		     con2 = ((bdmg/100)*(100+(Double.valueOf(obj.value))));
                	    }
        		        break;
        	        }
        	        case("hp"): {
        	        	if(obj.neg) {
                	        con1 = ((bhp/100)*(100-(Double.valueOf(obj.value))));
                	    } else {
                		     con1 = ((bhp/100)*(100+(Double.valueOf(obj.value))));
                	    }
        		        break;
        	        }
        	        case("sp"): {
        	        	if(obj.neg) {
                	        con3 = ((bsp/100)*(100-(Double.valueOf(obj.value))));
                	    } else {
                		    con3 = ((bsp/100)*(100+(Double.valueOf(obj.value))));
                	    }
        		        break;
        	        }
        	    }
    		}
    		}
        }
    	
    	for(StatEffect obj : activeEffects) {
    		if(obj != null) {
    		if(!obj.proc) {
        	switch(obj.efOn) {
        	case("dmg"): {
        	    	if(obj.neg) {
        	    		con2 -= (Double.valueOf(obj.value));
        	    	} else {
        	    		con2 += (Double.valueOf(obj.value));
        	    	}
        	    
        		break;
        	}
        	case("hp"): {
    	    	if(obj.neg) {
    	    		con1 -= (Double.valueOf(obj.value));
    	    	} else {
    	    		con1 += (Double.valueOf(obj.value));
    	    	}
        		break;
        	}
        	case("sp"): {
    	    	if(obj.neg) {
    	    		con3 -= (Double.valueOf(obj.value));
    	    	} else {
    	    		con3 += (Double.valueOf(obj.value));
    	    	}
        		break;
        	}
        	}
    		}
        }
    	}
    	hp = con1;
    	dmg = con2;
    	sp = con3;
    	
    	
     }
     public void addEffect(StatEffect s) {
    	activeEffects[addToEnd()] = s;
    	reOrgStat();
    	calculateStat();
    }
    public StatEffect findEffect(String s) {
    	for(StatEffect obj : activeEffects) {
    	    if(obj != null){
    	    	if(obj.name.equals(s)) {
    	    		return(obj);
    	    	}
    	    }
    	}
    	return(null);
    }
    public void removeEffect(StatEffect s)  {
    	for(byte i = 0; i < activeEffects.length; i++) {
    		if(activeEffects[i] != null && activeEffects[i].equals(s)) {
    			activeEffects[i] = null;
    		}
    	}
    	reOrgStat();
    	calculateStat();
    }
    
    
    */
    
    
    
    
    
    // undra ifall det inte är smartare att bara göra om allt ist, Stateffect är i princip onödigt.
    // man kan ist bara ha items som equipas och dequipas, och 
    
    
    
	// skapar föremål genom att gå igenom alla xmlnoder och ta deras atribut, sedan stoppa in dem i ett nytt item som går rakt in i itemarrayen.
	public void debugFillInv() {
		int is = 0;
		for(Item obj : itemA) {
			game.Game.player.inventory[is] = obj;
			is++;
		};
	}
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
				break;
			}
			case("strength"): {
				itemA[i1] = new items.DamagePotion(w);
				break;
			}
			
			
			}
			i1++;
		}
		for(int i = 0; i < this.nM; i++) {
			Node w = xHand.NR("quest", i);
		    itemA[i1] = new items.Quest(w);
			i1++;
		}
		
	}	
	
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
    static public class StatEffect {
    	
    	byte state;
    	boolean neg, proc; 
    	String efOn, name, desc, value;
    	public StatEffect(String nam, String des, String efO, String v, boolean ne, boolean pro) {
    		if(!nam.isEmpty()) {
    			this.value = v;
    			this.proc = pro;
    			this.neg = ne;
        		this.efOn = efO;
        		this.name = nam;
        		this.desc = des;
        		this.state = 1;
    		} else {
    			this.state = 0;
    		}
    		
    		
    	}
    }
    
    
    
    
    // spelarsprites
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
    		//case('*'): {
    		//	cs = 3;
    		//	break;
    		//}
    		
    	}
    	}
    	
    }
    
    
    // Misc
    public int addToEnd() {
    	for(int i = 0; i < activeEffects.length; i++) {
			   if(activeEffects[i] == null) {
				   return(i);
			   }			   
		   }
    	return(0);
    }
    public void reOrgStat() {
    	StatEffect[] temp = new StatEffect[20];
    	byte temps = 0;
    	for(byte i = 0; i < activeEffects.length; i++) {
    		if(activeEffects[i] != null) {
    			temp[temps] = activeEffects[i];
    			temps++;
    		}
    	}
    	activeEffects = temp;
    }
    
}
