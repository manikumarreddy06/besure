package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.livwell.R;

/*
public class bankaccountsadapter extends RecyclerView.Adapter<bankaccountsadapter.bankadapterrecyclerviweholder> {

    Context context;
    String bankacountnumber,bankname,ifsccode,branchname;

    public bankaccountsadapter(Context context, String bankacountnumber, String bankname, String ifsccode, String branchname) {
        this.context = context;
        this.bankacountnumber = bankacountnumber;
        this.bankname = bankname;
        this.ifsccode = ifsccode;
        this.branchname = branchname;
    }

    @NonNull
    @Override
    public bankadapterrecyclerviweholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.bankslayout_one_item,parent,false);
        return new bankadapterrecyclerviweholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull bankadapterrecyclerviweholder holder, int position) {
      holder.bank_name.setText(bankname);
      holder.bank_accountnum.setText(bankacountnumber);
      holder.ifsccode.setText(ifsccode);
      holder.branchname.setText(branchname);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class bankadapterrecyclerviweholder extends RecyclerView.ViewHolder {
                TextView bank_accountnum,bank_name,ifsccode,branchname;

        public bankadapterrecyclerviweholder(@NonNull View itemView) {
            super(itemView);
            bank_accountnum=itemView.findViewById(R.id.bankaccnum);
            bank_name=itemView.findViewById(R.id.bankname);
            ifsccode=itemView.findViewById(R.id.IFSCcode);
            branchname=itemView.findViewById(R.id.branchname);
        }
    }
}
*/
