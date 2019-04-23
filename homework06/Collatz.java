import java.io.IOException;

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Collatz.java
 *  Purpose       :  Main program to run collatz sequence
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2019-04-12
 *  Due Date      :  2019-04-25
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

public class Collatz {

    BrobInt bint;

    public Collatz( String s ) {
        this.bint = new BrobInt(s);
    }

    public boolean validArgs( String[] args ) {
        boolean valid = true;
        if ( args.length > 1 ) {
            valid = false;
        }
        return valid;
    }

    public void sequence() {
        int steps = 0;

        System.out.println("\n         COLLATZ SEQUENCE");
        System.out.println("\n         ===============================" );
        System.out.println("\n\n");
        //code here
        System.out.println("\n         It takes " + steps + " for the sequence to go to 1.\n\n");
    }

    public static void main ( String[] args ) {

        Collatz collatzBint = new Collatz( args[0] );

        try { 
            if ( collatzBint.validArgs(args) == false ) {
                throw new Exception();
            }
            collatzBint.sequence();
        }
        catch ( Exception e ) {
            System.out.println("\n         Please enter only one digit to do the collatz sequence with.");
        }
        

    }
}

