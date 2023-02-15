package game;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import game.Game;
public class Homeless  {
	// värden för lite småsaker
    int room, x, y, id, xp, arm;
    String name, img;
    Event ev[];
    Move mov[];
    Dialogue dia[];
    double hp;
    xmlHandler.XmlHandler xmHand;
	public Homeless(Node node) {
		xmHand = Game.homHand.xmlH;
		this.name = xmHand.getNodeText("name", node);	
		this.id = Integer.valueOf(node.getAttributes().getNamedItem("id").getTextContent());
		this.img = xmHand.getNodeText("img", node);	
		this.x = Integer.valueOf(xmHand.getNodeText("x", node));	
		this.y = Integer.valueOf(xmHand.getNodeText("y", node));	
		this.room = Integer.valueOf(xmHand.getNodeText("room", node));	
		this.xp = Integer.valueOf(xmHand.getNodeText("xp", node));	
		this.mov = new Move[xmHand.ChildNodeLength(xmHand.getNodeByName(node, "moves"))];
		this.ev = new Event[xmHand.ChildNodeLength(xmHand.getNodeByName(node, "events"))];
		this.dia = new Dialogue[xmHand.ChildNodeLength(xmHand.getNodeByName(node, "dialogues"))];
		this.arm = Integer.valueOf(xmHand.getNodeText("arm", node));
		this.hp = Integer.valueOf(xmHand.getNodeText("hp", node));	
		for(int i = 0; i < this.mov.length; i++) {
			this.mov[i] = new Move(xmHand.getNodeByName(node, "moves").getChildNodes().item(i));
		}
		for(int i = 0; i < this.ev.length; i++) {
			this.ev[i] = new Event(xmHand.getNodeByName(node, "events").getChildNodes().item(i));
		}
		for(int i = 0; i < this.dia.length; i++) {
			this.dia[i] = new Dialogue(xmHand.getNodeByName(node, "dialogue").getChildNodes().item(i));
		}
		
		
		
		
	}
	
	// place char
	void placeChar() {
		
	}
	// Homeless functions, on homless
	public void healthEffect(boolean neg, boolean proc, double effect) {
    	if(proc) {
    		if(neg) {
        	    this.hp = ((this.hp/100)*(100-effect));
        	} else {
        		this.hp = ((this.hp/100)*(100+effect));
        	}
	    } else {
	    	if(neg) {
	    		this.hp -= effect;
	    	} else {
	    		this.hp += effect;
	    	}
	    }
    	
    }
	
	// Event class som hanterar olika events efter dialog osv
	class Event {
		int id;
		String clas;
		Node n;
		Event(Node s) {
			this.n = s;
		    this.id = Integer.valueOf(s.getAttributes().getNamedItem("id").getTextContent());
		    this.clas = s.getAttributes().getNamedItem("class").getTextContent();
		    
		    
		}	
		public void event() {
			switch(this.clas) {
			case "loot": {
				Game.player.iH.addItemToInv(xmHand.getNodeByName(n, "loot").getTextContent(), null);
				break;
			}
			}
		}
	}
	// Move class som definierar ett move i en fight, tex slå eller sparka, och dess effekt
	class Move {
		int id, proc, effect;
		String clas, name, effecton;
		Move(Node s) {
			NamedNodeMap r = s.getAttributes();
			this.clas = r.getNamedItem("class").getTextContent();
			this.id = Integer.valueOf(r.getNamedItem("id").getTextContent());
		}
		public void makeMove() {
			switch(this.clas) {
			case "dmg": {
				    Game.player.healthEffect(true, proc == 1, effect);
				break;
			}
			case "heal": {
				    Game.homHand.currentInteraction.healthEffect(false, proc == 1, effect);
				break;
			}
			}
		}
	}
	// Dialog class som hanterar en dialog av alla en hemlösa kan ha.
	class Dialogue {
		int id;
		String end;
		String[] text;
		Dialogue(Node s) {
			this.id = Integer.valueOf(s.getAttributes().getNamedItem("id").getTextContent());
			this.end = xmHand.getNodeText("end", s);
			Node tex = xmHand.getNodeByName(s, "text");
			this.text = new String[xmHand.ChildNodeLength(tex)];
			for(int i = 0; i < this.text.length; i++) {
				text[i] = xmHand.getNodeText(String.valueOf(i), tex);
			}
			
			
		}
		
		public void end() {
			Game.homHand.currentInteraction.ev[Integer.valueOf(this.end.split(":")[2])-1].event();;
		}
	}

}


