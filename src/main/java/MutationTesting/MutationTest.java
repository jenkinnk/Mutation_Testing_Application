package MutationTesting;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.*;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 * Mutation Test Model Object
 * 
 * <p>
 * Contains methods and variables for running a mutation test on source code.
 * 
 * @author jbreeden
 *
 */
public class MutationTest {

	private static final Logger logger = Logger.getLogger(MutationTest.class);

	private TestSuite testSuite;
	private Program goldVersion;
	private MutationReport report;
	private File buildFile;
	private String buildCommand;
	private String dataDirectory;
	private ArrayList<Mutation> mutationList;

	/**
	 * @return the dataDirectory
	 */
	public String getDataDirectory() {
		return dataDirectory;
	}

	/**
	 * @param dataDirectory
	 *            the dataDirectory to set
	 */
	public void setDataDirectory(String dataDirectory) {
		this.dataDirectory = dataDirectory;
	}

	/**
	 * MutationTest Constructor
	 * 
	 * Establishes new instances for variables goldVersion and report.
	 */
	public MutationTest() {
//		BasicConfigurator.configure();
		goldVersion = new Program();
		report = new MutationReport();
		testSuite = new TestSuite();
		dataDirectory = "output";
		mutationList = new ArrayList<Mutation>();
	}

	/**
	 * @return the buildCommand
	 */
	public String getBuildCommand() {
		return buildCommand;
	}

	/**
	 * @param buildCommand
	 *            the buildCommand to set
	 */
	public void setBuildCommand(String buildCommand) {
		this.buildCommand = buildCommand;
	}

	/**
	 * Setter function for goldVersion.
	 * 
	 * This function accepts a Program as a parameter and stores it into
	 * goldVersion
	 * 
	 * @param theGoldVersion
	 */
	public void setGoldVersion(Program theGoldVersion) {
		goldVersion = theGoldVersion;
	}

	/**
	 * Getter function for goldVersion.
	 * 
	 * @return goldVersion the gold version program.
	 */
	public Program getGoldVersion() {
		return goldVersion;
	}

	/**
	 * Copy Gold Version to temp directory and Build
	 * 
	 * Make the temp directory if necessary, copy the gold version into it, then
	 * build it according to the buildCommand and buildFile.
	 */
	public void prepareGoldVersion() {
		String goldVersionPath = dataDirectory + File.separator + "gold-version";
		File goldDirectory = new File(goldVersionPath);
		goldDirectory.mkdirs();
		copyVersionToDirectoryAtPath(goldVersionPath, goldVersion);
		try{
			buildVersionAtPath(goldVersionPath);
		}
		catch(IOException e)
		{
			logger.error(e.getMessage());
		}
	}

	/**
	 * Setter function for buildFile
	 * 
	 * This function accepts a String as a parameter and initializes buildFile
	 * using the path specified in the string.
	 * 
	 * @param pathToBuildFile
	 */
	public void setBuildFile(String pathToBuildFile) {
		buildFile = new File(pathToBuildFile);
		if (!buildFile.exists())
			logger.warn("Build file \"" + buildFile.getName()
					+ "\" does not exist.");
	}

	/**
	 * This function imports specified goldVersion files and correctly
	 * identifies the files that have been selected for mutation.
	 * 
	 * @param listOfFiles
	 * @param fileArrayIndicesToMutate
	 */
	public void importGoldVersionFiles(String listOfFiles[],
			String fileArrayIndicesToMutate[]) {
		logger.debug("importing gold version files");
		int numberOfFiles = listOfFiles.length;
		List<String> mutateList = Arrays.asList(fileArrayIndicesToMutate);

		for (int i = 0; i < numberOfFiles; i++) {
			SourceFile file = new SourceFile(listOfFiles[i]);
			if (file.exists()) {
				if (mutateList.contains(Integer.toString(i + 1)))
					file.setShouldBeMutated(true);
				this.goldVersion.addSourceFile(file);
				System.out.println("Imported " + listOfFiles[i]
						+ (file.getShouldBeMutated() ? " - MUTATE" : ""));
			} else {
				System.out.println("Unable to locate file: " + listOfFiles[i]);
			}
		}
	}

	public void copyVersionToDirectoryAtPath(String path, Program version) {
		File directory = new File(path);
		if (!directory.isDirectory()) {
			logger.warn("invalid temporary directory for version copy");
			return;
		}

		try {
			FileUtils.copyFileToDirectory(buildFile, directory);
		} catch (IOException e) {
			logger.warn("IOException while trying to copy file: "
					+ e.getMessage());
		}
		try {
			for (int i = 0; i < version.getNumberOfSourceFiles(); i++) {
				FileUtils.copyFileToDirectory(version.getSourceFileAtIndex(i),
						directory);
			}
		} catch (IOException e) {
			logger.fatal("unable to copy source file to temp directory :"
					+ e.getMessage());
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
	public void buildVersionAtPath(String path) throws IOException {
		File compilePath = new File(path);
		Runtime runtime = Runtime.getRuntime();
		try {
			String line;
			Process process = runtime.exec(buildCommand, null, compilePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			while ((line = in.readLine()) != null) {
				logger.debug(line);
			}
			in.close();

		} catch (IOException e) {
			logger.warn("unable to compile: " + e.getMessage());
			throw new IOException("Compilation failed.");
		}
	}

	/**
	 * This function generates mutations from the goldVersion program.
	 */
	public void generateMutations() {
		// generate the Mutation objects for each SourceFile
		logger.debug("generating Mutation objects for " + goldVersion.getNumberOfSourceFiles() + " possible input files");
		for(int i=0;i<goldVersion.getNumberOfSourceFiles();i++) {
			SourceFile file = goldVersion.getSourceFileAtIndex(i);
			if(file.getShouldBeMutated()) {
					ArrayList<Mutation> mutationsFromFile = Mutator.listMutationsInFile(file);
					if(mutationsFromFile.size() != 0) {
						logger.debug("attempting to add " + mutationsFromFile.size()  + " mutations for file " + file.getName());
						getMutationList().addAll(mutationsFromFile);
						logger.debug("mutationList now has " +getMutationList().size() + " mutations");
					} else
					{
						logger.debug("file " + file.getName() + " contains no mutations");
					}
			}
		}
		
		logger.debug("creating mutation directory");
		File directory = new File(dataDirectory+File.separator+"mutations");
		directory.mkdirs();
		
		// create unique directories and files for each mutation
		logger.debug("saving all mutations to disk");
		for(int i=0;i<getMutationList().size();i++) {
			File currentDir = new File(directory.getAbsolutePath()+File.separator+i);
			currentDir.mkdir();
			
			Mutator.saveMutatedFileToDirectory(getMutationList().get(i),currentDir);
			
			/*
			 *  copy all of the files which are not being mutated in this
			 *  current mutation into the mutation's directory
			 */
			for(int j=0;j<goldVersion.getNumberOfSourceFiles();j++) {
				File gvFile = goldVersion.getSourceFileAtIndex(j);
				if(!gvFile.equals(mutationList.get(i).getFile() ) ) {

					try {
						FileUtils.copyFileToDirectory(gvFile, currentDir);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			// copy build file to mutation's directory
			try {
				FileUtils.copyFileToDirectory(buildFile, currentDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Create list of all mutations by operator
	 * @return
	 */
	public String listMutationsByOperator(){
		StringBuilder list = new StringBuilder();
		list.append("List of all mutations by operator: " + "\n");
		list.append("Operator +: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++) {
		    if(getMutationList().get(i).getNewOperator().equals("+"))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator -: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals("-"))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator *: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals("*"))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator /: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals("/"))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator ==: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals("=="))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n"); 	
		    }
		}
		list.append("Operator !=: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals("!="))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator <=: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals("<="))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator >=: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals(">="))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator <: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals("<"))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		list.append("Operator >: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++){
		    if(getMutationList().get(i).getNewOperator().equals(">"))
		    {
				list.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		return list.toString();
	}

	/**
	 * Create list of remaining live mutations to console
	 * @return
	 */
	public String listOfRemainingLiveMutations(){
		StringBuilder live = new StringBuilder();
		live.append("List of remaining live mutations: " + "\n");
		for (int i = 0; i < getMutationList().size(); i++) {
		    if(!getMutationList().get(i).isFailed())
		    {
		    	live.append(getMutationList().get(i).report() + " in file " + 
						getMutationList().get(i).getFile() + "\n");
		    }
		}
		
		return live.toString();
	}
	
	/**
	 * Print list of all mutations by operator to console
	 */
	public void printListOfMutationsToConsole() {
		System.out.print(listMutationsByOperator());
	}
	
	/**
	 * Print list of remaining live mutations to console
	 */
	public void printRemainingLiveMutations() {
		System.out.print(listOfRemainingLiveMutations());
	}
	
	/**
	 * This function attempts to compile all of the generated mutations.
	 */
	public void compileMutations() {
		MutationCompiler.compileAll(mutationList, report, buildCommand);
	}

	/**
	 * This function retrieves the Summary Report and prints it to the console.
	 */
	public void printSummaryReportToConsole() {
		report.printToSummaryReportConsole();
	}

	public void setTestSuite(String path) {
		testSuite.setTestSuiteFolder(path);
	}

	
	/**
	 * This function accepts a mutate source file and prints the file to the screen.
	 * @param File
	 * @throws IOException 
	 */
	public void viewSourceFile(File mutatedSourceFile) throws IOException {
        BufferedReader br = null;
		 
		try 
		{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(mutatedSourceFile));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		finally 
		{
			try 
			{
				if (br != null)br.close();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
	}

	public ArrayList<Mutation> getMutationList() {
		return mutationList;
	}

	public void setMutationList(ArrayList<Mutation> mutationList) {
		this.mutationList = mutationList;
	}
}
