package us.xingkong.xingpostcard.Activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import us.xingkong.xingpostcard.R;
import us.xingkong.xingpostcard.Utils.IOFile;

/**
 * Created by Garfield on 5/15/16. 11:38PM
 * 用户编辑明信片页
 */
public class ArtActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView tv, tv2;
    private Button bt;
    private ScrollView sv;
    private LinearLayout ll;
    Intent intent;
    String clipedPhotoPath;

    private int styleCode = 0;
    private int ivHeight;
    private int ivWidth;
    private int areaHeight;
    private int areaWidth;
    int x = 0, y = 0;

    private enum picturePath {
        PATH, ID
    }

    private picturePath flag = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        getStyle();//获取intent的值做初始化

        initViews();//根据获得的板式种类，初始化界面，绑定控件监听等...


    }


    private void getStyle() {
        if (getIntent().getIntExtra("styleCode", -1) != -1) {
            styleCode = getIntent().getIntExtra("styleCode", -1);
        }
        if (getIntent().getStringExtra("pictureFromLocal") == null) {
            flag = picturePath.ID;
        } else {
            flag = picturePath.PATH;
        }
    }


    private void initViews() {
        System.out.println("stylecode"+styleCode);
        ll = (LinearLayout) findViewById(R.id.ll);
        sv = (ScrollView) findViewById(R.id.art_picsarea);
        clipedPhotoPath = getIntent().getStringExtra("myPhotoPath");
        switch (styleCode) {
            case 0:
                View view = LayoutInflater.from(this).inflate(R.layout.pattern_1, sv, true);
                iv = (ImageView) findViewById(R.id.iv1);
                tv = (TextView) findViewById(R.id.tv1);
                bt = (Button) findViewById(R.id.done);
                tv2 = (TextView) findViewById(R.id.tv_date);
                iv.setImageBitmap(BitmapFactory.decodeFile(clipedPhotoPath));
                break;
            case 1:
                View v = LayoutInflater.from(this).inflate(R.layout.pattern_2, sv, true);
                iv = (ImageView) findViewById(R.id.iv1);
                tv = (TextView) findViewById(R.id.tv1);
                bt = (Button) findViewById(R.id.done);
                tv2 = (TextView) findViewById(R.id.tv_date);
                iv.setImageBitmap(BitmapFactory.decodeFile(getIntent().getStringExtra("myPhotoPath")));
                break;
        }

        if (getIntent().getStringExtra("words") != null) {

            int changeViewID = getIntent().getIntExtra("viewId", -1);
            if (tv.getId() == changeViewID) {
                tv.setText(getIntent().getStringExtra("words"));
            } else if (tv2.getId() == changeViewID) {
                tv2.setText(getIntent().getStringExtra("words"));
            } else {
                System.out.println("ID会变！");
            }
        }
        tv.setOnClickListener(new textOnClickListener());
        tv2.setOnClickListener(new textOnClickListener());
        bt.setOnClickListener(new btOnClickListener());

    }

    private class textOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ArtActivity.this, EditCardActivity.class);
            if (!((TextView) view).getText().toString().isEmpty()) {
                intent.putExtra("words", ((TextView) view).getText().toString());
            }
            /**---------------下方要改为正确的值------------------*/
            intent.putExtra("styleCode", 0);
            intent.putExtra("myPhotoPath", clipedPhotoPath);
            intent.putExtra("viewId", view.getId());
            startActivity(intent);
        }
    }


    private class btOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            intent = new Intent(ArtActivity.this, ResultActivity.class);
            /**---------------下方要改为正确的值------------------*/
            switch (styleCode) {
                case 0:
                    x = areaWidth = sv.getWidth();
                    y = areaHeight = sv.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    Canvas canvas = new Canvas(bmp);
                    Bitmap pic = BitmapFactory
                            .decodeFile(getIntent()
                                    .getStringExtra("myPhotoPath"));
                    canvas.drawBitmap(pic, new Rect(0, 0, pic.getWidth(), pic.getHeight()),
                            new Rect(iv.getLeft(),
                                    iv.getTop(),
                                    x - iv.getLeft(),
                                    iv.getTop() + iv.getHeight()), paint);
                    tv.setDrawingCacheEnabled(true);
                    Bitmap textBMP = tv.getDrawingCache();
                    canvas.drawBitmap(textBMP,
                            new Rect(0, 0, textBMP.getWidth(), textBMP.getHeight()),
                            new Rect(tv.getLeft(),
                                    tv.getTop(),
                                    x - tv.getLeft(),
                                    tv.getTop() + tv.getHeight()), paint);
                    tv2.setDrawingCacheEnabled(true);
                    Bitmap text2BMP = tv.getDrawingCache();
                    canvas.drawBitmap(text2BMP, new Rect(0, 0, text2BMP.getWidth(), text2BMP.getHeight()),
                            new Rect(tv2.getLeft(),
                                    tv2.getTop(),
                                    x - tv2.getLeft(),
                                    tv2.getTop() + tv2.getHeight()), paint);
                    String path = IOFile.toSaveFile(bmp);
                    intent.putExtra("resultPath", path);
                    startActivity(intent);
                    break;

            }
        }
    }


}
