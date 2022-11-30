package game;
import gfxproc.Gfx;
import game.Itemhandler;
import game.Itemhandler.Item;
// föräldrarklassen, i princip innehåller alla små funktioner och klasser / attribut en spelare kan ha. Tex en spelare kan ha stats (altså en ny klass stats), samt kan en spelare ha en sprite (klassen playersprite).
public class Player {
	int x, y;
    public PlayerSprite pls;
    public Itemhandler iH;
	String name;
	Item[] equipped;
	double hp, dmg, sp, bhp, bdmg, bsp;
    StatEffect[] activeEffects;
	Item[] inventory;
    public Player(boolean init) {
    	// STILLA NERÅT POs
    	
    	pls = new PlayerSprite();
    	pls.cs = 3;
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
    		iH = new Itemhandler();
    		inventory = new Item[99];
    		// plats 0 = dräkt,1 = amulet, 2 = vapen
    		equipped = new Item[3];
    	} else {
    		iH = new Itemhandler();
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
    public void calculateStat() {
    	double con1, con2, con3;
    	con1 = bhp;
    	con2 = bdmg;
    	con3 = bsp;
    	
    	// SÅ ATT VI RÄKNAR PROC FÖRST
    	for(int i = 0; i < activeEffects.length; i++) {
    		if(activeEffects[i].proc) {
        	    switch(activeEffects[i].efOn) {
        	        case("dmg"): {
            		    if(activeEffects[i].neg) {
                	        con2 = ((bdmg/100)*(100-(Double.valueOf(activeEffects[i].value))));
                	    } else {
                		     con2 = ((bdmg/100)*(100+(Double.valueOf(activeEffects[i].value))));
                	    }
        		        break;
        	        }
        	        case("hp"): {
        	        	if(activeEffects[i].neg) {
                	        con1 = ((bhp/100)*(100-(Double.valueOf(activeEffects[i].value))));
                	    } else {
                		     con1 = ((bhp/100)*(100+(Double.valueOf(activeEffects[i].value))));
                	    }
        		        break;
        	        }
        	        case("sp"): {
        	        	if(activeEffects[i].neg) {
                	        con3 = ((bsp/100)*(100-(Double.valueOf(activeEffects[i].value))));
                	    } else {
                		    con3 = ((bsp/100)*(100+(Double.valueOf(activeEffects[i].value))));
                	    }
        		        break;
        	        }
        	    }
    		}
        }
    	
    	for(int i = 0; i < activeEffects.length; i++) {
    		if(!activeEffects[i].proc) {
        	switch(activeEffects[i].efOn) {
        	case("dmg"): {
        	    	if(activeEffects[i].neg) {
        	    		con2 -= (Double.valueOf(activeEffects[i].value));
        	    	} else {
        	    		con2 += (Double.valueOf(activeEffects[i].value));
        	    	}
        	    
        		break;
        	}
        	case("hp"): {
    	    	if(activeEffects[i].neg) {
    	    		con1 -= (Double.valueOf(activeEffects[i].value));
    	    	} else {
    	    		con1 += (Double.valueOf(activeEffects[i].value));
    	    	}
        		break;
        	}
        	case("sp"): {
    	    	if(activeEffects[i].neg) {
    	    		con3 -= (Double.valueOf(activeEffects[i].value));
    	    	} else {
    	    		con3 += (Double.valueOf(activeEffects[i].value));
    	    	}
        		break;
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
    
    public void removeEffect(StatEffect s)  {
    	for(byte i = 0; i < activeEffects.length; i++) {
    		if(activeEffects[i].equals(s)) {
    			activeEffects[i] = null;
    		}
    	}
    	reOrgStat();
    	calculateStat();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    // KLASSER
    static public class StatEffect {
    	
    	byte state;
    	boolean neg, proc; 
    	String efOn, name, desc, img, value;
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
    	public PlayerSprite() {
    		
    		standardSprites = new String[12];
            for(int i = 0; i < 12; i++) {
            	String[] a = {String.valueOf((i+1)), "player"};
            	standardSprites[i] = Gfx.SpriteHandler.SpriteS(a);
            }          
    	}
    	public String cSprite() {
    		return(standardSprites[cs]);
    	}
    	public void WLAE(char s) {
    		if(game.Keyboard_handler.cK) {
    			cmPos = 0;
    			game.Keyboard_handler.cK = false;
    		}
    		switch(s) {
    		case('W'): {
    			if(cmPos < 3) {
    				cmPos = 0;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		
    		case('A'): {
    			if(cmPos < 9) {
    				cmPos = 6;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		
    		case('S'): {	
    			if(cmPos < 6) {
    				cmPos = 3;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    		    break;
    		}
    		
    		case('D'): {
    			if(cmPos < 12) {
    				cmPos = 9;
    			}
    			cs = (byte)(cmPos);
    			cmPos++;
    			break;
    		}
    		case('*'): {
    			cs = 3;
    			break;
    		}
    		
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
