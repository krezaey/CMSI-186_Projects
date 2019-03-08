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

     double timeSlice = 1.0; 
     double ballCount = 0;

     public SoccerSim() {}

     public void handleInitialArgs( String args[] ) {

        System.out.println( "\n Welcome to the Soccer Simulator program! \n" ) ;
        if( 0 == args.length ) {
           System.out.println( "   Sorry, you must enter at least one argument and an appropriate number of arguments.\n" +
                               "   Usage: java SoccerSim <xlocation> <ylocation> <xvelocity> <yvelocity> (repeat this for as many balls as you want) [timeSlice]\n" +
                               "   Please try again..........." );
           System.exit( 1 );
        }

     }

     public void validateArgs ( String args[] ) {
         System.out.println("I am doing stuff right now");
     }

    //  public void setBallCount(int num) {
    //      this.ballCount = num;
    //  }

     public static void main ( String args[] ) {

        SoccerSim sim = new SoccerSim();

         sim.ballCount = (int) (args.length / 4);
         int j = 0; 
         ball[] b = new Ball[sim.ballCount];

		 for ( int i = 0; i < args.length; i += 4 ) {
			b[j] = new Ball ( args[i], args[i+1], args[i+2], args[i+3] );
			j++;
		 }
     }

 }