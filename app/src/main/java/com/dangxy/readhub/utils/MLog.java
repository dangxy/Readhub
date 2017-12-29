package com.dangxy.readhub.utils;

import android.os.Environment;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by author_dang on 16/8/15.
 */
public class MLog {

    private static final String TAG_ = "Shape";

    static {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    enum LogMode {
        NORMAL, // As usual.
        NONE, // Don't show any log at all.
        ALL // Always show logs.
    }

    public static LogMode logMode = LogMode.NORMAL;

    public static void v(String tag, String message) {
        if (interceptLog(tag, message)) {
            return;
        }
        printLog(Log.VERBOSE, tag, message);
    }

    public static void i(String tag, String message) {
        if (interceptLog(tag, message)) {
            return;
        }
        printLog(Log.INFO, tag, message);
    }

    public static void d(String message) {
        d("", message);
    }

    public static void d(String tag, String message) {
        if (interceptLog(tag, message)) {
            return;
        }
        printLog(Log.DEBUG, tag, message);
    }

    public static void w(String message) {
        w("", message);
    }

    public static void w(String tag, String message) {
        if (interceptLog(tag, message)) {
            return;
        }
        printLog(Log.WARN, tag, message);
    }

    public static void e(String tag, String message) {
        if (interceptLog(tag, message)) {
            return;
        }
        printLog(Log.ERROR, tag, message);
    }

    private static boolean interceptLog(String tag, String message) {
        boolean isIntercepted = false;
        switch (logMode) {
            case NORMAL:
                // isIntercepted = false;
                break;
            case ALL:
                // Set priority WARN to show logs always even in release mode.
                printLog(Log.WARN, tag, message);
            case NONE:
                isIntercepted = true;
                break;
            default:
                break;
        }
        return isIntercepted;
    }

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    private static void printLog(int priority, String tag, String message) {
        try {
            switch (priority) {
                case Log.VERBOSE:
                    Logger.t(tag).v(message);
                    break;
                case Log.INFO:
                    Logger.t(tag).i(message);
                    break;
                case Log.DEBUG:
                    if (isJSONValid(message)) {
                        Logger.t(tag).json(message);
                    } else {
                        Logger.t(tag).d(message);
                    }
                    break;
                case Log.WARN:
                    Logger.t(tag).w(message);
                    break;
                case Log.ERROR:
                    Logger.t(tag).e(message);
                    break;
                default:
                    Logger.t(tag).d(message);
                    break;
            }
            return;
        } catch (Exception exception) {
            exception.printStackTrace();
            Logger.t(tag).w(exception.toString());
        }
    }


    /**
     * 将log信息写入文件中
     *
     * @param type
     * @param tag
     * @param msg
     */
    public static void writeToFile(char type, String tag, String msg) {
        String logPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "ChenZao";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);//日期格式;
        Date date = new Date();//因为log日志是使用日期命名的，使用静态成员变量主要是为了在整个程序运行期间只存在一个.log文件中;
        if (null == logPath) {
            return;
        }

        String fileName = logPath + File.separator + "log_" + dateFormat.format(new Date()) + ".log";//log日志名，使用时间命名，保证不重复
        String log = dateFormat.format(date) + " " + type + " " + tag + " " + msg + "\n";//log日志内容，可以自行定制

        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }

        FileOutputStream fos = null;//FileOutputStream会自动调用底层的close()方法，不用关闭
        BufferedWriter bw = null;
        try {

            fos = new FileOutputStream(fileName, true);//这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(log);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
