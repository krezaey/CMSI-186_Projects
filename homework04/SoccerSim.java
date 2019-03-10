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

     private double timeSlice = 0; 
     private int ballCount = 0;
     private double xposition = 0;
     private double yposition = 0;
     private double xvelocity = 0;
     private double yvelocity = 0;
  

     public SoccerSim() {}

   /** 
    * Setter method to set the ball count for the current iteration of the program via user input
    * @param  integer value of the indicated ball count
    * @return void method
    */

     public void setBallCount(int num) {
        this.ballCount = num;
    }

   /** 
    * Getter method to return the set ball count for current iteration of the program
    * @param  none
    * @return integer value of the set ball count
    */

    public int getBallCount() {
        return this.ballCount;
    }

   /** 
    * Method to remove a instance of the ball class in the ball array if out of bounds
    * @param  ball array that is created in soccersim; integer for element index
    * @return void method
    */

    public void removeBall() {
        System.out.println("Placeholder");
    }

    //change to void later, just using return value of Ball[] for testing
    public Ball[] handleInitialArgs( String args[] ) {

        System.out.println( "\n Welcome to the Soccer Simulator program! \n" ) ;

        if( (0 == args.length) || (args.length < 4) ) {
           System.out.println( "   Sorry, you must enter at least four arguments and an appropriate number of arguments.\n" +
                               "   Usage: java SoccerSim <xlocation> <ylocation> <xvelocity> <yvelocity> (repeat this for as many balls as you want) [timeSlice]\n" +
                               "   Please try again..........." );
           System.exit( 1 );
        }

        setBallCount(args.length / 4);

         int j = 0; 
         Ball [] balls = new Ball[getBallCount()];

		 for ( int i = 0; i < args.length; i += 4 ) {
           
            if ( (args.length % 4) == 0 ) {
              Ball.validateTimeSliceArg("1.0");
            }
            else {
              Ball.validateTimeSliceArg(String.valueOf(args.length - 1));
              if ( Ball.validateTimeSliceArg(String.valueOf(args.length - 1)) == -1.0 ) {
                  throw new NumberFormatException("Please enter a valid time slice or don't enter one to use the default time slice of 1.0 seconds.");
              }
            }

            xposition = Ball.validateBallPosition( args[i] );
            yposition = Ball.validateBallPosition( args[i + 1] );

            if ( (Ball.validateBallPosition( args[i] ) == -1.0) || (Ball.validateBallPosition( args[i + 1] ) == -1.0) ) {
                throw new NumberFormatException("Please enter valid ball positions. The ball cannot start out of bounds.");
            }

            xvelocity = Ball.validateBallVelocity( args[i + 2] );
            yvelocity = Ball.validateBallVelocity( args[i + 3] );

            balls[j] = new Ball ( xposition, yposition, xvelocity, yvelocity );

            j++;

         }
         return balls;
     }

     public static void main ( String args[] ) {
         SoccerSim sim = new SoccerSim();

         sim.handleInitialArgs( args );

         System.out.println("\n   This is merely a test to see if validation of arguments via terminal is working correctly.");
         System.out.println("\n   Creating new ball with coordinates of (" + sim.xposition + " , " + sim.yposition + ") and velocities of (" + sim.xvelocity + " , " + sim.yvelocity + ").");
    
     }

 }