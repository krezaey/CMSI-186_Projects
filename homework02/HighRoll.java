/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Main program to run HighRoll game
 *  Author        :  Keziah Rezaey
 *  Date          :  2018-02-13
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {
   public static void main ( String[] args ) {

     int dieCount = 0;
     int sideCount = 0;
     int highScore = 0;
     int score = 0;

     try {
       dieCount = Integer.parseInt( args[0] );
       sideCount = Integer.parseInt( args[1] );
     }
     catch ( NumberFormatException nfe ) {
       System.out.println("Only enter numbers or make sure there are more than 4 sides.");
     }

     DiceSet gameDice = new DiceSet(dieCount, sideCount);

     System.out.println("\nWelcome to HighRoll!");
     System.out.println("\nEnter an option below.\n 1. Roll all the dice.\n 2. Roll a single die.\n 3. Calculate a score for this set.\n 4. Save the score as as high score.\n 5. Display the high score.\n 6. Enter q to quit the program.\n");

     BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;

         try {
            inputLine = input.readLine();

            if( 0 == inputLine.length() ) {
               System.out.println("Please enter an option.");
            }
            else if ( inputLine.length() > 1 ) {
              throw new IOException();
            }
            else if ( 'q' == inputLine.charAt(0) ) {
               System.out.println("Thanks for playing HighRoll.\nPlease rate us 5 stars on the App Store and we'll send you a free Amazon gift card!\n");
               break;
            }
            else if ( '1' == inputLine.charAt(0) ) {
               gameDice.roll();
               System.out.println( "Your current dice deck is " + gameDice.toString() );
            }
            else if ( '2' == inputLine.charAt(0) ) {
               System.out.println("Enter the index of the die that you want to roll.");
               int diceIndex = Integer.parseInt(input.readLine());
               try {
                 gameDice.rollIndividual(diceIndex);
                 System.out.println( "Your current dice deck is " + gameDice.toString() );
               }
               catch ( Exception e ) {
                 System.out.println("Please enter an index value in between 0 and " + (dieCount - 1) + ".");
               }
            }
            else if ( '3' == inputLine.charAt(0) ) {
               score = gameDice.sum();
               System.out.println("Your current dice deck of " + gameDice.toString() + " gives you a score of " + score + ".");
            }
            else if ( '4' == inputLine.charAt(0) ) {
               if ( score > highScore ) {
                 highScore = score;
                 System.out.println("High score saved!");
               }
               else {
                 System.out.println("Your score is not high enough chump.");
               }
            }
            else if ( '5' == inputLine.charAt(0) ) {
                System.out.println("Your current high score is " + highScore + ".");
            }
            else {
              throw new IOException();
            }
          }
         catch( IOException ioe ) {
            System.out.println( "Please enter a valid input." );
         }
     }
  }
}
