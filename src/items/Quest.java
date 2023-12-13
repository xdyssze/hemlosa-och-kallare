package items;
import org.w3c.dom.Node;
public class Quest implements Item {
	String image, name, desc;
	public Quest(Node w) {
		
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return("");
	}
	public String[] getInfo() {
		return(new String[7]);
	}
	public int getId() {
		return(0);
	}
	public void equip() {
		
	}
	public void dequip() {
		
	}

	public int calculateAdd() {
		return(0);
	}
}
