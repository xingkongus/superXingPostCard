package us.xingkong.xingpostcard.Activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import us.xingkong.xingpostcard.Contants.State;
import us.xingkong.xingpostcard.R;
import us.xingkong.xingpostcard.Utils.IOFile;

/**
 * Created by Garfield on 5/15/16. 11:38PM
 * OverWritten by hugeterry(http://hugeterry.cn)
 * Date: 2016/5/14 03:37
 * 用户编辑明信片页
 */
public class ArtActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView tv, tv2;
    private Button bt;
    private ScrollView sv;
    private LinearLayout ll;
    Intent intent;
    private int styleCode = 0;
    private int myphoto;
    private String myphotopath;
    int x = 0, y = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);
        getStyle();//获取intent的值做初始化
        initViews();//根据获得的板式种类，初始化界面，绑定控件监听等...

    }

    private void getStyle() {
        if (getIntent().getIntExtra("styleCode", -1) != -1) {
            styleCode = getIntent().getIntExtra("styleCode", styleCode);
            System.out.println("styleCode=" + styleCode);
        }
    }

    private void getPhoto(ImageView iv) {
        myphoto = getIntent().getIntExtra("myphoto", -1);
        myphotopath = getIntent().getStringExtra("myphotopath");
        if (myphoto != -1) {
            iv.setImageResource(myphoto);
        } else if (myphotopath != null) {
            iv.setImageBitmap(BitmapFactory.decodeFile(myphotopath));
        }

    }

    private void initViews() {

        System.out.println("stylecode" + styleCode);

        sv = (ScrollView) findViewById(R.id.art_picsarea);

        switch (styleCode) {
            case 0:
                View view = LayoutInflater.from(this).inflate(R.layout.pattern_1, sv, true);
                break;
            case 1:
                View v = LayoutInflater.from(this).inflate(R.layout.pattern_2, sv, true);
                break;
            case 3:
                LayoutInflater.from(this).inflate(R.layout.pattern_4, sv, true);
                break;
            case 4:
                LayoutInflater.from(this).inflate(R.layout.pattern_5, sv, true);
                break;
            case 5:
                LayoutInflater.from(this).inflate(R.layout.pattern_6, sv, true);
                break;
            case 6:
                LayoutInflater.from(this).inflate(R.layout.pattern_7, sv, true);
                break;
            case 7:
                LayoutInflater.from(this).inflate(R.layout.pattern_8, sv, true);
                break;
            case 8:
                LayoutInflater.from(this).inflate(R.layout.pattern_9, sv, true);
                break;
        }

        init();//初始化控件
        if (State.tv1_text == "") {
            State.tv1_text = tv.getText().toString();
            State.tv2_text = tv2.getText().toString();
        }

        if (getIntent().getStringExtra("words") != null) {

            int changeViewID = getIntent().getIntExtra("viewId", -1);
            if (tv.getId() == changeViewID) {
                State.tv1_text = getIntent().getStringExtra("words");

            } else if (tv2.getId() == changeViewID) {
                State.tv2_text = getIntent().getStringExtra("words");

            } else {
                System.out.println("ID会变");
            }
            tv.setText(State.tv1_text);
            tv2.setText(State.tv2_text);
        }


        tv.setOnClickListener(new textOnClickListener());
        tv2.setOnClickListener(new textOnClickListener());
        bt.setOnClickListener(new btOnClickListener());

    }

    private void init() {
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setDrawingCacheEnabled(true);
        iv = (ImageView) findViewById(R.id.iv1);
        iv.setDrawingCacheEnabled(true);
        tv = (TextView) findViewById(R.id.tv1);
        bt = (Button) findViewById(R.id.done);
        tv2 = (TextView) findViewById(R.id.tv_date);
        getPhoto(iv);
    }

    private class textOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ArtActivity.this, EditCardActivity.class);
            if (!((TextView) view).getText().toString().isEmpty()) {
                intent.putExtra("words", ((TextView) view).getText().toString());
            }
            intent.putExtra("styleCode", 0);
            intent.putExtra("myphotopath", myphotopath);
            intent.putExtra("myphoto", myphoto);
            intent.putExtra("viewId", view.getId());
            intent.putExtra("styleCode", getIntent().getIntExtra("styleCode", -1));
            startActivity(intent);
        }
    }


    private class btOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            intent = new Intent(ArtActivity.this, ResultActivity.class);


            Bitmap bmp = ll.getDrawingCache();
//                    aLowWay();
            String path = IOFile.toSaveFile(bmp);
            intent.putExtra("resultPath", path);
            startActivity(intent);
        }
    }


    private void aLowWay() {
//                    x = sv.getWidth();
//                    y = sv.getHeight();
//                    Bitmap bmp = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
//                    Paint paint = new Paint();
//                    paint.setAntiAlias(true);
//                    Canvas canvas = new Canvas(bmp);
//                    Bitmap pic = iv.getDrawingCache();
//                    canvas.drawBitmap(pic, new Rect(0, 0, pic.getWidth(), pic.getHeight()),
//                            new Rect(iv.getLeft(),
//                                    iv.getTop(),
//                                    x - iv.getLeft(),
//                                    iv.getTop() + iv.getHeight()), paint);
//                    tv.setDrawingCacheEnabled(true);
//                    Bitmap textBMP = tv.getDrawingCache();
//                    canvas.drawBitmap(textBMP,
//                            new Rect(0, 0, textBMP.getWidth(), textBMP.getHeight()),
//                            new Rect(tv.getLeft(),
//                                    tv.getTop(),
//                                    x - tv.getLeft(),
//                                    tv.getTop() + tv.getHeight()), paint);
//                    tv2.setDrawingCacheEnabled(true);
//                    Bitmap text2BMP = tv.getDrawingCache();
//                    canvas.drawBitmap(text2BMP, new Rect(0, 0, text2BMP.getWidth(), text2BMP.getHeight()),
//                            new Rect(tv2.getLeft(),
//                                    tv2.getTop(),
//                                    x - tv2.getLeft(),
//                                    tv2.getTop() + tv2.getHeight()), paint);
    }
}
