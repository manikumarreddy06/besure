package com.bsure;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsure.dialog.ImageViewDialog;
import com.bsure.models.NomineeDetailsResponse;
import com.google.android.material.transition.Hold;

import java.util.List;

public class NomineesAdapter extends RecyclerView.Adapter<NomineesAdapter.ViewHolder> {
    List<NomineeDetailsResponse> nomineeList;
    Context mContext;
    public NomineesAdapter(Context context, List<NomineeDetailsResponse>category){
        this.mContext = context;
        nomineeList = category;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.nominee_line_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NomineeDetailsResponse nomineeBean = nomineeList.get(position); //instance of categories class
        holder.tvNomName.setText(nomineeBean.getUserNomineeName());

        holder.tvNomRelation.setText(nomineeBean.getUserNomineeRelation());
        holder.tvNomNumber.setText(nomineeBean.getUserNomineeMobileNumber());

        holder.tvNomAge.setText(nomineeBean.getUserNomineeAge());

        if(!TextUtils.isEmpty(nomineeBean.getUserGardianName())) {
            holder.llNomGuardianName.setVisibility(View.VISIBLE);
            holder.tvGuardianName.setText(nomineeBean.getUserGardianName());
        }
        else{
            holder.llNomGuardianName.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(nomineeBean.getUserGardianMobileNumber())) {
            holder.llGuardianPhNumber.setVisibility(View.VISIBLE);
            holder.tvGuardianPhNo.setText(nomineeBean.getUserGardianMobileNumber());
        }
        else{
            holder.llGuardianPhNumber.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(nomineeBean.getUserNomineeAttachment())){
            holder.llAttachment.setVisibility(View.VISIBLE);
        }
        else{
            holder.llAttachment.setVisibility(View.GONE);
        }

        holder.tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewDialog dailog = new ImageViewDialog(mContext, nomineeBean.getUserNomineeAttachment());
                dailog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return nomineeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNomAge, tvGuardianPhNo, tvGuardianName;
        TextView tvNomName;
        TextView tvNomRelation;
        TextView tvNomNumber;
        LinearLayout llAttachment,llNomGuardianName,llGuardianPhNumber;
        TextView tvPreview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomName = itemView.findViewById(R.id.tvNomName);
            tvNomRelation = itemView.findViewById(R.id.tvNomRelation);
            tvNomNumber = itemView.findViewById(R.id.tvNomNumber);
            llAttachment = itemView.findViewById(R.id.llAttachment);
            tvPreview = itemView.findViewById(R.id.tvPreview);

            tvNomAge = itemView.findViewById(R.id.tvNomAge);
            tvGuardianName = itemView.findViewById(R.id.tvNomGuardianName);
            tvGuardianPhNo = itemView.findViewById(R.id.tvGuardianPhNumber);

            llNomGuardianName = itemView.findViewById(R.id.llNomGuardianName);
            llGuardianPhNumber = itemView.findViewById(R.id.llGuardianPhNumber);


        }
    }
}
