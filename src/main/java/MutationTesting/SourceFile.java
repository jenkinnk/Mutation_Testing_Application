/**
 * 
 */
package MutationTesting;

import java.io.File;
import java.net.URI;

/**
 * A subclass of File that knows whether or not it should be mutated
 * 
 * @author jbreeden
 *
 */
public class SourceFile extends File {
	private boolean shouldBeMutated = false;

	/**
	 * inherited constructor
	 * 
	 * @param arg0
	 */
	public SourceFile(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * inherited constructor
	 * 
	 * @param arg0
	 */
	public SourceFile(URI arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * inherited constructor
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public SourceFile(String arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * inherited constructor
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public SourceFile(File arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * setter for shouldBeMutated
	 * 
	 * @param bool indicates whether or not this file should be mutated
	 */
	public void setShouldBeMutated(boolean bool) {
		shouldBeMutated = bool;
	}
	
	/**
	 * getter for shouldBeMutated
	 * 
	 * @return 
	 */
	public boolean getShouldBeMutated() {
		return shouldBeMutated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (shouldBeMutated ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SourceFile other = (SourceFile) obj;
		if (shouldBeMutated != other.shouldBeMutated)
			return false;
		return true;
	}

}
