/**
 * 
 */
package MutationTesting;

import java.io.IOException;


import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.commons.cli.*;

/**
 * The MutationTestingApp class serves as the overall class
 * driving the mutation testing application.
 *
 */
public class MutationTestingApp {

	/**
	 * This function executes when the program is launch.
	 * This function accepts a variety of command line arguments
	 * to specify the operation of the application.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	
	private static final Logger logger = Logger.getLogger(MutationTestingApp.class);

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		logger.debug("instantiating MutationTest");
		MutationTest mutationTest = new MutationTest();
		
		// Create command line options

		logger.debug("creating command-line arguments");
		Options options = new Options();
		Option comparisonScript  = OptionBuilder.withArgName("file")
												.hasArg()
												.withDescription("path to comparison script file")
												.create("c");
		Option goldVersionFiles  = OptionBuilder.withArgName("file(s)")
											  	.hasArgs()
											  	.withDescription("files included in gold version")
											  	.create("g");
		Option buildFile		 = OptionBuilder.withArgName("file")
												.hasArg()
												.withDescription("build configuration file")
												.create("bf");
		Option buildCommand		 = OptionBuilder.withArgName("command")
												.hasArg()
												.withDescription("build command")
												.create("bc");
		Option mutationFiles	 = OptionBuilder.withArgName("number(s)")
												.hasArgs()
												.withDescription("argument numbers (1-n) of gold version files to be mutated")
												.create("m");
		Option preliminaryTest   = OptionBuilder.withArgName("file")
												.hasArg()
												.withDescription("preliminary test input")
												.create("p");
		Option testSuite		 = OptionBuilder.withArgName("directory")
												.hasArg()
												.withDescription("test suite directory")
												.create("t");
		options.addOption(buildFile);
		options.addOption(comparisonScript);
		options.addOption(goldVersionFiles);
		options.addOption(mutationFiles);
		options.addOption(preliminaryTest);
		options.addOption(testSuite);
		options.addOption(buildCommand);
		
		CommandLineParser parser = new GnuParser();
		/* right now, print the arguments to the command line flags
		 * until we have something useful to do
		 */
		
		logger.debug("parsing command line arguments");
		try{
			CommandLine line = parser.parse(options,args);
			
			if(line.hasOption("c")) {
				System.out.println("Comparison script: "
						+ line.getOptionValue("c") );
			}
			
			if(line.hasOption("bf")) {
				mutationTest.setBuildFile(line.getOptionValue("bf"));
			}
			
			if(line.hasOption("bc")) {
				mutationTest.setBuildCommand(line.getOptionValue("bc"));
			}
			
			if(line.hasOption("g")) {
				if(line.hasOption("m") ) {
					mutationTest.importGoldVersionFiles(line.getOptionValues("g"),line.getOptionValues("m") );
				}
				else {
					logger.error("no gold version files selected for mutation.");
				}
			}
			else {
				logger.error("no gold version files specified.");
			}
			
			if(line.hasOption("p")){
				System.out.println("Preliminary Test: " + line.getOptionValue("p"));
			}
			
			if(line.hasOption("t")) {
				mutationTest.setTestSuite(line.getOptionValue("t") );
			}

		}
		catch(ParseException e) {
			logger.error("Usage error: " + e.getMessage() );
			System.exit(1);
		}
		
		mutationTest.prepareGoldVersion();
		
		/*
		 * After program input, next step is to generate mutations.
		 */
		mutationTest.generateMutations();
		
		/*
		 * After mutation generation, run compilation.
		 */
		mutationTest.compileMutations();
		
		logger.debug("Running Preliminary Test"); //not yet implemented
		//mutationTest.runTest(); // run preliminary test
		 
		/*
		 * After test suite, run comparator script.
		 */
		/*
		 * Lastly, print report.
		 */
		mutationTest.printSummaryReportToConsole();
	}

}
