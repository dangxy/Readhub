package com.dangxy.readhub.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/14
 */

public class ViewUtils {
    private static String TAG = "ViewUtils";
    private static float DEVICE_DENSITY = 0;
    private static int statusBarHeight;//状态栏的高度

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据某个密度从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue, float density) {
        return (int) (pxValue / density + 0.5f);
    }

    /**
     * 获取一个View的截图Bitmap
     */
    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(screenshot);
        c.translate(-v.getScrollX(), -v.getScrollY());
        v.draw(c);
        return screenshot;
    }

    public static int getStatusBarHeight(Context context) {
        if (statusBarHeight == 0) {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return statusBarHeight;
    }

    /**
     * 获得实际可以用的屏幕宽度
     * 如果有状态栏，则减去
     *
     * @return
     */
    public static int getScreenAvailabe(Activity act) {
        WindowManager.LayoutParams attrs = act.getWindow().getAttributes();
        if (attrs.flags == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            return getScreenHeight(act);
        } else {
            return getScreenHeight(act) - getStatusBarHeight(act);
        }

    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }



    /**
     * 虚拟按键的情况下获取物理分辨率
     *
     * @param context
     * @return 屏幕宽度
     */
    public static int getRealScreenSizeX(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point outSize = new Point();
            display.getRealSize(outSize);
            return outSize.x;
        } else {
            return getScreenWidth(context);
        }
    }

    /**
     * 虚拟按键的情况下获取物理分辨率
     *
     * @param context
     * @return 屏幕高度
     */
    public static int getRealScreenSizeY(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point outSize = new Point();
            display.getRealSize(outSize);
            return outSize.y;
        } else {
            return getScreenHeight(context);
        }

    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public  static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    private static final String SD_PATH = "/sdcard/dangxy/save/";
    private static final String IN_PATH = "/dangxy/save/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }
    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(savePath + generateFileName() + ".png");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        MLog.e("DANG","保存成功~"+filePic.getAbsolutePath());
        return filePic.getAbsolutePath();
    }

    private static final Canvas sCanvas = new Canvas();

    /**
     * 从一个view创建Bitmap:
     * 注意点：绘制之前要清掉 View 的焦点，因为焦点可能会改变一个 View 的 UI 状态
     * 来源：https://github.com/tyrantgit/ExplosionField
     *
     * @param view
     * @return
     */
    public static Bitmap createBitmapFromView(View view, float scale) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        view.clearFocus();
        Bitmap bitmap = createBitmapSafely((int) (view.getWidth() * scale),
                (int) (view.getHeight() * scale), Bitmap.Config.ARGB_8888, 1);
        if (bitmap != null) {
            synchronized (sCanvas) {
                Canvas canvas = sCanvas;
                canvas.setBitmap(bitmap);
                canvas.save();
                canvas.drawColor(Color.WHITE); // 防止 View 上面有些区域空白导致最终 Bitmap 上有些区域变黑
                canvas.scale(scale, scale);
                view.draw(canvas);
                canvas.restore();
                canvas.setBitmap(null);
            }
        }
        return bitmap;
    }

    public static Bitmap createBitmapFromView(View view) {
        return createBitmapFromView(view, 1f);
    }
    /**
     * 安全的创建bitmap;如果新建 Bitmap 时产生了 OOM，可以主动进行一次 GC - System.gc()，然后再次尝试创建
     *
     * @param width
     * @param height
     * @param config
     * @param retryCount
     * @return
     */
    public static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
        try {
            return Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (retryCount > 0) {
                System.gc();
                return createBitmapSafely(width, height, config, retryCount - 1);
            }
            return null;
        }
    }
    /**
     * 创建图片File对象
     *
     * @return
     */
    public static File createImageFile() {
        File imageFile = null;
        String storagePath;
        File storageDir;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        try {
            storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + "camerademo";
            //storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + "camerademo";
            //storagePath = App.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            //storagePath = App.getInstance().getExternalCacheDir().getAbsolutePath() + File.separator + "camerademo";
            //storagePath = App.getInstance().getFilesDir().getAbsolutePath() + File.separator + "images";
            storageDir = new File(storagePath);
            storageDir.mkdirs();
            imageFile = File.createTempFile(timeStamp, ".jpg", storageDir);
            Log.e(TAG, imageFile.getAbsolutePath());
        } catch (IOException e) {
            Log.e(TAG,"error"+e.getMessage());
            e.printStackTrace();
        }
        return imageFile;
    }



}
