package items;
import org.w3c.dom.Node;
public class HealthPotion extends Consumable{
    int todo;
	public HealthPotion(Node it) {
		super(it);
		if(this.effect < 1 && this.effect > -1) {
			todo = calculateAdd();
		} else {
			todo += this.effect;
		}
		
	}
    public int calculateAdd() {		
		return((int) (game.Game.player.bhp * this.effect));
	}
	@Override
    public void doEffect() {
		game.Game.player.hp += todo;
    }
}
