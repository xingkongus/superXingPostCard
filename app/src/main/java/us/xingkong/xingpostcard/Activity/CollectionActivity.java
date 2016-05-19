package us.xingkong.xingpostcard.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import ly.img.android.ui.activities.CameraPreviewActivity;
import ly.img.android.ui.activities.CameraPreviewIntent;
import ly.img.android.ui.activities.PhotoEditorIntent;
import ly.img.android.ui.utilities.PermissionRequest;
import us.xingkong.xingpostcard.Adapter.Collection_SimpleAdapter;
import us.xingkong.xingpostcard.R;
import us.xingkong.xingpostcard.Utils.ShareUtils;

public class CollectionActivity extends AppCompatActivity {
    public static int CAMERA_PREVIEW_RESULT = 1;
    public ArrayList<Integer> Collection_data_real, Collection_data_cartoon;
    public ArrayList<Integer> Collection_data_real_mini, Collection_data_cartoon_mini;
    private RecyclerView recyclerView_cartoon, recyclerView_real;
    public Collection_SimpleAdapter mAdapter_real, mAdapter_cartoon;
    private Toolbar toolbar;

    Context con;
    int Pick_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initToolbar();
        initData();
        initView();

        Pick_position = getIntent().getIntExtra("styleCode", -1);

        view_layout(); //布局设置

        real_Click();
        cartoon_Click();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("选择图片");
    }

    private void cartoon_Click() {
        mAdapter_cartoon.setOnItemClickListener(new Collection_SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CollectionActivity.this, ArtActivity.class);
                intent.putExtra("myphoto",
                        Collection_data_cartoon.get(position));
                intent.putExtra("styleCode", Pick_position);

                CollectionActivity.this.startActivity(intent);
            }
        });
    }

    private void real_Click() {
        mAdapter_real.setOnItemClickListener(new Collection_SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CollectionActivity.this, ArtActivity.class);
                intent.putExtra("myphoto",
                        Collection_data_real.get(position));
                intent.putExtra("styleCode", Pick_position);
                CollectionActivity.this.startActivity(intent);
            }
        });
    }


    private void view_layout() {
        final LinearLayoutManager linearLayoutMannger = new LinearLayoutManager(con, LinearLayoutManager.HORIZONTAL, false);
        mAdapter_real = new Collection_SimpleAdapter(this, Collection_data_real_mini);
        recyclerView_real.setAdapter(mAdapter_real);
        recyclerView_real.setLayoutManager(linearLayoutMannger);

        final LinearLayoutManager linearLayoutMannger2 = new LinearLayoutManager(con, LinearLayoutManager.HORIZONTAL, false);
        mAdapter_cartoon = new Collection_SimpleAdapter(this, Collection_data_cartoon_mini);
        recyclerView_cartoon.setAdapter(mAdapter_cartoon);
        recyclerView_cartoon.setLayoutManager(linearLayoutMannger2);
    }

    private void initView() {
        recyclerView_cartoon = (RecyclerView) findViewById(R.id.recyclerView_cartoon);
        recyclerView_real = (RecyclerView) findViewById(R.id.recyclerView_real);
    }

    private void initData() {

        Collection_data_real = new ArrayList<Integer>(Arrays.asList(
                R.mipmap.a01, R.mipmap.a02, R.mipmap.a03,
                R.mipmap.a04, R.mipmap.a05, R.mipmap.a06,
                R.mipmap.a07, R.mipmap.a08, R.mipmap.a09,
                R.mipmap.a10, R.mipmap.a11, R.mipmap.a12
        ));
        Collection_data_cartoon = new ArrayList<Integer>(Arrays.asList(R.mipmap.bg01,
                R.mipmap.bg02, R.mipmap.bg03, R.mipmap.bg04, R.mipmap.bg05));

        Collection_data_real_mini = new ArrayList<Integer>(Arrays.asList(
                R.mipmap.a01_mini, R.mipmap.a02_mini, R.mipmap.a03_mini,
                R.mipmap.a04_mini, R.mipmap.a05_mini, R.mipmap.a06_mini,
                R.mipmap.a07_mini, R.mipmap.a08_mini, R.mipmap.a09_mini,
                R.mipmap.a10_mini, R.mipmap.a11_mini, R.mipmap.a12_mini
        ));
        Collection_data_cartoon_mini = new ArrayList<Integer>(Arrays.asList(R.mipmap.bg01_mini,
                R.mipmap.bg02_mini, R.mipmap.bg03_mini, R.mipmap.bg04_mini, R.mipmap.bg05_mini));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_PREVIEW_RESULT) {
            String path = data.getStringExtra(CameraPreviewActivity.RESULT_IMAGE_PATH);

            Toast.makeText(this, "Image Save on: " + path, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CollectionActivity.this, ArtActivity.class);
            intent.putExtra("myphotopath", path);
            intent.putExtra("styleCode", Pick_position);
            CollectionActivity.this.startActivity(intent);

        }
    }

    //Important for Android 6.0 permisstion request, don't forget this!
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void permissionGranted() {

    }

    public void permissionDenied() {
        //The Permission whas rejected by the user, so the Editor was not opened because it can not save the result Image.

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_add:
                new CameraPreviewIntent(CollectionActivity.this)
                        .setExportDir(CameraPreviewIntent.Directory.DCIM, "ImgLyExample")
                        .setExportPrefix("example_")
                        .setEditorIntent(
                                new PhotoEditorIntent(CollectionActivity.this)
                                        .setExportDir(PhotoEditorIntent.Directory.DCIM, "ImgLyExample")
                                        .setExportPrefix("result_")
                                        .destroySourceAfterSave(true)
                        )
                        .startActivityForResult(CAMERA_PREVIEW_RESULT);

        }
        return super.onOptionsItemSelected(item);
    }

}
