package us.xingkong.xingpostcard.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

import us.xingkong.xingpostcard.Adapter.Pick_SimpleAdapter;
import us.xingkong.xingpostcard.R;

/**
 * Created by Garfield on 4/26/16.
 */
public class PicPickActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public Pick_SimpleAdapter mAdapter;
    Context con;
    Button one, two;
    public ArrayList<Integer> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picpick);


        initData();
        initViews();
        view_layout(); //布局设置
//        change_Click();//换比例
        item_Click();

    }

    Uri uri = null;

    //    ImageView img;
    private void item_Click() {
        mAdapter.setOnItemClickListener(new Pick_SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(CollectionActivity.this,"2222sad"+position,Toast.LENGTH_SHORT).show();
//                    img.setImageResource(mData.get(position));
//                Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +"://"
//                        + r.getResourcePackageName(mData.get(position)) + "/"
//                        + r.getResourceTypeName(mData.get(position) + "/"
//                        + r.getResourceEntryName(mData.get(position));
//                Uri uri = Uri.parse("res:///" + mData.get(position));

                System.out.println("ttttttttt" + mData.get(position));
//                int pick=position;
                Intent intent = new Intent(PicPickActivity.this, CollectionActivity.class);
                //  第一个参数是key， 第二个参数是要传递的值
                intent.putExtra("styleCode", 0);
                PicPickActivity.this.startActivity(intent);
            }
        });
    }

//    private void  change_Click() {
//        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
//                StaggeredGridLayoutManager.HORIZONTAL);
////        one.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
////            }
////        });
//
//    }

    private void view_layout() {
        final StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.HORIZONTAL);
        mAdapter = new Pick_SimpleAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        //如果确定每个item的内容不会改变RecyclerView的大小，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
    }

    private void initData() {
        mData = new ArrayList<Integer>(Arrays.asList(R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher));

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        one= (Button) findViewById(R.id.one);
//        two= (Button) findViewById(R.id.two);
    }
}
