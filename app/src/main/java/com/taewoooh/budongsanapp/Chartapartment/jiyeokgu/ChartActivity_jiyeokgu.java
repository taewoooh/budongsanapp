package com.taewoooh.budongsanapp.Chartapartment.jiyeokgu;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.taewoooh.budongsanapp.Buyapartment.MainActivity;
import com.taewoooh.budongsanapp.Chartapartment.Apartname.ChartActivity_apartname;
import com.taewoooh.budongsanapp.Chartapartment.Apartname.ChartDialogClickListener;
import com.taewoooh.budongsanapp.Chartapartment.Bupjungdong.ChartActivity_bup;

import com.taewoooh.budongsanapp.R;
import com.taewoooh.budongsanapp.TWPreference;
import com.taewoooh.budongsanapp.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartActivity_jiyeokgu extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    CardView day_cardview;
    CardView day_cardview2;
    TextView cardview_button;
    TextView cardview_button2;
    private Retrofit retrofit;


    private final String BASE_URL = "https://taewoooh88.cafe24.com/";

    TWPreference twPreference;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private static String TAG = "8888888888888";


    TextView contents;

    EditText search_edit;

    ImageView delete_textimageview;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView bup;

    ImageView list_setup_imageview;
    ImageView cycleimageview;
    String tablecode = "";
    int prefer = 0;
    CardView b1;
    CardView cv;
    TextView totalgunsu;
    TextView singogagunsu;
    TextView inflation;
    TextView today;

    Chart_jiyeokgu_dialog dialog;


    private static ArrayList<ListViewItem4> itemArrayList;
    RvAdapter4 adapter;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_main);
        findview();
        ContentsStory();


        twPreference = new TWPreference(this);
        twPreference.putInt("value", prefer);
        twPreference.putInt("value1", 0);


        Tongsin("jiyeokgu_nonstop");
        llm = new LinearLayoutManager(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        delete_textimageview.setOnClickListener(this);
        day_cardview.setOnClickListener(this);
        day_cardview2.setOnClickListener(this);
        b1.setOnClickListener(this);

        itemArrayList = new ArrayList<>();
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = search_edit.getText().toString()
                        .toLowerCase(Locale.getDefault());
                adapter.filter(text);


                if (s.length() > 0) {

                    delete_textimageview.setVisibility(View.VISIBLE);


                } else if (s.length() == 0) {
                    delete_textimageview.setVisibility(View.INVISIBLE);

                }

            }
        });
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() { //핀터레스트 카드뷰 기능
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 80) {
//                    Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
//                            slid_up);
//                    b1.startAnimation(bottomUp);
                    b1.setVisibility(View.VISIBLE);

                    // Toast.makeText(getApplicationContext(),"감추기",Toast.LENGTH_SHORT).show();

                } else if (dy == 0 || !rv.canScrollVertically(-1)) {
                    // Toast.makeText(getApplicationContext(),"보이기",Toast.LENGTH_SHORT).show();
//                    Animation bottomdown = AnimationUtils.loadAnimation(getApplicationContext(),
//                            slid_up);
//                    b1.startAnimation(bottomdown);
                    b1.setVisibility(View.GONE);


                }


            }


        });

        list_setup_imageview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                // Collections.sort(itemArrayList);
                prefer++;
                twPreference.putInt("value", prefer);


                if (prefer % 2 == 0) {  //짝수
                    Collections.sort(itemArrayList);
                    DataView(); //데이터 화면에 뿌리기
                    list_setup_imageview.setColorFilter(getColor(R.color.Off_Textcolor));
                    contents.setText("거래건수");


                } else {  //홀수

                    list_setup_imageview.setColorFilter(getColor(R.color.On_Btcolor));


                    Collections.sort(itemArrayList);
                    DataView(); //데이터 화면에 뿌리기
                    contents.setText("신고가율순");
                }

            }
        });


        dialog = new Chart_jiyeokgu_dialog(this, new ChartDialogClickListener() {
            @Override
            public void onNameClicked() {
                Intent intent = new Intent(getApplicationContext(), ChartActivity_apartname.class);

                startActivity(intent);
                finish();
            }

            @Override
            public void onJiyoekdongClicked() {
                Intent intent = new Intent(getApplicationContext(), ChartActivity_bup.class);

                startActivity(intent);
                finish();
            }

            @Override
            public void onJiyeokguClicked() {

            }
        });


        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ContentsStory() {
        today.setText(new Util().Getday2()); // 이번달 표시
        bup.setText("법정동");

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
        GitHub4 gitHub = retrofit.create(GitHub4.class);
        Call<List<ListViewItem4>> call = gitHub.contributors(tablecode);
        call.enqueue(new Callback<List<ListViewItem4>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            // 성공시
            public void onResponse(Call<List<ListViewItem4>> call, Response<List<ListViewItem4>> response) {
                List<ListViewItem4> contributors = response.body();
                // 받아온 리스트를 순회하면서
                //Log.e("Test8888", response.body().toString());

                for (ListViewItem4 contributor : contributors) {


                    String jiyeokgu = contributor.jiyeokgu;
                    int totalgunsu = contributor.totalgunsu;
                    int singogunsu = contributor.singogunsu;
                    int inflation = contributor.inflation;


                    Log.e("TW", "" + jiyeokgu + " / " + totalgunsu + " / " + singogunsu + " / " + inflation);


                    itemArrayList.add(new ListViewItem4(jiyeokgu, totalgunsu, singogunsu, inflation));
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
            public void onFailure(Call<List<ListViewItem4>> call, Throwable t) {

                Log.d("deberg", "------->" + t.toString());
                Toast.makeText(ChartActivity_jiyeokgu.this, "정보받아오기 실패", Toast.LENGTH_LONG)
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
    public void
    DataView() {  // itemArraylist 에 담김 데이터를 화면에 뿌려준다


        Collections.sort(itemArrayList);


        adapter = new RvAdapter4(itemArrayList, ChartActivity_jiyeokgu.this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        if (Integer.parseInt(new Util().Getday2()) == Integer.parseInt((String) today.getText())) {


            Log.e("cycleimageview2", "On ->  " + new Util().Getday2() + " / " + Integer.parseInt((String) today.getText()));


            cycleimageview.setVisibility(View.VISIBLE);
        } else if (Integer.parseInt(new Util().Getday2()) != Integer.parseInt((String) today.getText())) {
            Log.e("cycleimageview2", "off -> " + new Util().Getday2() + " / " + Integer.parseInt((String) today.getText()));

            cycleimageview.setVisibility(View.INVISIBLE);


        }

    }

    public void findview() {
        day_cardview = (CardView) findViewById(R.id.day_cardview);
        day_cardview2 = (CardView) findViewById(R.id.day_cardview2);
        cardview_button = (TextView) findViewById(R.id.cardview_button);
        cardview_button2 = (TextView) findViewById(R.id.cardview_button);
        rv = (RecyclerView) findViewById(R.id.main_rv);//
        // day_textview = (TextView) findViewById(R.id.day_textview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        search_edit = (EditText) findViewById(R.id.search_edit);
        delete_textimageview = (ImageView) findViewById(R.id.delete_textImageview);
        // singogun = (TextView) findViewById(R.id.singogun);
        // jisu = (TextView) findViewById(R.id.jisu);
        // cv = (CardView) findViewById(R.id.cv);//
        list_setup_imageview = (ImageView) findViewById(R.id.list_setup);
        //ilbyeoldata_imageview = (ImageView) findViewById(R.id.ilbyeoldata); //
        // main_layout = (RelativeLayout) findViewById(R.id.main_layout); //
        cycleimageview = (ImageView) findViewById(R.id.cycleimageview);
        b1 = (CardView) findViewById(R.id.b1);
        contents = (TextView) findViewById(R.id.contents);
        bup = (TextView) findViewById(R.id.bup);
        singogagunsu = (TextView) findViewById(R.id.singogunsu);
        inflation = (TextView) findViewById(R.id.inflation);
        totalgunsu = (TextView) findViewById(R.id.totalgunsu);
        today = (TextView) findViewById(R.id.today);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);


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

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

                dialog.dismiss();


                finish();

                break;

            case R.id.day_cardview2:

                dialog.show();



                break;

            case R.id.delete_textImageview:

                search_edit.setText(null);


                break;
            case R.id.b1:


                llm.scrollToPositionWithOffset(0, 0);

                //rv.smoothScrollToPosition(0);

                break;

        }

    }

    @Override
    public void onRefresh() {
        itemArrayList.clear();
        Tongsin("jiyeokgu_nonstop");
        swipeRefreshLayout.setRefreshing(false);

    }
}