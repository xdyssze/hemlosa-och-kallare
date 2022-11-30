package game;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import game.Game;
import game.Player.StatEffect;
import xmlHandler.XmlHandler;

public class Itemhandler {
    // DET FINNS 3 typer av föremål, knsk lägger till fler i framtiden. Karaktärsföremål, konsumerbara föremål och questföremål. Dessa är ännu en gång indelad i subclasser. För karaktärsföremål är detta kläder, vapen, halsband.
	// PÅ konsumerbara föremål finns det Kraftmat, styrkedryck, hälsedryck, negativaeasterföremål. Sedan questföremål har ingen attribut så den är bara ett föremål i princip. Så man kan se det som en subclass av föremål i sig själv.
	static XmlHandler xHand;
	Item[] itemA;
	int nW, nC, nM;
	game.Player pC;
	public Itemhandler() {
		LoadItemsFromXML();
		this.nW = xHand.NodeCounter(null, "wearable");
		this.nC = xHand.NodeCounter(null, "consumable");
		this.nM = xHand.NodeCounter(null, "quest");
		itemA = new Item[(this.nW+this.nC+this.nM)];
		pC = Game.player;
		createItems();
		
	}
	
	// FUNKTIONER
	public void createItems() {
		int i1 = 0;
		for(int i = 0; i < this.nW; i++) {
			Node w = xHand.NR("wearable", i);
			itemA[i1] = new Item(w);
			i1++;
		}
		for(int i = 0; i < this.nC; i++) {
			Node w = xHand.NR("consumable", i);
			itemA[i1] = new Item(w);
			i1++;
		}
		for(int i = 0; i < this.nM; i++) {
			Node w = xHand.NR("quest", i);
			itemA[i1] = new Item(w);
			i1++;
		}
	}	
    public void LoadItemsFromXML() {
    	xHand = new XmlHandler("items", false);
    }	
    
    public void equip(Item s) {
    	switch(s.itemtype) {
	    	case("wearable"): {
	    		try {
	    	    	switch(s.clas) {
	    	    	
	    	    	    case("suit"): {	    	
	    	    		   game.Game.player.equipped[0] = s;
	    	    		   game.Game.player.equipped[0].onEquip();
	    	    	    }
	    	    	    case("amulet"): {
	    	    	    	game.Game.player.equipped[1] = s;
	    	    	    	game.Game.player.equipped[1].onEquip();
	    	    	    }
	    	    	    case("weapon"): {
	    	    	    	game.Game.player.equipped[2] = s;
	    	    	    	game.Game.player.equipped[2].onEquip();
	    	    	    }
	    	    	}
	    	    	} catch(Exception e) {
	    	    		e.printStackTrace();
	    	    	}
	         	
	    	    break;
	    	}
	    	case("consumable"): {
	    	    s.onEquip();
	    	    break;
	        }
	    	case("quest"): {
	    	    s.onEquip();
	    		break;
	    	}
    	}
    }
    public void dequip(String type) {
    	switch(type) {
    	case("suit"): {
    		game.Game.player.equipped[0] = new Item(null);
    		break;
    	}
    	case("amulet"): {
    		game.Game.player.equipped[1] = new Item(null);
    		break;
    	}
    	case("weapon"): {
    		game.Game.player.equipped[2] = new Item(null);
    		break;
    	}
    	}
    }
    
    // KLASSER
	public static class Item{
		String clas, img, effecttype, effect, name, desc, itemtype;
		boolean proc, neg;
    	int id, size;  
    	// införskaffar noden sätter olika parametrar av föremålet
	    public Item(Node w) {
	    	if(w.equals(null)) {
	    		this.itemtype = "gay";
	    	} else {
	    	this.itemtype = w.getNodeName();
	    	NamedNodeMap s = w.getAttributes();
    	    this.clas = s.getNamedItem("class").getNodeValue(); 
    	    this.id = Integer.parseInt(s.getNamedItem("id").getNodeValue());
    	    this.size = 8;
    	    try {
    	    this.img = xHand.getNodeText("img", w);
    	    this.proc = xHand.getNodeText("proc", w).equals("1");
    	    this.neg = xHand.getNodeText("neg", w).equals("1");
    	    this.name = xHand.getNodeText("name", w);
    	    this.desc = xHand.getNodeText("desc", w);
    	    this.effecttype = xHand.getNodeText("effecttype", w);
    	    this.effect = xHand.getNodeText("effect", w);	 
    	    } catch(Exception e) {
    	    	e.printStackTrace();
    	    }
	    	}
	    }	
	    // klass equip, som beroende på vad det är för itemtype gör olika saker på equipnpen, altså du får inte en hälsoboost från att käka en stövel tex.
	    
	    public void onEquip() {
	    	switch(itemtype) {
	    	case("wearable"): {
	    		StatEffect f = new game.Player.StatEffect(this.name, this.desc, this.effecttype, this.effect, this.neg, this.proc);
	    		game.Game.player.addEffect(f);
	    		break;
	    	}
	    	// LÄGG TILL TIMER FÖR VISSA EFFEKTER; ETT "ATT GÖRA"
	    	case("consumable"): {
	    		switch(effecttype) {
	    		case("healthboost"): {
	    			game.Game.player.healthEffect(this.neg, this.proc, Double.valueOf(this.effect));
	    			break;
	    		}
	    		case("damageboost"): {
	    			game.Game.player.damageEffect(this.neg, this.proc, Double.valueOf(this.effect));
	    			break;
	    		}
	    		}
	    		break;
	    	}
	    	}
	    }
	    public void onDequip() {
	    	
	    }
	    
	}
	    
	    
	
	// KLASSERS FUNKTIONER
	    
}
