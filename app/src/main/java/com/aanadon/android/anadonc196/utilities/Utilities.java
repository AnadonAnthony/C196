package com.aanadon.android.anadonc196.utilities;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {


    private static SimpleDateFormat _DateFormat;

    public static String toString(Date pDate)  {
        if (null == pDate)
            pDate   = new Date();

        if (null == _DateFormat)
            _DateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return _DateFormat.format(pDate);
    }

    public static Date parseDate(String pDateText)  {
        if (null == _DateFormat)
            _DateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date Result;
        try {
            Result  = _DateFormat.parse(pDateText);
        }
        catch (Exception pEx)   {
            Result  = new Date();
        }

        return Result;
    }
}
