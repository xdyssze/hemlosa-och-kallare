package gfxproc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class Gfx {
	public static int sizex, sizey;
	public static Segment[] pix;
	public static int[] chars, objs, plays;
	static Document doc;
	public static Sprite[] sprites;
	public static void main(String[] args) {
		if (args.length >= 1) {
		switch(args[0]) {
		    case ("set"): {
		        sizex = Integer.parseInt(args[1]);
		        sizey = Integer.parseInt(args[2]);
		        pix = new Segment[sizex*sizey];
		        plays = new int[12];
		        
		     // SPRITES:
				try {
					File xml = new File(System.getProperty("user.dir") + "\\resources\\sprites.xml");	
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					doc = builder.parse(xml);
					
					} catch(Exception e) {
						e.printStackTrace();			
					}
				// ORdern är spelare, föremål, karaktärer
				System.out.print(doc.getElementsByTagName("sprite").getLength());
				sprites = new Sprite[doc.getElementsByTagName("sprite").getLength()];
				objs = new int[doc.getElementsByTagName("sprite").getLength()-12];
				chars = new int[doc.getElementsByTagName("sprite").getLength()-12];
				
				for(int i = 0; i < doc.getElementsByTagName("sprite").getLength(); i++) {
					Node s = doc.getElementsByTagName("sprite").item(i);
				    NodeList d = s.getChildNodes();
				    switch(d.item(1).getTextContent()) {
				    case("player"): {
				    	addToEnd(plays, i, null, null);
				    	sprites[i] = new Sprite(i, d);
				    	break;
				    }
                    case("object"): {
                    	addToEnd(objs, i, null, null);
                    	sprites[i] = new Sprite(i, d);
				    	break;
				    }
                    case("character"): {
                    	addToEnd(chars, i, null, null);
                    	sprites[i] = new Sprite(i, d);
				    	break;
				    }
				    
				    }
				}
			
				// END OF SPRITES
		        
		        Seghand("cr", 0, 0, ' ');
                break;
		    }
		    default: {
		    	
		    	break;
		    }
		
	    }
		
		}
		
	}
	public static void addToEnd(int[] a, int v, String[] as, String vs) {
	   if(a == null) {
		   for(int i = 0; i < as.length; i++) {
			   if(as[i] != null) {
				   as[i] = vs;
				   i = as.length;
			   }
			   
		   }
	   } else {
		   for(int i = 0; i < a.length; i++) {
			   if(a[i] == 0) {
				   a[i] = v;
				   i = a.length;
			   }
			   
		   }
	   }
		
	}
	public static String rends() {
		String s = "";
		for(int i = 0; i < sizey; i++) {
		    for(int z = 0; z < sizex; z++) {
		    	int id = sizex*(i)+z;
		    	s += String.valueOf(Gfx.pix[id].symbol);    	
		    }
		    s += "\r\n";
		}
		return(s);
	}
	
	public static String SpriteHand() {
		
		return("");
	}
	public static void Seghand(String ar1, int ar2, int ar3, char ar4) {
		switch (ar1) {
		    case ("cr"): {
		    	for(int i = 0; i < sizex*sizey; i++) {
		    		try {
		    			
		    		pix[i] = new Segment(i);
		    		
		    		} catch(Exception e) {
		    			System.out.println(e);
		    		}
		    	}		    		
		    	break;
		    }
		    case ("chng"): {
		    
		    	int id = sizex*(ar3)+ar2;
		    	pix[id].symbol = ar4;
		    	
		    	break;
		    }
		    case ("clr"): {
		    	int id = sizex*(ar3)+ar2;
		    	pix[id].symbol = ' ';
		    	break;
		    }
		    default: {
		    	game.Game.lg.Logbuilder(" **  AT SEGHAND:  INVALID SEGHAND AR1 PASSED TO SWITCH  **  ");
				break;
			}   	
		}		
	}
	public static void CLS(){

	    
	    try {
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (IOException | InterruptedException ex) {}

	    }

	public static class Segment {
		public char symbol;		
		int id;
		public Segment(int id) {			
			this.id = id;
			this.symbol = ' ';
		}				
		}
	
	public static class Sprite {
		int id, sid, sx, sy, px, py;
		String s, type, name;
		
		public Sprite(int is, NodeList d) {
			this.id = is;
			this.sid = Integer.valueOf(d.item(0).getTextContent());
			this.type = d.item(1).getTextContent();
			this.s = d.item(2).getTextContent();
			this.sx = Integer.valueOf(d.item(3).getTextContent());
			this.sy = Integer.valueOf(d.item(4).getTextContent());
			this.px = Integer.valueOf(d.item(5).getTextContent());
			this.py = Integer.valueOf(d.item(6).getTextContent());
			this.name = d.item(7).getTextContent();
		}		

	}
	
}


