package com.bsure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bsure.R;

public class ContactUs extends AppCompatActivity {
    ImageView instagram, linkedIn, email, call, website;
    EditText et_name, et_mail, et_suggestion;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        instagram = findViewById(R.id.iv_instagram);
        linkedIn = findViewById(R.id.iv_linkedIn);
        email = findViewById(R.id.iv_mail);
        call = findViewById(R.id.iv_call);
        website = findViewById(R.id.iv_website);
        et_name = findViewById(R.id.et_name);
        et_mail = findViewById(R.id.et_mailId);
        et_suggestion = findViewById(R.id.et_suggestion);
        send = findViewById(R.id.btn_send);

        instagram.setOnClickListener(view -> {

        });
        linkedIn.setOnClickListener(view -> {

        });
        email.setOnClickListener(view -> {

        });
        call.setOnClickListener(view -> {

        });
        website.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bsure.live/"));
            startActivity(browserIntent);
        });
        send.setOnClickListener(view -> {
            String[] address = {"suggestions@bsure.live"};
            String subject = "Suggestion to Bsure";
            String body = et_suggestion.getText().toString();
            composeEmail(address,subject, body);
        });
    }
    public void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}