package com.example.image.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.image.R;

public class ClickedPhotoActivity extends AppCompatActivity {

    ImageView fullImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_photo);

        fullImage = findViewById(R.id.full_image);

        Intent intent = getIntent();
        Bundle bundleImage = intent.getExtras();

        if (bundleImage != null) {
            String image = (String) bundleImage.get("image");
            Glide.with(this).load(image).into(fullImage);
        }
    }
}