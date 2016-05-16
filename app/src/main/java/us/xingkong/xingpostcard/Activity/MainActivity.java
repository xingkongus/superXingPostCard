package us.xingkong.xingpostcard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import us.xingkong.xingpostcard.Adapter.CoverFlow;
import us.xingkong.xingpostcard.Adapter.CoverFlowSampleAdapter;
import us.xingkong.xingpostcard.R;

public class MainActivity extends AppCompatActivity {

    private CoverFlow fancyCoverFlow;
    Button yes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        onClick();
//        text();


        this.fancyCoverFlow = (CoverFlow) this.findViewById(R.id.fancyCoverFlow);

        this.fancyCoverFlow.setAdapter(new CoverFlowSampleAdapter());
        this.fancyCoverFlow.setUnselectedAlpha(0.9f);//
        this.fancyCoverFlow.setUnselectedSaturation(1f);//透明度
        this.fancyCoverFlow.setUnselectedScale(0.5f);//深度差
        this.fancyCoverFlow.setSpacing(50);
        this.fancyCoverFlow.setMaxRotation(0);
        this.fancyCoverFlow.setScaleDownGravity(0.5f);//高度差

        this.fancyCoverFlow.setActionDistance(CoverFlow.ACTION_DISTANCE_AUTO);
    }

//    private void text() {
//       fancyCoverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//           public void onItemClick(AdapterView<?> parent, View view,
//                                   int position, long id) {
//               Toast.makeText(MainActivity.this,
//                       filmList.get(position % filmList.size()).getFilmName(),
//                       0).show();
//           }
//       });
//    }

    private void onClick() {
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PicPickActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        yes= (Button) findViewById(R.id.yes);
    }
}
