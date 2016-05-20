package us.xingkong.xingpostcard.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import us.xingkong.xingpostcard.R;
import us.xingkong.xingpostcard.Utils.IOFile;
import us.xingkong.xingpostcard.Utils.ShareUtils;

/**
 * Created by Garfield on 5/14/16.
 */
public class ResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView resultIv;
    private Bitmap bitmap;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initToolbar();

        resultIv = (ImageView) findViewById(R.id.result_iv);
        path = getIntent().getStringExtra("resultPath");
        bitmap = BitmapFactory.decodeFile(path);
        resultIv.setImageBitmap(bitmap);

    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("已保存到相册");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                ShareUtils.shareImage(this, path);

        }
        return super.onOptionsItemSelected(item);
    }

}
