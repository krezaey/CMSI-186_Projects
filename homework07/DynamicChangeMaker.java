/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DynamicChangeMaker.java
 *  Purpose       :  Main program to run Riemann sum calculator
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2019-05-05
 *  Due Date      :  2019-05-09
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicChangeMaker {

    public static int target;

    //public static String[] denoms;

    //public static int[] parseDenoms;

    static List <String> denoms;

    static ArrayList <Integer> parseDenoms = new ArrayList <Integer> (); 

    /**
     * Constructor method here!
     */

    public DynamicChangeMaker() {}

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate targetValue in args[1].
   *  @param   String[] args from the argument line
   *  @throws  IllegalArgumentException if have input is invalid
   *  @returns boolean true if targetValue is acceptable, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public static void validateTarget( String args[] ) {
        try {
            target = Integer.parseInt( args[1] );

            if ( target < 0 ) {
                throw new IllegalArgumentException();
            }
            for ( int i = 0; i < args[1].length(); i++ ) {
                if ( !Character.isDigit( args[1].charAt(i) ) ) {
                    throw new IllegalArgumentException();
                }
            } 
        }
        catch ( Exception e ) {
            System.out.println("\n         The target value is required and must be a non-negative integer value.");
        }
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate denominations in args[0].
   *  @param   String[] args from the argument line
   *  @throws  IllegalArgumentException if have input is invalid
   *  @returns boolean true if denominations are valid, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public static void validateDenominations( String args[] ) {
        try {
            String[] tempDenoms = args[0].split(",");
            denoms = Arrays.asList( tempDenoms );

            for ( int i = 0; i < denoms.size(); i++ ) {
                if ( Integer.parseInt( denoms.get(i) ) < 0 ) {
                    throw new IllegalArgumentException();
                } 
                else if ( Integer.parseInt( denoms.get(i) ) > target ) {
                    throw new IllegalArgumentException();
                }
                else if ( parseDenoms.contains( Integer.parseInt( denoms.get(i) ) ) ) {
                    throw new IllegalArgumentException();
                }
                else {
                    for ( int j = 0; j < tempDenoms[i].length(); j++ ) {
                        if ( !Character.isDigit( tempDenoms[i].charAt(j) ) ) {
                            throw new IllegalArgumentException();
                        }
                    }  
                }

                parseDenoms.add( Integer.parseInt( denoms.get(i) ) );
            }
        }
        catch ( Exception e ) {
            System.out.println("\n         Coin denominations must be unique non-negative integer values and the denominations cannot be greater than the target value.");
        }
    }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to track validation of the target value and entered denominations. Also checks general argument errors.
   *  @param   String[] args from the argument line
   *  @throws  IllegalArgumentException if have input is invalid
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public static void validateArguments( String args[] ) {
        try {
          validateDenominations( args );
          validateTarget( args );
          if ( args.length != 2 ) {
                throw new IllegalArgumentException();
            }
        }
        catch ( Exception e ) {
            System.out.println( "\n         BAD DATA: Illegal arguments. Must have 2 valid arguments." 
            +  "\n         Usage: java DynamicChangeMaker denoms(separated by commas) targetValue\n");
        }
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
        validateArguments(args);
    
    }

 }