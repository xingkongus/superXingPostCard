package us.xingkong.xingpostcard.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import us.xingkong.xingpostcard.Adapter.CoverFlow;
import us.xingkong.xingpostcard.Adapter.CoverFlowSampleAdapter;
import us.xingkong.xingpostcard.BuildConfig;
import us.xingkong.xingpostcard.R;
import us.xingkong.xingpostcard.update.DownLoadDialog;
import us.xingkong.xingpostcard.update.Update;
import us.xingkong.xingpostcard.update.UpdateDialog;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CoverFlow fancyCoverFlow;
    private SharedPreferences sh_updateurl;

    private String version = BuildConfig.VERSION_NAME;

    private String apkUrl = "";

    Handler up_handler = new Handler() {
        public void handleMessage(Message msg) {
            int i;
            i = msg.arg1;
            switch (i) {
                case 1:
                    // 弹出提示更新对话框
                    showNoticeDialog();
                    break;
                default:
                    break;
            }

        }
    };


    class MyRunnable_update implements Runnable {

        @Override
        public void run() {
            // 检测更新
            Update update = new Update();
            update.start();

            Message msg = new Message();
            try {
                update.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            apkUrl = update.up_url;

            sh_updateurl = getSharedPreferences("sh_updateurl", MODE_APPEND);
            SharedPreferences.Editor up = sh_updateurl.edit();
            up.putString("sh_updateurl", apkUrl);
            up.commit();

            System.out.println("apkUrl: " + apkUrl);
            if (update.version == null) {
                System.out.println("无联网，不更新");
                msg.arg1 = 2;
                up_handler.sendMessage(msg);
            } else if (!update.version.equals(version)) {
                System.out.println("需更新版本");
                msg.arg1 = 1;
                up_handler.sendMessage(msg);
            } else {
                System.out.println("版本已是最新");
                msg.arg1 = 2;
                up_handler.sendMessage(msg);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        fancyCoverFlowSetting();

        Thread thread_update = new Thread(new MyRunnable_update());
        thread_update.start();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("选择相册");
    }

    private void fancyCoverFlowSetting() {
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
                System.out.println("styleCode" + position);
                intent.putExtra("styleCode", position);
                startActivity(intent);
            }
        });

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

    private void showNoticeDialog() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, UpdateDialog.class);
        startActivityForResult(intent, 100);
    }

    private void showDownloadDialog() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DownLoadDialog.class);
        System.out.println(apkUrl);
        startActivityForResult(intent, 0);
    }

    // 获取对话框的返回值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 2:
                showDownloadDialog();
                break;
            default:
                break;
        }
    }
}
