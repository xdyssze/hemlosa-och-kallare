package mainfile;



import java.nio.file.*;

import game.*;

public class Funny {
    public static void main(String[] args) throws Exception{
		
		if(args.length == 0) {
			System.out.println("sex");
		} else {
			switch(args[0]) {
				case "no": {
					System.out.println("no sex");
					break;
				}
				case "game": {
					Logcreator.main();
					Game snake = new Game();
					snake.startup();
					
					break;
				}
				case "clear": {
					try {
					for (int i = 0; i < 100; i++) {
                      System.out.print("\rThinking... " + i);
                      System.out.flush();
                      Thread.sleep(100);
                    }
					} catch(Exception e){
						System.out.println(e);
					}
					break;
				}
				default: {
					System.out.println("prankad du skrev feeeeel");
					break;
				}
			}
		}
	}
	public static void Sleep(int to) {
		try {
									
			Thread.sleep(to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String fileread(String fn)throws Exception {	    				
	
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fn)));
		return data;
		
	    
	}
	
}

