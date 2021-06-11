package com.example.budongsanapp.Chartapartment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budongsanapp.R;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    CardView day_cardview;
    CardView day_cardview2;
    TextView cardview_button;
    TextView cardview_button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        findview();

        day_cardview.setOnClickListener(this);


    }


    public void findview() {
        day_cardview = (CardView) findViewById(R.id.day_cardview);
        day_cardview2 = (CardView) findViewById(R.id.day_cardview2);
        cardview_button = (TextView) findViewById(R.id.cardview_button);
        cardview_button2 = (TextView) findViewById(R.id.cardview_button);


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.day_cardview :

                day_cardview.setCardBackgroundColor(getColor(R.color.On_Btcolor));
                cardview_button.setTextColor(getColor(R.color.On_Textwcolor));

                day_cardview2.setCardBackgroundColor(getColor(R.color.off_Btcolor));
                cardview_button2.setTextColor(getColor(R.color.Off_Textcolor));

                finish();

                break;



        }

    }
}