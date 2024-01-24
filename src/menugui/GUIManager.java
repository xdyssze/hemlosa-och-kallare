package menugui;



// i princip är detta klassen som importeras, och kör varje javafil sepera
public class GUIManager {
    String currentGUI;
    public GuiI gui;
	public GUIManager() {
		currentGUI = "";

	}
	// bestämmmer vilket gui som visas
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
    // ritar guit.
    public void drawGUI() {
    	gui.drawMenu();
    }
    
    
    
    // flyttar currently selected om det går.
   
    
    public void mvPos(char d, int s) {
    	if(gui.checkmv(d)) {
    		gui.currentlySelected = (byte) s;
    	};
    }
    // kör action.
    public void action() {
    	gui.action();
    }
}
