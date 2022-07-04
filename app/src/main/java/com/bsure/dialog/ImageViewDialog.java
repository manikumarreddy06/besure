package com.bsure.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;

import com.bsure.R;
import com.bsure.Utils;


public class ImageViewDialog extends Dialog {


    public ImageViewDialog(Context context, String imageUrl) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_dialog);
        ImageView cancelButton = findViewById(R.id.imCancel);

        ImageView imageView = findViewById(R.id.imageView);
        setCancelable(true);

        Utils.Companion.loadImage(context, imageView, imageUrl, false);

        cancelButton.setOnClickListener(v->{
            dismiss();
        });

    }


}
