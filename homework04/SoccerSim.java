/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  Main program to run SoccerSimulation
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2018-03-05
 *  Due Date      :  2019-03-19
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.util.ArrayList;

 public class SoccerSim {

     final double FIELD_SIZE = 500;

     int ballsRemoved = 0;
     int ballsStopped = 0;

     boolean simulationStopped = false;

     double timeSlice = 0; 
     double xposition = 0;
     double yposition = 0;
     double xvelocity = 0;
     double yvelocity = 0;

     ArrayList <Ball> balls = new ArrayList<Ball>();
  
     public SoccerSim() {}

    public String ballsToString() {
        String ballReport = "   ";
        for ( int i = 0; i < balls.size(); i++ ) {
            ballReport += "\n   Ball " + (i + 1); 
            ballReport += balls.get(i).toString() + "\n";
        }
        return ballReport;
    }

    public void moveAll(double timeSlice) {
        for ( int i = 0; i < balls.size(); i++ ) {
            balls.get(i).move(timeSlice);
        }
    }

    public void removeBalls() {
        for ( int i = 0; i < balls.size(); i++ ) {
            if ( balls.get(i).isInBounds() == false ) {
                // balls.remove(i);
                ballsRemoved++;
            }
        }
    }

    public void stopBalls() {
        for ( int i = 0; i < balls.size(); i++ ) {
            if ( balls.get(i).isInMotion() == false ) {
                ballsStopped++;
            }
        }
    }

    public boolean allBallsStopped() {
        boolean response = false;
        if ( ballsStopped + ballsRemoved == balls.size() ) {
            response = true;
        }
        return response;
    }

    public boolean detectCollision() {
        return false;
    }

    public void runSimulation( double timeSlice ) {
        moveAll(timeSlice);
        stopBalls();
        removeBalls();
    }

    public void handleInitialArgs( String args[] ) {

        System.out.println("\n  | Welcome to the Soccer Simulation program!");
        System.out.println("  | We are playing in a field that is 1000 feet wide and 1000 feet long.");
        System.out.println("  | There is a flagpole in the center of the field at (0,0).");

        if( (0 == args.length) || (args.length < 4) ) {
           System.out.println( "   Sorry, you must enter at least four arguments and an appropriate number of arguments.\n" +
                               "   Usage: java SoccerSim <xlocation> <ylocation> <xvelocity> <yvelocity> (repeat this for as many balls as you want) [timeSlice]\n" +
                               "   Please try again..........." );
           System.exit( 1 );
        }

		 for ( int i = 0; i < args.length; i += 4 ) {
           
            if ( (args.length % 4) == 0 ) {
                //change LATER!!!
              timeSlice = Ball.validateTimeSliceArg("50");
            }
            else {
              //not working, won't allow optional time slice
              if ( Ball.validateTimeSliceArg(args[args.length - 1]) == -1.0 ) {
                  throw new NumberFormatException("Please enter a valid time slice or don't enter one to use the default time slice of 1.0 seconds.");
              }
              timeSlice = Ball.validateTimeSliceArg(args[args.length - 1]);
            }   

            xposition = Ball.validateBallPosition( args[i] );
            yposition = Ball.validateBallPosition( args[i + 1] );

            if ( (Ball.validateBallPosition( args[i] ) == -1.0) || (Ball.validateBallPosition( args[i + 1] ) == -1.0) ) {
                throw new NumberFormatException("Please enter valid ball positions. The ball cannot start out of bounds.");
            }

            xvelocity = Ball.validateBallVelocity( args[i + 2] );
            yvelocity = Ball.validateBallVelocity( args[i + 3] );

            balls.add(new Ball ( xposition, yposition, xvelocity, yvelocity ) );
         }
     }

     public static void main ( String args[] ) {
         SoccerSim sim = new SoccerSim();
         Timer time = new Timer();

         sim.handleInitialArgs( args );

         System.out.println("\n  Running simulation with " + sim.balls.size() + " balls and a time slice of " + sim.timeSlice + ".");

            while ( sim.simulationStopped == false ) {
                System.out.println("\n   " + time.toString());
                System.out.println(sim.ballsToString());

                sim.runSimulation(sim.timeSlice);
                time.tick(sim.timeSlice);

                if (sim.allBallsStopped()) {
                    System.out.println("\n   All ball(s) stopped. No collision detected.\n");
                    System.exit(0);
                }

                if (sim.detectCollision()) {
                    System.out.println("Collision detected at time " + time.toString() + " .");
                    System.exit(0);
                }

             }   
     }
 }