package com.myproject.livwell.FAQ;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.livwell.R;

import java.util.List;

public class FAQ_item_Adapter extends RecyclerView.Adapter<FAQ_item_Adapter.FAQ_item_AdapterVH> {
    List<FAQ_items>FAQitemsList;

    public FAQ_item_Adapter(List<FAQ_items> FAQitemsList) {
        this.FAQitemsList = FAQitemsList;
    }

    @NonNull
    @Override
    public FAQ_item_AdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_items_list,parent,false);

        return new FAQ_item_AdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQ_item_AdapterVH holder, int position) {
        FAQ_items faq_items = FAQitemsList.get(position);
        holder.questionTxt.setText(faq_items.getQuestion());
        holder.ansTxt.setText(faq_items.getAnswer());

        boolean isExpandable = FAQitemsList.get(position).isExpandable(); // accessing static variable
        holder.expandableLayout.setVisibility(isExpandable ?View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return FAQitemsList.size();
    }

    public class FAQ_item_AdapterVH extends RecyclerView.ViewHolder {
        TextView questionTxt, ansTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;
        public FAQ_item_AdapterVH(@NonNull View itemView) {
            super(itemView);

            questionTxt = itemView.findViewById(R.id.question);
            ansTxt = itemView.findViewById(R.id.answer);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(v -> {
                FAQ_items faq_items = FAQitemsList.get(getAbsoluteAdapterPosition());
                faq_items.setExpandable(! faq_items.isExpandable());
                notifyItemChanged(getAbsoluteAdapterPosition());
            });

        }
    }
}
