package com.kamilabrito.meuaptchallenge.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static String formateDate(String createdAt) throws ParseException {
        SimpleDateFormat rawDateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date rawFormatedDate = rawDateFormater.parse(createdAt);
            rawDateFormater.setTimeZone(TimeZone.getDefault());
            rawDateFormater = new SimpleDateFormat("MMM dd yyyy");
            String formatedDate = rawDateFormater.format(rawFormatedDate);
            return formatedDate;
        } catch (ParseException e) {
            throw new ParseException("The current date format is invalid", e.getErrorOffset());
        }
    }
}
