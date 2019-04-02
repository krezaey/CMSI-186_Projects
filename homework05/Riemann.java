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
    double lowerBound;
    double upperBound;
    double dx;

    boolean functionValid = true;
    boolean optionalArgPresent = false;

    String functionArgument;

    List <String> functions = Arrays.asList("poly", "sin", "cos", "tan", "log", "exp", "runtests");
    ArrayList <Double> coefficients = new ArrayList <Double> ();

    public Riemann () {}

    public void validateFunction( String[] args ) {
        for ( int i = 0; i < functions.size(); i++ ) {
            if ( functions.contains(args[0].toLowerCase()) == false ) {
                functionValid = false;
            }
            else {
                functionArgument = args[0].toLowerCase();
            }
        }
    }

    public void validatePercentArg( String[] args ) {
        String percent;
        if ( args[args.length - 1].contains("%") ) {
            percent = args[args.length - 1].replace("%", " ").trim();
            percentArgument = Double.parseDouble(percent);
            optionalArgPresent = true;
        }
        else {
            percentArgument = 1.0;
        }
    }

    public void validateCoefficents( String[] args ) {
        if ( optionalArgPresent == true ) {
            for ( int i = 1; i < args.length - 3; i++ ) {
                coefficients.add( Double.parseDouble(args[i]) );
            }
        }
        else {
            for ( int i = 1; i < args.length - 2; i++ ) {
                coefficients.add( Double.parseDouble(args[i]) );
            }
        }
    }

    public void validateBounds ( String[] args ) {
        if ( optionalArgPresent == true ) {
            lowerBound = Double.parseDouble( args[args.length - 3] );
            upperBound = Double.parseDouble( args[args.length - 2] );
        } 
        else {
            lowerBound = Double.parseDouble( args[args.length - 2] );
            upperBound = Double.parseDouble( args[args.length - 1] );
        }
    }

    public void handleInitialArguments( String [] args ) {
        String usageMessage = "\n  Usage: java Riemann function coefficents lowerBound upperBound <accuracy>%";
    
        validateFunction(args);
        validatePercentArg(args);
        validateCoefficents(args);
        validateBounds(args);

        if ( functionValid == false ) {
            throw new NumberFormatException("\n  Please enter a valid function." + usageMessage);
        }

        System.out.println( "\n\n  Welcome to the Riemann Integral calculator!" );
        System.out.println( "  We are integrating the function " + functionArgument + " from " + lowerBound + " to " + upperBound + " with an accuracy of " + percentArgument + "%."  );
        System.out.println( "  **************************************************************************************\n\n" );
    }

    public double getPolyValue() {
        return 1;
    }

    public double integrate() {
        switch (functionArgument) {
            case "poly": break;
            case "sin": break;
            case "cos": break;
            case "log": break;
            case "exp": break;
        }
        return 1;
    }

    

    public void runMyTests() {
        System.out.println("Hello");
    }

    public static void main ( String args[] ) {
        Riemann r = new Riemann();

        r.handleInitialArguments(args);
        for ( int i = 0; i < r.coefficients.size(); i++ ) {
            System.out.println(r.coefficients.get(i));
        }
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