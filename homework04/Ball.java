/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Ball class to track ball's state
 *  Author        :  Keziah Rezaey
 *  Date          :  2019-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

 public class Ball() {

   final double BALL_RADIUS = 4.45;
   final double BALL_DISTANCE = 8.9;

   double locx = 0.0;
   double locy = 0.0;
   double velx = 0.0;
   double vely = 0.0;

   public Ball( xloc, yloc, xvel, yvel ) {
     this.locx = xloc;
     this.locy = yloc;
     this.xvel = velx;
     this.yvel = yvel;
   }

   public static void move() {
     return 0;
   }

   public static double updateVelocity() {
     return 0;
   }

   public static double getLocation() {
     return 0;
   }

   public static double getSpeed() {
     return 0;
   }

   public static String toString() {
     return "here's your string";
   }

   public static boolean isInMotion() {
     return true;
   }

   public static boolean isInBounds() {
     return true;
   }

   public static void main ( String args[] ) {
     //type ball tests right here
     //use conditional statements
     try {
       System.out.println("yolo");
     }
     catch ( Exception e ) {
       System.out.println("yolo");
     }
   }

 }
