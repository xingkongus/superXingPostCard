package us.xingkong.xingpostcard.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Garfield on 5/1/16.
 */
public class Ruler {


    public static int getWindowWidth(Context context) {

        /**
         * 获取手机屏幕的宽度像素
         */
        DisplayMetrics dm = context.getApplicationContext()
                .getApplicationContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getWindowHeight(Context context) {

        /**
         * 获取手机屏幕的高度像素
         */
        DisplayMetrics dm = context.getApplicationContext()
                .getApplicationContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    public static int dip2px(Context context, float dpValue) {

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int px2dip(Context context, float pxValue) {

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



}
