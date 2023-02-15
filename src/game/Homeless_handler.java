package game;

import xmlHandler.XmlHandler;
// homeless handler, hanterar alla hemlösa i källaren.
public class Homeless_handler {
    // den behöver hämta hemlösa från en xml fil, skapa ett antal hemlösa objekt, placera in de hemlösas atribut i varje hemlös, stoppa hemlösa i en array
	// därefter ska gfx kunna rita ut de hemlösa på mappen, göra dem impassable, och gfx ska kunna parsa när en hemlös är i närheten och skapa en meny, snack eller fight.
	// varje hemlös dialog eller fight kan efteråt triggera ett script, och det står i en separat scriptfil
	XmlHandler xmlH;
	game.Homeless[] homlesseses;
	game.Homeless currentInteraction;
	public Homeless_handler() {
	    xmlH = new XmlHandler("homeless",false);
	    homlesseses = new game.Homeless[xmlH.NodeCounter(null, "homelesser")];
	    for(int i = 0; i < homlesseses.length; i++) {
	    	homlesseses[i] = new game.Homeless(xmlH.NR("homelesser", i));
	    }
	    currentInteraction = homlesseses[0];
	    
	}
	void onInteraction() {
		
	}

}
