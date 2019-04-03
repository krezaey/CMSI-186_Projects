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
    boolean boundsValid = true;
    boolean optionalArgPresent = false;
    
    String functionArgument;
    String usageMessage = ("\n  Usage: java Riemann function coefficents lowerBound upperBound <accuracy>%");
    String exceptionMessage = ("\n  Please enter a valid function and/or proper number or arguments and/or bounds in numerical order. \n" + usageMessage + "\n");

    List <String> functions = Arrays.asList("poly", "sin", "cos", "tan", "sqrt", "log", "exp", "runtests");
    ArrayList <Double> coefficients = new ArrayList <Double> ();

    public Riemann () {}

    /** 
     * Method that validates the function argument aka args[0]
     * @param String args[] from the terminal
     * @return void method
     */

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

    /** 
     * Method that validates the optional percent argument; must be followed by %
     * @param String args[] from the terminal
     * @return void method
     */

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

    /** 
     * Method that validates the coefficients of the function entered; used for polynomial solving
     * @param String args[] from the terminal
     * @return void method
     */

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

    /** 
     * Method that validates the lower and upper bounds
     * @param String args[] from the terminal
     * @return void method
     */

    public void validateBounds ( String[] args ) {
        if ( args.length > 1 ) {
            if ( optionalArgPresent == true ) {
                lowerBound = Double.parseDouble( args[args.length - 3] );
                upperBound = Double.parseDouble( args[args.length - 2] );
            } 
            else {
                lowerBound = Double.parseDouble( args[args.length - 2] );
                upperBound = Double.parseDouble( args[args.length - 1] );
            }
            if ( lowerBound > upperBound ) {
                boundsValid = false;
            }
        }  
    }

    /** 
     * Method that handles any special cases; this includes domain for trig functions
     * @param String args[] from the terminal
     * @return void method
     * @throws nfe when needed for special cases
     */

    public void handleSpecialCases(String[] args) {
        switch (functionArgument) {
            case "poly":
                if ( coefficients.size() < 1 ) {
                    throw new NumberFormatException(exceptionMessage);
                }
                if ( (coefficients.size() == 1) && (coefficients.contains(0.0)) ) {
                    System.out.println("  The integral value is: 0.0000.\n\n");  
                    System.exit(0);          
                }
                if ( optionalArgPresent == true ) {
                    if ( (Double.parseDouble(args[args.length-3]) <= 0) ) {
                        throw new NumberFormatException(exceptionMessage);
                    }
                }
                else {
                    if ( (Double.parseDouble(args[args.length-2]) <= 0) ) {
                        throw new NumberFormatException(exceptionMessage);
                    }
                }   
                break;
            case "sqrt":
                for (int i = 0; i < coefficients.size(); i++) {
                    if (lowerBound < 0) {
                        throw new NumberFormatException(exceptionMessage);
                    }
                }
                break;
        }
        
    }

    /** 
     * Method that handles and validates the initial arguments
     * @param String args[] from the terminal
     * @return void method
     * @throws nfe when bounds or functions aren't valid or when args are not sufficient
     */

    public void handleInitialArguments( String [] args ) {
        validateFunction(args);
    
        if ( functionArgument != "runtests" ) {
            validatePercentArg(args);
            validateCoefficents(args);
            validateBounds(args);
        }

        if ( functionValid == false || boundsValid == false ) {
            switch (functionArgument) {
                case "poly": if ( args.length < 4 ) {throw new NumberFormatException(exceptionMessage);} break;
                default: if ( args.length < 3 ) {throw new NumberFormatException(exceptionMessage);} break;
            }
        } else if (args.length < 1) {
            throw new NumberFormatException(exceptionMessage);
        }
    }

    /** 
     * Method that calculates the y values for the polynomial functions based on the coefficients
     * @param double x "degree"
     * @return double y value
     */

    public double getPolyValue(double x) {
        double yValue = 0;
        for ( int i = 0; i < coefficients.size(); i++ ) {
            yValue += ( (Math.pow(x, i)) * (coefficients.get(i)) );
        }
        return yValue;
    }

    /** 
     * Method that integrates the functions based on solved y values
     * @param String args[] from terminal, double q value
     * @return double value calculated from integrated area
     */

    public double integrate( String[] args, double q ) {
        double area = 0;
        dx = (upperBound - lowerBound) / q;

        switch (functionArgument) {
            case "poly": 
                for ( double j = lowerBound; j < upperBound; j += dx ) {
                    area += ( getPolyValue(j) * dx );
                }   
                break;
            case "sin": 
                for ( double j = lowerBound; j < upperBound; j += dx ) {
                    area += ( Math.sin(j) * dx );
                }
                break;
            case "cos": 
                for ( double j = lowerBound; j < upperBound; j += dx ) {
                    area += ( Math.cos(j) * dx );
                }
                break;
            case "tan": 
                for ( double j = lowerBound; j < upperBound; j += dx ) {
                    area += ( Math.tan(j) * dx );
                }
                break;
            case "sqrt": 
                for ( double j = lowerBound; j < upperBound; j += dx ) {
                    if (j > 0) {
                        area += ( Math.sqrt(j) * dx );
                    }
                    else {
                        throw new NumberFormatException(exceptionMessage);
                    }   
                }  
                break;
            case "log": 
                for ( double j = lowerBound; j < upperBound; j += dx ) {
                    area += ( Math.log(j) * dx );
                }
                break;
            case "exp": 
                for ( double j = lowerBound; j < upperBound; j += dx ) {
                    area += ( Math.exp(j) * dx );
                }
                break;
            case "runtests": 
                if (args.length > 1) {
                    throw new NumberFormatException("\n  To run tests, you must only have one argument.");
                }
                else {
                    runMyTests();
                    System.exit(0);
                }
                break;
        }
        return area;
    }

    /** 
     * Testing method for validation
     * @param none
     * @return void method
     */

    public void validateArgsTest() {
        String [] myArgs = {"poly", "1", "2", "3", "4", "4%"};
        handleInitialArguments(myArgs);  
        System.out.println("Hello.");
    }

    /** 
     * Testing method for polynomial integration
     * @param none
     * @return void method
     */

    public void integratePolyTest() {

    }

    /** 
     * Testing method for functions other than polynomials; aka anything in the math package
     * @param none
     * @return void method
     */

    public void integrateOtherTest() {

    }

    /** 
     * Testing method for all the tests; initiated when "runtests" is arg[0]
     * @param none
     * @return void method
     */

    public void runMyTests() {
        validateArgsTest();
        integratePolyTest();
        integrateOtherTest();
    }

    /*
     * Main program runs here! Uses methods from Riemann class.
     */
    public static void main ( String args[] ) {
        Riemann r = new Riemann();

        r.handleInitialArguments(args);

        System.out.println( "\n\n  Welcome to the Riemann Integral calculator! We calculate left-hand sums for you!" );
        System.out.println( "  We are integrating the function " + r.functionArgument + " from " + r.lowerBound + " to " + r.upperBound + " with an accuracy of " + r.percentArgument + "%."  );
        System.out.println( "  **************************************************************************************\n\n" );

        r.handleSpecialCases(args); 

        double previous = r.integrate(args, 1.0);
        double q = 2.0;

        while (true) {

            double current = r.integrate(args, q);
            
            if ( ( Math.abs(1 - (previous/current))  <= ( r.percentArgument / 100.0 ) ) ) {
                System.out.println("  The integral value is: " + previous + ".\n\n");
                System.exit(0);
                break;     
            }
        
            previous = current;
            q++;
        }
    }
 }

 
//  public void runMyTests() {
//      testValidateArgs();
//      testIntegratePoly();
//      testIntegrateSin();
//  }

//  public boolean integratePoly() {
//      // myArgs = new String[] {"poly", "1", "2", "3"};
        // result = validatArgs(myArgs);
        // System.out.println("Expected true, got " + result);
//  }
// 
// check to see if asking for sqrt of negative number