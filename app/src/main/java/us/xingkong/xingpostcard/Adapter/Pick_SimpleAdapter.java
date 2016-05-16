package us.xingkong.xingpostcard.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import us.xingkong.xingpostcard.R;

import java.util.List;

/**
 * Created by lenovo on 2016/5/5.
 */
public class Pick_SimpleAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private LayoutInflater mInfalter1;
     Context mContext;
    private  List<Integer> mData;

    private OnItemClickListener mOnItemClickListener = null;

    public Pick_SimpleAdapter(Context context, List<Integer> mData){
        this.mContext=context;
        this.mData=mData;
        mInfalter1=LayoutInflater.from(context); //构造方法初始化数据

    }

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

//    public void onClick(View v) {
//        if (mOnItemClickListener != null) {
//            //注意这里使用getTag方法获取数据
//            mOnItemClickListener.onItemClick(v,(String)v.getTag());
//        }
//    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override //创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInfalter1.inflate(R.layout.item_pick,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        viewHolder.item_img = (ImageView) view
                .findViewById(R.id.item_img);
        //将创建的View注册点击事件
//        view.setOnClickListener((View.OnClickListener) mContext);
        return viewHolder;
    }

    @Override  //绑定ViewHolder
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

//        holder.tv.setText(mData.get(position));//对tv赋值
        holder.item_img.setImageResource(mData.get(position));
        if(mOnItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



}

class  MyViewHolder extends RecyclerView.ViewHolder {

    ImageView item_img;
    public MyViewHolder(View v) {
        super(v);

        item_img= (ImageView) v.findViewById(R.id.item_img);
    }


}
