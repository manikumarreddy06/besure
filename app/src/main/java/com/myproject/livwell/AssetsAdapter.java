package com.myproject.livwell;

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

import com.myproject.livwell.models.Assets;
import com.myproject.livwell.models.CategorysReponse;
import com.myproject.livwell.models.UserAsset;

import java.util.List;

public class AssetsAdapter extends RecyclerView.Adapter<AssetsAdapter.ViewHolder> {
    List<UserAsset> assetCategoriesList;
    Context mContext;
    public AssetsAdapter(Context context, List<UserAsset>category){
        this.mContext = context;
        assetCategoriesList = category;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_asset_details, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserAsset assets = assetCategoriesList.get(position); //instance of categories class

        Log.d("srini","srini---->"+assets.getAssetsList());

        for(Assets data:assets.getAssetsList()) {
            View newView = LayoutInflater.from(mContext).inflate(R.layout.line_item_asset_view, null);

            TextView titleText = (TextView) newView.findViewById(R.id.titleName);
            TextView valueTxt = (TextView) newView.findViewById(R.id.valueTxt);
            titleText.setText(data.getLabel());
            valueTxt.setText(data.getValue());
            holder.llContainer.addView(newView);
        }








    }

    @Override
    public int getItemCount() {
        return assetCategoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        LinearLayout llContainer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            llContainer = itemView.findViewById(R.id.llContainer);
        }
    }
}
