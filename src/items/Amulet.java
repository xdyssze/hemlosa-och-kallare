package items;
import org.w3c.dom.Node;
public class Amulet extends Wearable {
    int todo;
    boolean health;
	public Amulet(Node w) {
		super(w);
		if(xHand.getNodeText("effecttype", w).equals("dmg")) {
			health = false;
		}
		
		if(this.effect < 1 && this.effect > -1) {
			this.todo = calculateAdd();
		} else {
			this.todo += this.effect;
		}
	}
	@Override
	public int calculateAdd() {
		if(health) {
			return((int)(game.Game.player.bhp * this.effect));
		} else {
			return((int)(game.Game.player.bdmg * this.effect));
		}
		
	}
	@Override
	public void doEffect() {
		game.Game.player.equipped[1] = this;
		if(health) {
			game.Game.player.hp += this.todo;
		} else {
			game.Game.player.dmg += this.todo;
		}
		
		
	}
	
    public void dequip() {
		
		for(int i = 0; i < 3; i++) {
			if(this.equals(game.Game.player.equipped[i])) {
				game.Game.player.equipped[i] = null;
				i = 69;
			}
		}
		if(health) {
			game.Game.player.hp -= this.todo;
		} else {
			game.Game.player.dmg -= this.todo;
		}
		
		
	}

}
