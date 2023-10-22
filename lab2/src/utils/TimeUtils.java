package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeUtils {
  public static String formatTime(long time) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(new Date(time));
  }
}
