/**
 * Mutation Compiler
 * 
 * This class is used to compile the mutated source code that is found
 * in the specified directory
 * 
 */
package MutationTesting;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public abstract class MutationCompiler {
	
	static final Logger logger = Logger.getLogger(MutationCompiler.class);
	
	/**
	 * This function will cycle through each mutation that was generated and
	 * attempt to compile the mutation. The mutation report is updated for
	 * mutations that compile.
	 * 
	 * @param mutations
	 * @param report
	 * @param buildCommand
	 */
	public static void compileAll(ArrayList<Mutation> mutations, MutationReport report, String buildCommand)
	{
		for (int i = 0; i < mutations.size(); i++) {
			try
			{
				
				boolean compileStatus = compileAtPath("output/mutations/" + i, buildCommand);
				if(compileStatus)
				{
					report.addMutant();
					System.out.println("Mutant program " + i + " compiled.");
				}
				else
				{
					Mutation failedMutant = mutations.get(i);
					failedMutant.setFailed(true);
					System.out.println("Mutant program " + i + " failed to compile.");
				}
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
		}
	}
	
	/**
	 * runs the buildCommand specified by the user at the path specified in the
	 * String argument. This is the general purpose build function that will
	 * make multiple compiled copies of the program on which tests can be run.
	 * 
	 * @param path
	 *            filepath in which the build command should be executed.
	 * @throws IOException 
	 */
	public static boolean compileAtPath(String path, String buildCommand) throws IOException {
        try 
        {
            String s= null;
            String compileLog = "";
            File compilePath = new File(path);
            boolean error = false;
            //change this string to your compilers location
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(buildCommand, null, compilePath);
                    
//            BufferedReader stdError = new BufferedReader(new 
//            		InputStreamReader(process.getErrorStream()));
                   
	        try {
				process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if(process.exitValue() != 0) {
	        	return false;
	        }

//	        stdError.close();
//	        System.out.println(compileLog);
	        return true;
//	        return error;
        }
        catch (IOException e) {
        	logger.error("IOException: " + e.getMessage());
			throw new IOException("IOException: " + e.getMessage());
		}
	}
	
	private static boolean isRunning(Process process) {
	    try {
	        process.exitValue();
	        return false;
	    } catch (Exception e) {
	        return true;
	    }
	}
}
