/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
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

import java.text.DecimalFormat;

public class Clock {

  /**
   *  Class field definintions go here
   */

   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

   public static double elapsedTimeSeconds = 0;
   public static double angleValue = 0;
   public static double timeSlice = 0;
   public static double hourAngle = 0;
   public static double minuteAngle = 0;
   public static double handAngle = 0;


  /**
   *  Constructor goes here
   */

   public Clock() {}

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */

   public static double tick() {
      elapsedTimeSeconds += timeSlice;
      getHourHandAngle();
      getMinuteHandAngle();
      getHandAngle();
      return elapsedTimeSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */

   public static double validateAngleArg( String argValue ) throws NumberFormatException {
      angleValue = Double.parseDouble ( argValue );
      if ( (angleValue < 0) || (angleValue >= MAXIMUM_DEGREE_VALUE) ) {
        angleValue = INVALID_ARGUMENT_VALUE;
      }
      return angleValue;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */

   public static double validateTimeSliceArg( String argValue ) {
      timeSlice = Double.parseDouble( argValue );
      if ( timeSlice < 0.0 || timeSlice > 1800.0 ) {
        timeSlice = INVALID_ARGUMENT_VALUE;
      }
      return timeSlice;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */

   public static double getHourHandAngle() {
      hourAngle = elapsedTimeSeconds * HOUR_HAND_DEGREES_PER_SECOND;
      return hourAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */

   public static double getMinuteHandAngle() {
      minuteAngle = (elapsedTimeSeconds * MINUTE_HAND_DEGREES_PER_SECOND) % 360.0;
      return minuteAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */

   public static double getHandAngle() {
      handAngle = Math.abs( getHourHandAngle() - getMinuteHandAngle() );
      return handAngle;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */

   public double getTotalSeconds() {
      return elapsedTimeSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */

   public String toString() {
      DecimalFormat hourFormat = new DecimalFormat("00");
      DecimalFormat minuteFormat = new DecimalFormat("00");
      DecimalFormat secondFormat = new DecimalFormat("00.00");

      double hoursLeft = (elapsedTimeSeconds / 3600);
      double hourTime = Math.floor(hoursLeft);
      double minutesLeft = ((hoursLeft - hourTime) * 3600) / 60;
      double minuteTime = Math.floor(minutesLeft);
      double secondTime = (minutesLeft - minuteTime) * 60;

      if (hourTime == 0) {
        hourTime = 12;
      }

      String hour = String.valueOf(hourFormat.format(hourTime));
      String minute = String.valueOf(minuteFormat.format(minuteTime));
      String seconds = String.valueOf(secondFormat.format(secondTime));
      String timeString =  hour + ":" + minute + ":" + seconds;

      return timeString;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */

   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg() and validateTimeSliceArg()....");
      System.out.println( "      sending '  0 degrees', expecting double value   0.0" );
      try {
        //invalid argument values will yield nfe in clock solver class, tests will only be used to make sure they detect invalid arguments
        System.out.println( "\nvalidateAngleArg() Tests");
        System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? "Good job - got 0.0" : "Sadness" );
        System.out.println( (30.0 == clock.validateAngleArg("30.0")) ? "Good job - got 30.0" : "Sadness" );
        System.out.println( (45.1 == clock.validateAngleArg("45.1")) ? "Good job - got 45.1" : "Sadness" );
        System.out.println( (INVALID_ARGUMENT_VALUE == clock.validateAngleArg( "-1.0" )) ? "Good job - got invalid argument" : "Sadness" );
        System.out.println( (INVALID_ARGUMENT_VALUE == clock.validateAngleArg( "361.0" )) ? "Good job - got invalid argument" : "Sadness" );
        System.out.println( (INVALID_ARGUMENT_VALUE == clock.validateAngleArg( "40000.000" )) ? "Good job - got invalid argument" : "Sadness" );

        System.out.println( "\nvalidateTimeSliceArg() Tests");
        System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0" )) ? "Good job - got 0.0" : "Sadness" );
        System.out.println( (100.1 == clock.validateTimeSliceArg( "100.1" )) ? "Good job - got 100.1" : "Sadness" );
        System.out.println( (1800.0 == clock.validateTimeSliceArg( "1800.0" )) ? "Good job - got 1800.0" : "Sadness" );
        System.out.println( (INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg( "-1.0" )) ? "Good job - got invalid argument" : "Sadness" );
        System.out.println( (INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg( "1801.0" )) ? "Good job - got invalid argument" : "Sadness" );
        System.out.println( (INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg( "4000.0" )) ? "Good job - got invalid argument" : "Sadness" );

    }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
