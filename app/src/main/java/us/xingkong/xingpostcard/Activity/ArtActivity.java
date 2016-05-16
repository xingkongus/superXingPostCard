package us.xingkong.xingpostcard.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import us.xingkong.xingpostcard.R;

/**
 * Created by Garfield on 5/15/16. 11:38PM
 * 用户编辑明信片页
 */
public class ArtActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv;
    private Button bt;
    private LinearLayout


    private int styleCode = 0;
    private int ivHeight;
    private int ivWidth;
    private int areaHeight;
    private int areaWidth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        initStyle();//获取intent的值做初始化


    }

    private void initStyle() {
        if (getIntent().getIntExtra("styleCode", -1) != -1) {
            styleCode = getIntent().getIntExtra("styleCode", -1);
        }

        ScrollView sv = (ScrollView) findViewById(R.id.art_picsarea);
        switch (styleCode) {
            case 0:
                View view = LayoutInflater.from(this).inflate(R.layout.pattern_1, sv, true);

                iv = (ImageView) findViewById(R.id.iv1);
                tv = (TextView) findViewById(R.id.tv1);
                bt = (Button) findViewById(R.id.done);

                iv.setImageBitmap(BitmapFactory.decodeFile(getIntent().getStringExtra("myPhotoPath")));
                break;
        }
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
                    ViewTreeObserver vto1 = iv.getViewTreeObserver();
                    vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            ivHeight = iv.getHeight();
                            ivWidth = iv.getWidth();
                            Log.e("hhhhhhhh", iv.getHeight() + "," + iv.getWidth());
                        }
                    });
                    ViewTreeObserver vto2 = iv.getViewTreeObserver();
                    vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            ivHeight = iv.getHeight();
                            ivWidth = iv.getWidth();
                            Log.e("hhhhhhhh", iv.getHeight() + "," + iv.getWidth());
                        }
                    });

                    break;

            }
        }
    }
}
