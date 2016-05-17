package us.xingkong.xingpostcard.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import us.xingkong.xingpostcard.R;

/**
 * Created by lenovo on 2016/5/5.
 */
public class Collection_SimpleAdapter extends RecyclerView.Adapter<MyViewHolder1>{

    private LayoutInflater mInfalter1;
     Context mContext;
    private  List<Integer> mData;

    private OnItemClickListener mOnItemClickListener = null;

    public Collection_SimpleAdapter(Context context, List<Integer> mData){
        this.mContext=context;
        this.mData=mData;
        mInfalter1=LayoutInflater.from(context); //构造方法初始化数据

    }

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override //创建ViewHolder
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInfalter1.inflate(R.layout.item_collection,parent,false);
        MyViewHolder1 viewHolder=new MyViewHolder1(view);
        viewHolder.item_img = (ImageView) view
                .findViewById(R.id.item_img);
        //将创建的View注册点击事件
//        view.setOnClickListener((View.OnClickListener) mContext);
        return viewHolder;
    }

    @Override  //绑定ViewHolder
    public void onBindViewHolder(final MyViewHolder1 holder,final int position) {

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

class  MyViewHolder1 extends RecyclerView.ViewHolder {

    ImageView item_img;
    public MyViewHolder1(View v) {
        super(v);
        item_img= (ImageView) v.findViewById(R.id.item_img);

    }


}
