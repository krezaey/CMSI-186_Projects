import com.sun.jndi.url.dns.dnsURLContext;

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Main program to run SoccerSimulation
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2018-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

 public class SoccerSim {

     final double FIELD_SIZE = 500;

     static double ballCount = 0;
     double timeSlice = 1.0; 

     public SoccerSim() {}

     public void handleInitialArgs( String args[] ) {
         System.out.println("I want spring break now");

     }

     public static void main ( String args[] ) {
         ballCount = (int) (args.length / 4);

         ball[] b = new Ball[ballCount];
		 int j = 0; 
		 for ( int i = 0; i < args.length; i += 4 ) {
			b[j] = new Ball (args[i], args[i+1], args[i+2], args[i+3]);
			j++;
		 }
     }

 }