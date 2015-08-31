package MutationTesting;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MutationTestTest {
	private MutationTest test;
	private String s = File.separator;
	@Before
	public void setUp() {
		test = new MutationTest();
		String files[] = {"src"+s+"test"+s+"resources"+s+"lucas"+s+"lucas.cpp","src"+s+"test"+s+"resources"+s+"lucas"+s+"lucas.h"};
		String toMutate[] = {"1","2"};
		
		test.importGoldVersionFiles(files, toMutate);		
		test.setBuildFile("src"+s+"test"+s+"resources"+s+"lucas"+s+"makefile");
		test.setBuildCommand("make");
		
		File testDir = new File("src"+s+"test"+s+"temp"+s+"lucas");
		
		if(! testDir.exists()) {
			testDir.mkdirs();
		}
	}
	
	@Test
	public void testImportGoldVersionFiles() {
		File lucas_cpp = new File("src"+s+"test"+s+"resources"+s+"lucas"+s+"lucas.cpp");
		File lucas_h = new File("src"+s+"test"+s+"resources"+s+"lucas"+s+"lucas.h");
		assertEquals(test.getGoldVersion().getNumberOfSourceFiles(), 2);
		assertEquals(test.getGoldVersion().getSourceFileAtIndex(0), lucas_cpp);
		assertEquals(test.getGoldVersion().getSourceFileAtIndex(1), lucas_h);
	}

	@Test
	public void testCopyVersionToDirectoryAtPath() {
		File testDir = new File("src"+s+"test"+s+"temp"+s+"lucas");
		
		if(! testDir.exists()) {
			testDir.mkdirs();
		}
		test.copyVersionToDirectoryAtPath(testDir.getAbsolutePath(), test.getGoldVersion());
		
		File makefile = new File("src"+s+"test"+s+"temp"+s+"lucas"+s+"makefile");
		assertTrue(makefile.exists());
		
		for(int i=0; i<test.getGoldVersion().getNumberOfSourceFiles(); i++) {
			assertTrue(test.getGoldVersion().getSourceFileAtIndex(i).exists() );
		}
	}
	
	@Test
	public void testBuildVersionAtPath() {
		File testDir = new File("src"+s+"test"+s+"temp"+s+"lucas");
		
		if(! testDir.exists()) {
			testDir.mkdirs();
		}
		
		test.copyVersionToDirectoryAtPath(testDir.getAbsolutePath(), test.getGoldVersion());
		try{
			test.buildVersionAtPath("src"+s+"test"+s+"temp"+s+"lucas");
		}
		catch(IOException e){
			fail(e.getMessage());
		};
	}
	
	@Test
	public void testGenerateMutations() {
		test.generateMutations();
	}
	

	@Test
	public void testCompileMutations() {
		test.compileMutations();
	}
	
	@Test
	public void testViewSourceFile() {
		try{
			test.viewSourceFile(new File("output/mutations/0/lucas.cpp"));
		}
		catch(IOException e)
		{
			fail(e.getMessage() );
		}
	}

	@Test public void listMutationsByOperator(){
		Mutation mutation1 = new Mutation();
		MutationTest mTest = new MutationTest();
		ArrayList<Mutation> list = new ArrayList<Mutation>(Arrays.asList(mutation1));
		mTest.setMutationList(list); 
		String fname = "path";
		File file = new File(fname);
		
		mutation1.setOldValue("+");
		mutation1.setNewOperator("*");
		mutation1.setLine(5);
		mutation1.setStart(3);
		mutation1.setEnd(5);
		mutation1.setFile(file);

		
		StringBuilder testList = new StringBuilder();
		testList.append("List of all mutations by operator: " + "\n");
		testList.append("Operator +: " + "\n");
		testList.append(mutation1.report() + " in file " + 
						mutation1.getFile() + "\n");
		
		assertEquals(testList.toString(),mTest.listMutationsByOperator());	
	}
	
	@Test public void listOfRemainingLiveMutations(){
		Mutation mutation2 = new Mutation();
		MutationTest mTest2 = new MutationTest();
		ArrayList<Mutation> list = new ArrayList<Mutation>(Arrays.asList(mutation2));
		mTest2.setMutationList(list); 
		String fname = "path";
		File file = new File(fname);
		
		mutation2.setOldValue("+");
		mutation2.setNewOperator("*");
		mutation2.setLine(6);
		mutation2.setStart(4);
		mutation2.setEnd(6);
		mutation2.setFile(file);
		mutation2.setFailed(false);
		
		StringBuilder testListAlive = new StringBuilder();
		testListAlive.append("List of remaining live mutations: " + "\n");
	    if(!mutation2.isFailed())
	    {
	    	testListAlive.append(mutation2.report() + " in file " + 
					mutation2.getFile() + "\n");
	    }
		
		assertEquals(testListAlive.toString(),mTest2.listOfRemainingLiveMutations());	
	}
	
	@After
	public void tearDown() {
		File tempDirectory = new File("src"+s+"test"+s+"temp");
		try {
			FileUtils.forceDelete(tempDirectory);
		} catch (IOException e) {
			
		}
	}
}
