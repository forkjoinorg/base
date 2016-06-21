package org.forkjoin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 *
 */
public class DateTimeUtils {
	// public static final String FORMAT_ZONE = "yyyy-MM-dd HH:mm:ss Z";

	public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMAT = "yyyy-MM-dd";
	private final static int[] DAY_ARR = new int[]{20, 19, 21, 20, 21, 22, 23,
			23, 23, 24, 23, 22};
	private final static String[] CONSTELLATION_ARR = new String[]{"摩羯座",
			"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
			"天蝎座", "射手座", "摩羯座"};

	public static String format(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		dateFormat.setLenient(false);
		return dateFormat.format(date);
	}

	public static Date parse(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		dateFormat.setLenient(false);
		return dateFormat.parse(date);
	}

	// public static void main(String[] args) {
	// SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_ZONE);
	// dateFormat.setLenient(false);
	// System.out.println(dateFormat.format(new Date()));
	// }

	public static String getConstellation(Date date) {
		LocalDateTime dateTime = LocalDateTime.fromDateFields(date);
		return getConstellation(dateTime.getMonthOfYear(),
				dateTime.getDayOfMonth());
		// Instant instant = date.to
		// instant.
	}

	public static String getConstellation(int month, int day) {
		return day < DAY_ARR[month - 1]
				? CONSTELLATION_ARR[month - 1]
				: CONSTELLATION_ARR[month];
	}

	/**
	 * 周岁年龄 周岁—出生时为0岁，每过一个公历生日长1岁
	 *
	 * @param birthday
	 *            生日
	 * @return 周岁年龄
	 */
	public static int getAge(Date birthday) {
		LocalDate today = LocalDate.now();
		LocalDateTime birthDate = LocalDateTime.fromDateFields(birthday);
		int age = today.getYear() - birthDate.getYear();
		if (today.getDayOfYear() < birthDate.getDayOfYear()) {
			--age;
		}
		return age;
	}

	/**
	 * 返回随机在大约1年附近的日期
	 * 
	 * @param age
	 * @return
	 */
	public static Date random(int age) {
		LocalDate today = LocalDate.now();
		today = today.minusYears(age).minusDays(
				RandomUtils.nextInt(365) - 365 / 2);
		return today.toDate();
	}

	/**
	 * 随机时间
	 */
	public static Date random(Date startDate, Date endDate) {
		long startDateTime = startDate.getTime();
		long endDateTime = endDate.getTime();
		if (startDateTime == endDateTime) {
			return startDate;
		} else {
			return new Date(
					RandomUtils.nextLong(startDateTime, endDateTime + 1));
		}
	}

	/**
	 * 时间字符串转换为Date类型
	 *
	 * @param aMask
	 *            格式
	 * @param strDate
	 *            时间字符串
	 * @return Date
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		date = df.parse(strDate);
		return (date);
	}

	/**
	 * 时间格式转换为字符串格式
	 *
	 * @param aDate
	 *            日期类型
	 * @param aMask
	 *            格式
	 * @return String
	 */
	public static final String convertDateToString(Date aDate, String aMask) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate == null) {
			return null;
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	public static int getDays(Date beginTime, Date endTime) {
		long start = beginTime.getTime();
		// 需要计算运行天数时，计算差值
		long end = endTime.getTime();
		long days = (end - start) / (1000 * 60 * 60 * 24);
		// 运行天数从1开始计数
		long runningDays = days + 1;
		// 判断是否跨天，若跨天，运行天数还要+1
		long probableEndMillis = start + (1000 * 60 * 60 * 24) * days;
		if (new Date(probableEndMillis).getDay() != new Date(end).getDay()) {
			runningDays++;
		}
		return (int) runningDays;
	}
}
