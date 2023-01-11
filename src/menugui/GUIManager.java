package menugui;

import menugui.Gui;

// i princip är detta klassen som importeras, och kör varje javafil sepera
public class GUIManager {
    String currentGUI;
    String[] guiName;
    byte currentlySelected;
    public Gui gui;
	public GUIManager() {
		currentGUI = "esc";
		currentlySelected = 0;
	}
    public void setGUI(String g) {
    	currentGUI = g;
    	gui = new Gui(g);
    }
    public void drawGUI() {
    	gui.draw();
    }
    // flyttar currentlySelected
    public void mvPos(char d, int s) {
    	if(gui.checkmv(d)) {
    		currentlySelected = (byte) s;
    		gui.currentlySelected = this.currentlySelected;
    	};
    }
    public void action() {
    	gui.action();
    }
}
