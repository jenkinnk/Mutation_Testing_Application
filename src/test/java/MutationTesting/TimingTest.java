package MutationTesting;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimingTest {

	@Test
	public void testTiming() {
		Timing timer = new Timing();
		assertEquals(timer.getTotalRunTime(), 0);
	}

	@Test
	public void testTimingLong() {
		long gold = 10;
		Timing timer = new Timing(gold);
		assertEquals(timer.goldTime, gold);
	}

	@Test
	public void testSetStartTime() {
		Timing timer = new Timing();
		timer.setStartTime();
		assertNotEquals(timer.startTime,0);
	}

	@Test
	public void testSetEndTime() {
		Timing timer = new Timing();
		timer.setEndTime();
		assertNotEquals(timer.endTime,0);
	}

	@Test
	public void testGetTotalRunTime() {
		Timing timer = new Timing();
		timer.setStartTime();
		timer.setEndTime();
		assertNotEquals(timer.getTotalRunTime(), 0);
	}

	@Test
	public void testGetCurrentRunTime() {
		Timing timer = new Timing();
		timer.setStartTime();
		timer.getCurrentRunTime();
		assertNotEquals(timer.getCurrentRunTime(),0);
	}

	@Test
	public void testValidRunTime() {
		long gold = 10;
		Timing timer = new Timing(gold);
		timer.setStartTime();
		timer.setEndTime();
		assertFalse(timer.validRunTime());
		//gold = 0;
		//Timing timer2 = new Timing(gold);
		//assertTrue(timer2.validRunTime());
	}

	@Test
	public void testSetGoldTime() {
		Timing timer = new Timing();
		long gold = 10;
		timer.setGoldTime(gold);
		assertEquals(timer.goldTime, 10);
	}
	
	@Test
	public void testGetGoldTime() {
		long gold = 10;
		
		Timing timer = new Timing(gold);
		
		assertEquals(timer.getGoldTime(), 10);
	}

	@Test
	public void testMonitorGoldVersionRunTime() {
		fail("Not yet implemented -- Need Additional Framework");
	}

	@Test
	public void testMonitorMutationRunTime() {
		fail("Not yet implemented -- Need Additional Framework");
	}

}
