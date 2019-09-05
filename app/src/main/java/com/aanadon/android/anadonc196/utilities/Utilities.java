package com.aanadon.android.anadonc196.utilities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {


    //  <editor-fold default-state="collapsed" desc="Date String Parsing">
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
    //  </editor-fold>

    //  <editor-fold default-state="collapsed" desc="Toast Messaging">
    public static void BurnToast(Fragment pFrag, String pText)  {
        Toast Toaster   = Toast.makeText(pFrag.getContext(),
            pText,
            Toast.LENGTH_LONG);

        ((View)Toaster.getView()).getBackground().setColorFilter(Color.RED,
            PorterDuff.Mode.SRC_IN);

        Toaster.show();
    }

    public static void ButterToast(Fragment pFrag, String pText)    {
        Toast Toaster   = Toast.makeText(pFrag.getContext(),
                pText,
                Toast.LENGTH_SHORT);

        ((View)Toaster.getView()).getBackground().setColorFilter(Color.GREEN,
                PorterDuff.Mode.SRC_IN);

        Toaster.show();
    }
    //  </editor-fold>

    //  <editor-fold default-state="collapsed" desc="Text Colorizing Methods">
    public static void ColorLabel(TextView pLabel, boolean pTextOK) {
        if (null != pLabel) {
            if (pTextOK)
                pLabel.setTextColor(Color.GRAY);
            else
                pLabel.setTextColor(Color.RED);
        }
    }
    //  </editor-fold>
}
