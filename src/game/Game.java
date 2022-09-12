package game;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import gfxproc.Gfx;


public class Game {
	public volatile static int state;
	public static Tailpiece[] thand;
	public static char[] keyq;
	static String ascii;
    static int f, locp;
    public static int points;
    private static Update update;
    public static Thread up1;
    static Scanner kay;
	public static void main(String[] args) throws Exception {
		Logcreator.main();
		ascii = fileread(System.getProperty("user.dir") + "\\src\\steffe.txt");
		thand = new Tailpiece[60];
		points = 0;
		startup();
	}
	
	public static void startup() {		
		
		Gfx.CLS();
		kay = new Scanner(System.in);
		keyq = new char[2];
		System.out.println(Game.ascii);
		Sleep(2000);
		update = new Update();
		state = 0;
		up1 = new Thread(update, "t1");
		up1.start();	
		
		thand[0] = new Tailpiece(3, 10, 0);	
		String[] argu = {"set", "40", "40"};
		Gfx.main(argu);		
	    state = 1;		
	    
		}
	
	public static class Tailpiece {
		int locx, locy, idp, prevx, prevy;
		String facing;
		boolean lc, lc1;
		
		public Tailpiece(int x, int y, int id) {
			if(id == 0) {				
				
				this.idp = 0;
				this.locx = x;
				this.locy = y;	
				this.prevx = x;
				this.prevy = y;
	            this.facing = "right";				
			} else {				
			this.idp = id;
				
			}
		}
		public void update() {
			if (this.idp == 0) {
				if(!lc1) {
				switch(this.locx) {
				     case (0): {
				    	 lc = true;
				    	 this.prevx = this.locx;
				    	 this.locx = (Gfx.sizex-1);
				    	 break;
				     }
				     case (39): {
				    	 this.prevx = this.locx;
				    	 this.locx = 0;
				    	 
				    	 lc = true;
				    	 Logcreator.Logbuilder(" ** BOTTOM Reached x**  ");
				    	 break;
				     }
				}
				 switch(this.locy) {
				     case (0): {
				    	 this.prevy = this.locy;
				    	 lc = true;
				    	 this.locy = (Gfx.sizey-1);
				    	 break;
				     }
				     case (39): {
				    	 this.prevy = this.locy;
				    	 this.locy = 0;
				    	 lc = true;
				    	 Logcreator.Logbuilder(" ** BOTTOM Reached y**  ");
				    	 break;
				     }
				 
				}
				} else {
					lc1 = false;
				}
				
				  if(!lc) {
				switch(this.facing) {
					case ("right"): {
						this.prevx = this.locx;
						this.locx += 1;
						break;
					}
					case ("left"): {
						this.prevx = this.locx;
						this.locx -= 1;
						break;
					}
					case ("up"): {
						this.prevy = this.locy;
						
						this.locy -= 1;

						break;
					}
					case ("down"): {
						this.prevy = this.locy;						
						this.locy += 1;					
						break;
					}
					
				} 
				  } else {
					  lc = false;
					  lc1 = true;
				  }
				  
				
			} else {
				this.prevy = this.locy;
				this.prevx = this.locx;
				try {				  
					
				  this.locy = Game.thand[idp-1].locy;
							
				  this.locx = Game.thand[idp-1].locx;
				} catch (Exception e) {
					System.out.print(e);
				}
			}
			String[] ar = {"chng", String.valueOf(this.prevx), String.valueOf(this.prevy), " "};
			Logcreator.Logbuilder(ar[1] + " " + ar[2]);
			Logcreator.Logbuilder(this.prevx + " " + this.prevy + " " + this.idp);
			Gfx.Seghand(ar);
			ar[1] = String.valueOf(this.locx);
			ar[2] = String.valueOf(this.locy);
			ar[3] = "*";
			Gfx.Seghand(ar);			
			}
				
		}
	public static String fileread(String fn)throws Exception {	    				
		
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fn)));
		return data;
		
	    
	}
	private static void Sleep(int to) {
		try {
									
			Thread.sleep(to);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}

