package gfxproc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
	
	// SEGMENTS
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
			  plays = new int[12];		        
			     
					try {
						File xml = new File(System.getProperty("user.dir") + "\\resources\\sprites.xml");	
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						doc = builder.parse(xml);
						
						} catch(Exception e) {
							e.printStackTrace();			
						}
					System.out.print(doc.getElementsByTagName("sprite").getLength());
					sprites = new Sprite[doc.getElementsByTagName("sprite").getLength()];
					objs = new int[doc.getElementsByTagName("sprite").getLength()];
					chars = new int[doc.getElementsByTagName("sprite").getLength()];
					
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
			return(SpriteF(arg).s);
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
		
		public Sprite(int is, NodeList d) {
			this.id = is;
			this.sid = Integer.valueOf(d.item(0).getTextContent());
			this.type = d.item(1).getTextContent();
			this.s = d.item(2).getTextContent();
			this.sx = Integer.valueOf(d.item(3).getTextContent());
			this.sy = Integer.valueOf(d.item(4).getTextContent());
			if(this.type != "player") {
				this.px = Integer.valueOf(d.item(5).getTextContent());
				this.py = Integer.valueOf(d.item(6).getTextContent());
			}
			this.name = d.item(7).getTextContent();
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


