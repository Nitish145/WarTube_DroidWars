package com.aggarwals.wartube;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;

public class profile extends Activity {

    private TextView ProfileText;
    private ImageView profileImage;
    private TextView ProfileName;
    private TextView ProfileEmail;

    @Override
    protected void onCreate(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ProfileText = findViewById(R.id.ProfileText);
        ProfileName = findViewById(R.id.ProfileName);
        ProfileEmail = findViewById(R.id.ProfileEmail);
        profileImage = findViewById(R.id.profileImage);

        Uri photoUrl = Uri.parse(getIntent().getExtras().getString("photoUrl"));

        ProfileName.setText(getIntent().getExtras().getString("name"));
        ProfileEmail.setText(getIntent().getExtras().getString("email"));
        ProfileText.setText("PROFILE");

        Glide.with(this)
                .load(photoUrl)
                .into(profileImage);

    }
}
