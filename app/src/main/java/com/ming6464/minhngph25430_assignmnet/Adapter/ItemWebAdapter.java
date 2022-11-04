package com.ming6464.minhngph25430_assignmnet.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ming6464.minhngph25430_assignmnet.DTO.ItemWeb;
import com.ming6464.minhngph25430_assignmnet.R;
import java.util.List;

public class ItemWebAdapter extends RecyclerView.Adapter<ItemWebAdapter.MyViewHolder>{
    private final OnEvent action;
    private List<ItemWeb> list;
    private View view;

    public ItemWebAdapter(OnEvent action){
        this.action = action;
    }

    public void setData(List<ItemWeb> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_web,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemWeb obj = list.get(position);
        int index = holder.getAdapterPosition() + 1;
        if(index < 10)
            holder.id.setText("0" + index);
        else
            holder.id.setText(String.valueOf(index));
        holder.noiDung.setText(obj.getTitle());
        view.setOnClickListener(v ->{
            action.onActionClick(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }
    public interface OnEvent{
        void onActionClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView id,noiDung;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.itemWeb_tv_id);
            noiDung = itemView.findViewById(R.id.itemWeb_tv_noiDung);
        }
    }
}
