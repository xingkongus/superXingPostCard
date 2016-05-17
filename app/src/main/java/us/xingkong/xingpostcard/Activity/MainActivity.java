package us.xingkong.xingpostcard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import us.xingkong.xingpostcard.Adapter.CoverFlow;
import us.xingkong.xingpostcard.Adapter.CoverFlowSampleAdapter;
import us.xingkong.xingpostcard.R;

public class MainActivity extends AppCompatActivity {

    private CoverFlow fancyCoverFlow;
    private Button yes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fancyCoverFlowSetting();

    }

    private void fancyCoverFlowSetting() {
        this.fancyCoverFlow = (CoverFlow) this.findViewById(R.id.fancyCoverFlow);
        this.fancyCoverFlow.setAdapter(new CoverFlowSampleAdapter());
        this.fancyCoverFlow.setUnselectedAlpha(0.9f);//
        this.fancyCoverFlow.setUnselectedSaturation(1f);//透明度
        this.fancyCoverFlow.setUnselectedScale(0.5f);//深度差
        this.fancyCoverFlow.setSpacing(1);
        this.fancyCoverFlow.setMaxRotation(0);
        this.fancyCoverFlow.setScaleDownGravity(0.5f);//高度差
        this.fancyCoverFlow.setActionDistance(CoverFlow.ACTION_DISTANCE_AUTO);

        this.fancyCoverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       final int position, long id) {
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,CollectionActivity.class);
                        System.out.println("styleCode"+position);
                        intent.putExtra("styleCode",  position);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void initView() {
        yes= (Button) findViewById(R.id.yes);
    }

}
