package gfxproc;


import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import game.Game;
public class Gfx {
	public static int sizex, sizey;
	public static Segment[] pix;
	public static int[] chars, objs, plays;
	public static NodeList funsy;
	public static Node fuqw;
	static Document doc;
	public static Sprite[] sprites;
	public static xmlHandler.XmlHandler xHand;
	public static void main(String[] args) {
		if (args.length >= 1) {
		switch(args[0]) {
		    case ("set"): {
		        sizex = Integer.parseInt(args[1]);
		        sizey = Integer.parseInt(args[2]);
		        pix = new Segment[sizex*sizey];		        
		        SpriteHandler.main();
		        Seghand("cr", 0, 0, ' ');
                break;
		    }
		    default: {		    	
		    	break;
		    }		
	    }		
		}	
		
	}
	// RENDERING
	

	// SEGMENTS
	
	
	public static String segToString() {
		String s;
		s = "";
		for(int y = 0; y < sizey; y++) {
			for(int x = 0; x < sizex; x++) {
				s += pix[(y*sizex)+x].symbol;
			}
			s += "\r\n";
		}
		return(s);
	}
	
	
	
	public static void updateScreen() {
		String r = Segproc();
		game.Update.CLS();
		System.out.print(r);
	}
	public static void clearSec() {
		for(int i = 0; i < sizex*sizey; i++) {
			pix[i].symbol = ' ';
		}
			}
	public static String Segproc() {
		//map, obj, char, player den ordningen. Gå igenom alla funktioner som kräver plats på skärmen, fråga om något ska vara på den platsen och om det finns göra ett ranksystem av vad som är topp
		String s;
		s = "";
		for(int y = 0; y < sizey; y++) {
			for(int x = 0; x < sizex; x++) {
				char mp;
				mp = game.Game.maph.maprender(x, y);
				
				s += String.valueOf(mp);
				Seghand("chng", x, y, mp);
			}
			s += "\r\n";
		}
		StringBuilder ss = new StringBuilder(s);
		
		// Spelare
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				char CSC;
				try {
					// För varje y läggs 2 karaktärer till för r/n
					CSC = Game.player.pls.cSprite().charAt((8*y)+x);
					ss.setCharAt(((82*(16+y))+(36+x)), CSC);
				} catch (Exception e) {
					
					e.printStackTrace();
				}	
			}
		}
		return(ss.toString());
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
	
	public static class Segment {
		public char symbol;		
		int id;
		public Segment(int id) {			
			this.id = id;
			this.symbol = ' ';
		}				
		}
	
	// SPRITES
	
	public static class SpriteHandler {
		public static void main() {
			        
			        xHand = new xmlHandler.XmlHandler("sprites", false);
			       
								
				   // NodeList d = xHand.main.getChildNodes();
				    plays = new int[xHand.NodeLength()];		
					sprites = new Sprite[xHand.NodeLength()];
					objs = new int[xHand.NodeLength()];
					chars = new int[xHand.NodeLength()];
					
					for(int i = 0; i < (xHand.NodeLength()/2); i++) {
						Node sp1 = xHand.NR("sprite", i);
						if (sp1.getNodeType() == Node.ELEMENT_NODE)
						 {
							Element spr = (Element) sp1;
					    System.out.println(spr.getElementsByTagName("type").item(0).getTextContent());
						String type = spr.getElementsByTagName("type").item(0).getTextContent();
					    switch(type) {
					    case("player"): {
					    	addToEnd(plays, ((i+1)/2), null, null);
					    	sprites[((i+1)/2)] = new Sprite(((i+1)/2), spr);
					    	break;
					    }
	                    case("object"): {
	                    	addToEnd(objs, ((i+1)/2), null, null);
	                    	sprites[((i+1)/2)] = new Sprite(((i+1)/2), spr);
					    	break;
					    }
	                    case("character"): {
	                    	addToEnd(chars, ((i+1)/2), null, null);
	                    	sprites[((i+1)/2)] = new Sprite(((i+1)/2), spr);
					    	break;
					    }	
					    
					    }
						}
						
					}				
					
		}
		public static Sprite nF(String name) {
			for(int i = 0; i < sprites.length; i++) {
				if(sprites[i].name == name) {
					return(sprites[i]);
				}
			} 
			return(null);			
		}
		public static Sprite idtF(int id, String type) {
			switch(type) {
			case("player"): {
				return(sprites[plays[id]]);
			}
			case("character"): {
				return(sprites[chars[id]]);			
			}
			case("object"): {
				return(sprites[objs[id]]);				
			}			
			}
			return(null);
		}
		public static Sprite idF(int id) {
			return(sprites[id]);
		}
		public static Sprite SpriteF(String[] arg) {
			if(arg.length == 2) {
				return(idtF(Integer.valueOf(arg[0]), arg[1]));
			} else {
				for(int i = 0; i < arg[0].toCharArray().length; i++) {
					if(Character.isDigit(arg[0].toCharArray()[i])) {
						return(idF(Integer.valueOf(arg[0])));
					}
				}
				return(nF(arg[0]));
			}		    			
			
		}
		public static String SpriteS(String[] arg) {
			Sprite sss = SpriteF(arg);
			return(sss.s);
		}
		public static int[] SpriteSize(String[] arg) {
			Sprite f = SpriteF(arg);    
			int[] r = {f.sx, f.sy};
			return(r);		    
		}
		public static int[] SpritePos(String[] arg) {
			Sprite f = SpriteF(arg);
			int[] r = {f.px, f.py};
			return(r);
		}
		public static String SpriteName(String[] arg) {
			return(SpriteF(arg).name);
		}
	}
	
	public static class Sprite {
		int id, sid, sx, sy, px, py;
		String s, type, name;
		
		public Sprite(int is, Element d) {
			
			this.id = is;
			try {
			this.sid = Integer.valueOf(d.getElementsByTagName("id").item(0).getTextContent());
			this.type = (String) d.getElementsByTagName("type").item(0).getTextContent();
			Game.lg.Logbuilder("\r\n"+this.type+" TYPE; \r\n");
			Game.lg.Logwriter();
			this.s = d.getElementsByTagName("img").item(0).getTextContent();
			this.sx = Integer.valueOf(d.getElementsByTagName("sx").item(0).getTextContent());
			this.sy = Integer.valueOf(d.getElementsByTagName("sy").item(0).getTextContent());
			if(!this.type.equals("player")) {
				this.px = Integer.valueOf(d.getElementsByTagName("px").item(0).getTextContent());
				this.py = Integer.valueOf(d.getElementsByTagName("py").item(0).getTextContent());			
			} 
				this.name = d.getElementsByTagName("name").item(0).getTextContent();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
 
	}
	
	// ARtS AND CRAFTS
	
	
	
	public static void drawBox(int x, int y, int xx, int yy, String style, boolean trans) {
		byte xd;
		char lc;
		switch(style) {
		    case("dotted"): {
			    xd = 2;
			    lc = '*';
			    break;
		    }
		    case("solid"): {
		    	xd = 1;	 
			    lc = '*';
			    break;
		    }
		    default: {
		    	xd = 1;    
		    	lc = '*';
		    	break;
		    }
		}	
		try {
		// ritar lådan;
		for(int z = y; z <= yy; z+=xd) {
			if(z >= sizey) {
				
			} else {
			
			for(int t = x; t <= xx; t+=xd) {
				if(t >= sizex) {
					
				} else {
				Seghand("chng", t, z, lc);
				}
			}
			}
		}
		
		if(trans) {
			for(int z = y+1; z <= yy-1; z++) {
				for(int t = x+1; t <= xx-1; t++) {
					Seghand("chng", t, z, ' ');
				}
			}
		} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void text(int x, int y, String t) {
		char[] a = t.toCharArray();
		for(int i = 0; i < t.length(); i++) {
			Seghand("chng", (x+i), y, a[i]);
		}
	}
	
	// RANDOM ACCESSORY FUNCTIONS
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
	
	public static void CLS(){   
	    try {
	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (IOException | InterruptedException ex) {}
	    }
	
}


