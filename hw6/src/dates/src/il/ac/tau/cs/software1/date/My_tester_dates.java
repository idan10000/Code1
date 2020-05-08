package dates.src.il.ac.tau.cs.software1.date;

import java.util.Arrays;

public class My_tester_dates {
	
	public static void main(String[] args) {
		
		//Initialize Date object from class DateString
		
		DateString day1 = new DateString("23/1/1963");
		DateString day2 = new DateString("06/12/1994");
		DateString day3 = new DateString("1/1/1");
		DateString day4 = new DateString("7/5/2020");
		DateString day5 = new DateString("10/5/2020");
		DateString day6 = new DateString("10/5/2021");
		DateString day7 = new DateString("2/11/2019");
		DateString day8 = new DateString("26/1/2");
		DateString day9 = new DateString("24/9/1986");
		
		

		
		//Test internal methods executed inside the interface (based on DateString Class's constructor:
		System.out.println("------------------------------------------------------------------");
		System.out.println("/////////////////// - Date - Inteface - Test - ///////////////////");
		System.out.println("------------------------------------------------------------------");

		System.out.println("");
		System.out.println("");
		System.out.println("Test - GetDaysInMonth: ");
		System.out.println("-----------------------" );
	
		System.out.println("In month (= 1) there are: " + Date.getDaysInMonth(1) + " days" );
		System.out.println("In month (= 2) there are: " + Date.getDaysInMonth(2) + " days" );
		System.out.println("In month (= 4) there are: " + Date.getDaysInMonth(4) + " days" );
		System.out.println("");
		
		System.out.println("");

		System.out.println("Test -  differenceInDays: " );
		System.out.println("-------------------------" );

		System.out.println("Days between 7/5/2020 and 10/5/2020 : " + day4.differenceInDays(day5) + " days" );
		System.out.println("Days between 10/5/2020 and 7/5/2020 : " + day5.differenceInDays(day4) + " days" );
		System.out.println("Days between 10/5/2020 and 10/5/2020 : " + day5.differenceInDays(day5) + " days" );
		System.out.println("Days between 10/5/2020 and 10/5/2020 : " + day5.differenceInDays(day5) + " days" );
		System.out.println("Days between 10/5/2020 and 10/5/2021 : " + day5.differenceInDays(day6) + " days" );
		System.out.println("");
		System.out.println("");

		System.out.println("Test -  isBetweenDates: " );
		System.out.println("-----------------------" );
		System.out.println("10/5/2020 is between 10/5/2021 and  7/5/2020 : " + day5.isBetweenDates(day4, day6));
		System.out.println("10/5/2020 is between  7/5/2020 and  10/5/2021 : " + day5.isBetweenDates(day4, day6));
		System.out.println("06/12/1994 is between  7/5/2020 and  10/5/2021 : " + day2.isBetweenDates(day4, day6));
		System.out.println("");
		System.out.println("");

		//Test DateString class methods:
		System.out.println("-------------------------------------------------------------------");
		System.out.println("/////////////////// - DateString Class - Test - ///////////////////");
		System.out.println("-------------------------------------------------------------------");
		
		
		System.out.println("");
		System.out.println("(date = ''23/1/1963''.toString() = " + day1.toString() + " , (should be (23/1/1963)");
		System.out.println("");
		
		System.out.println("get day of 6/12/1994 : " + day2.getDay());
		System.out.println("get month of 6/12/1994 : " + day2.getMonth());
		System.out.println("get year of 6/12/1994 : " + day2.getYear());
		System.out.println("");
		System.out.println("");
		System.out.println("Test -  addDays: " );
		System.out.println("-----------------------" );
		day8.addDays(-400);
		System.out.println("26/1/2 after subtracting 400 days: " + day8 + " (should be: 1/1/1)" );
		day9.addDays(12084);
		System.out.println("24/9/1986 after adding 12084 days: " + day9 + " (should be: 2/11/2019)" );
		day3.addDays(10);
		System.out.println("01/01/01 after adding 10 days: " + day3 + " (should be: 11/1/1)" );
		day7.addDays(-12084);
		System.out.println("2/11/2019 after subtracting 12084 days: " + day7 + " (should be: 24/9/1986)" );
			
		System.out.println("");
		System.out.println("");
		
		//Test DateArray class methods:
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("/////////////////// - DateArray Class - Test - ///////////////////");
		System.out.println("----------------------------------------------------------------");
		
		int[] d1 = {1963,1,23};
		int[] d2 = {1994,12,6};
		int[] d3 = {1,1,1};
		int[] d4 = {2020,5,7};
		int[] d5 = {2020,5,10};
		int[] d6 = {2021,5,10};
		int[] d7 = {2019,11,2};
		int[] d8 = {2,1,26};
		int[] d9 = {1986,9,24};
		
		DateArray day21 = new DateArray(d1);
		DateArray day22 = new DateArray(d2);
		DateArray day23 = new DateArray(d3);
		DateArray day24 = new DateArray(d4);
		DateArray day25 = new DateArray(d5);
		DateArray day26 = new DateArray(d6);
		DateArray day27 = new DateArray(d7);
		DateArray day28 = new DateArray(d8);
		DateArray day29 = new DateArray(d9);
		
		System.out.println("");
		System.out.println("(date = {1963,1,23}).toString() = " + day21.toString() + " , (should be (23/1/1963)");
		System.out.println("");
		
		System.out.println("");
		System.out.println("get day of 6/12/1994 : " + day22.getDay());
		System.out.println("get month of 6/12/1994 : " + day22.getMonth());
		System.out.println("get year of 6/12/1994 : " + day22.getYear());
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test -  addDays: " );
		System.out.println("-----------------------" );
		day28.addDays(-400);
		System.out.println("26/1/2 after subtracting 400 days: " + day28 + " (should be: 1/1/1)" );
		day29.addDays(12084);
		System.out.println("24/9/1986 after adding 12084 days: " + day29 + " (should be: 2/11/2019)" );
		day23.addDays(10);
		System.out.println("01/01/01 after adding 10 days: " + day23 + " (should be: 11/1/1)" );
		day27.addDays(-12084);
		System.out.println("2/11/2019 after subtracting 12084 days: " + day27 + " (should be: 24/9/1986)" );
		
		//String day = DateInt.intToDate(10);
		//System.out.println("");
		//System.out.println("");
		//System.out.println(day);
		
		//Test DateArray class methods:
		System.out.println("");
		System.out.println("");
		System.out.println("----------------------------------------------------------------");
		System.out.println("/////////////////// - DateInt Class - Test - ///////////////////");
		System.out.println("----------------------------------------------------------------");
		System.out.println("");
		DateInt day31 = new DateInt(396);
		DateInt day32 = new DateInt(10);
		DateInt day33 = new DateInt(3650);
		DateInt day34 = new DateInt(390);
		DateInt day35 = new DateInt(0);

		

		System.out.println("(date = 396).toString() = " + day31.toString() + " , (should be (1/2/2)");
		System.out.println("(date = 10).toString() = " + day32.toString() + " , (should be (11/1/1)");
		System.out.println("(date = 3650).toString() = " + day33.toString() + " , (should be (1/1/11)");
		System.out.println("(date = 390).toString() = " + day34.toString() + " , (should be (26/1/2)");
		System.out.println("(date = 0).toString() = " + day35.toString() + " , (should be (1/1/1)");


		System.out.println("");
		System.out.println("get day of 26/1/2 : " + day34.getDay());
		System.out.println("get month of 26/1/2 : " + day34.getMonth());
		System.out.println("get year of 26/1/2 : " + day34.getYear());
		

		System.out.println("");
		System.out.println("");
		System.out.println("Test -  addDays: " );
		System.out.println("-----------------------" );
		day34.addDays(-400);
		System.out.println("26/1/2 after subtracting 400 days: " + day34 + " (should be (1/1/1))");
		
		day33.addDays(-365);
		System.out.println("1/1/11 after subtracting 365 days: " + day33 + " (should be (1/1/10))");
		
		day31.addDays(365);
		System.out.println("1/2/2 after subtracting 10 days: " + day31 + " (should be (1/2/3))");
		
		day32.addDays(-15);
		System.out.println("11/1/1 after subtracting 15 days: " + day32 + " (should be (1/1/1))");
		}}


