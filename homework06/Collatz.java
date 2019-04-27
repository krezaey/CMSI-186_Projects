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

    String internalValue;
    String result = "";

   /**
   *  Constructor takes a string and assigns it to the internal storage. Creates a BrobInt instance
   *  from the inputted string, which then creates a Collatz instance. Validates argument.
   *  @param  String  array value to make into a BrobInt which then makes into a Collatz
   */

    public Collatz( String[] args ) {
        try {
            validArgs( args );
            this.bint = new BrobInt( args[0] );
            this.internalValue = args[0];
        }
        catch ( Exception e ) {
            System.out.println("\n         Please enter an appopriate argument." +
                               "\n         Only decimal numbers and no more than one argument.\n");
            System.exit(0);
        }
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate digits in argument.
   *  @param   String args from the argument line
   *  @throws  IllegalArgumentException if have more than one argument or non-decimal values
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public void validArgs( String[] args ) {
        boolean valid = true;
        for ( int i = 0; i < args[0].length() - 1; i++ ) {
            if ( args.length > 1 ) {
                throw new IllegalArgumentException();
            }
            if ( args.length == 0 ) {
                throw new IllegalArgumentException();
            }
            if ( (i != 0) && (!Character.isDigit( args[0].charAt(i) ) ) ) {
                throw new IllegalArgumentException();
            }
        }
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to perform and print Collatz sequence.
   *  @param none
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public void sequence() {
        int steps = 0;

        BrobInt answer = new BrobInt( this.internalValue );
        boolean equal = false;

        System.out.println("\n         COLLATZ SEQUENCE");
        System.out.println("\n         For BrobInt: \n         " + this.bint.toString());
        System.out.println("\n         =======================================================" );

        result += this.bint.toString() + " \n";

        while ( !equal ) {
            if ( answer.toString().charAt(answer.toString().length() - 1) == '2' || 
                 answer.toString().charAt(answer.toString().length() - 1) == '4' || 
                 answer.toString().charAt(answer.toString().length() - 1) == '6' || 
                 answer.toString().charAt(answer.toString().length() - 1) == '8' || 
                 answer.toString().charAt(answer.toString().length() - 1) == '0' ) {
                answer = answer.divide( BrobInt.TWO );
                steps++;
                result += answer.toString() + " \n";
            }
            else {
                answer = answer.multiply( BrobInt.THREE );
                answer = answer.add( BrobInt.ONE );
                steps++;
                result += answer.toString() + " \n";
            }

            if ( answer.equals( BrobInt.ONE ) ) {
                equal = true;
            }
        }

        System.out.println( result );
        System.out.println("\n         It takes " + steps + " steps for the sequence to converge to 1.\n\n");
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  The main creates an instance of Collatz class from arguments and performs the sequence.
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public static void main ( String[] args ) {

        Collatz collatzBint = new Collatz( args );

        collatzBint.sequence();

    }
}
