package com.achek.learnhttp.model;
import java.util.Calendar;

public class Utils {
    public  static String getDataTimeInMillis(long timeStamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);


        String mYear = Integer.toString(calendar.get(Calendar.YEAR));
        String mMonth = Integer.toString(calendar.get(Calendar.MONTH));
        String mDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String mHours = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        String mMinutes = Integer.toString(calendar.get(Calendar.MINUTE));

        if (mDay.length() < 2 ) mDay = "0" + mDay;
        if (mMonth.length() < 2 ) mMonth = "0" + mMonth;

        return mDay + "." + mMonth + "." + mYear + " " +  mHours + ":" + mMinutes;
    }
}
