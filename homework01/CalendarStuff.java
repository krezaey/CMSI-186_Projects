/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Keziah Rezaey
 *  Date          :  due 2019-01-31
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */

public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static String[] months      = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
   private static String[] weekDays    = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */

   public static boolean isLeapYear( long year ) {
     if ( ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) ) {
       return true;
     }
     else {
       return false;
     }
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */

   public static long daysInMonth( long month, long year ) {
     int response = 0;
     switch ( (int) month ) {
       case 1: response = days[JANUARY]; break;
       case 2:
         if ( isLeapYear(year) == true ) {
           response = (days[FEBRUARY] + 1);
         }
         else {
           response = days[FEBRUARY];
         }
         break;
       case 3: response = days[MARCH]; break;
       case 4: response = days[APRIL]; break;
       case 5: response = days[MAY]; break;
       case 6: response = days[JUNE]; break;
       case 7: response = days[JULY]; break;
       case 8: response = days[AUGUST]; break;
       case 9: response = days[SEPTEMBER]; break;
       case 10: response = days[OCTOBER]; break;
       case 11: response = days[NOVEMBER]; break;
       case 12: response = days[DECEMBER]; break;
     }
     return response;
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */

   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ( (month1 == month2) && (day1 == day2) && (year1 == year2) ) {
        return true;
      }
      else {
        return false;
      }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */

   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ( dateEquals( month1, day1, year1, month2, day2, year2 ) == true ) {
        return 0;
      }
      else if ((year1 < year2) || ( (month1 < month2) || (day1 < day2) ) ) {
        return -1;
      }
      else {
        return 1;
      }
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */

   public static boolean isValidDate( long month, long day, long year ) {
    if ( ((day <= (long) daysInMonth(month, year) && (day >= 1))) && ((month >= 1) && (month <= 12)) && (year > 0) ) {
      return true;
    }
    else {
      return false;
    }
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */

   public static String toMonthString( int month ) {
      String monthResponse = "";
      switch( month ) {
        case 1: monthResponse = months[JANUARY]; break;
        case 2: monthResponse = months[FEBRUARY]; break;
        case 3: monthResponse = months[MARCH]; break;
        case 4: monthResponse = months[APRIL]; break;
        case 5: monthResponse = months[MAY]; break;
        case 6: monthResponse = months[JUNE]; break;
        case 7: monthResponse = months[JULY]; break;
        case 8: monthResponse = months[AUGUST]; break;
        case 9: monthResponse = months[SEPTEMBER]; break;
        case 10: monthResponse = months[OCTOBER]; break;
        case 11: monthResponse = months[NOVEMBER]; break;
        case 12: monthResponse = months[DECEMBER]; break;
        default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
      return monthResponse;
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      String dayResponse = "";
      switch( day ) {
        case 1: dayResponse = weekDays[SUNDAY]; break;
        case 2: dayResponse = weekDays[MONDAY]; break;
        case 3: dayResponse = weekDays[TUESDAY]; break;
        case 4: dayResponse = weekDays[WEDNESDAY]; break;
        case 5: dayResponse = weekDays[THURSDAY]; break;
        case 6: dayResponse = weekDays[FRIDAY]; break;
        case 7: dayResponse = weekDays[SATURDAY]; break;
        default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
      return dayResponse;
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */

   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     long dayCount = 0;
     long [] firstDate = { 0, 0, 0 };
     long [] secondDate = { 0, 0, 0 };
     switch ( compareDate(month1, day1, year1, month2, day2, year2) ) {
       case 1:
       firstDate[0] = month1;
       firstDate[1] = day1;
       firstDate[2] = year1;
       secondDate[0] = month2;
       secondDate[1] = day2;
       secondDate[2] = year2;
       break;
       case -1:
       firstDate[0] = month2;
       firstDate[1] = day2;
       firstDate[2] = year2;
       secondDate[0] = month1;
       secondDate[1] = day1;
       secondDate[2] = year1;
       break;
     }

     for (long i = secondDate[2]; i < firstDate[2]; i++) {
       if ( isLeapYear( i + 1 ) ) {
         dayCount += 366;
       }
       else {
         dayCount += 365;
       }
     }

     if (firstDate[0] > secondDate[0]) {
       for (long j = secondDate[0]; j < firstDate[0]; j++) {
         dayCount += (long) days[(int)j+1];
       }
     }
     else if (secondDate[0] > firstDate[0]) {
       for (long k = firstDate[0]; k < secondDate[0]; k++) {
         dayCount -= (long) days[(int)k];
       }
     }

     dayCount -= secondDate[1];
     dayCount += firstDate[1];

     System.out.println("D: " + dayCount);
     return Math.abs(dayCount);
   }
}
