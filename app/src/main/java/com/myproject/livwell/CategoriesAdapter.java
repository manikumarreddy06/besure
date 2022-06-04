package com.myproject.livwell;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.livwell.models.CategorysReponse;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    List<CategorysReponse> assetCategoriesList;
    Context context;
    public CategoriesAdapter(Context context, List<CategorysReponse>category){
        this.context = context;
        assetCategoriesList = category;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategorysReponse Asset_categories = assetCategoriesList.get(position); //instance of categories class
        holder.categoryName.setText(Asset_categories.getCategoryName());
        Log.d("srini","srini"+Asset_categories.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return assetCategoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
}
