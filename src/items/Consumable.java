package items;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

// abstract klass för alla konsumerbara föremål
public abstract class Consumable implements Item {
	String image, name, desc;
	xmlHandler.XmlHandler xHand = game.Game.player.xHand;
	Double effect;
	boolean temp, neg, proc;

	int id;
	public Consumable(Node w) {
		NamedNodeMap s = w.getAttributes();
		this.id = Integer.parseInt(s.getNamedItem("id").getNodeValue());
		// detta är temporärt, fattar du?
		this.temp = true;
		try {
			this.proc = xHand.getNodeText("proc", w).equals("1");
    	    this.neg = xHand.getNodeText("neg", w).equals("1");
    	    this.name = xHand.getNodeText("name", w);
    	    this.desc = xHand.getNodeText("desc", w);
    	    this.effect = Double.valueOf(xHand.getNodeText("effect", w));	
    	    this.image = xHand.getNodeText("img", w);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	// returnerar idt av föremålet
	public int getId() {
			return(this.id);
		}
	// returnerar infon av föremålet i en array
	public String[] getInfo() {
		String[] temp = new String[7];
		temp[0] = String.valueOf(this.proc);
		temp[1] = String.valueOf(this.neg);
		temp[2] = this.name;
		temp[3] = this.desc;
		temp[4] = String.valueOf(this.effect);
		temp[5] = this.image;
		temp[6] = this.getClass().getSimpleName();
		return(temp);
	}
	// returnerar föremålets namn
	public String getName() {
		return(this.name);
	}
	// equippar föremålet
	public void equip() {
		doEffect();
		if(this.temp) {
			game.Game.menhand.gui.currentlySelected -= 1;
			game.Game.player.removeItem(this.id);
		}
	}
	// abstract
	public abstract void doEffect();
	
	

	

}
