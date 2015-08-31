package MutationTesting;

import static org.junit.Assert.*;
import org.junit.Test;

public class MutationReportTest{
	
	@Test
	public void testMutationReport(){
		MutationReport report = new MutationReport();
		assertEquals(report.getTotalNumberOfMutants(),0);
		assertEquals(report.getNumberOfKilledMutants(),0);
	}
	
	@Test
	public void testGetTotalNumberOfMutants(){
		MutationReport report = new MutationReport();
		assertEquals(report.getTotalNumberOfMutants(),0);
		
	}
	
	@Test
	public void testSetTotalNumberOfMutants(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfMutants(5);
		assertEquals(report.getTotalNumberOfMutants(),5);
	}
	
	@Test
	public void testAddMutant(){
		MutationReport report = new MutationReport();
		report.addMutant();
		report.addMutant();
		assertEquals(report.getTotalNumberOfMutants(),2);
		
	}
	
	@Test
	public void testAddMutants(){
		MutationReport report = new MutationReport();
		report.addMutants(3);
		assertEquals(report.getTotalNumberOfMutants(),3);
	}
	
	@Test
	public void testGetNumberOfKilledMutants(){
		MutationReport report = new MutationReport();
		report.setNumberOfKilledMutants(3);
		assertEquals(report.getNumberOfKilledMutants(),3);
		
	}
	
	@Test
	public void testSetNumberOfKilledMutants(){
		MutationReport report = new MutationReport();
		report.setNumberOfKilledMutants(3);
		assertEquals(report.getNumberOfKilledMutants(),3);
	}
	
	@Test
	public void testKillMutant(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfMutants(7);
		report.setNumberOfKilledMutants(3);
		report.killMutant();
		assertEquals(report.getNumberOfKilledMutants(),4);
	}
	
	@Test
	public void testKillNumberOfMutants(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfMutants(5);
		report.killNumberOfMutants(3);
		assertEquals(report.getNumberOfKilledMutants(),3);
		assertEquals(report.getNumberOfMutantsAlive(),2);
	}
	
	@Test
	public void testGetNumberOfLegalTests(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfTests(5);
		assertEquals(report.getNumberOfLegalTests(),5);
	}
	
	@Test
	public void testAddLegalTest(){
		MutationReport report = new MutationReport();
		report.setNumberOfLegalTests(3);
		report.addLegalTest();
		assertEquals(report.getNumberOfLegalTests(),4);
	}
	
	@Test
	public void testAddNumberOfLegalTests(){
		MutationReport report = new MutationReport();
		report.setNumberOfLegalTests(3);
		report.addNumberOfLegalTests(4);
		assertEquals(report.getNumberOfLegalTests(),7);
	}
	
	@Test
	public void testSetNumberOfLegalTests(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfTests(15);
		report.setNumberOfLegalTests(8);
		assertEquals(report.getNumberOfLegalTests(),8);
	}
	
	@Test
	public void testRemoveLegalTest(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfTests(15);
		report.setNumberOfLegalTests(10);
		report.removeLegalTest();
		assertEquals(report.getNumberOfLegalTests(),9);
	}
	
	@Test
	public void testGetTotalNumberOfTests(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfTests(10);
		assertEquals(report.getTotalNumberOfTests(),10);
	}
	
	@Test
	public void testAddTest(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfTests(10);
		report.addTest();
		assertEquals(report.getTotalNumberOfTests(),11);
	}
	
	@Test
	public void testAddNumberOfTests(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfTests(15);
		report.addNumberOfTests(5);
		assertEquals(report.getTotalNumberOfTests(),20);
	}
	
	@Test
	public void testSetTotalNumberOfTests(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfTests(20);
		assertEquals(report.getTotalNumberOfTests(),20);
	}
	
	@Test
	public void testGetNumberOfMutantsAlive(){
		MutationReport report = new MutationReport();
		int numMutants = report.getTotalNumberOfMutants();
		int numMutantsKilled = report.getNumberOfKilledMutants();
		int numMutantsAlive = numMutants - numMutantsKilled;
		assertEquals(numMutantsAlive,report.getNumberOfMutantsAlive());
		
	}
	
	@Test
	public void testGetSummaryReport(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfMutants(15);
		report.setNumberOfKilledMutants(7);
		report.setTotalNumberOfTests(30);
		report.setNumberOfLegalTests(23);
		StringBuilder st = new StringBuilder();
		st.append("Summary Report:\n");
        st.append("Total number of mutants:             " + report.getTotalNumberOfMutants() + "\n");
        st.append("Number of mutants killed:            " + report.getNumberOfKilledMutants() + "\n");
        st.append("Number of mutations remaining alive: " + report.getNumberOfMutantsAlive() + "\n");
        st.append("Number of legal tests:               " + report.getNumberOfLegalTests() + "\n");
		report.getSummaryReport();
		assertEquals(st.toString(),report.getSummaryReport());	
	}
	
	@Test
	public void testPrintToSummaryReportConsole(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testPrintSummaryReportToFile(){
		fail("Not yet implemented");
	}
	
	@Test
	public void qualityScore(){
		MutationReport report = new MutationReport();
		report.setTotalNumberOfMutants(10);
		report.setNumberOfKilledMutants(7);
		int total = report.getTotalNumberOfMutants();
		int killed = report.getNumberOfKilledMutants();
		double test = killed/total;
		double score = report.qualityScore();
		assertEquals(test,score,0.01);
	}
}
