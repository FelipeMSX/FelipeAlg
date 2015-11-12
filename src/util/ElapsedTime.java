package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;

public class ElapsedTime implements Iterable<Double> {
	private long initTime;
	private ArrayList<Double> times;
	
	public ElapsedTime() {
		this.initTime = System.nanoTime();
	}
	
	public double calculateElapsedTimeInSeconds(){
		double temp = System.nanoTime();
		temp -= this.initTime;
		temp = temp/1000000000d;
		times.add(temp);
		this.initTime = System.nanoTime();
		return temp;
	}
	
	public double calculateElapsedTimeInMilliSeconds(){
		double temp = System.nanoTime();
		temp -= this.initTime;
		temp = temp/1000000d;
		times.add(temp);
		this.initTime = System.nanoTime();
		return temp;
	}
	
	public double calculateElapsedTimeInMicroSeconds(){
		double temp = System.nanoTime();
		temp -= this.initTime;
		temp = temp/1000d;
		times.add(temp);
		this.initTime = System.nanoTime();
		return temp;
	}

	public double calculateElapsedTimeInNano(){
		double temp = System.nanoTime();
		temp -= this.initTime;
		times.add(temp);
		this.initTime = System.nanoTime();
		return temp;
	}


	public void reset(){
		initTime = 0;
	}

	@Override
	public Iterator<Double> iterator() {
		return times.iterator();
	}


}
