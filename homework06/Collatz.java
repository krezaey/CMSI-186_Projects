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
        for ( int i = 0; i < args.length - 1; i++ ) {
            if ( args.length > 1 || args.length == 0 || Character.isDigit( this.bint.internalValue.charAt(i) ) == false ) {
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

        System.out.println("\n         COLLATZ SEQUENCE");
        System.out.println("\n         For BrobInt: \n         " + this.bint.toString());
        System.out.println("\n         =======================================================" );
        //code here
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

