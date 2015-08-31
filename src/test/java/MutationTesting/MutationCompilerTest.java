package MutationTesting;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class MutationCompilerTest {

	@Test
	public void goldVersionCompileAtPathTest() {
		String s = File.separator;
		String path = "src"+s+"test"+s+"resources"+s+"lucas";
		String buildCommand = "cmd.exe /C make";
		try{
			boolean compileError = MutationCompiler.compileAtPath(path, buildCommand);
			assertFalse(compileError);
		}
		catch(IOException e)
		{
			fail("IOExceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void badMutationCompileAtPathTest() {
		String s = File.separator;
		String path = "src"+s+"test"+s+"output"+s+"mutations"+s+"0";
		String buildCommand = "cmd.exe /C make";
		try{
			boolean compileError = MutationCompiler.compileAtPath(path, buildCommand);
			assertTrue(compileError);
		}
		catch(IOException e)
		{
			fail("IOExceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void compileAllPathTest() {
		ArrayList<Mutation> mutations = new ArrayList<Mutation>();
		mutations.add(new Mutation());
		mutations.add(new Mutation());
		mutations.add(new Mutation());
		
		MutationReport report = new MutationReport();
		
		String buildCommand = "cmd.exe /C make";
		MutationCompiler.compileAll(mutations, report, buildCommand);
		assertEquals(report.getNumberOfMutantsAlive(),0);
		
	}

}
