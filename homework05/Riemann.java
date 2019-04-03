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

    public void handleSpecialCases() {
        if ( (coefficients.size() == 1) && (coefficients.contains(0.0)) ) {
            System.out.println("  The integral value is: 0.0000.\n\n");  
            System.exit(0);          
        }
    }

    public void handleInitialArguments( String [] args ) {
        validateFunction(args);
    
        if ( functionArgument != "runtests" ) {
            validatePercentArg(args);
            validateCoefficents(args);
            validateBounds(args);
        }

        if ( (functionValid == false || boundsValid == false) || (args.length <= 1) ) {
            throw new NumberFormatException(exceptionMessage);
        }   
    }

    public double getPolyValue(double x) {
        double yValue = 0;
        for ( int i = 0; i < coefficients.size(); i++ ) {
            yValue += ( (Math.pow(x, i)) * (coefficients.get(i)) );
        }
        return yValue;
    }

    public double integrate( String[] args, double q ) {
        double area = 0;
        dx = (upperBound - lowerBound) / q;

        switch (functionArgument) {
            case "poly": 
                if ( coefficients.size() < 1 ) {
                    throw new NumberFormatException(exceptionMessage);
                }
                else {
                    for ( double j = lowerBound; j < upperBound; j += dx ) {
                        area += ( getPolyValue(j) * dx );
                    }   
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
                    area += ( Math.sqrt(j) * dx );
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

    public void validateArgsTest() {
        String [] myArgs = {"poly", "1", "2", "3", "4", "4%"};
        handleInitialArguments(myArgs);

        // if ( functionValid ) {
        //     if ( optionalArgPresent ) {
        //         if ( coefficients.contains(1.0) ) {
        //             if  ( coefficients.contains(2.0) ) {
        //                 if (lowerBound == 4) {
        //                     if (upperBound == 4) {
        //                         System.out.println("Yes! Got a poly function 2x + 1 from x = 3 to x = 4 with an error bound of 4%.");
        //                     }
        //                 }
        //             }
        //         }
        //     }
            
        // }
        // else {
        //     System.out.println("Failure! :(");
        // }
        
        // myArgs = new String[] {"poly", "1", "2", "3"};
        // result = validatArgs(myArgs);
        // System.out.println("Expected true, got " + result);
    }

    public void runMyTests() {
        validateArgsTest();
    }

    public static void main ( String args[] ) {
        Riemann r = new Riemann();

        r.handleInitialArguments(args);

        System.out.println( "\n\n  Welcome to the Riemann Integral calculator! We calculate left-hand sums for you!" );
        System.out.println( "  We are integrating the function " + r.functionArgument + " from " + r.lowerBound + " to " + r.upperBound + " with an accuracy of " + r.percentArgument + "%."  );
        System.out.println( "  **************************************************************************************\n\n" );

        r.handleSpecialCases(); 

        double previous = r.integrate(args, 1.0);
        double q = 2.0;

        while (true) {

            double current = r.integrate(args, q);
            
            if ( ( Math.abs(1 - (previous/current))  <= ( r.percentArgument / 100.0 ) ) ) {
                System.out.println("  The integral value is: " + previous + ".\n\n");
                //System.out.println("  The q value is: " + q + ".\n\n");
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
//     
//  }
// 
// check to see if asking for sqrt of negative number