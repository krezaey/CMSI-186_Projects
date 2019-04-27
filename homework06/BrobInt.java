/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *  1.2.0  2019-04-18  B.J. Johnson  Fixed bug in add() method that was causing errors in Collatz
 *                                     sequence.  Added some tests into the main() method at the bottom
 *                                     of the file to test construction.  Also added two tests there to
 *                                     test multiplication by three and times-3-plus-1 operations
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import javax.naming.spi.ResolveResult;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   public  byte   sign          = 0;         // "0" is positive, "1" is negative
  /// You can use this or not, as you see fit.  The explanation was provided in class
   private String reversed      = "";        // the backwards version of the internal String representation

   private int CHUNK_SIZE = 9;
   private int MAX_INT_VALUE = 999999999;
   
  ///constructor fields
   int[] intChunks;
   int chunks;
   int stop;
   int start;

   DecimalFormat df = new DecimalFormat("000000000");

   private static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON  = false;

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String s ) {
      internalValue = new String(s);
      this.chunks = (internalValue.length() / CHUNK_SIZE);
      if ( s.length() % CHUNK_SIZE != 0 ) { this.chunks++; }

      if ( s.charAt(0) == '-' ) { this.sign = 1; }
      else { this.sign = 0; }
      
      this.intChunks = new int[this.chunks];
      this.stop = internalValue.length();
      this.start = this.stop - 9;

      if ( chunks == 1 ) { start = 0; }

      for ( int i = 0; i < chunks; i++ ) {
         this.intChunks[i] = Integer.parseInt( internalValue.substring(this.start,this.stop) );
         this.stop -= 9;

         if ( i == this.intChunks.length - 2 ) { this.start = 0; }
         else { this.start -= 9; }
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() throws IllegalArgumentException {
      boolean valid = false;
      for ( int i = 0; i < this.intChunks.length; i++) {
         for ( int j = 0; j < CHUNK_SIZE; j++ ) {
            if ( internalValue.length() == 0 ) {
               throw new IllegalArgumentException( "\n         Please enter at least one digit." );
            }
            else if ( !Character.isDigit( internalValue.charAt(i) ) ) {
               throw new IllegalArgumentException( "\n         Please enter valid decimel numbers." );
            }
            else if ( i != this.intChunks.length - 1 ) {
               //no digits other than the first digit can contain a negative or positive sign
               if ( j != 0 && ( this.internalValue.charAt(j) == '-' || this.internalValue.charAt(j) == '+' ) ) {
                  throw new IllegalArgumentException( "\n         Please enter valid decimel numbers." );
               }
            } 
            else { 
               valid = true;
            }
         }
      }
      return valid;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  NOTE: you can use this or not, as you see fit; explanation was given in class
   *  @see StringBuffer API page for an easy way to do this
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  bint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  NOTE: you can use this or not, as you see fit; explanation was given in class
   *  @see StringBuffer API page for an easy way to do this
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt bint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt bint ) {
      int k = 0;
      int newSign = 0;
      int carry = 0;
      int max = Math.max( this.intChunks.length, bint.intChunks.length );
      int min = Math.min( this.intChunks.length, bint.intChunks.length );
      
      boolean originalBigger = true;
      
      String addString = "";

      int[] result = new int [ max + 2 ];

      if ( this.sign == bint.sign ) {
         newSign = this.sign;
         if ( this.compareTo( bint ) > 0 )  { 
            if ( newSign == 1 ) {
               originalBigger = false;
            }    
         }
      }
      else {
         if ( bint.compareTo( this ) > 0 ) {
            originalBigger = false;
         }
      }

      int[] biggerArray = ( originalBigger ) ? new int[this.intChunks.length] : new int[bint.intChunks.length];
      int[] smallerArray = ( originalBigger ) ? new int[bint.intChunks.length] : new int[this.intChunks.length];
   
      if ( originalBigger ) {
         for ( int i = 0; i < max; i++ ) {
            biggerArray[i] = this.intChunks[i];
         }
         for ( int j = 0; j < min; j++ ) {
            smallerArray[j] = bint.intChunks[j];
         }
      } 
      else {
         for ( int i = 0; i < max; i++ ) {
            biggerArray[i] = bint.intChunks[i];
         }
         for ( int j = 0; j < min; j++ ) {
            smallerArray[j] = this.intChunks[j];
         }
      }

      for ( int i = 0; i < min; i++ ) {
         result[i] = biggerArray[i] + smallerArray[i] + carry;
         if ( result[i] > MAX_INT_VALUE ) {
            result[i] -= 1000000000;
            carry = 1;
         }
         else { carry = 0; }
         k++;
      }

      for ( int i = k; i < max; i++ ) {
         if ( originalBigger ) {
            result[i] += this.intChunks[i] + carry;
            if ( result[i] > MAX_INT_VALUE ) {
               result[i] -= 1000000000;
               carry = 1;
            }
            else { carry = 0; }
         } 
         else {
            result[i] += bint.intChunks[i] + carry;
            if ( result[i] > MAX_INT_VALUE ) {
               result[i] -= 1000000000;
               carry = 1;
            }
            else { carry = 0; }
         }
      }
   
      for ( int i = result.length - 1; i >= 0; i-- ) { 
         if ( i < result.length - 3 ) {
            addString += df.format( (double) result[i] );
         }
         else {
            addString += result[i];
         }
      }

      for ( int i = 0; i < addString.length(); i++ ) {
         int actualIndex = 0;
         if ( ( i != 0 ) && ( addString.charAt(i) == '-' ) ) {
            actualIndex = i;
            addString = addString.substring( actualIndex, addString.length() );
         }
      }

      if ( ( newSign == 1 ) && ( addString.charAt(0) != '-' ) ) { addString += '-' + addString; }

      BrobInt add = new BrobInt ( addString );

      return add.removeLeadingZeros( add );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt bint ) {
      boolean resultNegative = false;
      boolean bothPositive = ( this.sign == 0 && bint.sign == 0 ) ? true : false;
      boolean bothNegative = ( bothPositive ) ? false : true; 
      boolean differentSigns = ( this.sign != bint.sign ) ? true : false;
      boolean specialCondition = false;

      int max = Math.max( this.intChunks.length, bint.intChunks.length );
      int min = Math.min( this.intChunks.length, bint.intChunks.length );

      String subtractString = "";

      int[] longArray;
      int[] shortArray;
      int[] result = new int[ max + 2 ];

      BrobInt answerBrobInt = ZERO;

      if ( ( bothPositive ) && ( this.compareTo( bint ) < 0 ) ) {
         resultNegative = true;
      } 
      else if ( ( differentSigns ) && ( this.compareTo( bint ) > 0 ) ) {
         String switchArg = bint.internalValue.substring( 1, bint.internalValue.length() );
         BrobInt nowPositive = new BrobInt( switchArg );
         specialCondition = true;
         answerBrobInt = new BrobInt( String.valueOf( this.add( nowPositive ) ) );
      }
      else if ( ( differentSigns ) && ( this.compareTo( bint ) < 0 ) ) {
         resultNegative = true;
         String switchOriginal = this.internalValue.substring( 1, this.internalValue.length() );
         BrobInt newOriginal = new BrobInt( switchOriginal );
         String answer = '-' + bint.add( newOriginal ).toString();
         specialCondition = true;
         answerBrobInt = new BrobInt( answer );
      }
      else if ( ( bothNegative ) && ( this.compareTo( bint ) > 0 ) ) {
         resultNegative = true;
      }
   
      longArray = new int[ max ];
      shortArray = new int[ min ];

      if ( this.internalValue.length() > bint.internalValue.length() ) {
         for ( int i = 0; i < max; i++ ) {
            longArray[i] = this.intChunks[i];
         }
         for ( int j = 0; j < min; j++ ) {
            shortArray[j] = bint.intChunks[j];
         }
      }
      else {
         for ( int i = 0; i < max; i++ ) {
            longArray[i] = bint.intChunks[i];
         }
         for ( int j = 0; j < min; j++ ) {
            shortArray[j] = this.intChunks[j];
         }
      }
      
      for ( int i = 0; i < min; i++ ) {
         if ( longArray[i] < shortArray[i] ) {
            longArray[i] += 0;
            if ( i != shortArray.length - 1) {
               longArray[ i + 1 ]--;
            }
         }
         result[i] = longArray[i] - shortArray[i];
      }

      for ( int i = result.length - 1; i >= 0; i-- ) { 
         if ( i < result.length - 3 ) {
            subtractString += df.format( (double) result[i] );
         }
         else {
            subtractString += result[i];
         }
      }

      for ( int i = 0; i < subtractString.length(); i++ ) {
         int actualIndex = 0;
         if ( ( i != 0 ) && ( subtractString.charAt(i) == '-' ) ) {
            actualIndex = i;
            if ( !resultNegative ) { actualIndex++; }
            subtractString = subtractString.substring( actualIndex, subtractString.length() );
         }
      }

      if ( resultNegative ) { subtractString = '-' + subtractString; }

      BrobInt subtract = new BrobInt ( subtractString );

      if ( !specialCondition ) { 
         answerBrobInt = subtract.removeLeadingZeros( subtract );
      }

      return answerBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to multiply this by
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt bint ) {
      int k = 0;
      int newSign = 0; 
      int lengthCarry = 0;

      long carry = 0;
      
      String multiplyString = "";
      String carryString = "";
      String resultString = "";

      int[] longArray;
      int[] shortArray;
      long[] result;

      if ( bint.sign != this.sign ) {
         newSign = 1;
      } 

      if ( this.internalValue.length() > bint.internalValue.length() ) {
         longArray = new int[this.chunks];
         for ( int i = 0; i < this.chunks; i++ ) {
            longArray[i] = this.intChunks[i];
         }
         shortArray = new int[bint.chunks];
         for ( int j = 0; j < bint.chunks; j++ ) {
            shortArray[j] = bint.intChunks[j];
         }
      }
      else {
         longArray = new int[bint.chunks];
         for ( int i = 0; i < bint.chunks; i++ ) {
            longArray[i] = bint.intChunks[i];
         }
         shortArray = new int[this.chunks];
         for ( int j = 0; j < this.chunks; j++ ) {
            shortArray[j] = this.intChunks[j];
         }
      }

      result = new long[longArray.length + shortArray.length + 1];
      for ( int i = 0; i < result.length; i++ ) {
         result[i] = 0;
      }

      for ( int i = 0; i < shortArray.length; i++ ) {
         k = i;
         for ( int j = 0; j < longArray.length; j++ ) {
            if ( result[k] > 9 ) {
               carry = 1;
            } 
            else {
               carry = 0;
            }
            result[k] += ( (long) longArray[j] * (long) shortArray[i] ) + carry;

            if ( result[k] > MAX_INT_VALUE ) {
               carry = result[k] / 1000000000;
               result[k] = result[k] % 1000000000;
            }
            else {
               carry = 0;
            }
            k++;
         }
         
         if ( carry != 0 ) {
            result[ k - 1 ] += carry;
            carry = 0;
         }
         
         multiplyString = String.valueOf( result[i] );

         if ( multiplyString.length() <= 9 ) {
            carry = 0;
         } else {
            lengthCarry = multiplyString.length() - 9;
            carryString = multiplyString.substring(0, lengthCarry);
            carry = Long.parseLong(carryString);
         }
      }

      for ( int i = result.length - 1; i >= 0; i-- ) {
         if ( i < result.length - 3 ) {
            resultString += df.format( (double) result[i] );
         }
         else {
            resultString += result[i];
         }
      }

      if ( newSign == 1 ) { resultString = "-" + resultString; }

      BrobInt multiply = new BrobInt( resultString );

      return multiply.removeLeadingZeros( multiply );

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  bint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt bint ) {
      int nlength = 0;

      BrobInt remain = ZERO;
      BrobInt quotient = ZERO;

      if ( bint.equals( ZERO ) ) {
         throw new IllegalArgumentException("\n         You can't divide by 0.");
      }    
      else if ( this.equals( bint ) ) {
         return ONE;
      }
      else if ( this.compareTo( bint ) < 0 ) {
         return ZERO;
      }
      else {
         nlength = bint.internalValue.length();
         remain = new BrobInt( this.toString().substring( 0, nlength ) );

         if ( this.compareTo( remain ) < 0 ) {
            nlength++;
            remain = new BrobInt( this.toString().substring( 0, nlength ) );
         }
      }

      while ( nlength <= this.toString().length() ) {

         while ( remain.compareTo( bint ) > 0 ) {
            
            if ( allZeroDetect( remain ) ) {
               break;
            }

            remain = remain.subtract( bint );
            quotient = quotient.add( ONE );
         }

         if ( remain.equals( bint ) ) {
            remain = ZERO;
            quotient = quotient.add( ONE );
         }

         nlength++;

         if ( nlength > this.toString().length() ) {
            break;
         }

         remain = remain.multiply( TEN );
         quotient = quotient.multiply( TEN );

         remain = remain.add( new BrobInt( this.toString().substring( nlength - 1, nlength ) ) );

      }
      return this.removeLeadingZeros( quotient );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  bint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt bint ) {
      return this.subtract( this.divide( bint ).multiply( bint ) );
   }

 /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  bint  BrobInt to compare to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int compareTo( BrobInt bint ) {

   // remove any leading zeros because we will compare lengths
    String me  = removeLeadingZeros( this ).toString();
    String arg = removeLeadingZeros( bint ).toString();

   // handle the signs here
    if( 1 == this.sign && 0 == bint.sign ) {
       return -1;
    } else if( 0 == this.sign && 1 == bint.sign ) {
       return 1;
    } else if( 0 == this.sign && 0 == bint.sign ) {
      // the signs are the same at this point ~ both positive
      // check the length and return the appropriate value
      //   1 means this is longer than bint, hence larger positive
      //  -1 means bint is longer than this, hence larger positive
       if( me.length() != arg.length() ) {
          return (me.length() > arg.length()) ? 1 : -1;
       }
    } else {
      // the signs are the same at this point ~ both negative
       if( me.length() != arg.length() ) {
          return (me.length() > arg.length()) ? -1 : 1;
       }
    }

   // otherwise, they are the same length, so compare absolute values
    for( int i = 0; i < me.length(); i++ ) {
       Character a = Character.valueOf( me.charAt(i) );
       Character b = Character.valueOf( arg.charAt(i) );
       if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
          return 1;
       } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
          return (-1);
       }
    }
    return 0;
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to check if a BrobInt passed as argument is equal to this BrobInt
 *  @param  bint     BrobInt to compare to this
 *  @return boolean  that is true if they are equal and false otherwise
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public boolean equals( BrobInt bint ) {
    return ( (this.sign == bint.sign) && (this.toString().equals( bint.toString() )) );
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to return a BrobInt given a long value passed as argument
 *  @param  value    long type number to make into a BrobInt
 *  @return BrobInt  which is the BrobInt representation of the long
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public static BrobInt valueOf( long value ) throws NumberFormatException {
    BrobInt bi = null;
    try { bi = new BrobInt( Long.valueOf( value ).toString() ); }
    catch( NumberFormatException nfe ) { throw new NumberFormatException( "\n  Sorry, the value must be numeric of type long." ); }
    return bi;
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to return a String representation of this BrobInt
 *  @return String  which is the String representation of this BrobInt
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public String toString() {
    return this.internalValue;
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to remove leading zeros from a BrobInt passed as argument
 *  @param  bint     BrobInt to remove zeros from
 *  @return BrobInt that is the argument BrobInt with leading zeros removed
 *  Note that the sign is preserved if it exists
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public BrobInt removeLeadingZeros( BrobInt bint ) {
    Character sign = null;
    String returnString = bint.toString();
    int index = 0;

    if( allZeroDetect( bint ) ) {
       return bint;
    }
    if( ('-' == returnString.charAt( index )) || ('+' == returnString.charAt( index )) ) {
       sign = returnString.charAt( index );
       index++;
    }
    if( returnString.charAt( index ) != '0' ) {
       return bint;
    }

    while( returnString.charAt( index ) == '0' ) {
       index++;
    }
    returnString = bint.toString().substring( index, bint.toString().length() );
    if( sign != null ) {
       returnString = sign.toString() + returnString;
    }
    return new BrobInt( returnString );

 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to return a boolean if a BrobInt is all zeros
 *  @param  bint     BrobInt to compare to this
 *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public boolean allZeroDetect( BrobInt bint ) {
    for( int i = 0; i < bint.toString().length(); i++ ) {
       if( bint.toString().charAt(i) != '0' ) {
          return false;
       }
    }
    return true;
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to display an Array representation of this BrobInt as its bytes
 *  @param   d  byte array from which to display the contents
 *  NOTE: may be changed to int[] or some other type based on requirements in code above
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public void toArray( byte[] d ) {
    System.out.println( "Array contents: " + Arrays.toString( d ) );
 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Method to display a prompt for the user to press 'ENTER' and wait for her to do so
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public void pressEnter() {
    String inputLine = null;
    try {
       System.out.print( "      [Press 'ENTER' to continue]: >> " );
       inputLine = input.readLine();
    }
    catch( IOException ioe ) {
       System.out.println( "Caught IOException" );
    }

 }

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  the main method redirects the user to the test class
 *  @param  args  String array which contains command line arguments
 *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public static void main( String[] args ) {
    System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
    System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

    BrobInt b1 = null;;
    try { System.out.println( "   Making a new BrobInt: " ); b1 = new BrobInt( "147258369789456123" ); }
    catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
    try { System.out.println( "   expecting: 147258369789456123\n     and got: " + b1.toString() ); }
    catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
    System.out.println( "\n    Multiplying 82832833 by 3: " );
    try { System.out.println( "      expecting: 248498499\n        and got: " + new BrobInt("82832833").multiply( BrobInt.THREE ) ); }
    catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

    System.out.println( "\n    Multiplying 3 by 82832833 and adding 1: " );
    try { System.out.println( "      expecting: 248498500\n        and got: " + BrobInt.THREE.multiply( new BrobInt( "82832833" ) ).add( BrobInt.ONE ) ); }
    catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
    System.exit( 0 );

 }
}