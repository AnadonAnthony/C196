package com.aanadon.android.anadonc196.utilities;

import android.util.Log;

import com.aanadon.android.anadonc196.models.TermEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Samples {
    public static List<TermEntity> getSampleTerms() {
        Log.i(Constants.LOG_TAG, "Creating Sample Term Data (Samples Class)");
        List<TermEntity> TermList   = new ArrayList<>();

        Calendar Today  = Calendar.getInstance();
        Calendar Start  = Calendar.getInstance();
        Start.add(Calendar.MONTH, -12);
        Start.set(Calendar.DATE, 1);

        for (int Id = 0; Id < 8; Id++) {
            Log.i(Constants.LOG_TAG, "Creating Sample Term : " + Id);
            TermEntity Term = new TermEntity();

            Term.setTermId(Id);
            Term.setTermStart(Start.getTime());
            Term.setCreateDate(Today.getTime());
            Term.setTermTitle(String.format("%1$s Term",
                new SimpleDateFormat("yyyy-MM").format(Start.getTime())));

            Log.i(Constants.LOG_TAG, "→\t" + Term.toString());

            TermList.add(Term);

            Start.add(Calendar.MONTH, 6);
        }

        return TermList;
    }
}
