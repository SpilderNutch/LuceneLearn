/**
 * @author yuouyang
 * @createtime 2017-12-18
 * @version V1.0
 */
package com.yutian.util;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTool {
	
	public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String dateFormat = "yyyy-MM-dd";
	/** 日期格式1 yyyy-MM-dd */
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

	/** 日期格式2 yyyy-MM-dd HH:mm:ss */
	private static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 功能:构造子
	 */
	private DateTool() {
	}

	/**
	 * 
	 * 功能:得到本月的最后一天
	 * @param date 日期
	 * @return Date 本月的最后一天
	 *  
	 */
	public static java.util.Date getLastDateOfCurMonth(Date date) {
		Date next = getFirstDateOfNextMonth(date);
		return beforeSeconds(next, 1);
	}
	
	/**
	 * 功能:判断当前日期是否是当前日期所在月份的最后一天
	 * @param date 日期
	 * @return boolean 是-返回true、否-false
	 *  
	 */
	public static boolean isLastDayOfCurMonth(Date date) {
		Date next = getFirstDateOfNextMonth(date);
		Date day = beforeSeconds(next, 1);
		return DateTool.getDay(day) == DateTool.getDay(date);
	}

	/**
	 * 
	 * 功能:得到上个月的最后一天
	 * @param date 日期
	 * @return Date 上个月的最后一天
	 *  
	 */
	public static java.util.Date getLastDateOfPriorMonth(Date date) {
		Date start = getFistDateOfCurrentMonth(date);
		return beforeSeconds(start, 1);
	}
	
	/**
	 * 
	 * 功能:得到上个月的第一天
	 * @param date 日期
	 * @return Date 上个月的最后一天
	 *  
	 */
	public static java.util.Date getFirstDateOfPriorMonth(Date date) {
		Date start = getLastDateOfPriorMonth(date);
		String dateStr = DateTool.dateToString(start, "yyyy-MM");
		start = DateTool.stringToDate(dateStr + "-01", "yyyy-MM-dd");
		return start;
	}

	/**
	 * 
	 * 功能:得到本日期的结束时间 <br>
	 * 示列：假如当前日期为,2010-03-12 11:22:26 <br>
	 *      则执行函数后结果为，2010-03-12 23:59:59
	 * 
	 * @param date 当前日期
	 * @return 本日期的结束时间
	 *  
	 */
	public static java.util.Date getLastTime(Date date) {
		try {
			return sdfTime.parse(sdfDate.format(date) + " 23:59:59");
		} catch (ParseException ex) {
			throw new RuntimeException("日期时间解析异常.", ex);
		}
	}

	/**
	 * 功能:得到距离本时间以后seconds秒的时间
	 * 示列：假如当前日期为,2010-03-03 12:11:11 <br>
	 *   seconds=10时,   则执行函数后结果为，2010-03-03 12:11:21
	 * @param date 当前日期
	 * @param seconds 秒数
	 * @return  Date
	 *  
	 */
	public static java.util.Date afterSeconds(Date date, long seconds) {
		long time = date.getTime();
		time = time + 1000 * seconds;
		return new java.util.Date(time);
	}

	/**
	 * 
	 * 功能:得到距离本时间以后minutes分钟的时间
	 * 示列：假如当前日期为,2010-03-03 12:11:11 <br>
	 *   minutes=10时，则执行函数后结果为，2010-03-03 12:21:11
	 * @param date 当前日期
	 * @param minutes 分钟数
	 * @return
	 *  
	 */
	public static java.util.Date afterMinutes(Date date, long minutes) {
		return afterSeconds(date, minutes * 60);
	}

	/**
	 * 
	 * 功能:得到距离本时间以后hours小时的时间
	 * 示列：假如当前日期为,2010-03-03 12:11:11 <br>
	 *   hours=10时，则执行函数后结果为，2010-03-03 22:11:11
	 * @param date 当前日期
	 * @param hours 小时数
	 * @return
	 *  
	 */
	public static java.util.Date afterHours(Date date, long hours) {
		return afterMinutes(date, hours * 60);
	}

	/**
	 * 
	 * 功能: 得到距离本时间以后days天的时间
	 * @param date 当前日期 
	 * @param days 天数
	 * @return
	 *  
	 */
	public static java.util.Date afterDays(Date date, long days) {
		return afterHours(date, days * 24);
	}

	/**
	 * 
	 * 功能:得到距离本时间以前seconds秒的时间
	 * @param date
	 * @param seconds
	 * @return
	 *  
	 */
	public static java.util.Date beforeSeconds(Date date, long seconds) {
		long time = date.getTime();
		time = time - 1000 * seconds;
		return new java.util.Date(time);
	}

	/**
	 * 
	 * 功能:得到距离本时间以前minutes分钟的时间
	 * @param date
	 * @param minutes
	 * @return
	 *  
	 */
	public static java.util.Date beforeMinutes(Date date, long minutes) {
		return beforeSeconds(date, minutes * 60);
	}

	/**
	 * 
	 * 功能: 得到距离本时间以前hours小时的时间
	 * @param date
	 * @param hours
	 * @return
	 *  
	 */
	public static java.util.Date beforeHours(Date date, long hours) {
		return beforeMinutes(date, hours * 60);
	}

	/**
	 * 
	 * 功能:得到距离本时间以前days天的时间
	 * @param date
	 * @param days
	 * @return
	 *  
	 */
	public static java.util.Date beforeDays(Date date, long days) {
		return beforeHours(date, days * 24);
	}

	/**
	 * 
	 * 功能:得到本日期的起始时间
	 * @param date
	 * @return
	 *  
	 */
	public static java.util.Date getFirstTime(Date date) {
		try {
			return sdfTime.parse(sdfDate.format(date) + " 00:00:00");
		} catch (ParseException ex) {
			return null;
		}
	}
     /**
      * 
      * 功能:得到下个月的第一天
      * @param date
      * @return
      *  
      */
	public static java.util.Date getFirstDateOfNextMonth(Date date) {
		int month = getMonth(date);
		int year = getYear(date);
		if (12 == month) {
			month = 1;
			year = year + 1;
		} else {
			month = month + 1;
		}
		String strDate = null;
		if (month < 10) {
			strDate = year + "-" + "0" + month + "-01 00:00:00";
		} else {
			strDate = year + "-" + month + "-01 00:00:00";
		}
		return DateTool.stringToDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * 功能:得到本月的第一天
	 * @param date
	 * @return
	 *  
	 */
	public static java.util.Date getFistDateOfCurrentMonth(Date date) {
		String strDate = getYear(date) + "-";
		int month = getMonth(date);
		if (month < 10) {
			strDate = strDate + "0" + month + "-";
		} else {
			strDate = strDate + month + "-";
		}
		strDate = strDate + "01 00:00:00";
		return DateTool.stringToDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

    /**
     * 
     * 功能:得到日期中的秒数("HH:mm:ss"格式中的"ss")
     * @param date
     * @return
     *  
     */
	public static int getSecond(Date date) {
		String timeStr = DateTool.dateToString(date, "HH:mm:ss");
		int beginIndex = timeStr.lastIndexOf(":") + 1;
		int endIndex = timeStr.length();
		return (new Integer(timeStr.substring(beginIndex, endIndex)))
				.intValue();
	}
	/**
	 * 
	 * 功能:得到日期中的分钟数 ("HH:mm:ss"格式中的"mm")
	 * @param date
	 * @return
	 *  
	 */
	public static int getMinute(Date date) {
		String timeStr = DateTool.dateToString(date, "HH:mm:ss");
		int beginIndex = timeStr.indexOf(":") + 1;
		int endIndex = timeStr.lastIndexOf(":");
		return (new Integer(timeStr.substring(beginIndex, endIndex)))
				.intValue();
	}
	/**
	 * 
	 * 功能:得到日期中的小时数("HH:mm:ss"格式中的"HH")
	 * @param date
	 * @return
	 *  
	 */
	public static int getHour(Date date) {
		String timeStr = DateTool.dateToString(date, "HH:mm:ss");
		int beginIndex = 0;
		int endIndex = timeStr.indexOf(":");
		return (new Integer(timeStr.substring(beginIndex, endIndex)))
				.intValue();
	}
	
	/**
	 * 功能:得到日期字符串中的小时数("HH:mm:ss"格式中的"HH")
	 * @param sdate 日期字符串 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 *  
	 */
	public static int getHour(String sdate) {
		return Integer.valueOf(sdate.substring(11, 13));
	}
	/**
	 * 
	 * 功能:得到日期中的天数 ("yyyy-MM-dd"格式中的"dd")
	 * @param date
	 * @return
	 *  
	 */
	public static int getDay(Date date) {
		String dateStr = DateTool.dateToString(date, "yyyy-MM-dd");
		int beginIndex = dateStr.lastIndexOf("-") + 1;
		int endIndex = dateStr.length();
		return (new Integer(dateStr.substring(beginIndex, endIndex)))
				.intValue();
	}
	/**
	 * 
	 * 功能:得到日期中的月数("yyyy-MM-dd"格式中的"MM")
	 * @param date
	 * @return
	 *  
	 */
	public static int getMonth(Date date) {
		String dateStr = DateTool.dateToString(date, "yyyy-MM-dd");
		int beginIndex = dateStr.indexOf("-") + 1;
		int endIndex = dateStr.lastIndexOf("-");
		return (new Integer(dateStr.substring(beginIndex, endIndex)))
				.intValue();
	}

	/**
	 * 
	 * 功能:得到日期中的年数("yyyy-MM-dd"格式中的"yyyy")
	 * @param date
	 * @return
	 *  
	 */
	public static int getYear(Date date) {
		String dateStr = DateTool.dateToString(date, "yyyy-MM-dd");
		int beginIndex = 0;
		int endIndex = dateStr.indexOf("-");
		return (new Integer(dateStr.substring(beginIndex, endIndex)))
				.intValue();
	}

	/**
	 * 
	 * 功能:将日期字符串类型转换为日期类型
	 * @param date 日期字符串
	 * @param formatter 字符串的格式
	 * @return
	 *  
	 */
	public static java.util.Date stringToDate(String date, String formatter) {
		java.util.Date rlt = null;
		if (null == date) {
			return rlt;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		try {

			rlt = sdf.parse(date);
		} catch (Exception e) {
			rlt = null;
		}
		return rlt;
	}

	/**
	 * 
	 * 功能:将日期类型转换成字符串
	 * @param date 日期
	 * @param formatter 日期格式
	 * @return
	 *  
	 */
	public static String dateToString(java.util.Date date, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
	
	/**
	 * 
	 * 功能:将日期类型转换成字符串
	 * @param colValue 日期
	 * @return
	 *  
	 */
	public static String objDateToString(java.util.Date colValue) {
		if(colValue != null){
			return sdfTime.format((java.util.Date)colValue);
		}else{
			return "";
		}
	}

	/**
	 * 
	 * 功能:得到当前时间
	 * @return
	 *  
	 */
	public static java.util.Date now() {
		return new java.util.Date();
	}

	/**
	 * 
	 * 功能:得到今天的起始时间
	 * @param date
	 * @return
	 *  
	 */
	public static java.util.Date getTodayFirstTime(Date date) {

		return getFirstTime(date);
	}

	/**
	 * 
	 * 功能:得到今天的结束时间
	 * @param date
	 * @return
	 *  
	 */
	public static java.util.Date getTodayLastTime(Date date) {
		return getLastTime(date);
	}

	/**
	 * 
	 * 功能:得到时间段的月份数组
	 * 
	 * @param fromDate 开始日期
	 * @param toDate 结束日期
	 * @return Date[] 格式为 yyyy-MM
	 *  
	 */
	public static Date[] getMonthOfPeriod(Date fromDate, Date toDate) {
		// Date tempDate=fromDate;
		String tempDateStr = "";
		ArrayList arrayList = new ArrayList();
		int listIndex = 0;

		// 循环fromDate，toDate，将不同月份存至arrayList
		while (fromDate.before(toDate) || fromDate.equals(toDate)) {
			tempDateStr = DateTool.dateToString(fromDate, "yyyy-MM");
			Date tempDate = DateTool.stringToDate(tempDateStr, "yyyy-MM");
			if (arrayList.size() > 0) {
				if (tempDate.after((Date) arrayList.get(listIndex))) {
					listIndex = listIndex + 1;
					arrayList.add(listIndex, tempDate);
				}
			} else {
				arrayList.add(listIndex, tempDate);
			}

			fromDate = afterDays(fromDate, 1);
		}

		// 将arrayList转存至Date[]
		Date[] returnValue = new Date[arrayList.size()];
		Iterator iterator = arrayList.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			returnValue[i++] = (Date) iterator.next();
		}
		return returnValue;
	}

	/**
	 * 
	 * 功能: 获得当前日期的前monthes个月的月分（格式：yyyy-MM）
	 * @param date
	 * @param monthes
	 * @return
	 *  
	 */
	public static Date beforeMonthes(Date date, int monthes) {

		Date returnMonth = date;
		for (int i = 1; i <= monthes; i++) {
			returnMonth = getLastDateOfPriorMonth(returnMonth);
		}
		String dateStr = DateTool.dateToString(returnMonth, "yyyy-MM");
		returnMonth = DateTool.stringToDate(dateStr, "yyyy-MM");
		return returnMonth;
	}
	
	/**
	 * 
	 * 功能: 获得当前日期的前monthes个月的第一天（格式：yyyy-MM-dd）
	 * @param date
	 * @param monthes
	 * @return
	 *  
	 */
	public static Date beforeMonthesFirstDay(Date date, int monthes) {
		
		Date returnMonth = date;
		for (int i = 1; i <= monthes; i++) {
			returnMonth = getLastDateOfPriorMonth(returnMonth);
		}
		String dateStr = DateTool.dateToString(returnMonth, "yyyy-MM");
		returnMonth = DateTool.stringToDate(dateStr + "-01", "yyyy-MM-dd");
		return returnMonth;
	}

	/**
	 * 
	 * 功能: 获得当前日期的后monthes个月的月分（格式：yyyy-MM）
	 * @param date 当前日期
	 * @param monthes 月份数
	 * @return
	 */
	public static Date afterMonthes(Date date, int monthes) {

		Date returnMonth = date;
		for (int i = 1; i <= monthes; i++) {
			returnMonth = getFirstDateOfNextMonth(returnMonth);
		}
		String dateStr = DateTool.dateToString(returnMonth, "yyyy-MM");
		returnMonth = DateTool.stringToDate(dateStr, "yyyy-MM");
		return returnMonth;
	}
	
	/***********************************************************/
	
	public static void main(String[] args) {
		DateTool tt = new DateTool();
		System.out.println("获取当天日期:" + tt.getNowTime("yyyy-MM-dd"));
		System.out.println("获取本周一日期:" + tt.getMondayOFWeek());
		System.out.println("获取本周日的日期~:" + tt.getCurrentWeekday());
		System.out.println("获取上周一日期:" + tt.getPreviousWeekday());
		System.out.println("获取上周日日期:" + tt.getPreviousWeekSunday());
		System.out.println("获取下周一日期:" + tt.getNextMonday());
		System.out.println("获取下周日日期:" + tt.getNextSunday());
		System.out.println("获得相应周的周六的日期:" + tt.getSaturday());
		System.out.println("获取本月第一天日期:" + tt.getFirstDayOfMonth());
		System.out.println("获取本月最后一天日期:" + tt.getDefaultDay());
		System.out.println("获取上月第一天日期:" + tt.getPreviousMonthFirst());
		System.out.println("获取上月最后一天的日期:" + tt.getPreviousMonthEnd());
		System.out.println("获取下月第一天日期:" + tt.getNextMonthFirst());
		System.out.println("获取下月最后一天日期:" + tt.getNextMonthEnd());
		System.out.println("获取本年的第一天日期:" + tt.getCurrentYearFirst());
		System.out.println("获取本年最后一天日期:" + tt.getCurrentYearEnd());
		System.out.println("获取去年的第一天日期:" + tt.getPreviousYearFirst());
		System.out.println("获取去年的最后一天日期:" + tt.getPreviousYearEnd());
		System.out.println("获取明年第一天日期:" + tt.getNextYearFirst());
		System.out.println("获取明年最后一天日期:" + tt.getNextYearEnd());
		System.out.println("获取本季度第一天到最后一天:" + tt.getThisSeasonTime(11));
		System.out.println("获取两个日期之间间隔天数2008-12-1~2008-9-29: "
				+ DateTool.getTwoDay("2008-12-1", "2008-9-29"));
		
		System.out.println(DateTool.getDefaultDay());
		System.out.println(DateTool.getMaxYear()/7);
		System.out.println(DateTool.getWeek("2014-3-4"));
		System.out.println(DateTool.getDateTimeStr(new Date()));
		System.out.println("本周星期日："+DateTool.getCurrentWeekday());
		System.out.println("本周星期日："+DateTool.getSaturday());
//		System.out.println("本周星期日："+DateTool.getMonthPlus());
		
	}

	
	/**
	 * 功能：将类似2015-12-25日期类型，转变为2015年12月25日 格式
	 * @throws ParseException 
	 * 
	 */
	public static String convertStringToFormateDate(String dataString) throws ParseException{
		Date date = sdfDate.parse(dataString);
		int y = getYear(date);
		int m = getMonth(date);
		int d = getDay(date);
		return String.valueOf(y) + "年"+String.valueOf(m) + "月" + String.valueOf(d) + "日 ";
	}
	
	/**
	 * 功能：将类似2015-12-25  08日期类型，转变为2015年12月25日  08时 格式
	 * @throws ParseException 
	 * 
	 */
	public static String convertStringToFormateDateHour(String dataString) throws ParseException{
		Date date = sdfDate.parse(dataString);
		int y = getYear(date);
		int m = getMonth(date);
		int d = getDay(date);
		String hour = dataString.substring(11, 13);
		String minutes = dataString.substring(14, 16);
		String millions = dataString.substring(17);
		return String.valueOf(y) + "年"+String.valueOf(m) + "月" + String.valueOf(d) + "日 "+ hour+ "时"+minutes+"分"+millions+"秒";
	}
	
	
	/**
	 * 功能：获取年月日 星期
	 * @return String eg: 2013年9月12日 第37周 星期四
	 */
	public static String getDateTimeStr(Date date) {
		int y = getYear(date);
		int m = getMonth(date);
		int d = getDay(date);
		String week = getWeek(date);
		return String.valueOf(y) + "年"+String.valueOf(m) + "月" + String.valueOf(d) + "日 " + week;
	}
	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		if (sdate.length() > 10) {
			sdate = sdate.substring(0, 10);
		}
		// 再转换为时间
		Date date = DateTool.strToDate(sdate);
		return getWeek(date);
	}
	
	/**
	 * 根据一个日期，返回是星期几对应的数字
	 * @param sdate
	 * @return 1-星期一、2-星期二、、、7-星期天
	 */
	public static int getXq(String sdate) {
		if (sdate.length() > 10) {
			sdate = sdate.substring(0, 10);
		}
		// 再转换为时间
		Date date = DateTool.strToDate(sdate);
		String ww = getWeek(date);
		int xq = 0;
		if ("周一".equals(ww)) {
			xq = 1;
		} else if ("周二".equals(ww)) {
			xq = 2;
		} else if ("周三".equals(ww)) {
			xq = 3;
		} else if ("周四".equals(ww)) {
			xq = 4;
		} else if ("周五".equals(ww)) {
			xq = 5;
		} else if ("周六".equals(ww)) {
			xq = 6;
		} else {
			xq = 7;
		}
		return xq;
	}
	
	/**
	 * 根据一个日期，返回是星期几对应的数字
	 * @param date
	 * @return 1-星期一、2-星期二、、、7-星期天
	 */
	public static int getXq(Date date) {
		// 再转换为时间
		String ww = getWeek(date);
		int xq = 0;
		if ("周一".equals(ww)) {
			xq = 1;
		} else if ("周二".equals(ww)) {
			xq = 2;
		} else if ("周三".equals(ww)) {
			xq = 3;
		} else if ("周四".equals(ww)) {
			xq = 4;
		} else if ("周五".equals(ww)) {
			xq = 5;
		} else if ("周六".equals(ww)) {
			xq = 6;
		} else {
			xq = 7;
		}
		return xq;
	}
	
	/**
	 * 根据一个日期，返回是星期几对应的数字
	 * @param date
	 * @return 1-星期一、2-星期二、、、7-星期天
	 */
	public static int weekNum(Date date) {
		String ww = getWeek(date);
		int xq = 0;
		if ("周一".equals(ww)) {
			xq = 1;
		} else if ("周二".equals(ww)) {
			xq = 2;
		} else if ("周三".equals(ww)) {
			xq = 3;
		} else if ("周四".equals(ww)) {
			xq = 4;
		} else if ("周五".equals(ww)) {
			xq = 5;
		} else if ("周六".equals(ww)) {
			xq = 6;
		} else {
			xq = 7;
		}
		return xq;
	}
	

	
	public static String getWeek(Date date) {
//		String[] dayNames = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		String[] dayNames = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		// 再转换为时间
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		// 1=星期日 7=星期六，其他类推
//		return new SimpleDateFormat("EEEE").format(c.getTime());
		return dayNames[dayOfWeek-1];
	}
	
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Date strToDateTime(String strDate) throws ParseException {
		Date strtodate = sdfTime.parse(strDate);
		return strtodate;
	}

	/**
	 * 两个时间之间的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	// 计算当月最后一天,返回字符串
	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1); // 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1); // 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1); // 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 上月第一天
	public String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1); // 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1); // 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取当月第一天
	public String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1); // 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得本周星期日的日期
	public static String getCurrentWeekday() {
		int weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();

		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获取当天时间
	public String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat); // 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}
	
	// 获得本周一的日期
	public String getMondayOFWeek() {
		int weeks = 0;
		
		/*获得当前日期与本周日相差的天数*/
		
		int mondayPlus = this.getMondayPlus();
		
		GregorianCalendar currentDate = new GregorianCalendar();
//		currentDate.add(Calendar.DAY_OF_MONTH, mondayPlus); //或者currentDate.add(GregorianCalendar.Date, mondayPlus); 
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得相应周的周六的日期
	public static String getSaturday() {
		int weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 5);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得上周星期日的日期
	public String getPreviousWeekSunday() {
		int weeks = 0;
		weeks--;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得上周星期一的日期
	public String getPreviousWeekday() {
		int weeks = 0;
		weeks--;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得下周星期一的日期
	public String getNextMonday() {
		int weeks = 0;
		weeks++;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得下周星期日的日期
	public String getNextSunday() {

		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	private int getMonthPlus() {
		int MaxDate = 0;
		Calendar cd = Calendar.getInstance();
		int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
		cd.set(Calendar.DATE, 1); // 把日期设置为当月第一天
		cd.roll(Calendar.DATE, -1); // 日期回滚一天，也就是最后一天
		MaxDate = cd.get(Calendar.DATE);
		if (monthOfNumber == 1) {
			return -MaxDate;
		} else {
			return 1 - monthOfNumber;
		}
	}

	// 获得上月最后一天的日期
	public String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1); // 减一个月
		lastDate.set(Calendar.DATE, 1); // 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1); // 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月第一天的日期
	public String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1); // 减一个月
		lastDate.set(Calendar.DATE, 1); // 把日期设置为当月第一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月最后一天的日期
	public String getNextMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1); // 加一个月
		lastDate.set(Calendar.DATE, 1); // 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1); // 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得明年最后一天的日期
	public String getNextYearEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1); // 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得明年第一天的日期
	public String getNextYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1); // 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		str = sdf.format(lastDate.getTime());
		return str;

	}

	// 获得本年有多少天
	private static int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1); // 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1); // 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR); // 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1); // 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1); // 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	// 获得本年第一天的日期
	public static String getCurrentYearFirst() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	// 获得本年最后一天的日期 *
	public static String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"); // 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	// 获得上年第一天的日期 *
	public static String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"); // 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-1-1";
	}

	// 获得上年最后一天的日期
	public static String getPreviousYearEnd() {
		int weeks = 0;
		int MaxYear = 0;
		weeks--;
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks
				+ (MaxYear - 1));
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		getThisSeasonTime(11);
		return preYearDay;
	}

	// 获得本季度
	public static String getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + start_month + "-" + start_days
				+ ";" + years_value + "-" + end_month + "-" + end_days;
		return seasonDate;

	}

	/**
	 * 获取某年某月的最后一天
	 * @param year 年
	 * @param month 月
	 * @return 最后一天
	 */
	private static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * @param year 年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	
	/**
	 * 获取某个月的某一天的上一个月对应的时间字符串(环比)
	 * @param date 当前时间
	 * @return 格式：yyyy-MM-dd
	 */
	public static String getHbOfDay(Date date) {
		int _year = getYear(date); 
		int month = getMonth(date); 
		int _day = getDay(date); 
		int _month = month - 1;
		if (month == 1) {
			_month = 12;
			_year--;
		} 
		if (_day > getLastDayOfMonth(_year,_month)) {
			_day = getLastDayOfMonth(_year,_month);
		}
		return "" + _year + "-"+ (String.valueOf(_month).length()==1 ? "0" +_month : _month) + "-"+ (String.valueOf(_day).length()==1 ? "0" +_day : _day);
	}
	
	/**
	 * 获取某个月的某一天的上一个月对应的时间字符串
	 * @param date
	 * @return 格式：yyyy-MM-dd
	 */
	public static String relativeDay(Date date) {
		int year = getYear(date);
		int month = getMonth(date);
		int day = getDay(date);
		int _year = year;
		int _month = month - 1;
		if (month == 1) {
			_month = 12;
			_year = year - 1;
		} 
		int _day = day;
		if (_day > getLastDayOfMonth(_year,_month)) {
			_day = getLastDayOfMonth(_year,_month);
		}
		return "" + _year + "-"+ (String.valueOf(_month).length()==1 ? "0" +_month : _month) + "-"+ (String.valueOf(_day).length()==1 ? "0" +_day : _day);
	}
	
	/**
	 * 获取上一年对应的时间字符串 例如：2014年3月8号对应2013-03-08
	 * @param date
	 * @return 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getRelativeDay(Date date) {
		int _year = DateTool.getYear(date) - 1; //去年
		int _month = getMonth(date);
		int _day = getDay(date);
		
		return "" + _year + "-"+ (String.valueOf(_month).length()==1 ? "0" +_month : _month) + "-"+ (String.valueOf(_day).length()==1 ? "0" +_day : _day) + " " + dateToString(date, "HH:mm:ss");
	}
    
	/**
	 * 将时间字符串转格式为 某某月某某日，例如：2013-01-23 12:36:00 转换成 1月23号
	 * @param sdate yyyy-MM-dd HH:mm:ss
	 * @param formater 时间格式
	 * @return
	 */
	public static String getMonthDay(String sdate, String formater) {
		
		return DateTool.dateToString(DateTool.stringToDate(sdate, formater),"MM月dd日");
	}
	
	/**
	 * 功能：判断当前时间是否是周一
	 * @param date
	 * @return
	 */
	public static boolean isMonday(Date date) {
		return "星期一".equals(DateTool.getWeek(date));
	}
	
	/**
	 * 初始化一组以时间为key的Map value默认为0 
	 * @param from 
	 * @param to
	 * @param formatter key的时间格式默认为：yyyy-MM-dd HH:mm:ss
	 * @param flag 步进 -hour、day
	 * @return
	 */
	public static Map<String, Long> initKeyMapByFlag(Date from, Date to, String formatter, String flag) {
		
		Map dataMap = Maps.newLinkedHashMap();
		Date tmpDate = new Date(from.getTime());
		while (tmpDate.getTime()<=to.getTime()) {
			if ("day".equalsIgnoreCase(flag)) {
				if (!Strings.isNullOrEmpty(formatter)) {
					dataMap.put(DateTool.dateToString(tmpDate, formatter), 0L);
				} else {
					dataMap.put(DateTool.dateToString(tmpDate, "yyyy-MM-dd HH:mm:ss"), 0L);
				}
				tmpDate = DateTool.afterDays(tmpDate, 1);
			} else if ("hour".equalsIgnoreCase(flag)) {
				dataMap.put(DateTool.dateToString(tmpDate, "yyyy-MM-dd HH:mm:ss"), 0L);
				tmpDate = DateTool.afterHours(tmpDate, 1);
			} else if ("week".equals(flag)) {//周，则将每周的周一时间作为key添加
				if (!Strings.isNullOrEmpty(formatter)) {
					dataMap.put(DateTool.dateToString(tmpDate, formatter), 0L);
				} else {
					dataMap.put(DateTool.dateToString(tmpDate, "yyyy-MM-dd HH:mm:ss"), 0L);
				}
				tmpDate = DateTool.afterDays(tmpDate, 7);
			}
		}
		return dataMap;
	}
	
	/**
	 * 初始化一组以1-24为key的Map value默认为0 
	 * @return
	 */
	public static Map<Integer, Long> hourKey() {
		
		Map dataMap = Maps.newHashMap();
		for (int i = 1; i <= 24; i++) {
			dataMap.put(i, 0L);
		}
		return dataMap;
	}
	
	/**
	 * 功能：获得当前日期与本周一相差的天数
	 * @param date
	 * @author taowei20061122@163.com
	 * @return
	 */
	private static int getMondayPlus(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1

		if (dayOfWeek == 0) {//周末
			return -6;
		} else {//与周一相差的天数
			return 1 - dayOfWeek;
		}
	}
	
	/**
	 *  功能：获取当前日期所对应的星期天的日期（注意：中国以星期天为一周的最后一天）
	 * @param date 当前日期
	 * @return
	 */
	public static Date getCurrentSunday(Date date) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		int mondayPlus = getMondayPlus(date);
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date sunday = currentDate.getTime();
		return sunday;
	}
	
	/**
	 * @author ouyangy 
	 * Time: 2016/08/11 
	 *  功能：获取当前日期所对应的星期天的时间(最后一秒)（注意：中国以星期天为一周的最后一天）
	 * @param date 当前日期
	 * @return
	 */
	public static Date getCurrentSundayLastSecond(Date date) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		int mondayPlus = getMondayPlus(date);
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		currentDate.add(GregorianCalendar.HOUR, 23);
		currentDate.add(GregorianCalendar.MINUTE, 59);
		currentDate.add(GregorianCalendar.SECOND,59);
		Date sunday = currentDate.getTime();
		return sunday;
	}
	
	
	/**
	 *  功能：获取当前日期所对应的下一周的星期天的日期（注意：中国以星期天为一周的最后一天）
	 * @param date 当前日期
	 * @return
	 */
	public static Date getNextSunday(Date date) {
		Date currSunday = getCurrentSunday(date);
		//下一周的周一
		Date nextMonday = DateTool.afterDays(currSunday, 1);
		return getCurrentSunday(nextMonday);
	}
	
	/**
	 *  功能：获取当前日期所对应的星期一的日期（注意：中国以星期天为一周的最后一天）
	 * @param date 当前日期
	 * @return
	 */
	public static Date getCurrentMonday(Date date) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		int mondayPlus = getMondayPlus(date);
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		return monday;
	}
	/**
	 *  功能：获取当前日期所对应的下一周的星期一的日期（注意：中国以星期天为一周的最后一天）
	 * @param date 当前日期
	 * @return
	 */
	public static Date getNextMonday(Date date) {
		Date currSunday = getCurrentSunday(date);
		//下一周
		Date nextMonday = DateTool.afterDays(currSunday, 1);
		return nextMonday;
	}
	/**
	 * 功能：当前时间对应的年的最后一天
	 * @param currDate
	 * @return
	 */
	public static Date getCurrentYearEnd(Date currDate) {
		int year = getYear(currDate);
		return stringToDate(year + "-12-31 00:00:00", dateTimeFormat);
	}
	/**
	 * 功能：当前时间对应的年的第一天
	 * @param currDate
	 * @return
	 */
	public static Date getCurrentYearFirst(Date currDate) {
		int year = getYear(currDate);
		return stringToDate(year + "-01-01 00:00:00", dateTimeFormat);
	}
	/**
	 * 功能：当前时间对应的下一年的第一天
	 * @param currDate
	 * @return
	 */
	public static Date getNextYearFirst(Date currDate) {
		Date nextYearFirstDate = getCurrentYearEnd(currDate);
		return afterDays(nextYearFirstDate, 1);
	}
	/**
	 * 功能：当前时间对应的下一年的最后一天
	 * @param currDate
	 * @return
	 */
	public static Date getNextYearEnd(Date currDate) {
		Date nextYearFirstDate = getNextYearFirst(currDate);
		return getCurrentYearEnd(nextYearFirstDate);
	}
	
	/**
	 * 功能：获取当前时间对应的季度的第一天
	 * @param currDate
	 * @return
	 */
	public static Date getCurrentSeasonFirst(Date currDate) {
		int year = getYear(currDate);
		int month = getMonth(currDate);
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		return stringToDate(year + "-" + start_month + "-01 00:00:00", dateTimeFormat);
	}
	
	/**
	 * 功能：获取当前时间对应的下季度的第一天
	 * @param currDate
	 * @return
	 */
	public static Date getNextSeasonFirst(Date currDate) {
		Date currSeasonEnd = getCurrentSeasonEnd(currDate);
		Date nextSeasonFirst = afterDays(currSeasonEnd, 1);
		return nextSeasonFirst;
	}
	
	/**
	 * 功能：获取当前时间对应的下季度的最后一天
	 * @param currDate
	 * @return
	 */
	public static Date getNextSeasonEnd(Date currDate) {
		Date currSeasonEnd = getCurrentSeasonEnd(currDate);
		Date nextSeasonFirst = afterDays(currSeasonEnd, 1);
		return getCurrentSeasonEnd(nextSeasonFirst);
	}
	
	/**
	 * 功能：获取当前时间对应的季度的最后一天
	 * @param currDate
	 * @return
	 */
	public static Date getCurrentSeasonEnd(Date currDate) {
		int year = getYear(currDate);
		int month = getMonth(currDate);
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int end_month = array[season - 1][2];
		int end_days = getLastDayOfMonth(year, end_month);
		return stringToDate(year + "-" + end_month + "-" +end_days + " 00:00:00", dateTimeFormat);
	}
	
	public static String getCurrentSeason(Date currDate) {
		int month = getMonth(currDate);
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		return String.valueOf(season);

	}
	
	/**
	 * 功能：返回当前日期是星期几 1-星期天、2-星期一...7-星期六
	 * @param currdate
	 * @return
	 */
	public static int getDayOfWeek(Date currdate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	/**
	 * 功能：返回当前日期是星期几 1-星期天、2-星期一...7-星期六
	 * @param currdateString 时间格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static int getDayOfWeek(String currdateString) {
		Date currdate = stringToDate(currdateString, dateTimeFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	/**
	 * 功能：返回当前日期是星期几 1-星期天、2-星期一...7-星期六
	 * @param currdateLong
	 * @return
	 */
	public static int getDayOfWeek(Long currdateLong) {
		Date currdate = new Date(currdateLong);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currdate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	
	/**
	 * 功能：当前时间减一秒
	 * @param date
	 * @return
	 */
	public static Date subOneSecond(Date date){
		Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}

	public static Date addOneSecond(Date endDate) {
		Calendar calendar = Calendar.getInstance();
    	calendar.setTime(endDate);
    	calendar.add(Calendar.SECOND, 1);
		return calendar.getTime();
	}
	

	
	
}
