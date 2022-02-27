package com.nub.tourmate.MyActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nub.tourmate.R;

public class DetailActivity extends AppCompatActivity {


    private ImageView detailImageView;
    private TextView detailTittleTextVw,detailDesTextVw;
    String data,data2;
    int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        getData();
        setData();
    }


    private void init() {
        detailDesTextVw = findViewById(R.id.detailDesTextVw);
        detailTittleTextVw = findViewById(R.id.detailTittleTextVw);
        detailImageView = findViewById(R.id.imageView2);
    }
    private void setData() {
        detailTittleTextVw.setText(data);
        detailDesTextVw.setText(data2);
        detailImageView.setImageResource(image);
    }

    private void getData() {
        if(getIntent().hasExtra("data")&&getIntent().hasExtra("data2")
                && getIntent().hasExtra("image")){
            data = getIntent().getStringExtra("data");
            data2 = getIntent().getStringExtra("data2");
            image = getIntent().getIntExtra("image",1);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}
