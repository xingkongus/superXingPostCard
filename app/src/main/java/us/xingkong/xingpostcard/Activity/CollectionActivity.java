package us.xingkong.xingpostcard.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import us.xingkong.xingpostcard.Adapter.DividerItemDecoration;
import us.xingkong.xingpostcard.R;

public class CollectionActivity extends AppCompatActivity {
    public static int CAMERA_PREVIEW_RESULT = 1;
    public ArrayList<Integer> Collection_data_real, Collection_data_cartoon;
    private RecyclerView recyclerView_cartoon, recyclerView_real;
    public Collection_SimpleAdapter mAdapter_real, mAdapter_cartoon;
    Context con;
    Button bendi;
    int Pick_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initData();
        initView();

        Pick_position = getIntent().getIntExtra("styleCode", -1);
        System.out.println("styleCode:" + Pick_position);

        view_layout(); //布局设置
        select_Click();//点击选择本地图片
        real_Click();
        cartoon_Click();

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

    private void select_Click() {
        bendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    private void view_layout() {
        final LinearLayoutManager linearLayoutMannger = new LinearLayoutManager(this);
        linearLayoutMannger.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter_real = new Collection_SimpleAdapter(this, Collection_data_real);
        recyclerView_real.setLayoutManager(linearLayoutMannger);
        recyclerView_real.setAdapter(mAdapter_real);

        final LinearLayoutManager linearLayoutMannger2 = new LinearLayoutManager(this);
        linearLayoutMannger2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter_cartoon = new Collection_SimpleAdapter(this, Collection_data_cartoon);
        recyclerView_cartoon.setLayoutManager(linearLayoutMannger2);
        recyclerView_cartoon.setAdapter(mAdapter_cartoon);
    }

    private void initView() {
        recyclerView_cartoon = (RecyclerView) findViewById(R.id.recyclerView_cartoon);
        recyclerView_real = (RecyclerView) findViewById(R.id.recyclerView_real);
        bendi = (Button) findViewById(R.id.bendi);
    }

    private void initData() {

        Collection_data_real = new ArrayList(Arrays.asList(R.drawable.a01, R.drawable.a02,
                R.drawable.a03, R.drawable.a04, R.drawable.a05, R.drawable.a06, R.drawable.a07, R.drawable.a08
        ));
        Collection_data_cartoon = new ArrayList(Arrays.asList(R.drawable.bg01,
                R.drawable.bg02, R.drawable.bg03, R.drawable.bg04,
                R.drawable.bg05, R.drawable.bg06, R.drawable.bg07, R.drawable.bg08, R.drawable.bg09));
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
}
