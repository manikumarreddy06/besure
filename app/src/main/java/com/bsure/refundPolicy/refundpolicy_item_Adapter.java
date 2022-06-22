package com.bsure.refundPolicy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsure.FAQ.FAQ_items;
import com.bsure.R;

import java.util.List;

public class refundpolicy_item_Adapter extends RecyclerView.Adapter<refundpolicy_item_Adapter.refundpolicy_item_AdapterVH>{
    List<refund_items>refundItemsList;

    public refundpolicy_item_Adapter(List<refund_items> refundItemsList) {
        this.refundItemsList = refundItemsList;
    }

    @NonNull
    @Override
    public refundpolicy_item_AdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_items_list,parent,false);

        return new refundpolicy_item_AdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull refundpolicy_item_AdapterVH holder, int position) {
        refund_items refundItems = refundItemsList.get(position);
        holder.questionTxt.setText(refundItems.getQuestion());
        holder.ansTxt.setText(refundItems.getAnswer());

        boolean isExpandable = refundItemsList.get(position).isExpandable(); // accessing static variable
        holder.expandableLayout.setVisibility(isExpandable ?View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {return refundItemsList.size();}

    public class refundpolicy_item_AdapterVH extends RecyclerView.ViewHolder {
        TextView questionTxt, ansTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;
        public refundpolicy_item_AdapterVH(@NonNull View itemView) {
            super(itemView);
            questionTxt = itemView.findViewById(R.id.question);
            ansTxt = itemView.findViewById(R.id.answer);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(v -> {
                refund_items refundItems = refundItemsList.get(getAbsoluteAdapterPosition());
                refundItems.setExpandable(! refundItems.isExpandable());
                notifyItemChanged(getAbsoluteAdapterPosition());
            });
        }
    }
}
