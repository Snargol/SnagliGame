package model.other;

public class Timer extends Thread{
	private long start;
	private long stop;
	private int delay;
	
	public Timer() {
		this.start = System.currentTimeMillis();
		this.stop = 0;
		this.delay = 0;
	}
	
	public void stopTime() {
		this.stop = System.currentTimeMillis();
	}
	
	public void restartTime() {
		long restart = System.currentTimeMillis();
		this.delay += stop - restart;
		this.stop = 0;
	}
	
	//return time in seconds
	public int getTimeSeconds() {
		return (int) ( System.currentTimeMillis() - start - delay) / 1000;
	}
	
	//return time in milli seconds
	public long getTimeMilliSeconds() {
		return (int) ( System.currentTimeMillis() - start - delay);
	}
	
	//returns time in a display format
	public String getTimeDisplay() {
		//time is in seconds
		int time     = getTimeSeconds();
		int hours    = 0;
		int minutes  = 0;
		int seconds  = 0;
		double rest1 = 0;
		double rest2 = 0;
		String hour  = new String("");

		hours = (int) Math.floor(time / 3600) ;
		rest1 = ((double) time / 3600 ) - hours;

		minutes = ((int) (rest1 * 3600) / 60);
		rest2 = ((rest1 * 3600) / 60) - minutes; 

		seconds = (int) Math.round(rest2 * 60);
		
		hour  = ((hours >= 10) ? ""+hours : "0" + hours) + ":"; 
		hour += ((minutes >= 10) ? ""+minutes : "0" + minutes) + ":"; 
		hour += ((seconds >= 10) ? ""+seconds : "0" + seconds);
		
		return hour;
		
	}
	
	
}
