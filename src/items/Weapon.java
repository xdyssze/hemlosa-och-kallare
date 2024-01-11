package items;
import org.w3c.dom.Node;
public class Weapon extends Wearable {
    String attackTypes;
    int todo;
	public Weapon(Node w) {
		super(w);
		if(this.effect < 1 && this.effect > -1) {
			this.todo = calculateAdd();
		} else {
			this.todo += this.effect;
		}
		
	}
	@Override
	public int calculateAdd() {
		return((int)(game.Game.player.bdmg * this.effect));
	}
	@Override
	public void doEffect() {
		game.Game.player.equipped[2] = this;
		game.Game.player.dmg += this.todo;
		
	}
	
    public void dequip() {
		
		for(int i = 0; i < 3; i++) {
			if(this.equals(game.Game.player.equipped[i])) {
				game.Game.player.equipped[i] = null;
				i = 69;
			}
		}
		game.Game.player.dmg -= this.todo;
		
	}
	

}
