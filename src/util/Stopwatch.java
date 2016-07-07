package util;

import java.util.ArrayList;
import java.util.Iterator;

public class Stopwatch implements Iterable<Double> {
	private long initTime;
	private long endTime;
	private ArrayList<Double> times;
	
	public Stopwatch() {
		times = new ArrayList<>();
	}
	

	public void start(){
		initTime = System.nanoTime();
	}
	public void stop(){
		endTime = System.nanoTime();
	}

	public double calculateElapsedTimeInMilliSeconds(){
		double temp = endTime;
		temp -= this.initTime;
		temp = temp/1000000d;
		times.add(temp);
		return temp;
	}

	@Override
	public Iterator<Double> iterator() {
		return times.iterator();
	}


}
