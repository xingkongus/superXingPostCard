package us.xingkong.xingpostcard.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import us.xingkong.xingpostcard.R;
import us.xingkong.xingpostcard.Utils.Ruler;

/**
 * Created by Garfield on 5/14/16.
 */
public class ResultActivity extends Activity {

    ImageView resultIv;
    private int windowWidth;
    private int windowHeight;
    private Bitmap pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        resultIv= (ImageView) findViewById(R.id.result_iv);

        String path= getIntent().getStringExtra("resultPath");

        resultIv.setImageBitmap(BitmapFactory.decodeFile(path));

    }
}
