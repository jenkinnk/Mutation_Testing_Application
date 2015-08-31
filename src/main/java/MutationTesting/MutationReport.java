package MutationTesting;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
//import java.nio.file.Path;

import org.apache.log4j.Logger;

/**
 * Mutation Report Model Object
 * 
 * <p>Contains methods for maintaining information required to generate reports.
 * 
 */
public class MutationReport {
	
	static final Logger logger = Logger.getLogger(MutationReport.class);

	private int totalNumberOfMutants;
	private int numberOfKilledMutants;
	
	private int numberOfLegalTests;
	private int totalNumberOfTests;
	
	public MutationReport(){
		totalNumberOfMutants = 0;
		numberOfKilledMutants = 0;
		numberOfLegalTests = 0;
		totalNumberOfTests = 0;
	}

	// This section contains functions accessing and modifying totalNumberOfMutants
	
	/**
	 * Accessor function for totalNumberOfMutants
	 */
	public int getTotalNumberOfMutants() {
		return totalNumberOfMutants;
	}
	
	/**
	 * Mutator function for totalNumberOfMutants allowing an integer
	 * as a parameter for specifying a value.
	 * @param totalNumberOfMutants
	 */
	public void setTotalNumberOfMutants(int totalNumberOfMutants) {
		this.totalNumberOfMutants = totalNumberOfMutants;
	}
	
	/**
	 * Mutator function for totalNumberOfMutants
	 * This function increments totalNumberOfMutants
	 */
	public void addMutant()	{
		this.totalNumberOfMutants++;
	}
	
	/**
	 * Mutator function for totalNumberOfMutants
	 * This function increments totalNumberOfMutants by the value 
	 * specified in the parameter numOfMutantsToAdd
	 * @param numOfMutantsToAdd
	 */
	public void addMutants(int numOfMutantsToAdd) {
		this.totalNumberOfMutants += numOfMutantsToAdd;
	}
	
	// This section contains functions accessing and modifying numberOfKilledMutants
	
	/**
	 * Accessor function for numberOfKilledMutants
	 * @return
	 */
	public int getNumberOfKilledMutants() {
		return numberOfKilledMutants;
	}
	
	/**
	 * Mutator function for totalNumberOfMutants allowing an integer
	 * as a parameter for specifying a value.
	 * @param numberOfKilledMutants
	 */
	public void setNumberOfKilledMutants(int numberOfKilledMutants) {
		this.numberOfKilledMutants = numberOfKilledMutants;
	}
	
	/**
	 * Mutator function for numberOfKilledMutants
	 * This function increments numberOfKilledMutants
	 */
	public void killMutant() {
		this.numberOfKilledMutants++;
	}
	
	/**
	 * Mutator function for numberOfKilledMutants
	 * This function increments numberOfKilledMutants by the value
	 * specified in the parameter numberOfMutantsKilled
	 * @param numberOfMutantsToKill
	 */
	public void killNumberOfMutants(int numberOfMutantsToKill) {
		this.numberOfKilledMutants += numberOfMutantsToKill;
	}
	
	// This section contains functions accessing and modifying numberOfLegalTests
	
	/**
	 * Accessor function for numberOfLegalTests
	 */
	public int getNumberOfLegalTests(){
		return numberOfLegalTests;
	}
	
	/**
	 * Mutator function for numberOfLegalTests
	 * This function provides increment functionality for tracking the number of legal tests.
	 * This function will also increment the value stored in totalNumberOfTests
	 */
	public void addLegalTest(){
		numberOfLegalTests++;
		addTest();
	}
	
	/**
	 * Mutator function for numberOfLegalTests
	 * This function provides ability for incrementing number of legal tests
	 * by a specified integer parameter. This function will also increase the value
	 * stored in totalNumberOfTests
	 */
	public void addNumberOfLegalTests(int numberOfTestsToAdd){
		numberOfLegalTests += numberOfTestsToAdd;
		addNumberOfTests(numberOfTestsToAdd);
	}
	
	/**
	 * Mutator function for numberOfLegalTests
	 * This function stores the value of the parameter in the numberOfLegalTests variable
	 * @param numberOfTests
	 */
	public void setNumberOfLegalTests(int numberOfTests) {
		numberOfLegalTests = numberOfTests;
	}
	
	/**
	 * Mutator function for numberOfLegalTests
	 * This function provides ability to decrement number of legal tests
	 */
	public void removeLegalTest(){
		numberOfLegalTests--;
	}
	
	// This section contains functions accessing and modifying totalNumberOfTests
	
	/**
	 * Accessor function for totalNumberOfTests
	 * @return
	 */
	public int getTotalNumberOfTests() {
		return totalNumberOfTests;
	}
	
	/**
	 * Mutator function for totalNumberOfTests
	 * This function increments the value stored in totalNumberOfTests
	 */
	public void addTest() {
		totalNumberOfTests++;
	}
	
	/**
	 * Mutator function for totalNumberOfTests
	 * This function increments the value stored in totalNumberOfTests
	 * by the integer amount passed in as a parameter.
	 * 
	 * @param numberOfTestsToAdd
	 */
	public void addNumberOfTests(int numberOfTestsToAdd){
		totalNumberOfTests += numberOfTestsToAdd;
	}
	
	/**
	 * Mutator function for totalNumberOfTests
	 * This function will set the value of totalNumberOfTests equal to
	 * the integer amount passed in as a parameter.
	 * @param numberOfTests
	 */
	public void setTotalNumberOfTests(int numberOfTests){
		totalNumberOfTests =  numberOfTests;
	}
	
	// This section contains additional functions for generating reports
	
	/**
	 * This function calculates and returns the number of mutations
	 * that remain alive.
	 * 
	 * @return
	 */
	public int getNumberOfMutantsAlive(){
		return (totalNumberOfMutants - numberOfKilledMutants);
	}
	
	/**
	 * This function calculates a basic "quality score" by dividing the number
	 * of mutants killed by a given test suite by the total number of mutants
	 * @return
	 */
	public double qualityScore(){
		return (numberOfKilledMutants/totalNumberOfMutants);
	}
	/**
	 * This function builds a Summary Report and returns it as a string.
	 * @return
	 */
	public String getSummaryReport() {
		StringBuilder sb = new StringBuilder();
		sb.append("Summary Report:\n");
		sb.append("Total number of mutants:             " + totalNumberOfMutants + "\n");
		sb.append("Number of mutants killed:            " + numberOfKilledMutants + "\n");
		sb.append("Number of mutations remaining alive: " + getNumberOfMutantsAlive() + "\n");
		sb.append("Number of legal tests:               " + numberOfLegalTests + "\n");
		sb.append("Quality score:                       " + qualityScore() + "\n");
		return sb.toString();
	}
	
	/**
	 * This function prints the Summary Report to the console
	 */
	public void printToSummaryReportConsole() {
		System.out.print(getSummaryReport());
	}
	
	/**
	 * This function prints the Summary Report to a .txt file
	 */
	public void printSummaryReportToFile()
	{
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("MutationTestingReport.txt"), "utf-8"));
		    writer.write(getSummaryReport());
		} catch (IOException ex) {
			logger.error("The following IO error occurred while attempting to print the report to a file:");
			logger.error(ex.getMessage());
		} finally {
			try {writer.close();} catch (Exception ex) {
				logger.error("The following error occurred while attempting to clean up after printing to a file:\n");
				logger.error(ex.getMessage());
		   }
		}
	}
	

}
