package com.example.budongsanapp.Chartapartment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budongsanapp.Chartapartment.GitHub2;
import com.example.budongsanapp.Chartapartment.ListViewItem2;
import com.example.budongsanapp.Chartapartment.ChartActivity;
import com.example.budongsanapp.Chartapartment.RvAdapter2;
import com.example.budongsanapp.R;
import com.example.budongsanapp.TWPreference;
import com.example.budongsanapp.Util;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    CardView day_cardview;
    CardView day_cardview2;
    TextView cardview_button;
    TextView cardview_button2;
    private Retrofit retrofit;


    private final String BASE_URL = "https://taewoooh88.cafe24.com/";
    String ilbyeol_url = "https://taewoooh88.cafe24.com/showtables.php"; // 날짜별 일일 실거래가 추출
    TWPreference twPreference;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private static String TAG = "8888888888888";
    int hour;
    String areac;
    String ymd;
    TextView day_textview;
    TextView datavalue_textview;
    EditText search_edit;

    ImageView delete_textimageview;
    ArrayList<String> daylist;
    String daynum;
    ImageView cycle;

    TextView singogun;
    TextView jisu;
    ImageView ilbyeoldata_imageview;
    int count = 0;
    ImageView list_setup_imageview;
    ImageView cycleimageview;
    String tablecode = "";
    int prefer = 0;
    CardView b1;
    CardView cv;
    RelativeLayout main_layout;

    BottomSheetDialog bottomSheetDialog;
    int i_price = 0;
    int i_highprice = 0;

    RelativeLayout bottomsheet;

    private static ArrayList<ListViewItem2> itemArrayList;
    RvAdapter2 adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        findview();

        Tongsin("bupjungdong_nonstop");
        llm = new LinearLayoutManager(this);
        day_cardview.setOnClickListener(this);
        day_cardview2.setOnClickListener(this);

        itemArrayList = new ArrayList<>();
    }


//    @Override
//    protected void onStop() {
//        Toast.makeText(getApplicationContext(),"onStop() Call.",Toast.LENGTH_LONG).show();
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        Toast.makeText(getApplicationContext(),"onDestroy() Call.",Toast.LENGTH_LONG).show();
//
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onPause() {
//        Toast.makeText(getApplicationContext(),"onPause() Call.",Toast.LENGTH_LONG).show();
//        //daylist.clear();
//        super.onPause();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    protected void onResume() {
//       Toast.makeText(getApplicationContext(),"onResume() Call.",Toast.LENGTH_LONG).show();
//
//
//        super.onResume();
//    }
//
//    @Override
//    protected void onStart() {
//        Toast.makeText(getApplicationContext(),"onStart() Call.",Toast.LENGTH_LONG).show();
//        super.onStart();
//    }

    public void Tongsin(String tablecode) { // 서버 데이터를 가지고 온다 파라미터는 불러올 테이블 이름


        init();
        GitHub2 gitHub = retrofit.create(GitHub2.class);
        Call<List<com.example.budongsanapp.Chartapartment.ListViewItem2>> call = gitHub.contributors(tablecode);
        call.enqueue(new Callback<List<com.example.budongsanapp.Chartapartment.ListViewItem2>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            // 성공시
            public void onResponse(Call<List<com.example.budongsanapp.Chartapartment.ListViewItem2>> call, Response<List<com.example.budongsanapp.Chartapartment.ListViewItem2>> response) {
                List<com.example.budongsanapp.Chartapartment.ListViewItem2> contributors = response.body();
                // 받아온 리스트를 순회하면서
                //Log.e("Test8888", response.body().toString());

                for (com.example.budongsanapp.Chartapartment.ListViewItem2 contributor : contributors) {


                    String bupjungdong = contributor.bupjungdong;
                    int totalgunsu = contributor.totalgunsu;
                    int singogunsu = contributor.singogunsu;
                    int inflation = contributor.inflation;


                    Log.e("TW", "" + bupjungdong + " / " + totalgunsu + " / " + singogunsu+ " / " +inflation);

                    itemArrayList.add(new com.example.budongsanapp.Chartapartment.ListViewItem2(bupjungdong, totalgunsu, singogunsu, inflation));
                    Collections.sort(itemArrayList);
                    try {
                        DataView();
                    } catch (Exception e) {
                       //데이터 화면에 뿌리기


                    }


                }


            }

            @Override
            // 실패시
            public void onFailure(Call<List<ListViewItem2>> call, Throwable t) {

                Toast.makeText(ChartActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
            }
        });


    }

    public void init() {
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void DataView() {  // itemArraylist 에 담김 데이터를 화면에 뿌려준다


        Collections.sort(itemArrayList);


        adapter = new RvAdapter2(itemArrayList, ChartActivity.this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);


        day_textview.setText(daynum);


        if (Integer.parseInt(new Util().Getday()) == Integer.parseInt(daynum)) {


            Log.e("cycleimageview", "On" + new Util().Getday() + " / " + daynum);


            cycleimageview.setVisibility(View.VISIBLE);
        } else if (Integer.parseInt(new Util().Getday()) != Integer.parseInt(daynum)) {
            Log.e("cycleimageview", "off " + new Util().Getday() + " / " + daynum);

            cycleimageview.setVisibility(View.INVISIBLE);


        }

        datavalue_textview.setText(String.valueOf(itemArrayList.size()));


        double v = (double) count / (double) itemArrayList.size() * 100;

        singogun.setText(String.valueOf(count));
        jisu.setText(String.valueOf(String.format("%.0f", v)));


    }

    public void findview() {
        day_cardview = (CardView) findViewById(R.id.day_cardview);
        day_cardview2 = (CardView) findViewById(R.id.day_cardview2);
        cardview_button = (TextView) findViewById(R.id.cardview_button);
        cardview_button2 = (TextView) findViewById(R.id.cardview_button);
        rv = (RecyclerView) findViewById(R.id.main_rv);//
        day_textview = (TextView) findViewById(R.id.day_textview);
        datavalue_textview = (TextView) findViewById(R.id.datavalue_textview);
        search_edit = (EditText) findViewById(R.id.search_edit);
        delete_textimageview = (ImageView) findViewById(R.id.delete_textImageview);
        singogun = (TextView) findViewById(R.id.singogun);
        jisu = (TextView) findViewById(R.id.jisu);
        // cv = (CardView) findViewById(R.id.cv);//
        list_setup_imageview = (ImageView) findViewById(R.id.list_setup);
        //ilbyeoldata_imageview = (ImageView) findViewById(R.id.ilbyeoldata); //
        // main_layout = (RelativeLayout) findViewById(R.id.main_layout); //
        cycleimageview = (ImageView) findViewById(R.id.cycleimageview);
        b1 = (CardView) findViewById(R.id.b1);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.day_cardview:

                day_cardview.setCardBackgroundColor(getColor(R.color.On_Btcolor));
                cardview_button.setTextColor(getColor(R.color.On_Textwcolor));

                day_cardview2.setCardBackgroundColor(getColor(R.color.off_Btcolor));
                cardview_button2.setTextColor(getColor(R.color.Off_Textcolor));

                finish();

                break;

            case R.id.day_cardview2:


                Toast.makeText(getApplicationContext(), "차트 Call.", Toast.LENGTH_LONG).show();

                break;


        }

    }
}