package com.shmily.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具
 *@author huangguogang
 *@date 2016-12-23
 */

public class DateUtils {

  /**
   * 日期格式
  */
  public static final String PAT_DATE = "yyyy-MM-dd";
  /**
   * 完整日期格式
  */
  public static final String PAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
  /**
   * 日期格式（时分）
  */
  public static final String PAT_DATE_SIMTIME = "yyyy-MM-dd HH:mm";
  /**
   * 字符串日期格式
  */
  public static final String SIMPLE_DATE = "yyyyMMdd";
  /**
   * 时间格式
  */
  public static final String PAT_TIME = "HH:mm:ss";
  /**
   * 字符串时间格式
  */
  public static final String SIMPLE_TIME = "HHmmss";
  /**
   * 完整格式
  */
  public static final String SIMPLE = "yyyyMMddHHmmss";

  /**
   * 返回日期格式
  */
  public static String getDate( Date date ) {
    return format( date, PAT_DATE );
  }

  public static String getDateTime( Date date ) {
    return format( date, PAT_DATE_TIME );
  }

  /**
   * 返回完整格式
  */
  public static String getDateFormatPatter( Date date ) {
    return format( date, SIMPLE );
  }

  /**
   * 将字符串格式转换为日期格式
  */
  public static Date strToDate( String strDate ) throws ParseException {
    return parse( strDate, PAT_DATE );
  }

  /**
   * 将字符串格式转换为完整日期格式日期格式
  */
  public static Date strToPatDate( String strDate ) throws ParseException {
    return parse( strDate, PAT_DATE_TIME );
  }

  /**
   * 将字符串格式转换为时间格式
  */
  public static Date strToTime( String strTime ) throws ParseException {
    return parse( strTime, PAT_TIME );
  }

  /**
   * 将字符串格式转换为日期全格式
  */
  public static Date strToDateTime( String strDate ) throws ParseException {
    return parse( strDate, PAT_DATE_SIMTIME );
  }

  /**
   * 使用参数格式化date为字符串
  */
  public static String format( Date date, String patter ) {
    return null == date ? "" : new SimpleDateFormat( patter ).format( date );
  }

  /**
   * 严格按照patter参数格式检验数据
  */
  public static boolean checkDate( String obj, String patter ) {
    if (obj != null) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat( patter );
        sdf.setLenient( false );
        sdf.parse( obj );
      } catch ( ParseException e ) { return false; }
      return true;
    }
    return false;
  }
  /**
   * 将字符串转换为预设日期格式
  */
  public static Date parse( String strDate, String patter ) throws ParseException {
    return strDate == null ? null : new SimpleDateFormat( patter ).parse( strDate );
  }

  /**
   * 返回date加上days天数后的日期yyyy-MM-dd
  */
  public static Date plusDays( Date date, int days ) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime( date );
    calendar.add( Calendar.DATE, days );
    return calendar.getTime();
  }

  /**
   * 返回end到start的间隔天数
  */
  public static long getInvlDays( Date start, Date end ) {
    Calendar fromCalendar = Calendar.getInstance();
    fromCalendar.setTime( start );
    fromCalendar.set( Calendar.HOUR_OF_DAY, 0 );
    fromCalendar.set( Calendar.MINUTE, 0 );
    fromCalendar.set( Calendar.SECOND, 0 );
    fromCalendar.set( Calendar.MILLISECOND, 0 );
    Calendar toCalendar = Calendar.getInstance();
    toCalendar.setTime( end );
    toCalendar.set( Calendar.HOUR_OF_DAY, 0 );
    toCalendar.set( Calendar.MINUTE, 0 );
    toCalendar.set( Calendar.SECOND, 0 );
    toCalendar.set( Calendar.MILLISECOND, 0 );
    return ( toCalendar.getTime().getTime() - fromCalendar.getTime().getTime() ) / ( 1000 * 60 * 60 * 24 );
  }

  /**
   * 返回end到start的间隔秒数
  */
  public static long getInvlSeconds( Date start, Date end ) {
    return ( end.getTime() - start.getTime() ) / 1000;
  }

}