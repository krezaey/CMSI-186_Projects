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
            percent = args[args.length - 1].replace("%", " ").trim();
            percentArgument = Double.parseDouble(percent);
        }
        else {
            percentArgument = 1.0;
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

    public void handleInitialArguments() {
        System.out.println("Hello");
    }

    public void runMyTests() {
        System.out.println("Hello");
    }

    public double getPolyValue() {
        return 1;
    }


    public double integrate() {
        return 1;
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