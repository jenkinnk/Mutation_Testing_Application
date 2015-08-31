/**
 * This class is intended to be used to create the build configuration files necessary for the executable
 * programs created mutated or otherwise
 * 
 * @author njenkin
 */

package MutationTesting;

//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

public class BuildConfig{
	
	static final Logger logger = Logger.getLogger(BuildConfig.class);
	//Program targetProgram;
	int numSources;
	
	public BuildConfig(){
		numSources = 0;
	}
	
	
	public int getNumSources(){
		return numSources;
	}
	public int createAntBuildConfig(){
		
		PrintWriter build = null;
		
		try{
		build = new PrintWriter ("build.xml.experimental", "UTF-8");
		build.println("<project name=\"Mutated\" default=\"dist\" basedir=\".\">");
		build.println("<description>");
		build.println("Mutated Build File");
		build.println("</description>");
		build.println("<property name=\"src\" location=\"src\"/>");
		build.println("<property name=\"build\" location=\"build\"/>");
		build.println("<property name=\"dist\"  location=\"dist\"/>");
		build.println("<target name=\"init\">");
		build.println("<tstamp/>");
		build.println("<mkdir dir=\"${build}\"/>");
		build.println("</target>");
		build.println("<target name= \"compile\" depends= \"init\" description= \"compile the source\" >");
		build.println("<javac srcdir=\"${src}\" destdir=\"${build}\"/>");
		build.println("</target>");
		build.println("<target name=\"dist\" depends=\"compile\" description=\"generate the distribution\" >");
		build.println("<mkdir dir=\"${dist}/lib\"/>");
		build.println("<jar jarfile=\"${dist}/lib/Mutated-${DSTAMP}.jar \"basedir=\"${build}\"/>");
		build.println("</target>");
		build.println("<target name=\"clean\" description=\"clean up\" >");
		build.println("<delete dir=\"${build}\"/>");
		build.println("<delete dir=\"${dist}\"/>");
		build.println("</target>");
		build.println("</project>");
		}
		
		catch (IOException buildex){
			logger.error("Could not create build.xml because of the following error:");
			logger.error(buildex.getMessage());
			return 1;
		}
		
		//finally {
		  try {
			  build.close();
		  } 
		  catch (Exception buildex) {
			logger.error("Could not close build.xml because of the following error:");
			logger.error(buildex.getMessage());
		   return 1;
		   }
		//}
		
		return 0;
	}
	
	//numSources = targetProgram.numberOfSourceFiles();
	
	
}