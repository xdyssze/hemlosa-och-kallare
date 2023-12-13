package items;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
public abstract class Wearable implements Item {
    String image, name, desc;
    xmlHandler.XmlHandler xHand = game.Game.player.xHand;
	double effect;
	boolean neg, proc;
	int id;
	public Wearable(Node w) {
		NamedNodeMap s = w.getAttributes();
		
		this.id = Integer.parseInt(s.getNamedItem("id").getNodeValue());
		
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
		
		
		
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return(id);
	}
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
	public String getName() {
		return(this.name);
	}
	public void equip() {
		doEffect();
	}
	
	public abstract void doEffect();
	
	
	
}
