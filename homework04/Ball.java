/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Ball class to track ball's state
 *  Author        :  Keziah Rezaey
 *  Date          :  2019-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

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

   public void move() {
     System.out.println("Placeholder");
   }

   public double[] getLocation() {
     double[] location = new double[2];
     location[0] = this.locx;
     location[1] = this.locy;
     return location;
   }

   public double updateVelocity() {
    return 0;
   }

   public double getSpeed() {
    return 0;
   }

   public String toString() {
    return "Placeholder";
   }

   public boolean isInMotion() {
     return true;
   }

   public boolean isInBounds() {
     return true;
   }

   public static double validateBallVelocity( String argValue ) {
     double ballVelocity = Double.parseDouble ( argValue );
     return ballVelocity;
  }

  public static double validateTimeSliceArg( String argValue ) {
    double timeSlice = Double.parseDouble( argValue );
    if ( (timeSlice <= 0.0) || (timeSlice > 1800.0) ) {
      timeSlice = -1.0;
    }
    return timeSlice;
 }

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
     }
     catch ( Exception e ) {
       System.out.println("Invalid argument.");
     }
   }

 }
