package com.aanadon.android.anadonc196.utilities;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter_Date {

    @TypeConverter
    public static Date toDate(Long pTimestamp)  {
        return null == pTimestamp ? null : new Date(pTimestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date pDate)  {
        return null == pDate ? null : pDate.getTime();
    }
}
