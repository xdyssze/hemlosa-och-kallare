package items;
import org.w3c.dom.Node;
public class DamagePotion extends Consumable {
    // jag vill göra all logik i förtid, jag gör det i constructorn
	int todo;
	public DamagePotion(Node it) {
		// i princip skapar vi en funktion i constructorn som körs i doEffect, som är helt beroende av andra faktorer
	    // vi skapar ett värde som adderas med dmg i functionen doEffect(); om det är procent, är värdet bestämt av basedmg, sedan uppdaterad vid framtida levlar
		super(it);
		if(this.effect < 1 && this.effect > -1) {
			todo = calculateAdd();
		} else {
			todo += this.effect;
		}
		
    }

	
	
	public int calculateAdd() {		
		return((int) (game.Game.player.bdmg * this.effect));
	}
	@Override
    public void doEffect() {
		game.Game.player.dmg += todo;
    }
	 public void dequip() {
			game.Game.player.dmg -= this.todo;
			
		}
}
