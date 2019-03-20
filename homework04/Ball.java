/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Ball class to track ball's state
 *  Author        :  Keziah Rezaey
 *  Date          :  2019-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

public class Ball {

 final double FIELD_SIZE = 500;
 final double SMALLEST_VELOCITY = 0.08333333333;

 double locx = 0.0;
 double locy = 0.0;
 double velx = 0.0;
 double vely = 0.0;

 public Ball( double locx, double locy, double velx, double vely ) {
  this.locx = locx;
  this.locy = locy;
  this.velx = velx;
  this.vely = vely;
 }

 /** 
  * Setter method to update the balls' velocity, takes friction into account
  * @param  none
  * @return void method 
  */

 public void updateVelocity() {
  this.velx = this.velx * 0.99;
  this.vely = this.vely * 0.99;
 }

 /** 
  * Setter method to change the balls' velocity to 0, used in SoccerSim class when ball is out of bounds
  * @param  none
  * @return void method 
  */

 public void changeVelocity() {
  this.velx = 0.0;
  this.vely = 0.0;
 }

 /**
  * Setter method to update the ball's location, uses velocity to set it
  * @param none
  * @return void method
  */

 public void updateLocation( double timeSlice ) {
  this.locx += this.velx * timeSlice;
  this.locy += this.vely * timeSlice;
 }

 /** 
  * Method to check whether given ball is still in motion; no longer moving if speed <= 1/12 in per second
  * @param  none
  * @return boolean value indicating status of motion
  */

 public boolean isInMotion() {
  boolean response = true;
  if ( (Math.abs(this.velx) <= SMALLEST_VELOCITY) && (Math.abs(this.vely) <= SMALLEST_VELOCITY) ) {
   response = false;
  }
  return response;
 }

 /** 
  * Method to check whether given ball is still in bounds for the field
  * @param none
  * @return boolean value indicating status of location
  */

 public boolean isInBounds() {
  boolean response = true;
  if ( (Math.abs(this.locx) >= FIELD_SIZE) || (Math.abs(this.locy) >= FIELD_SIZE) ) {
   response = false;
  }
  return response;
 }

 /** 
  * Setter method to move the ball with given velocities
  * @param  none 
  * @return void method 
  */

 public void move( double timeSlice ) {
  updateLocation(timeSlice);
  updateVelocity();
 }

 /** 
  * Method to return string representation of ball's state
  * @param  none
  * @return string value of current location and velocity
  */

 public String toString() {
  return "\n   Location: (" + this.locx + " , " + this.locy + ")" +
   "\n   Velocity: (" + this.velx + " , " + this.vely + ")";
 }

 /** 
  * Method to parse the arguments for velocity
  * @param  string value from terminal arguments
  * @return double precision value representation of the entered velocity argument
  */

 public static double validateBallVelocity( String argValue ) {
  double ballVelocity = Double.parseDouble( argValue );
  return ballVelocity;
 }

 /** 
  * Method to parse the arguments and validate the optional time slice argument
  * @param  string value from terminal arguments
  * @return double precision value representation of the entered optional time slice argument or -1.0 if invalid
  */

 public static double validateTimeSliceArg( String argValue ) {
  double timeSlice = Double.parseDouble( argValue );
  if ( (timeSlice <= 0.0) || (timeSlice > 1800.0) ) {
   timeSlice = -1.0;
  }
  return timeSlice;
 }

 /** 
  * Method to parse the arguments and validate the indicated ball location. 
  * It will not allow balls to be set out of bounds.
  * @param  string value from terminal arguments
  * @return double precision value representation of the entered location argument or -1.0 if invalid
  */

 public static double validateBallPosition( String argValue ) {
  double ballPosition = Double.parseDouble( argValue );
  if ( Math.abs(ballPosition) >= 500 ) {
   ballPosition = -1.0;
  }
  return ballPosition;
 }

 public static void main(String args[]) {
  try {
   System.out.println( "\n  Validation of Ball Position tests." );
   System.out.println( (200 == Ball.validateBallPosition("200")) ? "    Good job - got 200" : "Eh, try again." );
   System.out.println( (40.2 == Ball.validateBallPosition("40.2")) ? "    Good job - got 40.2" : "Eh, try again." );
   System.out.println( (0 == Ball.validateBallPosition("0.0")) ? "    Good job - got 0.0" : "Eh, try again." );
   System.out.println( (-1.0 == Ball.validateBallPosition("900")) ? "    Good job - got invalid argument" : "Eh, try again." );
   System.out.println( (-1.0 == Ball.validateBallPosition("650")) ? "    Good job - got invalid argument" : "Eh, try again." );

   System.out.println( "\n  Validation of Ball Velocity tests." );
   System.out.println( (-1.32 == Ball.validateBallVelocity("-1.32")) ? "    Good job - got -1.32" : "Eh, try again." );
   System.out.println( (60.5 == Ball.validateBallVelocity("60.5")) ? "    Good job - got 60.5" : "Eh, try again." );

   System.out.println( "\n  Validation of Time Slice tests." );
   System.out.println( (2 == Ball.validateTimeSliceArg("2")) ? "    Good job - got 2" : "Eh, try again." );
   System.out.println( (36.4 == Ball.validateTimeSliceArg("36.4")) ? "    Good job - got 36.4" : "Eh, try again." );
   System.out.println( (-1.0 == Ball.validateTimeSliceArg("0")) ? "    Good job - got invalid argument" : "Eh, try again." );
   System.out.println( (-1.0 == Ball.validateTimeSliceArg("1801")) ? "    Good job - got invalid argument" : "Eh, try again." );
   System.out.println( (-1.0 == Ball.validateTimeSliceArg("-2.3")) ? "    Good job - got invalid argument" : "Eh, try again." );

   System.out.println( "\n  In Motion and In Bounds tests." );
   Ball a = new Ball( 100, 100, 20, -30 );
   System.out.println( "    Initializing ball with location (100, 100) and velocities (20, -30)." );
   System.out.println( (true == a.isInBounds()) ? "      Good job - got that the ball is in bounds." : "Eh, try again." );
   System.out.println( (true == a.isInMotion()) ? "      Good job - got that the ball is in motion." : "Eh, try again." );
   Ball b = new Ball( 501, 501, 1, -1 );
   System.out.println( "    Initializing ball with location (501, 501) and velocities (1, -1)." );
   System.out.println( (false == b.isInBounds()) ? "      Good job - got that the ball is not in bounds." : "Eh, try again." );
   System.out.println( (false == b.isInMotion()) ? "      Good job - got that the ball is not in motion." : "Eh, try again." );

   System.out.println( "    Move and ToString tests." );
   a.move( 1 );
   System.out.println( "      " + a.toString() );
   b.move( 1 );
   System.out.println( "      " + b.toString() );
  } 
  catch ( Exception e ) {
   System.out.println( "An error occured. Please try again." );
  }
 }

}