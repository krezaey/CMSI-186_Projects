/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Riemann.java
 *  Purpose       :  Main program to run Riemann sum calculator
 *  Author        :  Keziah Rezaey
 *  Date Created  :  2019-03-21
 *  Due Date      :  2019-04-02
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 public class Riemann {

    double percentArgument;
    String functionArgument;

    List <String> functions = Arrays.asList("poly", "sin", "cos", "runtests");

    public Riemann () {}

    public boolean validatePoly( String[] args ) {
        return false;
    }

    public boolean validateTrig( String[] args ) {
        return false;
    }

    public void validatePercentArg( String[] args ) {
        String percent;
        if ( args[args.length - 1].contains("%") ) {
            percent = args[args.length - 1];
            percent = percent.substring(0, percent.length() - 2 );
            percentArgument = Double.parseDouble(percent);
        }
        else {
            throw new NumberFormatException("Please enter a percent number value as the last argument followed by a %");
        }
    }

    public void validateFunction( String[] args ) {
        for ( int i = 0; i < functions.size(); i++ ) {
            if ( functions.contains(args[0].toLowerCase()) == false ) {
                throw new NumberFormatException("Please enter a valid method.");
            }
            else {
                functionArgument = args[0].toLowerCase();
            }
        }
    }

//parse 
    public void handleInitialArguments() {
        System.out.println("Hello");
    }

    public void detectString() {
        System.out.println("Hello");
    }

    public void runMyTests() {
        System.out.println("Hello");
    }

    public static void main ( String args[] ) {
        Riemann r = new Riemann();
   
        r.validatePercentArg(args);
        r.validateFunction(args);
        System.out.println(r.percentArgument);
    }
 }

 
//  public void runMyTests() {
//      testValidateArgs();
//      testIntegratePoly();
//      testIntegrateSin();
//  }

//  public boolean testValidateArgs() {
//      String [] myArgs = {"poly", 1, 2, 3};
//      boolean result = validateArgs(myArgs);
//      System.out.println("Expected true, got " + result);

//      myArgs = new String[] {"poly", 1, 2, 3};
//      result = validatArgs(myArgs);
//      System.out.println("Expected true, got " + result);
//  }
// 
// check to see if asking for sqrt of negative number