package MutationTesting;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import MutationTesting.SourceFile;
import java.io.IOException;
public class SourceFileTest {
	
	@Test
	public void testGetShouldBeMutated() {
		SourceFile file = new SourceFile("src/test/resources/lucas/lucas.cpp");
		assertFalse(file.getShouldBeMutated());
		
		file.setShouldBeMutated(true);
		assertTrue(file.getShouldBeMutated());
		
		file.setShouldBeMutated(true);
		assertTrue(file.getShouldBeMutated());
		
		file.setShouldBeMutated(false);
		assertFalse(file.getShouldBeMutated());
		
		file.setShouldBeMutated(false);
		assertFalse(file.getShouldBeMutated());
	}

	
	@Test
	public void testSetShouldBeMutated() {
		SourceFile file = new SourceFile("src/test/resources/lucas/lucas.cpp");
		assertFalse(file.getShouldBeMutated());
		
		file.setShouldBeMutated(true);
		assertTrue(file.getShouldBeMutated());
		
		file.setShouldBeMutated(true);
		assertTrue(file.getShouldBeMutated());
		
		file.setShouldBeMutated(false);
		assertFalse(file.getShouldBeMutated());
		
		file.setShouldBeMutated(false);
		assertFalse(file.getShouldBeMutated());
	}


}
