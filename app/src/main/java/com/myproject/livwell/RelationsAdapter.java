package com.myproject.livwell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.livwell.models.relationresponse;

import java.util.ArrayList;
import java.util.List;

public class RelationsAdapter extends RecyclerView.Adapter<RelationsAdapter.ViewHolder> {
    private List<relationresponse>relationresponseList;

    public RelationsAdapter(List<relationresponse> relationresponseList) {
        this.relationresponseList = relationresponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relations_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        relationresponse data = relationresponseList.get(position);
//        holder.tvRelation.setText(data.getData().get());
    }

    @Override
    public int getItemCount() {
        return relationresponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRelation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRelation = itemView.findViewById(R.id.tv_relation);
        }
    }
}
