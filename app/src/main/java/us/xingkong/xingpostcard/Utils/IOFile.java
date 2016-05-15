package us.xingkong.xingpostcard.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 2016/5/14 15:07
 * OverWritten by Garfield
 * Date: 5/15/2016 04:01
 */
public class IOFile {
    public static String toSaveFile(Bitmap bitmap){
        File sdCardDir = Environment.getExternalStorageDirectory();
        String strPath = "/StarMark/starmark" + System.currentTimeMillis()
                + ".jpg";
        File file = new File(sdCardDir, strPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream os = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public static String toSaveFile(Bitmap bitmap,String dir){
        File sdCardDir = Environment.getExternalStorageDirectory();
        String strPath = "/StarMark/" + dir + System.currentTimeMillis()
                + ".jpg";
        File file = new File(sdCardDir, strPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream os = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
    public static void scanPhotos(String filePath, Context context) {
        Intent intent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }


    public static Bitmap getBitmapFromUri(Context context,Uri uri) {
        /**
         * 通过uri获取bitmap
         * */

        try
        {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            return bitmap;
        }
        catch (Exception e)
        {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }


}
