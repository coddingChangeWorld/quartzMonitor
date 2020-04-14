package com.common.tool;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.SimpleTimeZone;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;

public class DateUtils {
	public static String sTimePatternUnsigned = "yyyyMMddHHmmssS";
	public static String sADefaultDatePattern = "yyyy-MM-dd";
	public static String sADefaultTimePattern = "HH:mm:ss";
	public static String sADefaultDateTimePattern = "yyyy-MM-dd HH:mm:ss";
	public static String sADefaultDateTimePattern_2 = "yyyy-MM-dd|HH:mm:ss";
	public static String sADefaultDateTimePattern_1 = "yyyy-MM-dd HH:mm";
	public static DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
	public static SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String Y_M_D = "yyyy-MM-dd";
	public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
	public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMD = "yyyyMMdd";
	public static final String YMDHM = "yyyyMMddHHmm";
	public static final String YMDHMS = "yyyyMMddHHmmss";
	public static final String ymd = "yyyy/MM/dd";
	public static final String ymd_HM = "yyyy/MM/dd HH:mm";
	public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";

	public static String addCronInterval(String selType, String val, String cron) {
		String[] cronUnit = cron.split(" ");
		StringBuffer expression = new StringBuffer();
		if (val.equals("0")) {
			return cron;
		}
		if (StringUtils.equals(selType, "second")) {
			expression.append(cronUnit[0]).append("/").append(val);
			cronUnit[0] = expression.toString();
		} else if (StringUtils.equals(selType, "minute")) {
			expression.append(cronUnit[1]).append("/").append(val);
			cronUnit[0] = "0";
			cronUnit[1] = expression.toString();
		}
		return StringUtils.join(cronUnit, " ");
	}
	
	/**
	 * 
	 * @Title: getNowCronInterval
	 * @Description: TODO(获取当前时间的cron表达式)
	 * @author: Administrator
	 * @date: 2018-5-9
	 * @param selType
	 * @param val
	 * @return
	 */
	public static String getNowCronInterval(String selType, String val) {
		String cron=getCron(new Date());
		String[] cronUnit = cron.split(" ");
		StringBuffer expression = new StringBuffer();
		if (val.equals("0")) {
			return cron;
		}
		if (StringUtils.equals(selType, "second")) {
			expression.append(cronUnit[0]).append("/").append(val);
			cronUnit[0] = expression.toString();
		} else if (StringUtils.equals(selType, "minute")) {
			expression.append(cronUnit[1]).append("/").append(val);
			cronUnit[0] = "0";
			cronUnit[1] = expression.toString();
		}
		return StringUtils.join(cronUnit, " ");
	}

	public static String formatDateByPattern(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	public static String getCron(Date date) {
		String dateFormat = "ss mm HH dd MM ? yyyy";
		return formatDateByPattern(date, dateFormat);
	}

	public static String getSubTwoTime(String endTime, String startTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d1.getTime() - d2.getTime();
			long days = diff / 86400000L;

			long hours = (diff - days * 86400000L) / 3600000L;
			long minutes = (diff - days * 86400000L - hours * 3600000L) / 60000L;
			if (hours < 0L) {
				hours = new BigDecimal(hours).abs().intValue();
			}
			if (minutes < 0L) {
				minutes = new BigDecimal(minutes).abs().intValue();
			}
			return days + "-" + hours + "-" + minutes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getSubTwoTimeYY(String endTime, String startTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d1.getTime() - d2.getTime();
			return Long.toString(diff / 86400000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getSubTwoTime1(String endTime, String startTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d2.getTime() - d1.getTime();
			long days = diff / 86400000L;

			long hours = (diff - days * 86400000L) / 3600000L;
			long minutes = (diff - days * 86400000L - hours * 3600000L) / 60000L;
			if (hours < 0L) {
				hours = new BigDecimal(hours).abs().intValue();
			}
			if (minutes < 0L) {
				minutes = new BigDecimal(minutes).abs().intValue();
			}
			return days + "-" + hours + "-" + minutes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getUnixTimeStamp(String dateTime) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dataTimeFormat.parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Long.toString(c.getTimeInMillis() / 1000);
	}

	public static String gerUnixTime2String(String timestampString, String formats) {
		if ((StringUtils.isBlank(timestampString)) || ("null".equals(timestampString))) {
			return "";
		}
		Long timestamp = Long.valueOf(Long.parseLong(timestampString) * 1000L);
		String date = new SimpleDateFormat(formats).format(new Date(timestamp.longValue()));
		return date;
	}

	public static String getCurrentUnixTimeStamp() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return Long.toString(c.getTimeInMillis() / 1000);
	}

	public static String formatDateTime(Date date) {
		return dataTimeFormat.format(date);
	}

	private static SimpleDateFormat newLongYMDFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	private static SimpleDateFormat newLongYMDHMSFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public static String toLongYMDHMS(String shortYMDHMS) throws ParseException {
		return newLongYMDHMSFormat().format(newShortYMDHMSFormat().parse(shortYMDHMS));
	}

	public static String getLongYMD() {
		return newLongYMDFormat().format(new Date());
	}

	public static String getLongYMDChina() {
		String str = newLongYMDFormat().format(new Date());
		return str.split("-")[0] + "年" + str.split("-")[1] + "月" + str.split("-")[2] + "日";
	}

	private static SimpleDateFormat newShortYMDFormat() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	private static SimpleDateFormat newShortYMDHMSFormat() {
		return new SimpleDateFormat("yyyyMMddHHmmss");
	}

	public static String getShortYMDHMS() {
		return newShortYMDHMSFormat().format(new Date());
	}

	public static String toLongYMD(String shortYMD) {
		try {
			return newLongYMDFormat().format(newShortYMDFormat().parse(shortYMD));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getDbDate() {
		return dateFormatDB.format(new Date());
	}

	public static String convertClientDateToDbDate(String dateStr) {
		String dbDateStr = null;
		try {
			dbDateStr = dateFormatDB.format(dateFormat.parse(dateStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbDateStr;
	}

	public static Date parseDate(String dateStr) {
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDate(String timestampString) {
		Long timestamp = Long.valueOf(Long.parseLong(timestampString) * 1000L);
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp.longValue()));
		return date;
	}

	public static String getDateStrFormat(String dateStr) {
		try {
			Date date = dateFormat.parse(dateStr);
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateStrFormat2(String dateStr) {
		try {
			Date date = dateFormat2.parse(dateStr);
			return dateFormat2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateStrFormatyyyyMMdd(String dateStr) {
		try {
			Date date = dateFormat.parse(dateStr);
			return dateFormatDB.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDateTime(String dateTimeStr) {
		Date date = null;
		try {
			date = dataTimeFormat.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static int calcDays(String startDate, String endDate) {
		int days = 1;
		try {
			long start = dateFormat.parse(startDate).getTime();
			long end = dateFormat.parse(endDate).getTime();
			days = (int) ((end - start) / 86400000L);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	public static int calcDay(String startDate, String endDate) {
		int days = 1;
		try {
			long start = dateFormatDB.parse(startDate).getTime();
			long end = dateFormatDB.parse(endDate).getTime();
			days = (int) ((end - start) / 86400000L);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + day * 24L * 3600L * 1000L);
		return c.getTime();
	}

	public static Date addMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + minute * 60L * 1000L);
		return c.getTime();
	}

	public static Date addSecond(Date date, int second) {
		long s = date.getTime();
		s += second * 1000;
		return new Date(s);
	}

	public static Date diffDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - day * 24L * 3600L * 1000L);
		return c.getTime();
	}

	public static Date getDateByMinuteAdd(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(12, minute);
		return calendar.getTime();
	}

	public static int diffDate(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		long s = (endMillis - startMillis) / 86400000L;
		return (int) s;
	}

	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date == null) {
				date = new Date();
			}
			if (StringUtil.isBlank(format)) {
				format = "yyyy-MM-dd";
			}
			DateFormat df = new SimpleDateFormat(format);
			result = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: format
	 * @Description: TODO(格式化Timestamp类型的时间)
	 * @author: linjie
	 * @date: 2018-9-3
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Timestamp date, String format) {
		String result = "";
        DateFormat sdf = new SimpleDateFormat(format);
        result = sdf.format(date); 
        return result;
	}

	public static Date format(String dateStr, String format) {
		if (StringUtil.isBlank(dateStr)) {
			return new Date();
		}
		if (StringUtil.isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		Date date = null;
		try {
			DateFormat f = new SimpleDateFormat(format);
			date = f.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String format(String dateStr, String format, String toFormat) {
		return format(format(dateStr, format), toFormat);
	}

	public static String formatRssDate(Date date) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		SimpleTimeZone zone = new SimpleTimeZone(8, "GMT");
		sdf.setTimeZone(zone);
		return sdf.format(date);
	}

	public static int getYear(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(1);
	}

	public static int getMonth(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(2) + 1;
	}

	public static int getDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(5);
	}

	public static int getHour(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(11);
	}

	public static int getMinute(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(12);
	}

	public static int getChinaWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(7) - 1;
		if (week == 0) {
			return 7;
		}
		return week;
	}

	public static int getSecond2(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(13);
	}

	public static long getMillis(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	public static Date getMonFirstDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date) - 1, 1);
		return c.getTime();
	}

	public static Date getMonLastDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date), 1);
		c.setTimeInMillis(c.getTimeInMillis() - 86400000L);
		return c.getTime();
	}

	public static Date getMonUpDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date) - 1, 1);
		c.setTimeInMillis(c.getTimeInMillis() - 86400000L);
		return c.getTime();
	}

	public static String getCurrMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		String s = getYear(cal) + "-" + getMonth(cal) + "-01";
		return s;
	}

	public static String getCurrMonth() {
		Calendar cal = Calendar.getInstance();
		String s = getYear(cal) + "-" + getMonth(cal);
		return getDateStrFormat2(s);
	}

	public static String getCurrMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		String s = getYear(cal) + "-" + getMonth(cal) + "-" + getDays(cal);
		return s;
	}

	public static int getDays(Calendar cal) {
		return cal.getActualMaximum(5);
	}

	public static int getYear(Calendar cal) {
		return cal.get(1);
	}

	public static int getMonth(Calendar cal) {
		return cal.get(2) + 1;
	}

	public static int getYear(String date_str, String type) {
		return convertStrToCal(date_str, type).get(1);
	}

	public static Calendar convertDateToCal(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Calendar convertStrToCal(String date_str, String type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStrToDate(date_str, type));
		return cal;
	}

	public static Date convertStrToDate(String date_str, String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		try {
			return dateformat.parse(date_str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrentDateTime() {
		Date date = new Date();
		return dataTimeFormat.format(date);
	}

	public static String getCurrentDate() {
		Date date = new Date();
		return dateFormat.format(date);
	}

	private static SimpleDateFormat newShortYMFormat() {
		return new SimpleDateFormat("yyyyMM");
	}

	public static String getShortYMDiffMonth(String month, int diffMonth) {
		SimpleDateFormat sdf = newShortYMFormat();
		try {
			sdf.parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(2, diffMonth);
		return sdf.format(c.getTime());
	}

	public static String getShortYMDDiffDay(String shortYMD, int diffDay) {
		SimpleDateFormat sdf = newShortYMDFormat();
		try {
			sdf.parse(shortYMD);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(5, diffDay);
		return sdf.format(c.getTime());
	}

	public static String getAddDay(int diffDay) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		Calendar c = Calendar.getInstance();
		c.add(5, diffDay);
		return sf.format(c.getTime());
	}

	public static String getEndDayOfMonth(String shortYM) {
		String month = "";
		try {
			month = getShortYMDiffMonth(shortYM, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getShortYMDDiffDay(month + "01", -1);
	}

	public static String getShortYMD() {
		return newShortYMDFormat().format(new Date());
	}

	/**
	 * 
	 * @param startShortYMD
	 * @param endShortYMD
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getShortYMDList(String startShortYMD, String endShortYMD) throws ParseException {
		SimpleDateFormat startDateFormat = newShortYMDFormat();
		startDateFormat.parse(startShortYMD);
		Calendar startCal = startDateFormat.getCalendar();

		SimpleDateFormat endDateFormat = newShortYMDFormat();
		endDateFormat.parse(endShortYMD);
		Calendar endCal = endDateFormat.getCalendar();

		List<String> dateList = new ArrayList<String>();
		while (startCal.before(endCal)) {
			dateList.add(startDateFormat.format(startCal.getTime()));
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(startDateFormat.format(endCal.getTime()));
		}
		return dateList;
	}

	public static List<String> getShortYMList(String startShortYM, String endShortYM) throws ParseException {
		SimpleDateFormat startDateFormat = newShortYMFormat();
		startDateFormat.parse(startShortYM);
		Calendar startCal = startDateFormat.getCalendar();

		SimpleDateFormat endDateFormat = newShortYMFormat();
		endDateFormat.parse(endShortYM);
		Calendar endCal = endDateFormat.getCalendar();

		List<String> dateList = new ArrayList<String>();
		while (startCal.before(endCal)) {
			dateList.add(startDateFormat.format(startCal.getTime()));
			startCal.add(Calendar.MONTH, 1);
		}
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(startDateFormat.format(endCal.getTime()));
		}
		return dateList;
	}

	public static boolean checkExpDate(String expDate) {
		int year = Integer.parseInt(expDate.substring(0, 4));
		int month = Integer.parseInt(expDate.substring(4, 6));
		int day = Integer.parseInt(expDate.substring(6, 8));
		if ((month > 12) || (month < 1)) {
			return false;
		}
		int[] monthLengths = { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (isLeapYear(year)) {
			monthLengths[2] = 29;
		} else {
			monthLengths[2] = 28;
		}
		int monthLength = monthLengths[month];
		if ((day < 1) || (day > monthLength)) {
			return false;
		}
		return true;
	}

	private static boolean isLeapYear(int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	public static int compareEndAndStart(String start, String end) throws Exception {
		long s = 0L;
		if (8 == start.length()) {
			s = dateFormatDB.parse(start).getTime();
		} else if (10 == start.length()) {
			s = dateFormat.parse(start).getTime();
		} else {
			s = dataTimeFormat.parse(start).getTime();
		}
		long e = 0L;
		if (8 == end.length()) {
			e = dateFormatDB.parse(end).getTime();
		} else if (10 == end.length()) {
			e = dateFormat.parse(end).getTime();
		} else {
			e = dataTimeFormat.parse(end).getTime();
		}
		if (e > s) {
			return 1;
		}
		if (e < s) {
			return -1;
		}
		return 0;
	}

	public static Date str2Date(String date) {
		Date ret = null;
		if (date.length() == 10) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 16) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 19) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 13) {
			try {
				ret = new SimpleDateFormat("yyyy-MM-dd HH").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date.length() == 7) {
			try {
				ret = new SimpleDateFormat("yyyy-MM").parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static String getRandomNum() {
		Random r = new Random();
		int a = r.nextInt(99999999);
		String cardNum = Integer.toString(a);
		int length = cardNum.length();
		if (length < 8) {
			for (int j = 0; j < 8 - length; j++) {
				cardNum = "0" + cardNum;
			}
		}
		return cardNum;
	}

	public static Date smartFormat(String text) {
		Date date = null;
		try {
			if ((text == null) || (text.length() == 0)) {
				date = null;
			} else if (text.length() == 10) {
				date = formatStringToDate(text, "yyyy-MM-dd");
			} else if (text.length() == 13) {
				date = new Date(Long.parseLong(text));
			} else if (text.length() == 14) {
				date = formatStringToDate(text, "yyyyMMddHHmmss");
			} else if (text.length() == 16) {
				date = formatStringToDate(text, "yyyy-MM-dd HH:mm");
			} else if (text.length() == 19) {
				date = formatStringToDate(text, "yyyy-MM-dd HH:mm:ss");
			} else {
				throw new IllegalArgumentException("日期长度不符合要求!");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("日期转换失败!");
		}
		return date;
	}

	public static Date formatStringToDate(String argDateStr, String argFormat) throws Exception {
		if ((argDateStr == null) || (argDateStr.trim().length() < 1)) {
			throw new Exception("参数[日期]不能为空!");
		}
		String strFormat = argFormat;
		if (StringUtils.isEmpty(strFormat)) {
			strFormat = "yyyy-MM-dd";
			if (argDateStr.length() > 16) {
				strFormat = "yyyy-MM-dd HH:mm:ss";
			} else if (argDateStr.length() > 10) {
				strFormat = "yyyy-MM-dd HH:mm";
			}
		}
		SimpleDateFormat sdfFormat = new SimpleDateFormat(strFormat);

		sdfFormat.setLenient(false);
		try {
			return sdfFormat.parse(argDateStr);
		} catch (ParseException e) {
			throw new Exception(e);
		}
	}

	public static String getSSTimeStamp() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
		String strddd = sdf.format(d);
		return strddd;
	}

	public static String getWeekOfDate() {
		String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };

		Calendar cal = Calendar.getInstance();

		int w = cal.get(7) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

	public static int getEarlyMidLate(Date date) {
		int day = getDay(date);
		int earlyMidLate = 0;
		if ((1 <= day) && (day <= 10)) {
			earlyMidLate = 1;
		}
		if ((11 <= day) && (day <= 20)) {
			earlyMidLate = 2;
		}
		if (20 < day) {
			earlyMidLate = 3;
		}
		return earlyMidLate;
	}

	public static int dateToJulian(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(1) % 100;
		int dayOfYear = calendar.get(6);
		return year * 1000 + dayOfYear;
	}

	public static Date JulianToDate(int date) {
		int year = date / 1000 + 2000;
		int dayOfYear = date % 1000;
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, year);
		calendar.set(6, dayOfYear);
		return calendar.getTime();
	}

	public static Date currentMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(2, 0);
		calendar.set(5, 1);
		return calendar.getTime();
	}

	public static Date currentMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(2, 0);
		calendar.set(5, calendar.getActualMaximum(5));
		return calendar.getTime();
	}

	public static int statisSubDay(Date endDate, Date startDate) {
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(11, 0);
		fromCalendar.set(12, 0);
		fromCalendar.set(13, 0);
		fromCalendar.set(14, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(11, 0);
		toCalendar.set(12, 0);
		toCalendar.set(13, 0);
		toCalendar.set(14, 0);
		Long tempString = Long.valueOf((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / 86400000L);
		return Integer.valueOf(tempString.toString()).intValue();
	}

	public static String getSubTwoDate(String strDate, int a) {
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar dar = Calendar.getInstance();
			dar.setTime(dft.parse(strDate));
			dar.add(11, -a);
			return dft.format(dar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static boolean getCurrentDate(String strDate, Date date) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar dar = Calendar.getInstance();
		try {
			dar.setTime(dft.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dft.format(dar.getTime()).equals(dft.format(date));
	}

	public static Date addDate1(Date d, long day) throws ParseException {
		long time = d.getTime();
		day = day * 24L * 60L * 60L * 1000L;
		time += day;
		return new Date(time);
	}

	public static Date getLastMonthDay() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(2);
		calendar.set(2, month - 1);
		calendar.set(5, calendar.getActualMaximum(5));
		Date strDateTo = calendar.getTime();
		return strDateTo;
	}

	public static boolean isSameMonth(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameMonth(cal1, cal2);
	}

	public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
		if ((cal1 == null) || (cal2 == null)) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(0) == cal2.get(0)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(2) == cal2.get(2));
	}
}
