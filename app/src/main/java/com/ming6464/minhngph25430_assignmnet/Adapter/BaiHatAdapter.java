package com.ming6464.minhngph25430_assignmnet.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ming6464.minhngph25430_assignmnet.DTO.BaiHat;
import com.ming6464.minhngph25430_assignmnet.R;
import java.util.List;

public class BaiHatAdapter extends RecyclerView.Adapter<BaiHatAdapter.MyViewHolder>{
    private List<BaiHat> list;
    private View view;
    private OnEvent action;

    public BaiHatAdapter(OnEvent action){
        this.action = action;
    }
    public void setData(List<BaiHat> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhac,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BaiHat obj = list.get(position);
        holder.caSi.setText(obj.getCaSi());
        holder.tenBaiHat.setText(obj.getTenBaiHat());
        holder.anh.setImageResource(obj.getAnh());
        view.setOnClickListener(v -> {
            action.onClickItem(holder.getAdapterPosition());
        });
    }
    public interface OnEvent {
        void onClickItem(int position);
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tenBaiHat,caSi;
        private final ImageView anh;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tenBaiHat = itemView.findViewById(R.id.itemNhac_tv_tenBaiHat);
            caSi = itemView.findViewById(R.id.itemNhac_tv_caSi);
            anh = itemView.findViewById(R.id.itemNhac_img_anh);
        }
    }
}
