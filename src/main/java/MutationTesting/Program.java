package MutationTesting;

import java.util.List;
import java.util.ArrayList;

/**
 * Program model object.
 * We use this to store the gold version of our program.
 * <p>A program consists primarily of source files and various supporting
 * documents, such as build configuration and comparison files.
 * <p>Each mutant version is created by duplicating and modifying the gold
 * version Program instance.
 * @author jbreeden
 *
 */
public class Program {
	private List<SourceFile> sourceFiles;
	
	/**
	 * Program Constructor
	 * This function initializes a new instance of ArrayList for sourceFiles.
	 */
	Program() {
		sourceFiles = new ArrayList<SourceFile>();
	}

	/**
	 * Mutator Function for SourceFile
	 * This function accepts a SourceFile and addes it to the list of source
	 * files for the program.
	 * 
	 * @param file
	 */
	public void addSourceFile(SourceFile file) {
		sourceFiles.add(file);
	}
	
	/**
	 * This function returns the number of sources files contained in a program instance.
	 * @return
	 */
	public int getNumberOfSourceFiles() {
		return sourceFiles.size();
	}

	/**
	 * returns the SourceFile at the specified index
	 * 
	 * @param index of SourceFile
	 * @return SourceFile at specified index
	 */
	public SourceFile getSourceFileAtIndex(int index) {
		return sourceFiles.get(index);
	}
	
	/**
	 * Determines the provided sources files are within the 
	 * standard size of a typical CS class assignment
	 * 
	 * The default maximum size is 10 MB
	 * 
	 * @return smallEnough boolean value
	 */
	public boolean determineSizeOfProgram(){
		long byteSize = 0;
		boolean smallEnough = true;
		SourceFile check;
		
		for(int i = 0; i < getNumberOfSourceFiles(); i++){
			check = getSourceFileAtIndex(i);
			byteSize += check.length();
		}
		
		if(byteSize > 10485760){
			smallEnough = false;
		}
		
		return smallEnough;
	}
	
}