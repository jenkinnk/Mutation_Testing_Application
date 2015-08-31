package MutationTesting;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.log4j.*;



/** Description of TestSuite class
 * Object model of a test suite, which consists of a folder containing
 * any number of test files
 * 
 * @author jbreeden
 *
 */
public class TestSuite {
	private String pathToTestSuiteFolder;
	private List <File> testFiles;
	
	private static final Logger logger = Logger.getLogger(TestSuite.class);

	List<Character> illegalInputs = Arrays.asList('!','@','#','$','%','^','&','*','(',')','-');

	/**
	 * empty constructor for TestSuite.
	 */
	TestSuite() {
		pathToTestSuiteFolder = "";
	}
	
	/**
	 * default constructor for TestSuite.
	 * 
	 * @param pathToSuite
	 */
	TestSuite(String pathToSuite) {
		logger.debug("instantiating TestSuite");
		setTestSuiteFolder(pathToSuite);
	}
	
	/**
	 * 
	 * @return number of test files in the test suite
	 */
	public int getNumberOfTests() {
		if(testFiles == null)
			return 0;
		else
			return testFiles.size();
	}
	
	/**
	 * getter for indexed test file
	 * @param index index of test file desired
	 * @return file at said index
	 */
	public File getTestFileAtIndex(int index) {
		return testFiles.get(index);
	}
	
	/**
	 * setter method for String pathToTestSuiteFolder. This function adds all
	 * the files contained in the directory to the testFiles List.
	 * 
	 * @param pathToSuite path to the test suite directory
	 */
	public void setTestSuiteFolder(String pathToSuite) {
		pathToTestSuiteFolder = pathToSuite;
		File folder = new File(pathToSuite);
		testFiles = addFilesFromFolder(folder);
	}
	
	/**
	 * getter method for String pathToTestSuiteFolder, which contains a path
	 * to the test suite directory
	 * 
	 * @return the path to the test suite folder
	 */
	public String getTestSuiteFolder() {
		return pathToTestSuiteFolder;
	}
	
	/**
	 * Recursively searches the input folder and adds all contained 
	 * files to a List, which is then returned.
	 * 
	 * @param folder File object containing the input directory
	 * @return A List of File objects contained in "folder"
	 */
	public List <File> addFilesFromFolder(final File folder) {
		logger.debug("adding files from folder \"" + folder.getName() + "\"");
		List<File> files = new ArrayList<File>();
		for(final File fileEntry : folder.listFiles()) {
			if(fileEntry.isDirectory()) {
				files.addAll(addFilesFromFolder(fileEntry));
			} else {
				files.add(fileEntry);
				logger.debug("added \"" + folder.getName() + "/" + fileEntry.getName() + "\" to test suite");
			}
		}
		return files;
	}
	
	/**
	 * Iterates through the list of test inputs and removes the inputs with illegal
	 * characters from the list of legal tests
	 * 
	 * @param preliminary
	 * @return edited list
	 * @throws IOException 
	 */
	public List <File> removeIllegalTests(List <File> preliminary) throws IOException{
		logger.debug("Removing illegal tests");
		List <File> editedList = null;
		File examine;
		InputStream instream;
		BufferedReader buffer;
		String line;
		char input;
		char compare;
		Boolean valid = true;
		 
		for(int i = 0; i < preliminary.size(); i++){
			
			valid = true;
			examine = preliminary.get(i);
			instream = new FileInputStream(examine.getName());
			buffer = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
			
			while ((line = buffer.readLine()) != null && valid) {
				for(int j = 0; j < line.length(); j++){
					input = line.charAt(j);
					for(int k = 0; k < illegalInputs.size(); k++){
				    	compare = illegalInputs.get(j);
				    	if(input == compare){
				    		valid = false;
				    		preliminary.remove(i);
				    	}
				    }
				}
			    
			}
			
			buffer.close();
			
		}
		
		editedList = preliminary;
		
		return editedList;
	}
	
	/**
	 * This function pares down the list of test inputs to be only the ones
	 * with the matching baseName value specified by a string
	 * 
	 * @param inputList
	 * @param baseName
	 * @return editedList of test inputs
	 */
	@SuppressWarnings("null")
	public List<File> specifyTestInputs(List<File> inputList, String baseName){
		logger.debug("Paring down the list of inputs to matched basenames");
		
		List<File> editedList = null;
		File nameExamine;
		
		for(int i = 0; i < inputList.size(); i++){
			nameExamine = inputList.get(i);
			if(nameExamine.getName().contentEquals(baseName)){
				editedList.add(nameExamine);
			}
			
		}
		
		return editedList;
	}
	
}
