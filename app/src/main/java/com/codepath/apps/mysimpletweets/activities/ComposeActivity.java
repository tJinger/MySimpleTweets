package com.codepath.apps.mysimpletweets.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.squareup.picasso.Picasso;

public class ComposeActivity extends AppCompatActivity {
    private ImageView ivProfileImage;
    private TextView tvName;
    private TextView tvAtName;
    private EditText etTweet;
    private TextView tvChar;
    private int totalChar = 140;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_twitter);

        String screenName = getIntent().getStringExtra("screenName");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
        tvName = (TextView) findViewById(R.id.tvName);
        tvAtName = (TextView) findViewById(R.id.tvAtName);
        tvChar = (TextView)findViewById(R.id.tvChar);
        tvChar.setText(String.valueOf(totalChar));
        etTweet = (EditText)findViewById(R.id.etTweet);
        etTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvChar.setText(String.valueOf(totalChar - s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvName.setText(screenName);
        tvAtName.setText("@"+screenName);
        Picasso.with(this).load(imageUrl).into(ivProfileImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tweet_compose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miTweet:
                sendTweet();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendTweet() {
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("tweet", etTweet.getText().toString());
        Log.d("DEBUG", etTweet.getText().toString());
        data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish();
    }
}
