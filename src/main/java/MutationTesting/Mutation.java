package MutationTesting;

import java.io.File;

/**
 * Mutation model object
 * 
 * This object stores information about one individual possible mutation to a
 * file, including the location (line, character range) of the modification to
 * be made, the old operator and the one it is being replaced with, whether or
 * not the mutation has failed/been killed, and which file the mutation is
 * derived from.
 * 
 * @author jbreeden
 *
 */
public class Mutation {
	private boolean failed;
	private String oldOperator;
	private String newOperator;
	private int line;
	private int start;
	private int end;
	private File file;
	
	/**
	 * empty constructor for Mutation
	 */
	public Mutation() {
		failed = false;
	}
	
	/**
	 * Default constructor for Mutation
	 * 
	 * @param file from which the mutation will be made
	 * @param oldOperator the operator being changed
	 * @param newOperator the operator it is being changed to
	 * @param line on which the mutation exists
	 * @param start character index on line
	 * @param end character index on line
	 */
	public Mutation(File file, String oldOperator, String newOperator, int line, int start, int end) {
		this.oldOperator = oldOperator;
		this.newOperator = newOperator;
		this.line = line;
		this.start = start;
		this.end = end;
		this.failed = false;
		this.file = file;
	}
	
	/**
	 * @return whether or not this mutation has been killed
	 */
	public boolean isFailed() {
		return failed;
	}
	/**
	 * @param failed whether or not this mutation has been killed
	 */
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	
	/**
	 * getter for oldOperator
	 * 
	 * @return the oldOperator
	 */
	public String getOldValue() {
		return oldOperator;
	}
	/**
	 * setter for oldOperator
	 * 
	 * @param oldOperator the oldOperator to set
	 */
	public void setOldValue(String oldOperator) {
		this.oldOperator = oldOperator;
	}
	
	/**
	 * getter for newOperator
	 * 
	 * @return the newOperator
	 */
	public String getNewOperator() {
		return newOperator;
	}
	/**
	 * setter for newOperator
	 * 
	 * @param newOperator the newOperator to set
	 */
	public void setNewOperator(String newOperator) {
		this.newOperator = newOperator;
	}
	/**
	 * getter for line
	 * 
	 * @return the line
	 */
	public int getLine() {
		return line;
	}
	/**
	 * setter for line
	 * 
	 * @param line the line to set
	 */
	public void setLine(int line) {
		this.line = line;
	}
	
	/**
	 * getter for start
	 * 
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * setter for start
	 * 
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	
	/**
	 * getter for end
	 * 
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * setter for end
	 * 
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	/**
	 * reports a string describing the mutation that this object describes
	 * 
	 * @return string describing the mutation
	 */
	public String report() {
		return  oldOperator
				+ " to " + newOperator
				+ " on line " + (line+1)
				+ ", char " + start
				+ (start != end ? (" to " + end) : "");
	}

	/**
	 * getter for file
	 * 
	 * @return the sourceFile
	 */
	public File getFile() {
		return file;
	}

	/**
	 * setter for file
	 * 
	 * @param sourceFile the sourceFile to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	
}
