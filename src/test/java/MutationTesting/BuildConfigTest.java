package MutationTesting;

import static org.junit.Assert.*;
import org.junit.Test;

public class BuildConfigTest{

	
	@Test
	public void testBuildConfig(){
		
		BuildConfig build = new BuildConfig();
		
		assertEquals(build.getNumSources(), 0);
		
	}
	
	@Test
	public void testCreateAntBuildConfig(){
		
		BuildConfig build = new BuildConfig();
		
		//build.createAntBuildConfig();
		
		fail("Not yet implemented");
		
	}
	
}