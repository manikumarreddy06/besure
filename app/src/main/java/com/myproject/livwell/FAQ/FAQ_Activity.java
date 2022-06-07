package com.myproject.livwell.FAQ;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.myproject.livwell.ContactUs;
import com.myproject.livwell.R;

import java.util.ArrayList;

public class FAQ_Activity extends AppCompatActivity {
    ArrayList<FAQ_items>FAQ_itemsList;
    RecyclerView recyclerView;
    Button sendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);


        // adding data to the arraylist
        recyclerView = findViewById(R.id.recycler_view);
        initData();
        setRecyclerView();

        // sending message
        sendMessage = findViewById(R.id.btn_send_a_Msg);
        sendMessage.setOnClickListener(view -> {
            Intent i=new Intent(FAQ_Activity.this, ContactUs.class);
            startActivity(i);
        });
    }

    private void setRecyclerView() {
        FAQ_item_Adapter faq_item_adapter = new FAQ_item_Adapter(FAQ_itemsList);
        recyclerView.setAdapter(faq_item_adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        FAQ_itemsList = new ArrayList<>();
        FAQ_itemsList.add(new FAQ_items("What does Bsure.live do?","\nHere is the list of things Bsure do\n" +
                "\t \u25CF Store your asset details in a secure platform\n" +
                "\t \u25CF Tracks your wellbeing and nudge you time to time to know your status\n" +
                "\t \u25CF Shares the asset details to your family members at unfortunate events\n" +
                "\t \u25CF Helps your family to get the assets transferred to your name\n"));

        FAQ_itemsList.add(new FAQ_items("Why Bsure.live charges a subscription fee?","\nThere are 2 kind of companies in this world\n" +
                "\t \u25CF Companies who offer services for free: Are the people who sell your personal information to other companies and get money out of you.\n" +
                "\t \u25CF Companies who charge a fee to give a service: They charge for the service they offer and don’t want to play around with your personal data\n"+
                "\t Bsure understands the value of your personal data and will not share it with anyone for any monetary gain. Your data is safe and secure and is stored in encrypted format. No one will have access to your financial information."));

        FAQ_itemsList.add(new FAQ_items("Who can be a nominee?","\nYou can decide any person as your nominee. Please note that the nominee you have selected may have rights over your properties and asset details eventually. In normal cases users nominate their spouse, kids and parents as nominees."));

        FAQ_itemsList.add(new FAQ_items("When can the information be shared with the nominee?","\nThe asset information can be shared with the nominee in following conditions\n" +
                "\t \u25CF Loss of life of the user : Bsure will ask for the details on loss of life (death certificate and validate the same from user mobile, email)\n" +
                "\t \u25CF Other Exigencies: User have encountered life threatening disease and a proof of the same is provided to Bsure.live, the validation from user is successful\n"));

        FAQ_itemsList.add(new FAQ_items("Can a minor be a nominee?","\nYes. A minor can be a nominee. But make sure to  appoint a guardian with high morals and is able to ensure that the money is passed on to the minor child once he/she is eligible.\n"));

        FAQ_itemsList.add(new FAQ_items("What details does the nominee need to provide to get the access to information?","\nIn normal circumstances, the nominee needs to provide the following details\n" +
                "\t \u25CF Death certificate of the user\n" +
                "\t \u25CF Nominees identity proof\n"));

        FAQ_itemsList.add(new FAQ_items("Things to be noted while filling the asset details","\nEnsure to fill all the columns of asset details\n" +
                "\t \u25CF It is highly recommended to put the folio number, bank account no, policy number\n" +
                "\t \u25CF We also recommend to attach a copy of the policy/bank statement in Bsure.live\n" +
                "\t \u25CF Ensure to fill the legal name of the nominees\n" +
                "\t \u25CF We recommend to attach a copy of identity proof of the nominees\n"));

        FAQ_itemsList.add(new FAQ_items("Who has the right over the wealth? The nominees mentioned on bank/investments or the nominees mentioned on the will?","\nIn most of the cases, the nominees mentioned on the will have a claim over the nominees mentioned on the investments/bank accounts.\n"));

        FAQ_itemsList.add(new FAQ_items("There are more than 1 wills registered for the user. Which “will” will have merit?","\nIn normal circumstances, the latest registered “will” precedes all the previous wills.\n" ));

        FAQ_itemsList.add(new FAQ_items("Should a digital will be enough or I need to register the will?","\nBsure encourages the users to opt for digital will as it has more legal validity. However registering the will is costly and the user has to bear the charges of registration. If the family doesn’t raise an objection in the court, even the digital will be enough.\n" ));

        FAQ_itemsList.add(new FAQ_items("What else is required to make a will?","\nA will should normally have the following\n" +
                "\t \u25CF 2 witnesses who understand that the “Will” is made in sound mind and without any forcefulness\n" +
                "\t \u25CF An executor of the will: Executor will ensure to execute the will after demise of the will creator\n" ));

    }
}