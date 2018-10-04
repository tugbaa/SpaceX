package com.tugba.cevizci.spacex.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    public static String convertToDate(String dt){

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.getDefault());
        Date date = new Date();

        String displayDate;
        SimpleDateFormat newDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        try {
            date = dateFormatter.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        displayDate = newDateFormatter.format(date);
        return displayDate;
    }

}
