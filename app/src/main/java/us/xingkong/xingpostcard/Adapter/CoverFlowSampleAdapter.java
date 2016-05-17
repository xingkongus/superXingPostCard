package us.xingkong.xingpostcard.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import us.xingkong.xingpostcard.R;


public class CoverFlowSampleAdapter extends CoverFlowAdapter {

    int a;
    // =============================================================================
    // Private members
    // =============================================================================

    private int[] images = {R.drawable.splash, R.drawable.splash,R.drawable.splash, R.drawable.splash, R.drawable.splash, R.drawable.splash};

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Integer getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        CustomViewGroup customViewGroup = null;

        if (reuseableView != null) {
            customViewGroup = (CustomViewGroup) reuseableView;
        } else {
            customViewGroup = new CustomViewGroup(viewGroup.getContext());
            customViewGroup.setLayoutParams(new CoverFlow.LayoutParams(500, 900));//图片高度
        }

        customViewGroup.getImageView().setImageResource(this.getItem(i));
//        customViewGroup.getTextView().setText(String.format("Item %d", i));
//        int pos=customViewGroup.getPos().String.format("Item %d", i);

        return customViewGroup;
    }

    private class CustomViewGroup extends LinearLayout {

        private ImageView imageView;
//        Button bt;

        public CustomViewGroup(final Context context) {
            super(context);
            this.imageView = new ImageView(context);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            this.imageView.setLayoutParams(layoutParams);
            this.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.imageView.setAdjustViewBounds(true);
//            this.imageView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    System.out.println("eeeeeeeeeee"+a);
//                    Intent intent=new Intent(context,PicPickActivity.class);
//                    v.getContext().startActivity(intent);
//
//                }
//            });


            this.addView(this.imageView);

        }

        public ImageView getImageView() {
            return imageView;
        }


    }
}
