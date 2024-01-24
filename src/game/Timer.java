package game;

// timer klass som egentligen ska hålla kåll på framelängder och hålla en viss framerate genom att timea hur långt handlingar tar
// ej i funktion för nuvarande
public class Timer {
	long t0, t1, rtms, rts;

	public boolean running;

	public Timer() {
	    rts = System.currentTimeMillis()/1000;
	    t0 = System.nanoTime();
	    running = true;
	}
	// setter timern
	public void setTimer() {
		t1 = System.nanoTime();
	}
	// returnar tiden i mil
	public long mil(long t) {
		return(t/1000);
	}
	//returnar tiden i sekunder
	public long s(long t) {
		return(t/1000000);
	}
	// returnar hur långt det tagit mellan tiden som togs först och tiden nun
	public long intervall() {
		return(System.nanoTime()-t1);
	}
	// en klass för tid, i princip ist för siffror skapar denna tidobjekt som kan innehåla mycket.
	class Time {
		private long tid;
	    int prefix;
		Time() {
			
		}
		
		public long mil() {
			return(tid/1000000);
		}
		public long sec() {
			return(tid/1000000000);
		}
	}

}


