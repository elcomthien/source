package com.elcom.adcenter.rvcadv.util;

import java.util.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;

import org.apache.axis2.Constants;
import org.apache.axis2.client.*;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
/**
 * This class contains helper methods for dealing with
 * Date objects.
 */
public final class DateHelper
{

  static final String Ver = "0.9";	

  public static String convertMoney(String money, String unit) {
        if(unit!=null && unit.equals("USD")){
            return money;
        }else{
            List ah = new ArrayList();
            int lenghtA = money.length();
            //System.out.println("lenghtA=" + lenghtA);
            int soDuA = lenghtA % 3;
            //System.out.println("soDuA=" + soDuA);
            int soNguyen = lenghtA / 3;
            int index = 1;
            if (soDuA > 0) {
                index = soNguyen + 1;
                //System.out.println("index=" + index);
            } else {
                if (soNguyen > 1) {
                    index = soNguyen;
                }
            }
            String newA = money;
            //System.out.println(newA);
            for (int i = 0; i < index; i++) {
               // System.out.println("newA bd =" + newA);
                if (newA.length() > 3) {
                    String tmp = money.substring(newA.length() - 3, newA.length());
                    newA = newA.substring(0, newA.length() - 3);
                   // System.out.println("i=" + i + ",newA=" + newA + ",tmp=" +tmp);
                    ah.add(0, tmp);
                } else {
                    ah.add(0, newA);
                }
            }
            //System.out.println(ah.toString());
            Iterator iterator = ah.iterator();
            return processMoney(iterator);
        }
    }

    private static String processMoney(Iterator vIterator) {
        StringBuffer str = new StringBuffer();
        while (vIterator.hasNext()) {
            str.append(vIterator.next());
            str.append(",");

        }
        String tmp = str.toString();
        tmp = tmp.substring(0, tmp.length() - 1);
        
        return tmp;

    }


  public static String format(Date date)
  {

	// returns a String representation of the date argument,
	// formatted according to the pattern argument, which
	// has the same syntax as the argument of the SimpleDateFormat
	// class1E
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	return formatter.format(date);
  }

  public static java.util.Date parseDate(String strDate)
  {
	try{
	  // return a Date
	  // Parse the previous string back into a Date.
	  java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
		  "MM/dd/yyyy HH:mm:ss");
	  java.text.ParsePosition pos = new java.text.ParsePosition(0);
	  java.util.Date d = formatter.parse(strDate, pos);

	  return d;

	} catch (Exception ex){}

	return null;
  }

  public static long toYYYYMMDD(java.util.Date date)
  {
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
        "yyyyMMdd");
    return Long.parseLong(formatter.format(date));
  }

  public static long getDateInSeconds(java.util.Date d)
  {
	int day = getDay(d);
	int month = getMonth(d);
	int year = getYear(d);

	long seconds = ( (year - 1900) * 365 * 24 + (year - 1900) * 6 +
					month * 30 * 24 + day * 24) * 60 * 60;

	Long convertLong = new Long(seconds);
	return convertLong.intValue();
  }

  public static long getTimeInSeconds(java.sql.Time t)
  {
	java.util.Date d = new java.util.Date(t.getTime());
	int h = getHour(d);
	int m = getMinute(d);
	int s = getSecond(d);

	long seconds = h * 60 * 60 + m * 60 + s;
	Long convertLong = new Long(seconds);
	return convertLong.intValue();
  }

  //=====================================================================
  public static final Date getDate(int year, int month, int day,
								   int hour, int minute)
  {
	// returns a Date with the specified time elements
	Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
										 day, hour, minute);

	return cal.getTime();

  } // getDate

  public static final Date getDate(int year, int month, int day)
  {
	// returns a Date with the specified time elements,
	// with the hour and minutes both set to 0 (midnight)

	Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
										 day);

	return cal.getTime();

  } // getDate

  static public int dayDiff(Date first, Date second)
  {

	// returns the difference, in days, between the first
	// and second Date arguments

	long msPerDay = 1000 * 60 * 60 * 24;
	long diff = (first.getTime() / msPerDay) - (second.getTime() / msPerDay);
	Long convertLong = new Long(diff);
	return convertLong.intValue();
  } // dayDiff

  static public int getYear(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.YEAR);
  } // getYear

  static public int getMonth(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	int calendarMonth = cal.get(Calendar.MONTH);
	return calendarMonthToInt(calendarMonth);
  } // getMonth

  static public int getDay(Date date)
  {
	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.DAY_OF_MONTH);
  } // getDay

  static public int getHour(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.HOUR_OF_DAY);
  } // geHour

  static public int getMinute(Date date)
  {

	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.MINUTE);
  } // geMinute

  static public int getSecond(Date date)
  {
	Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	return cal.get(Calendar.SECOND);
  } // getSecond

  private static int calendarMonthToInt(int calendarMonth)
  {

	if (calendarMonth == Calendar.JANUARY){
	  return 1;
	} else if (calendarMonth == Calendar.FEBRUARY){
	  return 2;
	} else if (calendarMonth == Calendar.MARCH){
	  return 3;
	} else if (calendarMonth == Calendar.APRIL){
	  return 4;
	} else if (calendarMonth == Calendar.MAY){
	  return 5;
	} else if (calendarMonth == Calendar.JUNE){
	  return 6;
	} else if (calendarMonth == Calendar.JULY){
	  return 7;
	} else if (calendarMonth == Calendar.AUGUST){
	  return 8;
	} else if (calendarMonth == Calendar.SEPTEMBER){
	  return 9;
	} else if (calendarMonth == Calendar.OCTOBER){
	  return 10;
	} else if (calendarMonth == Calendar.NOVEMBER){
	  return 11;
	} else if (calendarMonth == Calendar.DECEMBER){
	  return 12;
	} else{
	  return 1;
	}

  } // calendarMonthToInt

  private static int intToCalendarMonth(int month)
  {

	if (month == 1){
	  return Calendar.JANUARY;
	} else if (month == 2){
	  return Calendar.FEBRUARY;
	} else if (month == 3){
	  return Calendar.MARCH;
	} else if (month == 4){
	  return Calendar.APRIL;
	} else if (month == 5){
	  return Calendar.MAY;
	} else if (month == 6){
	  return Calendar.JUNE;
	} else if (month == 7){
	  return Calendar.JULY;
	} else if (month == 8){
	  return Calendar.AUGUST;
	} else if (month == 9){
	  return Calendar.SEPTEMBER;
	} else if (month == 10){
	  return Calendar.OCTOBER;
	} else if (month == 11){
	  return Calendar.NOVEMBER;
	} else if (month == 12){
	  return Calendar.DECEMBER;
	} else{
	  return Calendar.JANUARY;
	}

  } // intToCalendarMonth
 
  public static String format(Date date, String pattern)
  {

	// returns a String representation of the date argument,
	// formatted according to the pattern argument, which
	// has the same syntax as the argument of the SimpleDateFormat
	// class1E

	SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	return formatter.format(date);
  }
  
  public static long toYYYYMMDDHHMISS(java.util.Date d)
  {
    String temp = format(d, "yyyyMMddHHmmss");

    return (Long.parseLong(temp));
  }

  public static long toYYYYMMDDHHMISS(java.sql.Timestamp ts)
  {
    String temp = format(ts, "yyyyMMddHHmmss");

    return (Long.parseLong(temp));
  }
  
 //----------------------------------------------------------------------------------- 
  public static String getIpRemote()
  {
	  MessageContext curContext = MessageContext.getCurrentMessageContext();
	  //String remoteClientAddress = curContext.getProperty(MessageContext.REMOTE_ADDR);
	  Object ipProperty = curContext.getProperty(MessageContext.REMOTE_ADDR);
	  String remoteip = ipProperty.toString();
	  return remoteip + " => Ver: " + Ver;	  
  } 
//-----------------------------------------------------------------------------------
  public static String utilStringXml(String xml,String key)
  {	  
	  String result = "",keybegin="<" + key + ">",keyend="</" + key + ">";
	  int begin,end;
	  begin = xml.indexOf(keybegin);
	  end = xml.indexOf(keyend);
	  if (begin < 0 || end < 0)
		  return "0";
	  begin = begin + keybegin.length();
	  
	  result = xml.substring(begin,end);	  
	  return result;
  }
//-----------------------------------------------------------------------------------
  public static int createFile(String filename,String xml)
  {
	 int result = 0;
	  try{
	  FileOutputStream fos= new FileOutputStream(filename,false); 
      PrintWriter pw= new PrintWriter(fos); 
      pw.println(xml); 
      pw.close(); 
      result = 1;
	  }catch(Exception ex){result = 0;}
	  return result;
	  
  }
//-----------------------------------------------------------------------------------  
  public static void main(String[] args)
  {
	  //System.out.println(utilStringXml("<group><groupid>1</groupid><namegroup>khong biet</namegroup></group>","groupid"));
	  createFile("Config\\Layout.xml","<?xml version='1.0' encoding='UTF-8'?>\r\n" + 
			  	"<Layout>\r\n" +
 	            "<item>\r\n" +
 	            "<name>Clip 01</name>\r\n" +
 	            "<groupid>1</groupid>\r\n" +
 	            "<type>1</type>\r\n" +
 	            "<desc>Video quan cao 01</desc>\r\n" +
 	            "<x>180</x>\r\n" +
 	            "<y>0</y>\r\n" +
 	            "<width>1100</width>\r\n" +
 	            "<height>620</height>\r\n" +
 	            "<order>1</order>\r\n" +		
 	            "</item>\r\n" +
	  			"<item>\r\n" +
	  			"<name>Logo</name>\r\n" +
	  			"<groupid>1</groupid>\r\n" +
	  			"<type>3</type>\r\n" +
	  			"<desc>Logo quang cao 01</desc>\r\n" +
	  			"<x>0</x>\r\n" +
	  			"<y>0</y>\r\n" +
	  			"<width>180</width>\r\n" +	
	  			"<height>100</height>\r\n" +
	  			"<order>2</order>\r\n" + 
	  			"</item>\r\n"	+
	  			"</Layout>\r\n");
  }
} // class
