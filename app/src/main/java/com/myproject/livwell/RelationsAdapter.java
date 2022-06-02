package com.myproject.livwell;

import android.content.Context;
import android.provider.ContactsContract;
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
private List<relationresponse.data>relationresponse;

    public RelationsAdapter(List<com.myproject.livwell.models.relationresponse.data> relationresponse) {
        this.relationresponse = relationresponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.relations_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.tvrelation.setText(relationresponse.get(position).getRelationName());
    }

    @Override
    public int getItemCount() {
        return relationresponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvrelation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvrelation=itemView.findViewById(R.id.tv_relation);
        }
    }
}
