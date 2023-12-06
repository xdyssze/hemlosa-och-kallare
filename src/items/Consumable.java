package items;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


public abstract class Consumable implements Item {
	String image, name, desc;
	xmlHandler.XmlHandler xHand = game.Game.player.xHand;
	int id;
	Double effect;
	boolean temp, neg, proc;
	game.Player.StatEffect statEffect;
	
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
	
	public void equip() {
		doEffect();
		if(this.temp) {
			game.Game.player.removeItem(this.id);
		}
	}
	
	public abstract void doEffect();
	
	
	public void dequip() {
		game.Game.player.removeEffect(this.statEffect);
		
	}
	

}
