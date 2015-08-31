package MutationTesting;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProgramTest {

	@Test
	public void testProgram() {
		Program testProgram = new Program();
		
		assertEquals(testProgram.getNumberOfSourceFiles(), 0);
	}

	@Test
	public void testAddSourceFile() {
		Program testProgram = new Program();
		String name = "name";
		SourceFile testSource = new SourceFile(name);

		testProgram.addSourceFile(testSource);
		
		assertEquals(testProgram.getNumberOfSourceFiles(),1);
	}

	@Test
	public void testGetNumberOfSourceFiles() {
		Program testProgram = new Program();
		String name = "name";
		SourceFile testSource = new SourceFile(name);

		testProgram.addSourceFile(testSource);
		
		assertEquals(testProgram.getNumberOfSourceFiles(),1);
	}

	@Test
	public void testGetSourceFileAtIndex() {
		Program testProgram = new Program();
		String name = "name";
		SourceFile testSource = new SourceFile(name);

		testProgram.addSourceFile(testSource);
		
		assertEquals(testProgram.getSourceFileAtIndex(0), testSource);
	}

	@Test
	public void testDetermineSizeOfProgram(){
		Program testProgram = new Program();
		String name = "name";
		SourceFile testSource = new SourceFile(name);

		testProgram.addSourceFile(testSource);
		
		assertTrue(testProgram.determineSizeOfProgram());
	}

}
