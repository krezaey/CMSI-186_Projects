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

 public Timer() {}

 /**
  * Tick method to make the clock move in increments from the given time slice
  * @param double timeslice
  * @return void method
  */

 public double tick( double timeSlice ) {
  elapsedTimeSeconds += timeSlice;
  return elapsedTimeSeconds;
 }

 /**
  * Method to return string representation of the current clock
  * @param none
  * @return string value of clock with hours, minutes, seconds, and miliseconds
  */

 public String toString() {
  DecimalFormat hourFormat = new DecimalFormat( "00" );
  DecimalFormat minuteFormat = new DecimalFormat( "00" );
  DecimalFormat secondFormat = new DecimalFormat( "00.00" );

  double hoursLeft = ( elapsedTimeSeconds / 3600 );
  double hourTime = Math.floor( hoursLeft );
  double minutesLeft = ( (hoursLeft - hourTime) * 3600 ) / 60;
  double minuteTime = Math.floor( minutesLeft );
  double secondTime = ( minutesLeft - minuteTime ) * 60;

  String hour = String.valueOf( hourFormat.format(hourTime) );
  String minute = String.valueOf( minuteFormat.format(minuteTime) );
  String seconds = String.valueOf( secondFormat.format(secondTime) );
  String timeString = "------------" + hour + ":" + minute + ":" + seconds + "------------";

  return timeString;
 }

 /*
  * Main program runs here! Simply tests the existence of the timer class.
  */
 public static void main( String args[] ) {
  System.out.println( "I'm only checking that the timer class exists." );
 }

}