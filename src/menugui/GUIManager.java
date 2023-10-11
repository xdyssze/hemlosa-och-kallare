package menugui;



// i princip är detta klassen som importeras, och kör varje javafil sepera
public class GUIManager {
    String currentGUI;
    public GuiI gui;
	public GUIManager() {
		currentGUI = "";

	}
    public void setGUI(String g) {
    	switch(g) {
    	case("esc"): {
    		gui = new menugui.EscGUI();
    		break;
    	}
    	case("inv"): {
    		gui = new menugui.InventoryGUI();
    		break;
    	}
    	case("char"): {
    		gui = new menugui.CarachterGUI();
    		break;
    	}
    	case("main"): {
    		gui = new menugui.MainMenuGUI();
    		
    		break;
    	}
    	}
    	drawGUI();
    	currentGUI = g;
    }
    
    public void drawGUI() {
    	gui.drawMenu();
    }
    
    
    
    // flyttar
    
    
    public void mvPos(char d, int s) {
    	if(gui.checkmv(d)) {
    		gui.currentlySelected = (byte) s;
    	};
    }
    public void action() {
    	gui.action();
    }
}
