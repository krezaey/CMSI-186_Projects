/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Timer class to track the state of the timer
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2018-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.text.DecimalFormat;

public class Timer {

   private double elapsedTimeSeconds = 0;
   private double timeSlice = 0;
   
   public Timer( double timeSlice ) {
      this.timeSlice = timeSlice;
   }

   public double tick() {
      elapsedTimeSeconds += this.timeSlice;
      return elapsedTimeSeconds;
   }

   public double getTotalSeconds() {
      return elapsedTimeSeconds;
   }

   public String toString() {
      DecimalFormat hourFormat = new DecimalFormat( "00" );
      DecimalFormat minuteFormat = new DecimalFormat( "00" );
      DecimalFormat secondFormat = new DecimalFormat( "00.00" );

      double hoursLeft = ( elapsedTimeSeconds / 3600 );
      double hourTime = Math.floor(hoursLeft);
      double minutesLeft = ( (hoursLeft - hourTime) * 3600 ) / 60;
      double minuteTime = Math.floor( minutesLeft );
      double secondTime = ( minutesLeft - minuteTime ) * 60;

      if ( hourTime == 0 ) {
        hourTime = 12;
      }

      String hour = String.valueOf( hourFormat.format( hourTime ) );
      String minute = String.valueOf( minuteFormat.format( minuteTime ) );
      String seconds = String.valueOf( secondFormat.format( secondTime ) );
      String timeString =  hour + ":" + minute + ":" + seconds;

      return timeString;
   }

   public static void main ( String args[] ) {
       System.out.println("This is a temporary test! I'm only checking that the timer class exists.");
   }

}
