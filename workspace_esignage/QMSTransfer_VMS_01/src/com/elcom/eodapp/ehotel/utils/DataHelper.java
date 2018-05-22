package com.elcom.eodapp.ehotel.utils;

import java.util.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import javax.sql.rowset.spi.SyncFactory;


/**
 * This class contains helper methods for dealing with
 * Date objects.
 */
public final class DataHelper
{
	public static final char START_BYTE = 'S'; //Start Of MessagePacker (Don't change data type)
	public static final char END_BYTE = 'E'; //End  Of MessagePacker   (Don't change data type)
	public static final String PAD_CHAR = "__"; //Separator at Field Level
  
	/* ---------------------
	* Function: isValidMessage(): check if a message is valid format type 
	* Return  : true  - if the message is valid; false - if the message is invalid
	*/
	public static Boolean isValidMessage(String msgPackage) {
	System.out.println(msgPackage + " - START_BYTE:"  + msgPackage.charAt(0) + " - END_BYTE: " +  msgPackage.charAt(msgPackage.length() - 1));
	if (msgPackage.charAt(0) != START_BYTE || msgPackage.charAt(msgPackage.length() - 1) != END_BYTE) {
	            return false;
	        }
	        return true;
	}

	
	/* @Function: getMessageData()
	* Return a array:   - arr[0]: msgCode
	*    - arr[1->(n-1)]: msgData
	*/
	public static String[] getMessageData(String message) {
	try {
	            //Check a valid message
	            /*if (false == isValidMessage(message)) {
	                return null;
	            }*/

	            // Bo cac ky tu danh dau chuoi S____E
	            String data = message.substring(1, message.length() - 1);
	            System.out.println("Ktra MSG tai MyMessage " + data);

	            // Lay du lieu cua thong diep, chuyen vao String Array
	            String[] contentArr;
	            if (data.indexOf(PAD_CHAR) > 0) {
	                contentArr = data.split(PAD_CHAR);
	            } else {
	                contentArr = new String[]{data};
	            }
	            return contentArr;
	        } catch (Exception ex) {
	            //FileLog.getFileLog().writeException(ex);
	        }
	        return null;
	}
	
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
        System.out.println(tmp);
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

	public static java.util.Date parseDate(String strDate, String format)
	{
		try{
			// return a Date
			// Parse the previous string back into a Date.
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
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
  
  public static String getIpRemote()
  {
	 
	  InetAddress ip;
	  String hostname = "";
	  try {
		  ip = InetAddress.getLocalHost();
		  hostname = ip.getHostAddress();
	  } catch (UnknownHostException e) {

		  e.printStackTrace();
	  }	  
	  return hostname;
  }
  
  public static String getParamInfo(String methodName, String[] params) {
	  String title = "Ip: " + DataHelper.getIpRemote() + " - In Core." + methodName;
	  String paramStr = "";
	  for(int i = 0; i < params.length; i++) {
		  if(i < params.length - 1)
			  paramStr += params[i] + ",";
		  else paramStr += params[i];
	  }
	  String outStr = title + "(" + paramStr + ")"; 		  
	  return outStr;	  
  }
  
  public static void main(String arv[])
  {
	  String[] a = getMessageData("SA__0__1__1234__0__Phím 1__0+2__Đổi SIM+Đổi thông tin thuê bao__1__10__Quầy 11__Nil__Nil__09/19/2015 15:03:47 PM__09/19/2015 15:11:21 PM__Null__X1__X2__X3__X4E");
	 
		  System.out.println(a[7]);
	 
  }
} // class
