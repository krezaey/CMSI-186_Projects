/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Ball class to track ball's state
 *  Author        :  Keziah Rezaey
 *  Date          :  2019-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.util.Arrays; 

 public class Ball {

   final double BALL_RADIUS = 4.45;
   final double BALL_DISTANCE = 8.9;

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
    * Setter method to move the ball with given velocities
    * @param  double xvelocity, double yvelocity
    * @return void method 
    */

   public void move( double xvelocity, double yvelocity ) {
     System.out.println("Placeholder");
   }

   /** 
    * Getter method to return the location
    * @param  none
    * @return array of double precision values for Cartesian coordinate location
    */

   public double[] getLocation() {
     double[] location = new double[2];
     location[0] = this.locx;
     location[1] = this.locy;
     return location;
   }

   /** 
    * Setter method to update the balls' velocity with given velocities, takes friction into account
    * @param  double xvelocity, double yvelocity
    * @return void method 
    */

   public void updateVelocity() {
    System.out.println("Placeholder");
   }

   /** 
    * Getter method to return the value of the velocity
    * @param  none
    * @return array of double precision values for the x and y velocities at tick
    */

   public double[] getVelocity() {
    double[] velocity = new double[2];
    velocity[0] = this.velx; 
    velocity[1] = this.vely;
    return velocity;
   }

   /** 
    * Method to return string representation of location
    * @param  none
    * @return string value of current location
    */

   public String toString() {
    return "Placeholder";
   }

   /** 
    * Method to check whether given ball is still in motion; no longer moving if speed <= 1 in per second
    * @param  none
    * @return boolean value indicating status of motion
    */

   public boolean isInMotion() {
     return true;
   }

   /** 
    * Method to check whether given ball is still in bounds for the field; if so, the ball will be no longer
    * be considered for the simulation
    * @return boolean value indicating status of location
    */

   public boolean isInBounds() {
     return true;
   }

   /** 
    * Method to parse the arguments for velocity
    * @param  string value from terminal arguments
    * @return double precision value representation of the entered argument
    */

   public static double validateBallVelocity( String argValue ) {
     double ballVelocity = Double.parseDouble ( argValue );
     return ballVelocity;
  }

   /** 
    * Method to parse the arguments and validate the optional time slice argument
    * @param  string value from terminal arguments
    * @return double precision value representation of the entered argument or -1.0 if invalid
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
    * @return double precision value representation of the entered argument or -1.0 if invalid
    */

 public static double validateBallPosition( String argValue ) {
  double ballPosition = Double.parseDouble( argValue );
  if ( Math.abs(ballPosition) >= 500 ) {
    ballPosition = -1.0;
  }
  return ballPosition;
}

   public static void main ( String args[] ) {
     //tests return -1.0 if invalid; will throw nfe in SoccerSim class

     try {
       System.out.println("\n  Validation of Ball Position tests.");
       System.out.println( (200 == Ball.validateBallPosition("200")) ? "    Good job - got 200" : "Eh, try again.");
       System.out.println( (40.2 == Ball.validateBallPosition("40.2")) ? "    Good job - got 40.2" : "Eh, try again.");
       System.out.println( (0 == Ball.validateBallPosition("0.0")) ? "    Good job - got 0.0" : "Eh, try again.");
       System.out.println( (-1.0 == Ball.validateBallPosition("900")) ? "    Good job - got invalid argument" : "Eh, try again.");
       System.out.println( (-1.0 == Ball.validateBallPosition("650")) ? "    Good job - got invalid argument" : "Eh, try again.");

       System.out.println("\n  Validation of Ball Velocity tests.");
       System.out.println( (-1.32 == Ball.validateBallVelocity("-1.32")) ? "    Good job - got -1.32" : "Eh, try again.");
       System.out.println( (60.5 == Ball.validateBallVelocity("60.5")) ? "    Good job - got 60.5" : "Eh, try again.");

       System.out.println("\n  Validation of Time Slice tests.");
       System.out.println( (2 == Ball.validateTimeSliceArg("2")) ? "    Good job - got 2" : "Eh, try again.");
       System.out.println( (36.4 == Ball.validateTimeSliceArg("36.4")) ? "    Good job - got 36.4" : "Eh, try again.");
       System.out.println( (-1.0 == Ball.validateTimeSliceArg("0")) ? "    Good job - got invalid argument" : "Eh, try again.");
       System.out.println( (-1.0 == Ball.validateTimeSliceArg("1801")) ? "    Good job - got invalid argument" : "Eh, try again.");
       System.out.println( (-1.0 == Ball.validateTimeSliceArg("-2.3")) ? "    Good job - got invalid argument" : "Eh, try again.");

       System.out.println("\n  Get Location and Get Velocity tests.");
       Ball a = new Ball(100, 100, 20, -30);
       double[] testArray = {100, 100};
       System.out.println( Arrays.equals(testArray, a.getLocation()) ? "    Good job - got location [100,100]" : "Eh, try again.");
      //  double[] testArray = {20, -30};
      //  System.out.println( Arrays.equals(testArray, a.getLocation()) ? "    Good job - got [100,100]" : "Eh, try again.");
     }
     catch ( Exception e ) {
       System.out.println("Invalid argument.");
     }
   }

 }
