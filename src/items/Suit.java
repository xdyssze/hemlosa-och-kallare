package items;
import org.w3c.dom.Node;
public class Suit extends Wearable {
    int todo;
	public Suit(Node w) {
		super(w);
		if(this.effect < 1 && this.effect > -1) {
			this.todo = calculateAdd();
		} else {
			this.todo += this.effect;
		}

	}
	@Override
	public int calculateAdd() {
		return((int)(game.Game.player.bhp * this.effect));
	}
	@Override
	public void doEffect() {
		game.Game.player.equipped[0] = this;
		game.Game.player.hp += this.todo;
		
	}
	
    public void dequip() {
		
		for(int i = 0; i < 3; i++) {
			if(this.equals(game.Game.player.equipped[i])) {
				game.Game.player.equipped[i] = null;
				i = 69;
			}
		}
		game.Game.player.hp -= this.todo;
		
	}

}
