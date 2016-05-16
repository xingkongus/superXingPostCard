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
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import us.xingkong.xingpostcard.R;

/**
 * Created by Garfield on 5/15/16. 11:38PM
 * 用户编辑明信片页
 */
public class ArtActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv,tv2;
    private Button bt;
    private ScrollView sv;


    private int styleCode = 0;
    private int ivHeight;
    private int ivWidth;
    private int areaHeight;
    private int areaWidth;

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

        sv = (ScrollView) findViewById(R.id.art_picsarea);
        switch (styleCode) {
            case 0:
                View view = LayoutInflater.from(this).inflate(R.layout.pattern_1, sv, true);

                iv = (ImageView) findViewById(R.id.iv1);
                tv = (TextView) findViewById(R.id.tv1);
                bt = (Button) findViewById(R.id.done);
                tv2 = (TextView) findViewById(R.id.tv_date);


                iv.setImageBitmap(BitmapFactory.decodeFile(getIntent().getStringExtra("myPhotoPath")));
                break;
        }
        tv.setOnClickListener(this);
        bt.setOnClickListener(this);
        tv2.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view instanceof TextView) {
            Intent intent = new Intent(this, EditCardActivity.class);
            if (!((TextView) view).getText().toString().isEmpty()) {
                intent.putExtra("words", ((TextView) view).getText().toString());
            }
            /**---------------下方要改为正确的值------------------*/
            intent.putExtra("styleCode", 0);
            intent.putExtra("myPhotoPath", "aaaaaaaa");
            startActivity(intent);
        }

        if (view instanceof Button) {
            Intent intent = new Intent(this, ResultActivity.class);

            /**---------------下方要改为正确的值------------------*/
            int x = 0, y = 0;
            switch (styleCode) {

                case 0:
                    Bitmap bmp = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
//                    ViewTreeObserver vto1 = iv.getViewTreeObserver();
//                    vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                        @Override
//                        public void onGlobalLayout() {
//                            iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                            ivHeight = iv.getHeight();
//                            ivWidth = iv.getWidth();
//                            Log.e("hhhhhhhh", iv.getHeight() + "," + iv.getWidth());
//                        }
//                    });
                    ViewTreeObserver vto2 = sv.getViewTreeObserver();
                    vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            sv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            areaHeight = sv.getChildAt(0).getHeight();
                            areaWidth = sv.getWidth();
                            Log.e("hhhhhhhh", areaHeight + "," + areaWidth);
                        }
                    });
                    x = areaWidth;
                    y = areaHeight;
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    Canvas canvas = new Canvas(bmp);

                    Bitmap pic = BitmapFactory.decodeFile(getIntent().getStringExtra("myPhotoPath"));
                    canvas.drawBitmap(pic, new Rect(x * iv.getLeft() / areaWidth, y * iv.getTop() / areaHeight, x - x * iv.getLeft() / areaWidth, y - y * iv.getBottom()), new Rect(0, 0, pic.getWidth(), pic.getHeight()), paint);

                    tv.setDrawingCacheEnabled(true);
                    Bitmap textBMP= tv.getDrawingCache();
                    canvas.drawBitmap(textBMP,new Rect(x * tv.getLeft() / areaWidth, y * tv.getTop() / areaHeight, x - x * tv.getLeft() / areaWidth, y - y * tv.getBottom()),new Rect(0, 0, textBMP.getWidth(), textBMP.getHeight()),paint);
                    tv.setDrawingCacheEnabled(false);

                    tv2.setDrawingCacheEnabled(true);
                    Bitmap text2BMP= tv.getDrawingCache();
                    canvas.drawBitmap(text2BMP,new Rect(x * tv2.getLeft() / areaWidth, y * tv2.getTop() / areaHeight, x - x * tv2.getLeft() / areaWidth, y - y * tv2.getBottom()),new Rect(0, 0, text2BMP.getWidth(), text2BMP.getHeight()),paint);
                    tv2.setDrawingCacheEnabled(false);

                    break;
            }
        }
    }
}
