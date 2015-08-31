/**
 *  
 */
package MutationTesting;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * Abstract class for generating Mutations and creating new files with them
 * 
 * @author jbreeden
 *
 */
public abstract class Mutator {
	static final Logger logger = Logger.getLogger(Mutator.class);
	
	/**
	 * Search a file for each possible mutation, and create a Mutation object
	 * for each. Add each of these Mutations to an ArrayList, and return it
	 * 
	 * @param file
	 *            to be searched for Mutations
	 * @return ArrayList<Mutation> containing each possible mutation.
	 */
	static ArrayList<Mutation> listMutationsInFile(File file) {
		ArrayList<Mutation> mutations = new ArrayList<Mutation>();
		ArrayList<String> operators = mutationOperators();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			int lineIndex = 0;
		
			while(line != null) {
//				System.out.println(line);
			
				// search the line multiple times for each possible mutation operator
				for(String op : operators) {
					int charIndex = line.indexOf(op);
					
				// so long as we continue to find matches on this line...
					while(charIndex != -1) {
						ArrayList <String> matches = mutationsOfOperator(op);
						// create a mutation for every possible replacement of the match
						for(String match : matches) {
							Mutation mutation = new Mutation(file,op,match,lineIndex,charIndex,charIndex+op.length()-1);
//							System.out.println(mutations.size() + ": " + mutation.report());
							mutations.add(mutation);
						}

						charIndex = line.indexOf(op,++charIndex);
					}
				}
			
				line = reader.readLine();
				lineIndex++;
			}
			reader.close();	
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return mutations;
	}
	
	
	/**
	 * For a specified Mutation and destination directory, make a mutated copy
	 * of the original file and a save it to destination directory.
	 * 
	 * @param mutation
	 *            to be performed
	 * @param directory
	 *            to be saved in
	 */
	static void saveMutatedFileToDirectory(Mutation mutation, File directory) {
		try {
			File file = new File(directory.getAbsolutePath() + File.separator
					+ mutation.getFile().getName());
			if (!file.exists()) {
				file.createNewFile();
			}

			FileReader fr = new FileReader(mutation.getFile());
			BufferedReader reader = new BufferedReader(fr);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);

			for (int line = 0; line < mutation.getLine(); line++) {
				String nextLine = reader.readLine();
				if (nextLine != null) {
					writer.write(nextLine);
					writer.newLine();
				}
			}
			String oldLine = reader.readLine();
			if (oldLine != null) {
				String begin = oldLine.substring(0, mutation.getStart());
				String end = (mutation.getEnd() < oldLine.length() - 1 ? oldLine
						.substring(mutation.getEnd() + 1) : "");
				String newLine = begin + mutation.getNewOperator() + end;
				writer.write(newLine);
				writer.newLine();
			}


		// System.out.println(oldLine);
		// System.out.println("changed to");
		// System.out.println(newLine);
		// System.out.println("via mutation: " + mutation.report());

			String line = reader.readLine();
			while (line != null) {
				writer.write(line);
				writer.newLine();
				line = reader.readLine();
			}

			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * List each possible mutation of the specified operand String
	 * 
	 * @param op
	 *            operand to find mutations for
	 * @return ArrayList<String> of possible mutations of op
	 */
	private static ArrayList<String> mutationsOfOperator(String op) {
		ArrayList<String> muts = new ArrayList<String>();
		if (op.equals("+")) {
			muts.add("-");
			muts.add("*");
			muts.add("/");
		} else if (op.equals("-")) {
			muts.add("+");
			muts.add("*");
			muts.add("/");
		} else if (op.equals("*")) {
			muts.add("+");
			muts.add("-");
			muts.add("/");
		} else if (op.equals("/")) {
			muts.add("+");
			muts.add("-");
			muts.add("*");
		} else if (op.equals("==")) {
			muts.add("!=");
		} else if (op.equals("!=")) {
			muts.add("==");
		} else if (op.equals("<=")) {
			muts.add("<");
			muts.add(">");
			muts.add(">=");
		} else if (op.equals(">=")) {
			muts.add("<");
			muts.add(">");
			muts.add("<=");
		} else if (op.equals("<")) {
			muts.add(">");
			muts.add("<=");
			muts.add(">=");
		} else if (op.equals(">")) {
			muts.add("<");
			muts.add("<=");
			muts.add(">=");
		}
		return muts;
	}

	/**
	 * All possible character sequences to be mutated in a file
	 * 
	 * @return ArrayList of mutatable Strings.
	 */
	private static ArrayList<String> mutationOperators() {
		ArrayList<String> ops = new ArrayList<String>();

		// ops.add("+=");
		// ops.add("-=");
		// ops.add("*=");
		// ops.add("/=");

		ops.add("+");
		ops.add("-");
		ops.add("*");
		ops.add("/");

		ops.add("==");
		ops.add("!=");
		ops.add("<=");
		ops.add(">=");
		ops.add("<");
		ops.add(">");

		return ops;
	}

}
