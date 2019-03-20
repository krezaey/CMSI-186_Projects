/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Main program to run SoccerSimulation
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2018-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

//need to get collision to work with 2 balls
//single ball working
import java.util.ArrayList;

public class SoccerSim {

 final double BALL_DISTANCE = 8.9;

 int ballsRemoved = 0;
 int ballsStopped = 0;

 boolean simulationStopped = false;

 double timeSlice = 0;
 double xposition = 0;
 double yposition = 0;
 double xvelocity = 0;
 double yvelocity = 0;

 //String removeReport = "";

 //ArrayList < Integer > ballRemovedIndex = new ArrayList  <Integer> ();
 ArrayList < Ball > balls = new ArrayList < Ball > ();

 public SoccerSim() {}

 public String ballsToString() {
  String ballReport = "   ";
  String removeReport = "   ";
  for (int i = 0; i < balls.size(); i++) {
   ballReport += "\n   Ball " + (i + 1);
   ballReport += balls.get(i).toString() + "\n";

//    for (int index : ballRemovedIndex ) {
//     removeReport += "\n    Ball " + (index) + " removed from consideration for the simulation because it either stopped or went out of bounds. Refer to ball state report to see location and / or velocity.";  
//    }
  }
  
  return ballReport;
 }

 public void moveAll(double timeSlice) {
  for (int i = 0; i < balls.size(); i++) {
   balls.get(i).move(timeSlice);
  }
 }

 public void removeBalls() {
  for (int i = 0; i < balls.size(); i++) {
   if (balls.get(i).isInBounds() == false) {
    ballsRemoved++;
   }
  }
 }

 public void stopBalls() {
  for (int i = 0; i < balls.size(); i++) {
   if (balls.get(i).isInMotion() == false) {
    // balls.get(i).velx.set(i, 0.0);
    // balls.get(i).vely.set(i, 0.0);
    // balls.set(i, 0.0);
    // balls.set(i, 0.0);

    ballsStopped++;
   }
  }
 }

 public boolean allBallsStopped() {
  boolean response = false;
  if (ballsStopped + ballsRemoved == balls.size()) {
   response = true;
  }
  return response;
 }

 public boolean detectCollision() {
     boolean response = false;
     double xDist = 0;
     double yDist = 0;
     double distance = 0;
     if ( balls.size() > 1 ) {
        for ( int i = 0; i < balls.size() - 1; i++ ) {
            xDist = Math.abs(balls.get(i).locx - balls.get(i+1).locx);
            yDist = Math.abs(balls.get(i).locy - balls.get(i+1).locy);
            distance = Math.sqrt( (xDist * xDist) + (yDist * yDist) );

            if ( (balls.get(i).locx == balls.get(i+1).locx) && (balls.get(i).locy == balls.get(i+1).locy)) {
                response = true;
            }
   
            else if (distance <= 8.9) {
                response = true;
            }
        }
     }
     //checking for collision with pole located at (-10, 20)
     if ( balls.size() >= 1 ) {
         for ( int i = 0; i < balls.size(); i++ ) {
            xDist = Math.abs(balls.get(i).locx - (-10));
            yDist = Math.abs(balls.get(i).locy - 20);
            distance = Math.sqrt ( (xDist * xDist) + (yDist * yDist) );

            if (distance <= BALL_DISTANCE) {
                response = true;
            }
         }
     }   
     return response;
 }

 public void runSimulation(double timeSlice) {
  moveAll(timeSlice);
  stopBalls();
  removeBalls();
  System.out.println(ballsToString());
 }

 public void handleInitialArgs(String args[]) {

  System.out.println("\n  | Welcome to the Soccer Simulation program!");
  System.out.println("  | We are playing in a field that is 1000 feet wide and 1000 feet long.");
  System.out.println("  | There is a flagpole in the field at (-10,20) with (0,0) being the center of the field.");

  if ((0 == args.length) || (args.length < 4)) {
   System.out.println("   Sorry, you must enter at least four arguments and an appropriate number of arguments.\n" +
    "   Usage: java SoccerSim <xlocation> <ylocation> <xvelocity> <yvelocity> (repeat this for as many balls as you want) [timeSlice]\n" +
    "   Please try again...........");
   System.exit(1);
  }

  for (int i = 0; i < args.length; i += 4) {

   if ((args.length % 4) == 0) {
    //change LATER!!!
    timeSlice = Ball.validateTimeSliceArg("1");
   } else {
    //not working, won't allow optional time slice
    if (Ball.validateTimeSliceArg(args[args.length - 1]) == -1.0) {
     throw new NumberFormatException("Please enter a valid time slice or don't enter one to use the default time slice of 1.0 seconds.");
    }
    timeSlice = Ball.validateTimeSliceArg(args[args.length - 1]);
   }

   xposition = Ball.validateBallPosition(args[i]);
   yposition = Ball.validateBallPosition(args[i + 1]);

   if ((Ball.validateBallPosition(args[i]) == -1.0) || (Ball.validateBallPosition(args[i + 1]) == -1.0)) {
    throw new NumberFormatException("Please enter valid ball positions. The ball cannot start out of bounds.");
   }

   xvelocity = Ball.validateBallVelocity(args[i + 2]);
   yvelocity = Ball.validateBallVelocity(args[i + 3]);

   balls.add(new Ball(xposition, yposition, xvelocity, yvelocity));
  }
 }

 public static void main(String args[]) {
  SoccerSim sim = new SoccerSim();
  Timer time = new Timer();

  sim.handleInitialArgs(args);

  System.out.println("\n    Running simulation with " + sim.balls.size() + " ball(s) and a time slice of " + sim.timeSlice + ".");

  while (sim.simulationStopped == false) {

   System.out.println("\n   " + time.toString());
   sim.runSimulation(sim.timeSlice);
   time.tick(sim.timeSlice);

   if (sim.detectCollision()) {
    System.out.println("   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    System.out.println("   Collision detected at time " + time.toString() + ".\n");
    sim.simulationStopped = true;
   }
   if (sim.allBallsStopped()) {
    System.out.println("\n   All ball(s) stopped or out of bounds. No collision detected.\n");
    sim.simulationStopped = true;
   }
  }
 }
}