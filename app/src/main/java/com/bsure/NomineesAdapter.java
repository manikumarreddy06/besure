package com.bsure;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsure.models.CategorysReponse;
import com.bsure.models.NomineeDetailsResponse;

import java.util.List;

public class NomineesAdapter extends RecyclerView.Adapter<NomineesAdapter.ViewHolder> {
    List<NomineeDetailsResponse> assetCategoriesList;
    Context mContext;
    public NomineesAdapter(Context context, List<NomineeDetailsResponse>category){
        this.mContext = context;
        assetCategoriesList = category;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.nominee_line_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NomineeDetailsResponse Asset_categories = assetCategoriesList.get(position); //instance of categories class
        holder.tvNomName.setText(Asset_categories.getUserNomineeName());

        holder.tvNomRelation.setText(Asset_categories.getUserNomineeRelation());
        holder.tvNomNumber.setText(Asset_categories.getUserNomineeMobileNumber());




    }

    @Override
    public int getItemCount() {
        return assetCategoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNomName;
        TextView tvNomRelation;
        TextView tvNomNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomName = itemView.findViewById(R.id.tvNomName);
            tvNomRelation = itemView.findViewById(R.id.tvNomRelation);
            tvNomNumber = itemView.findViewById(R.id.tvNomNumber);
        }
    }
}
