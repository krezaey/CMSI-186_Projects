/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   public static double epsilon = 0;      // small value for double-precision comparisons

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */

   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n Welcome to the ClockSolver program! \n" ) ;
      if( 0 == args.length || args.length > 3 ) {
         System.out.println( "   Sorry you must enter at least one argument and a maximum of three arguments.\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      if ( args.length == 1 ) {
        Clock.validateAngleArg( args[0] );
        Clock.validateTimeSliceArg( "60.0" );
        epsilon = 0.1;
        if ( Clock.validateAngleArg( args[0] ) == -1.0 ) {
          throw new NumberFormatException();
        }
      }
      if ( args.length == 2 ) {
        Clock.validateAngleArg( args[0] );
        Clock.validateTimeSliceArg( args[1] );
        epsilon = 0.1;
        if ( (Clock.validateAngleArg( args[0] ) == -1.0) || (Clock.validateTimeSliceArg( args[1] ) == -1.0) ) {
          throw new NumberFormatException();
        }
      }
      if ( args.length == 3 ) {
        Clock.validateAngleArg( args[0] );
        Clock.validateAngleArg( args[1] );
        epsilon = Double.parseDouble( args[2] );
        if ( (Clock.validateAngleArg( args[0] ) == -1.0) || (Clock.validateTimeSliceArg( args[1] ) == -1.0) ) {
          throw new NumberFormatException();
        }
      }
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cs = new ClockSolver();
      Clock clock    = new Clock();
      cs.handleInitialArguments( args );

      System.out.println( "\n     Simulation running. Finding times below. If no times are found, the simulation will end." );
      System.out.println( "\n     Simulation with angle [" + clock.angleValue + "], time slice [" + clock.timeSlice + "], and epsilon value [" + cs.epsilon + "].\n" );

      while( clock.getTotalSeconds() <= 43200 ) {
         if ( (Math.abs(clock.angleValue - clock.handAngle) <= cs.epsilon) || (Math.abs((360 - clock.handAngle)  - clock.angleValue) <= cs.epsilon) ) {
           System.out.println("       Found angle of " + clock.angleValue + " at the time: " + clock.toString());
         }
         clock.tick();
      }
      System.out.println( "______________________________________________________________\n" );
      System.exit( 0 );
   }
}
