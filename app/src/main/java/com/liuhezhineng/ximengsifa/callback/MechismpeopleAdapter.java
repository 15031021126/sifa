package com.liuhezhineng.ximengsifa.callback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.callback.bean.MechPeopleBean;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishangnan on 2019/11/11.
 */

public class MechismpeopleAdapter extends RecyclerView.Adapter<MechismpeopleAdapter.MechismViewHoder> {
    Context context;
    List<MechPeopleBean.BodyBean.ListBean> mechlist;
    public MechismpeopleAdapter(Context context) {
        this.context=context;
        // TODO: 2019/11/25  测试数据
         mechlist=new ArrayList<>();
    }
    @NonNull
    @Override
    public MechismViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_mechanism_list,parent,false);
        return new MechismViewHoder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MechismViewHoder holder, int position) {
//        Picasso.get().load(NetConstant.FILE_URL + mechlist.get(position).getImageUrl())
        Picasso.get().load(NetConstant.BASE_URL + mechlist.get(position).getImageUrl())
                .placeholder(R.drawable.default_personnel_rect)
                .into(holder.iv_avatar);
        holder.tv_name.setText(mechlist.get(position).getPersonName());
        holder.tv_is_mongolian.setText("执法区域"+mechlist.get(position).getAgencyName());
        holder.tv_committee.setText("执法类型"+mechlist.get(position).getAgencyAddress());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MechPeopleBean.BodyBean.ListBean listBean = mechlist.get(position);
                DetailsActivity.actionStart(context,listBean,"0");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mechlist.size();
    }
//    List<MechPeopleBean.BodyBean.ListBean> list
    public void addList(List<MechPeopleBean.BodyBean.ListBean> list) {
        mechlist.addAll(list);
        notifyDataSetChanged();
    }


    public class MechismViewHoder extends RecyclerView.ViewHolder {
        ImageView iv_avatar;
    TextView tv_name;
    TextView tv_is_mongolian;
    TextView tv_committee;
    LinearLayout ll;
        public MechismViewHoder(View itemView) {
            super(itemView);
            iv_avatar= itemView.findViewById(R.id.iv_avatar);
            tv_name= itemView.findViewById(R.id.tv_name);
            tv_is_mongolian= itemView.findViewById(R.id.tv_is_mongolian);
            tv_committee= itemView.findViewById(R.id.tv_committee);
            ll= itemView.findViewById(R.id.ll);

        }
    }
}
