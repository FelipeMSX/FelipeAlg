package util;

public class ElapsedTime {
	private long initTime;
	private long finalTime;
	
	public ElapsedTime() {
		this.initTime = System.nanoTime();
		this.finalTime = 0;
	}
	
	public double calculateElapsedTimeInSeconds(){
		double temp = this.finalTime;
		temp -= this.initTime;
		temp = temp/1000000000;
		return temp;
	}
	
	public double calculateElapsedTimeInMilliSeconds(){
		double temp = this.finalTime;
		temp -= this.initTime;
		temp = temp/1000000;
		return temp;
	}
	
	public double calculateElapsedTimeInMicroSeconds(){
		double temp = this.finalTime;
		temp -= this.initTime;
		temp = temp/1000;
		return temp;
	}

	public double calculateElapsedTimeInNano(){
		double temp = this.finalTime;
		temp -= this.initTime;
		return temp;
	}

	public long getFinalTime() {
		return finalTime;
	}

	public void setFinalTime() {
		this.finalTime = System.nanoTime();
	}
	
	public void reset(){
		initTime = 0;
		finalTime = 0;
	}
	
}
