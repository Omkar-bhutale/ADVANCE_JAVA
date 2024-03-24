package in.omkar.main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConversionCodeApp {

	public static void main(String[] args) throws ParseException {
		
		System.out.println("enter the date in format:: (dd-MM-yyyy)");
		Scanner scanner = new Scanner(System.in);
		String sdate = scanner.next();
		
		//for conversion of date from string to date formal we use a class SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//note:the date format is case sensitive 
		java.util.Date uDate = sdf.parse(sdate);
		
		long value = uDate.getTime();
		
		java.sql.Date sqlDate = new Date(value);
		
		System.out.println("the string date is :"+sdate);
		System.out.println("the utility date is :"+uDate);
		System.out.println("the date is :"+sqlDate);
		
		//if dateString  is in the form yyyy-MM-dd then no need to first convert the date in 
		//utility date.we can directly convert the date in sql date
		System.out.println("enter the date in the format:: (yyyy-MM-dd)");
		 String sdate2 = scanner.next();
		 scanner.close();
		 java.sql.Date sqlDate2 = Date.valueOf(sdate2);
		 System.out.println("sqlDate2 is :"+sqlDate2);
		
	}
}
