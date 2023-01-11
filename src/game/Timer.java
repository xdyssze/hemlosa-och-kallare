package game;


public class Timer {
	long t0, t1, rtms, rts;
	public boolean running;

	public Timer() {
	    rts = System.currentTimeMillis()/1000;
	    t0 = System.nanoTime();
	    running = true;
	}
	public void setTimer() {
		t1 = System.nanoTime();
	}
	public long mil(long t) {
		return(t/1000);
	}
	public long s(long t) {
		return(t/1000000);
	}
	public long intervall() {
		return(System.nanoTime()-t1);
	}

}
