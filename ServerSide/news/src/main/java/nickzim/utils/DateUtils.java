package nickzim.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

import static nickzim.utils.StringHandleUtils.handleString;

public final class DateUtils {

    public static LocalDateTime getNewsPubDate(String dateString){
        DateTimeFormatter formatter;
        Pattern datePattern;

        datePattern = Pattern.compile("\\w{3},\\s?[0-3][0-9]\\s?\\w{3}\\s?\\d{4}\\s?[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\s?\\w{3}");
        if (datePattern.matcher(dateString).find()){
            formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss O", Locale.ENGLISH);
            return LocalDateTime.parse(handleString(dateString), formatter);
        }

        datePattern = Pattern.compile("\\d?\\s?\\w{3}\\s?\\d{4}\\s?[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\s?\\w{3}");
        if (datePattern.matcher(dateString).find()){
            formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss O", Locale.ENGLISH);
            return LocalDateTime.parse(handleString(dateString), formatter);
        }

        datePattern = Pattern.compile("\\d?\\d?\\s?\\w{3}\\s?\\d{4}\\s?[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\s?\\w{3}");
        if (datePattern.matcher(dateString).find()){
            formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss O", Locale.ENGLISH);
            return LocalDateTime.parse(handleString(dateString), formatter);
        }

        datePattern = Pattern.compile("\\w{3},\\s?\\d{2}\\s?\\w{3}\\s?\\d{4}\\s?[0-2][0-9]:[0-5][0-9]:[0-5][0-9]\\s?\\+\\d{4}");
        if (datePattern.matcher(dateString).find()){
            formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            return LocalDateTime.parse(handleString(dateString), formatter);
        }

        else return LocalDateTime.now();

    }
}
