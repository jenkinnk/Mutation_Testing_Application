/**
 * 
 */
package MutationTesting;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;


/**
 * @author jbreeden
 *
 */
public class MutatorTest {
	SourceFile blank,blankline,everyoperator,single,lucas;
	File outputdir,mutationsdir;
	@Before
	public void setUp() {
		blank = new SourceFile("src/test/resources/MutatorTest/blank.txt");
		blankline = new SourceFile("src/test/resources/MutatorTest/blankline.txt");
		everyoperator = new SourceFile("src/test/resources/MutatorTest/everyoperator.txt");
		single = new SourceFile("src/test/resources/MutatorTest/single.txt");
		lucas = new SourceFile("src/test/resources/lucas/lucas.cpp");
		outputdir = new File("src/test/output");
		mutationsdir = new File("src/test/output/mutations");
		mutationsdir.mkdirs();
	}

	/**
	 * Test method for
	 * {@link MutationTesting.Mutator#listMutationsInFile(java.io.File)}.
	 */
	@Test
	public void testListMutationsInFile() {		
		assertEquals(Mutator.listMutationsInFile(blank).size(),0);
		assertEquals(Mutator.listMutationsInFile(blankline).size(),0);
		assertEquals(Mutator.listMutationsInFile(single).size(),3);
		assertTrue(Mutator.listMutationsInFile(everyoperator).size() > 0);
		assertTrue(Mutator.listMutationsInFile(lucas).size() > 0);
	}

	/**
	 * Test method for
	 * {@link MutationTesting.Mutator#saveMutatedFileToDirectory(java.io.File, MutationTesting.Mutation, java.io.File)}
	 * .
	 */
	@Test
	public void testSaveMutatedFileToDirectory() {
		ArrayList<Mutation> mutations = new ArrayList<Mutation>();
		mutations.addAll(Mutator.listMutationsInFile(blank));
		mutations.addAll(Mutator.listMutationsInFile(blankline));
		mutations.addAll(Mutator.listMutationsInFile(everyoperator));
		mutations.addAll(Mutator.listMutationsInFile(single));

		for (int i = 0; i < mutations.size(); i++) {
			File savePath = new File(mutationsdir.getPath() + File.separator
					+ i);
			savePath.mkdir();
			Mutator.saveMutatedFileToDirectory(mutations.get(i), savePath);
		}
		for (int i = 0; i < mutations.size(); i++) {
			File savedFile = new File(mutationsdir.getPath() + File.separator
					+ i + File.separator + mutations.get(i).getFile().getName());
			assertTrue(savedFile.exists());
		}
	}
}
