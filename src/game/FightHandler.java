package game;

import org.w3c.dom.Node;

public class FightHandler {

	public FightHandler() {
		// TODO Auto-generated constructor stub
	}
    class Fight {
    	
    }
    
    class Move {
		int id, proc, effect;
		String clas, name, effecton;
		Move(Node s) {
			
		}
		public void makeMove() {
			switch(this.clas) {
			case "dmg": {
				switch(effecton) {
				    case "player": {
					    
				    }
				}
				break;
			}
			}
		}
	}
}
