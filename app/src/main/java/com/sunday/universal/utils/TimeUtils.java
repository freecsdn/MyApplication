package com.sunday.universal.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils{

    
    public static String getExpireTime(Long time) {
        
        StringBuffer sb = new StringBuffer();
        
        long mill = (long) Math.ceil(time /1000);//秒前

        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前

        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时

        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前

        long month = (long)Math.ceil(time/30/24/60/60/1000.0f);//月前
        if (day - 1 > 0) {
            sb.append(day-1 + "天");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour-1 + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute-1 + "分钟");
            }
        } else if (mill -1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            }
        } 
        return sb.toString();
    }
    
 	public static Date stringToDate(String strTime, String formatType){
 		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
 		Date date = null;
 		try {
			date = formatter.parse(strTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return date;
 	}
 	// string类型转换为long类型
 	// strTime要转换的String类型的时间
 	// formatType时间格式
 	// strTime的时间格式和formatType的时间格式必须相同
 	public static long stringToLong(String strTime, String formatType){
 		Date date = stringToDate(strTime, formatType); // String类型转成date类型
 		if (date == null) {
 			return 0;
 		} else {
 			long currentTime = dateToLong(date); // date类型转成long类型
 			return currentTime;
 		}
 	}
 
 	// date类型转换为long类型
 	// date要转换的date类型的时间
 	public static long dateToLong(Date date) {
 		return date.getTime();
 	}
	 	
	public static String getTime(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		return format.format(new Date(time));

	}

    public static void main(String args[]){
        System.out.println(TimeUtils.stringToLong("1969-1-1","yyMMdd"));
    }
}
