/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DynamicChangeMaker.java
 *  Purpose       :  Main program to run Riemann sum calculator
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2019-05-05
 *  Due Date      :  2019-05-09
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

public class DynamicChangeMaker {

    public static int target;

    public static String[] denoms;

    public static int[] parseDenoms;

    /**
     * Constructor method here!
     */

    public DynamicChangeMaker() {}

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate targetValue in args[1].
   *  @param   String args from the argument line
   *  @returns boolean true if targetValue is acceptable, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public static boolean validateTarget( String args[] ) {
        boolean valid = false;

        target = Integer.parseInt( args[1] );

        if ( target > 0 ) {
            valid = true;
        }

        return valid;
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate denominations in args[0].
   *  @param   String args from the argument line
   *  @returns boolean true if denominations are valid, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

   //need to check if repeat values are included
   //negative values not working??
    public static boolean validateDenominations( String args[] ) {
        boolean valid = false;

        denoms = args[0].split(",");
        parseDenoms = new int[ denoms.length ];

        for ( int i = 0; i < denoms.length; i++ ) {
            parseDenoms[i] = Integer.parseInt( denoms[i] );
            if ( ( Integer.parseInt( denoms[i] ) > 0 ) && ( Integer.parseInt( denoms[i] ) < target) ) {
                valid = true;
            }
        }

        return valid;
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to track validation of the target value and entered denominations. Also checks general argument errors.
   *  @param   String args from the argument line
   *  @throws  IllegalArgumentException if have input is invalid
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

   //change back to void method when finished testing
    public static boolean validateArguments( String args[] ) {
        boolean allArgsValid = false;

        if ( validateTarget( args ) && validateDenominations( args ) ) {
            allArgsValid = true;
        }
        else if ( args.length > 1 ) {
            throw new IllegalArgumentException("\n         Only 2 arguments for program accepted." 
                                            +  "\n         Usage: java DynamicChangeMaker denoms(separated by commas) targetValue");
        }
        else {
            throw new IllegalArgumentException("\n         Coin denominations must be non-negative integer values and the denominations cannot be greater than the target value."); 
        }

        return allArgsValid;
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to provide optimized change using dynamic programming.
   *  @param   int[] denom, int target 
   *  @return  Tuple object with optimized change with given arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    //change to return Tuple instead of void method
    public static void makeChangeWithDynamicProgramming( int[] denom, int target ) {

    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  The main method takes in arguments and performs the changemaking sequence.
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public static void main ( String args[] ) {
        
        System.out.println( DynamicChangeMaker.validateArguments(args));

    }

 }