package MutationTesting;

import java.util.Timer;
import java.util.TimerTask;

/**
 * provides functionality to measure compile time
 * 
 * @author njenkin
 */
public class Timing{
	
	long startTime;
	long endTime;
	long totalTime;
	long goldTime;
	long currentTime;
	
	/**
	 * Empty constructor 
	 */
	Timing(){
		startTime = 0;
		endTime = 0;
		totalTime = 0;
		goldTime = 0;
		currentTime = 0;
	}
	
	/**
	 * Constructor that takes the gold version run time
	 * 
	 * @param gold
	 */
	Timing(long gold){
		goldTime = gold;
	}
	
	/**
	 * Used to start the system timer
	 */
	public void setStartTime(){
		startTime = System.nanoTime();
	}

	/**
	 * Used to stop the system time. Sets the total run time once activated.
	 */
	public void setEndTime(){
		endTime = System.nanoTime();
		totalTime = endTime - startTime;
	}
	
	/**
	 * Returns the total run time 
	 * 
	 * @return totalTime
	 */
	public long getTotalRunTime(){
		return (endTime - startTime);
	}
	
	/**
	 * Used to get the current run time
	 * 
	 * @return currentTime - startTime
	 */
	public long getCurrentRunTime(){
		currentTime = System.nanoTime();
		return (currentTime - startTime);
	}
	
	/**
	 * Determines if the run time is less than or equal
	 * to twice of the gold version run time
	 * 
	 * @return totalTime <= 2*goldTime
	 */
	public boolean validRunTime(){
		return (getTotalRunTime() <= (2*goldTime));
	}
		
	/**
	 * Resets the gold value or sets it if the empty
	 * constructor was used
	 * 
	 * @param gold
	 */
	public void setGoldTime(long gold){
		goldTime = gold;
	}
	
	/**
	 * Used to get the gold time of the timing class
	 * 
	 * @return goldTime
	 */
	public long getGoldTime(){
		return goldTime;
	}
	
	
	/**
	 * *Records gold version run while while
	 * monitoring the run time of the gold program 
	 * ensuring that it does not exceed two minutes 
	 */
	public void monitorGoldVersionRunTime(){
		long gold = getGoldTime();
		
		Timer goldTimer = new Timer();
		TimerTask goldTask;
	
		
	}
	
	/**
	 * Ensures that the twice the gold version time is
	 * not exceeded while running a mutant version of the program
	 */
	public void monitorMutationRunTime(){
		
		
		
	}
	
	
	
}