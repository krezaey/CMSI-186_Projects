public class CountTheDays {
  public static void main( String[] args ) {
    try {
      long month1 = Long.parseLong( args[0] );
      long day1 = Long.parseLong( args[1] );
      long year1 = Long.parseLong( args[2] );
      long month2 = Long.parseLong( args[3] );
      long day2 = Long.parseLong( args[4] );
      long year2 = Long.parseLong( args[5] );
      
      if ( (CalendarStuff.isValidDate(month1, day1, year1) == true) && (CalendarStuff.isValidDate(month2, day2, year2) == true) ) {
        System.out.println( " \n There are " + CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2) + " day(s) in between " + month1 + "-" + day1 + "-" + year1 + " and " + month2 + "-" + day2 + "-" + year2 + ". \n ");
      }
      else {
        System.out.println( " \n These dates are not valid. Please enter a valid date. \n " );
      }
    }
    catch ( NumberFormatException nfe ) {
      System.out.println(" \n Incorrect format. The dates should only be numbers. \n ");
    }
  }
}
