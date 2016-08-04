package us.xingkong.xingpostcard.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import cn.hugeterry.updatefun.UpdateFunGO;

import cn.hugeterry.updatefun.config.UpdateKey;
import us.xingkong.xingpostcard.Adapter.CoverFlow;
import us.xingkong.xingpostcard.Adapter.CoverFlowSampleAdapter;
import us.xingkong.xingpostcard.Key;
import us.xingkong.xingpostcard.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CoverFlow fancyCoverFlow;
    private SharedPreferences sh_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        fancyCoverFlowSetting();
        UpdateModel();

    }

    private void UpdateModel() {
        UpdateKey.API_TOKEN = "";
        UpdateKey.APP_ID = "573ecefa748aac36f4000007";
        UpdateFunGO.init(this);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("选择相册");
    }

    private void fancyCoverFlowSetting() {
        setupFlowWidthAndHeight();

        this.fancyCoverFlow = (CoverFlow) this.findViewById(R.id.fancyCoverFlow);
        this.fancyCoverFlow.setAdapter(new CoverFlowSampleAdapter());
        this.fancyCoverFlow.setUnselectedAlpha(0.9f);//
        this.fancyCoverFlow.setUnselectedSaturation(1f);//透明度
        this.fancyCoverFlow.setUnselectedScale(0.5f);//深度差
        this.fancyCoverFlow.setSpacing(0);
        this.fancyCoverFlow.setMaxRotation(0);
        this.fancyCoverFlow.setScaleDownGravity(0.2f);//高度差
        this.fancyCoverFlow.setActionDistance(CoverFlow.ACTION_DISTANCE_AUTO);
        this.fancyCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
//                System.out.println("styleCode" + position);
                intent.putExtra("styleCode", position);
                startActivity(intent);
            }
        });

    }

    private void setupFlowWidthAndHeight() {
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Key.CoverFlow_height = height * 2 / 3;
        Key.CoverFlow_Width = width * 2 / 3;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent_about = new Intent(this, AboutActivity.class);
                startActivity(intent_about);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }
}
