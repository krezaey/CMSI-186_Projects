import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {
   // String options = "
   // \n Enter an option below.
   // \n 1. Roll all the dice.
   // \n 2. Roll a single die.
   // \n 3. Calculate a score for this set.
   // \n 4. Save the score as as high score.
   // \n 5. Display the high score.
   // \n 6. Enter q to quit the program.";

   public static void main (String[] args) {
     System.out.println("Welcome to HighRoll!");
     System.out.println("Testing..");
     // int dieCount = Integer.parseInt( args[0] );
     // int sideCount = Integer.parseInt ( args[1] );

     BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {
            inputLine = input.readLine();
            if( 0 == inputLine.length() ) {
               System.out.println("testing");
            }
            System.out.println( "   You typed: " + inputLine );
            if( 'q' == inputLine.charAt(0) ) {
               break;
            }
          }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
     }
  }
}
