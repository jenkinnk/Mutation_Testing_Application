package MutationTesting;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class MutationJUTest {

	@Test
	public void testMutation() {
		Mutation m = new Mutation();
		assertFalse(m.isFailed());
	}

	@Test
	public void testMutationFileStringStringIntIntInt() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertNotEquals(m, null);
	}

	@Test
	public void testIsFailed() {
		Mutation m = new Mutation();
		assertFalse(m.isFailed());
		m.setFailed(true);
		assertTrue(m.isFailed());
	}

	@Test
	public void testSetFailed() {
		Mutation m = new Mutation();
		assertFalse(m.isFailed());
		m.setFailed(true);
		assertTrue(m.isFailed());
		m.setFailed(false);
		assertFalse(m.isFailed());
	}

	@Test
	public void testGetOldValue() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getOldValue(), "*");
	}

	@Test
	public void testSetOldValue() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getOldValue(), "*");
		m.setOldValue("+");
		assertEquals(m.getOldValue(), "+");
	}

	@Test
	public void testGetNewOperator() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getNewOperator(), "+");
	}

	@Test
	public void testSetNewOperator() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getNewOperator(), "+");
		m.setNewOperator("-");
		assertEquals(m.getNewOperator(), "-");
	}

	@Test
	public void testGetLine() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getLine(), 35);
	}

	@Test
	public void testSetLine() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getLine(), 35);
		m.setLine(10);
		assertEquals(m.getLine(), 10);
	}

	@Test
	public void testGetStart() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getStart(), 5);
	}

	@Test
	public void testSetStart() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 3, 5);
		assertEquals(m.getStart(), 3);
		m.setStart(10);
		assertEquals(m.getStart(), 10);
	}

	@Test
	public void testGetEnd() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 5, 5);
		assertEquals(m.getEnd(), 5);
	}

	@Test
	public void testSetEnd() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 3, 5);
		assertEquals(m.getEnd(), 5);
		m.setEnd(10);
		assertEquals(m.getEnd(), 10);
	}

	@Test
	public void testReport() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 3, 3);
		
		assertEquals(m.report(), "* to + on line 36, char 3");
	}

	@Test
	public void testGetFile() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation(f, "*", "+", 35, 3, 5);
		
		assertEquals(f,m.getFile());
	}

	@Test
	public void testSetFile() {
		File f = new File("src/test/resources/fibo/fibo.cpp");
		Mutation m = new Mutation();
		
		m.setFile(f);
		assertEquals(f,m.getFile());
	}

}
