package game;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import game.Player.StatEffect;
import xmlHandler.XmlHandler;

public class Itemhandler {
    // DET FINNS 3 typer av föremål, föremål du kan ha på karaktären, tex kläder och vapen. Konsumerbara föremål som du konsumerar en gång och som gör att föremålet försvinner för olika efekter, samt questföremål som ska ha sina egna attribut för att kunna
	// användas på sitt egna sett, tex du ska inte kunna sälja den jätteviktiga nyckeln som behövs för att kunna gå vidare i spelet, samt ska kunna göra specialgrejor.
	
	// initialiserar alla variabler
	static XmlHandler xHand;
	Item[] itemA;
	int nW, nC, nM;
	game.Player pC;
	// startar itemhanteraren med sin constructor,
	public Itemhandler() {
		// Laddar in föremål från xml och räknar hur många föremål det finns av de tre slagen, och förvarar dem i nw, nc och nm.
		LoadItemsFromXML();
		this.nW = xHand.NodeCounter(null, "wearable");
		this.nC = xHand.NodeCounter(null, "consumable");
		this.nM = xHand.NodeCounter(null, "quest");
		// Itemarray är en array av alla items vi har, som kan kommas åt vid behov.
		itemA = new Item[(this.nW+this.nC+this.nM)];
		//Läser in Game.player här så den enklare går att komma åt.
		pC = Game.player;
		// skapa föremål
		createItems();
		
		
		
	}
	
	// FUNKTIONER
	
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
	
	public int itemCounter() {
		int count = 0;
		for(Item ob : game.Game.player.inventory) {
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
    // funktion för att equippa ett item, det itemet so equippas heter s i detta fall
    public void equip(Item s) {
    	// switchar vilket typ av item det är
    	if(s != null) {
    	switch(s.itemtype) {
    	 
	    	case("wearable"): {
	    		//bestämmer vilken typ av wearable det är, tex vapen kläder amueltt, därefter bestämmer rätt position av equipped att stoppa in itemet i och sedan equippar föremålet.
	    		try {
	    	    	switch(s.clas) {
	    	    	
	    	    	    case("suit"): {	    	    	    	       
	    	    		   game.Game.player.equipped[0] = s;
	    	    		   game.Game.player.equipped[0].onEquip();
	    	    		   break;
	    	    	    }
	    	    	    case("amulet"): {    	    	
	    	    	    	game.Game.player.equipped[1] = s;
	    	    	    	game.Game.player.equipped[1].onEquip();
	    	    	    	
	    	    	    	break;
	    	    	    }
	    	    	    case("weapon"): {	    	    	    	
	    	    	    	game.Game.player.equipped[2] = s;
	    	    	    	game.Game.player.equipped[2].onEquip();    	    	 
	    	    	    	break;
	    	    	    }
	    	    	}
	    	    	} catch(Exception e) {
	    	    		e.printStackTrace();
	    	    	}
	         	
	    	    break;
	    	}
	    	// om den är konsumerbar eller quest kör den bara föremålets equipfunktion direkt.
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
    }
    // dequip i princip tar bort föremålet från equippedslotten och ersätter den med ingenting. Senare ska föremålet sedan kunna läggas tillbaka i inv efteråt.
    public void dequip(Item s) {
    	
    	switch(s.clas) {
    	case("suit"): {
    		game.Game.player.equipped[0].onDequip();
    		game.Game.player.equipped[0] = null;
    		
    		break;
    	}
    	case("amulet"): {
    		game.Game.player.equipped[1].onDequip();
    		game.Game.player.equipped[1] = null;
    		break;
    	}
    	case("weapon"): {
    		game.Game.player.equipped[2].onDequip();
    		game.Game.player.equipped[2] = null;
    		break;
    	}
    	}
    	
    }
    
    // KLASSER
    
    // klassen föremål
	public static class Item{
		public String clas, img, effecttype, effect, name, desc, itemtype, extr;
		
		public boolean proc;

		boolean neg;
    	int id, size;  
    	// införskaffar noden och sätter olika parametrar av föremålet, tex den är grön eller jag vetefan, dess namn effekt, är det en procentuell effekt osv.
	    public Item(Node w) {
	    	if(w == null) {
	    		System.out.println("yay gay");
	    		this.itemtype = "gay";
	    		game.Game.lg.Logbuilder(" gay ");
	    		game.Game.lg.Logwriter();
	    	} else {
	    	this.itemtype = w.getNodeName();
	    	NamedNodeMap s = w.getAttributes();
    	    this.clas = s.getNamedItem("class").getTextContent(); 
    	    this.id = Integer.parseInt(s.getNamedItem("id").getNodeValue());
    	    this.size = 8;
    	    
    	    try {
    	    //this.img = xHand.getNodeText("img", w);
    	    this.proc = xHand.getNodeText("proc", w).equals("1");
    	    this.neg = xHand.getNodeText("neg", w).equals("1");
    	    this.name = xHand.getNodeText("name", w);
    	    game.Game.lg.Logbuilder(" sex " + this.name);
    	    game.Game.lg.Logwriter();
    	    this.desc = xHand.getNodeText("desc", w);
    	    this.effecttype = xHand.getNodeText("effecttype", w);
    	    this.effect = xHand.getNodeText("effect", w);	 
    	  //  this.extr = xHand.getNodeText("extr", w);
    	    } catch(Exception e) {
    	    	e.printStackTrace();
    	    	game.Game.lg.Logbuilder(" sex " + this.name);
        	    game.Game.lg.Logwriter();
    	    }
	    	}
	    }	
	    // onEquip, som beroende på vad det är för itemtype gör olika saker på equipnpen, altså du får inte en hälsoboost från att käka en stövel tex.
	    
	    public void onEquip() {
	    	switch(itemtype) {
	    	case("wearable"): {
	    		// skapar en ny effekt och lägger till den till spelaren
	    		StatEffect f = new game.Player.StatEffect(this.name, this.desc, this.effecttype, this.effect, this.neg, this.proc);
	    		game.Game.player.addEffect(f);
	    		break;
	    	}
	    	// LÄGG TILL TIMER FÖR VISSA EFFEKTER; ETT "ATT GÖRA"
	    	case("consumable"): {
	    		switch(effecttype) {
	    		case("healthboost"): {
	    			// skapar en ny effekt och lägger till den till spelaren
	    			game.Game.player.healthEffect(this.neg, this.proc, Double.valueOf(this.effect));
	    			break;
	    		}
	    		case("damageboost"): {
	    			// skapar en ny effekt och lägger till den till spelaren
	    			game.Game.player.damageEffect(this.neg, this.proc, Double.valueOf(this.effect));
	    			break;
	    		}
	    		}
	    		break;
	    	}
	    	
	    	}
	    }
	    // inte tillagd ännu men ska ta bort effekter osv.
	    public void onDequip() {
	    	game.Game.player.removeEffect(Game.player.findEffect(this.name));
	    	
	    }
	    
	}
	    
	    
	
	// KLASSERS FUNKTIONER
	    
}
