package MutationTesting;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class TestSuiteTest {

	@Test
	public void testGetNumberOfTests() {
		TestSuite testSuite = new TestSuite();
		assertEquals(testSuite.getNumberOfTests(), 0);
	}

	@Test
	public void testGetTestFileAtIndex() {
		TestSuite t = new TestSuite();
		t.setTestSuiteFolder("src/test/resources/lucas/testsuite");
		File f1 = new File("src/test/resources/lucas/testsuite/test1.in");
		File f2 = new File("src/test/resources/lucas/testsuite/f/test2.in");
		
		assertEquals(t.getTestFileAtIndex(0),f2);
		assertEquals(t.getTestFileAtIndex(1),f1);
	}

	@Test
	public void testSetTestSuiteFolder() {
		TestSuite t = new TestSuite();
		t.setTestSuiteFolder("src/test/resources/lucas/testsuite");
		assertEquals(t.getTestSuiteFolder(), "src/test/resources/lucas/testsuite");
	}

	@Test
	public void testGetTestSuiteFolder() {
		TestSuite testSuite = new TestSuite();
		assertTrue(testSuite.getTestSuiteFolder().equals("") );
	}

	@Test
	public void testAddFilesFromFolder() {
		TestSuite t = new TestSuite();
		File dir = new File("src/test/resources/lucas/testsuite");
		File f1 = new File("src/test/resources/lucas/testsuite/test1.in");
		File f2 = new File("src/test/resources/lucas/testsuite/f/test2.in");

		ArrayList<File> files = new ArrayList<File>();
		files = (ArrayList<File>) t.addFilesFromFolder(dir);
		
		assertEquals(files.size(), 2);
		assertTrue(files.contains(f1));
		assertTrue(files.contains(f2));
		
	}
	
	@Test
	public void testRemoveIllegalTests() throws IOException {
		TestSuite suite = new TestSuite();
		File directory = new File("src/test/resources/lucas/testsuite");
		File test1 = new File("src/test/resources/lucas/testsuite/test1.in");
		
		ArrayList<File> testFiles = new ArrayList<File>();
		testFiles = (ArrayList<File>) suite.addFilesFromFolder(directory);
		
		assertEquals(testFiles, suite.removeIllegalTests(testFiles));
	}

	@Test
	public void testSpecifyTestInputs() {
		TestSuite suite = new TestSuite();
		File directory = new File("src/test/resources/lucas/testsuite");
		File test1 = new File("src/test/resources/lucas/testsuite/test1.in");
		
		String base = "1";
		
		ArrayList<File> testFiles = new ArrayList<File>();
		testFiles = (ArrayList<File>) suite.addFilesFromFolder(directory);
		
		assertEquals(testFiles, suite.specifyTestInputs(testFiles, base));
	}

}
