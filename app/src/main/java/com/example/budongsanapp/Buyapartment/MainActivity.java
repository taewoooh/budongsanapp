package com.example.budongsanapp.Buyapartment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.budongsanapp.Chartapartment.Apartname.ChartActivity_apartname;
import com.example.budongsanapp.R;
import com.example.budongsanapp.TWPreference;
import com.example.budongsanapp.Util;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private final String BASE_URL = "https://taewoooh88.cafe24.com/";
    String ilbyeol_url = "https://taewoooh88.cafe24.com/showtables.php"; // 날짜별 일일 실거래가 추출
    TWPreference twPreference;
    private Retrofit retrofit;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private static String TAG = "8888888888d888";
    int hour;
    String areac;
    String ymd;
    TextView day_textview;
    TextView totalgunsu_textview;
    int seoul_count = 0;

    TextView seoul_c;
    TextView seoul_singoga;
    TextView seoul_singogayul;

    TextView gyeungi_c;
    TextView gyeungi_singoga;
    TextView gyeungi_singogayul;

    int gyeungi_count = 0;
    int gyeungi_singocount = 0;
    EditText search_edit;
    CardView day_cardview;
    ImageView delete_textimageview;
    ArrayList<String> daylist;
    ArrayList<String> daylist2;
    String daynum;
    String daynum2;
    ImageView cycle;
    TextView cardview_button;
    CardView day_cardview2;
    TextView cardview_button2;
    TextView singogun;
    TextView jisu;
    ImageView ilbyeoldata_imageview;
    int total_singocount = 0;
    int seoul_singocount = 0;

    int _singocount = 0;

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
    SwipeRefreshLayout swipeRefreshLayout;


    RvAdapter adapter;
    private static ArrayList<ListViewItem> itemArrayList;
    AlertDialog.Builder oDialog;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findview();


        twPreference = new TWPreference(this);
        twPreference.putInt("value", prefer);
        twPreference.putInt("value1", 0);


        daylist = new ArrayList();
        daylist2 = new ArrayList();

        Calendar cal = Calendar.getInstance();

        hour = cal.get(cal.HOUR_OF_DAY); //현재 시간 구하기

        // Statusbar();

        day_cardview.setOnClickListener(this);
        day_cardview2.setOnClickListener(this);
        delete_textimageview.setOnClickListener(this);
        b1.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        itemArrayList = new ArrayList<>();
        llm = new LinearLayoutManager(this);

        try {
            new ilbyeolUi_AsyncTask().execute(ilbyeol_url);
        } catch (Exception e) {


        }

        Log.e("ilbyeolui end", "");


        list_setup_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Collections.sort(itemArrayList);
                prefer++;
                twPreference.putInt("value", prefer);


                if (prefer % 2 == 0) {  //짝수
                    Collections.sort(itemArrayList);
                    DataView(); //데이터 화면에 뿌리기
                    list_setup_imageview.setColorFilter(getColor(R.color.Off_Textcolor));
                } else {  //홀수

                    list_setup_imageview.setColorFilter(getColor(R.color.On_Btcolor));


                    Collections.sort(itemArrayList);
                    DataView(); //데이터 화면에 뿌리기
                }

            }
        });


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


//        rv.setOnScrollListener(new RecyclerView.OnScrollListener() { //핀터레스트 카드뷰 기능
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 20 && cv.getVisibility() == View.VISIBLE) {
//                    cv.setVisibility(View.GONE);
//                   // Toast.makeText(getApplicationContext(),"감추기",Toast.LENGTH_SHORT).show();
//
//                    TranslateAnimation animate = new TranslateAnimation(
//                            0,                 // fromXDelta
//                            0,                 // toXDelta
//                            0,                 // fromYDelta
//                            500); // toYDelta
//                    animate.setDuration(500);
//                    animate.setFillAfter(true);
//                    cv.startAnimation(animate);
//                } else if (dy < 0 && cv.getVisibility() !=View.VISIBLE) {
//                   // Toast.makeText(getApplicationContext(),"보이기",Toast.LENGTH_SHORT).show();
//                    cv.setVisibility(View.VISIBLE);
//
//                    TranslateAnimation animate = new TranslateAnimation(
//                            0,                 // fromXDelta
//                            0,                 // toXDelta
//                            cv.getHeight(),  // fromYDelta
//                            0);                // toYDelta
//                    animate.setDuration(500);
//                    animate.setFillAfter(true);
//                    cv.startAnimation(animate);
//                }
//
//
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//
//            }
//        });


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

//    @Override
//    protected void onPause() {
//        Toast.makeText(getApplicationContext(),"onPause() Call.",Toast.LENGTH_LONG).show();
//        daylist.clear();
//        super.onPause();
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        // Toast.makeText(getApplicationContext(),"onResume() Call.",Toast.LENGTH_LONG).show();

        day_cardview.setCardBackgroundColor(getColor(R.color.On_Btcolor));
        cardview_button.setTextColor(getColor(R.color.On_Textwcolor));

        day_cardview2.setCardBackgroundColor(getColor(R.color.off_Btcolor));
        cardview_button2.setTextColor(getColor(R.color.Off_Textcolor));
        super.onResume();
    }

//    @Override
//    protected void onStart() {
//        Toast.makeText(getApplicationContext(),"onStart() Call.",Toast.LENGTH_LONG).show();
////
//        super.onStart();
//    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onRefresh() {
        String text = search_edit.getText().toString();

        if (text.equals("")) {
            daylist.clear();
            itemArrayList.clear();


            try {
                new ilbyeolUi_AsyncTask().execute(ilbyeol_url);


            } catch (Exception e) {

                swipeRefreshLayout.setRefreshing(false);
            }

        } else if (!text.equals("")) {

            search_edit.setText(null);
            swipeRefreshLayout.setRefreshing(false);
        }


    }


    public class ilbyeolUi_AsyncTask extends AsyncTask<String, Void, String> { // DB에서 일별 테이블 이름 가져오기


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            Log.d(TAG, "dhxodn2");
        }

        @Override

        protected String doInBackground(String... strings) {

            String serverURL = strings[0];

            try {

                Log.d(TAG, "dhxodn3");
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }
                Log.d(TAG, "dhxodn4");

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                Log.d(TAG, "dhxodn5" + sb.toString().trim());
                return sb.toString().trim();

            } catch (Exception e) {
                return null;
            }
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                String i;

                Log.d(TAG, "dhxodn6" + s);


                String day = s.replaceAll("[^0-9]", ""); //json 특수문자 제거 온니 숫자만

                while (day.length() != 0) {
                    i = day.substring(0, 8);
                    day = day.substring(8, day.length());

                    daylist.add(i);
                    //Log.e("ohtaewoo", "ilbyeolui end  /  "+i);
                    Log.e("ilbyeol 1", "" + day);
                    Collections.sort(daylist);
                    Collections.reverse(daylist);


                }
                daynum = daylist.get(0);


                Tongsin("DAY" + daylist.get(0));


                Log.d(TAG, "dhxodn6.5\n" + s + "\n" + daylist.toString() + "\n" + new Util().Jungbok(daylist).toString()); //배열 중복제거


            } catch (Exception e) {

                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "데이터를 가져올수 없습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }


        }


    }

    public class ilbyeolUi_AsyncTask2 extends AsyncTask<String, Void, String> { // DB에서 일별 테이블 이름 가져오기


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            Log.d(TAG, "dhxodn2");
        }

        @Override

        protected String doInBackground(String... strings) {

            String serverURL = strings[0];

            try {

                Log.d(TAG, "dhxodn3");
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }
                Log.d(TAG, "dhxodn4");

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                Log.d(TAG, "dhxodn5" + sb.toString().trim());
                return sb.toString().trim();

            } catch (Exception e) {
                return null;
            }
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {


                String i;

                Log.d(TAG, "dhxodn6" + s);


                String day = s.replaceAll("[^0-9]", ""); //json 특수문자 제거 온니 숫자만
                Log.e("ohtaewoo", "ilbyeolui end  /  " + day);
                // daylist.clear();
                while (day.length() != 0) {
                    i = day.substring(0, 8);
                    day = day.substring(8, day.length());

                    daylist2.add(i);
                    //Log.e("ohtaewoo", "ilbyeolui end  /  "+i);

                    Collections.sort(daylist2);
                    Collections.reverse(daylist2);


                }
                daynum = daylist2.get(0);

                Log.e("ohtaewoo", "ilbyeolui end  /  " + daylist.get(0));


            } catch (Exception e) {


                Toast.makeText(getApplicationContext(), "데이터를 가져올수 없습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }


        }


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.day_cardview:

                try {
                    twPreference.putInt("value1", 0);

                    day_cardview.setCardBackgroundColor(getColor(R.color.On_Btcolor));
                    cardview_button.setTextColor(getColor(R.color.On_Textwcolor));

                    day_cardview2.setCardBackgroundColor(getColor(R.color.off_Btcolor));
                    cardview_button2.setTextColor(getColor(R.color.Off_Textcolor));

                    Log.e("daylistsize  --->", "" + daylist.size());


//
//
//                    try {
//                        new ilbyeolUi_AsyncTask2().execute(ilbyeol_url);
//                    } catch (Exception e) {
//
//
//                    }
//


                    AlerDialog();


                    search_edit.setText(null);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "데이터를 가져올수 없습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();


                }


                break;

            case R.id.day_cardview2:
                day_cardview2.setCardBackgroundColor(getColor(R.color.On_Btcolor));
                cardview_button2.setTextColor(getColor(R.color.On_Textwcolor));
                day_cardview.setCardBackgroundColor(getColor(R.color.off_Btcolor));
                cardview_button.setTextColor(getColor(R.color.Off_Textcolor));


                twPreference.putInt("value1", 1);

                //Tongsin();
                // itemArrayList.clear();

                // Intent intent = new Intent(this, ChartActivity_bup.class);
                Intent intent = new Intent(this, ChartActivity_apartname.class);
                startActivity(intent);
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

    public void Statusbar() {  //  상태바 하얀 색상에 회색 아이콘 색상을 설정
        View view = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
            }
        } else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    public void AlerDialog() { //알럿 다이얼로그 리스트뷰


        final CharSequence[] oItems = daylist.toArray(new String[daylist.size()]);


        oDialog = new AlertDialog.Builder(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

        oDialog.setTitle("날짜를 선택하세요")
                .setItems(oItems, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        rv.getRecycledViewPool().clear();
                        adapter.notifyDataSetChanged();

                        total_singocount = 0;

//                        Toast.makeText(getApplicationContext(),
//                                oItems[which], Toast.LENGTH_LONG).show();
                        daynum = (String) oItems[which];
                        tablecode = "DAY" + oItems[which];
                        //tablecode = "totaldata";


                        Tongsin(tablecode);
                        itemArrayList.clear();


                    }
                })
                .setCancelable(true)
                .show();


    }


    public void init() {
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void findview() {
        rv = (RecyclerView) findViewById(R.id.main_rv);//
        day_textview = (TextView) findViewById(R.id.day_textview);
        //totalgunsu_textview = (TextView) findViewById(R.id.totalgunsu_textview);
        search_edit = (EditText) findViewById(R.id.search_edit);
        day_cardview = (CardView) findViewById(R.id.day_cardview);
        delete_textimageview = (ImageView) findViewById(R.id.delete_textImageview);
        cardview_button = (TextView) findViewById(R.id.cardview_button);
        day_cardview2 = (CardView) findViewById(R.id.day_cardview2);
        cardview_button2 = (TextView) findViewById(R.id.cardview_button2);
        //singogun = (TextView) findViewById(R.id.singogun);
        //jisu = (TextView) findViewById(R.id.jisu);
        // cv = (CardView) findViewById(R.id.cv);//
        list_setup_imageview = (ImageView) findViewById(R.id.list_setup);
        //ilbyeoldata_imageview = (ImageView) findViewById(R.id.ilbyeoldata); //
        // main_layout = (RelativeLayout) findViewById(R.id.main_layout); //
        cycleimageview = (ImageView) findViewById(R.id.cycleimageview);
        b1 = (CardView) findViewById(R.id.b1);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        seoul_c = (TextView) findViewById(R.id.seoul_c);
        seoul_singoga = (TextView) findViewById(R.id.seoul_singoga);
        seoul_singogayul = (TextView) findViewById(R.id.seoul_singogayul);

        gyeungi_c = (TextView) findViewById(R.id.  gyeungi_c);
        gyeungi_singoga = (TextView) findViewById(R.id.  gyeungi_singoga);
        gyeungi_singogayul = (TextView) findViewById(R.id.  gyeungi_singogayul);





    }

    public void Tongsin(String tablecode) { // 서버 데이터를 가지고 온다 파라미터는 불러올 테이블 이름


        init();
        GitHub gitHub = retrofit.create(GitHub.class);
        Call<List<ListViewItem>> call = gitHub.contributors(tablecode);
        call.enqueue(new Callback<List<ListViewItem>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            // 성공시
            public void onResponse(Call<List<ListViewItem>> call, Response<List<ListViewItem>> response) {
                total_singocount = 0;
                seoul_singocount = 0;
                seoul_count = 0;
                gyeungi_count = 0;
                gyeungi_singocount = 0;
                List<ListViewItem> contributors = response.body();
                // 받아온 리스트를 순회하면서


                // use response data and do some fancy stuff :)

                Log.e("Test888", response.body().toString());


                for (ListViewItem contributor : contributors) {


                    String name = contributor.name;
                    int price = contributor.price;
                    String area = contributor.area;
                    String year = contributor.year;
                    String month = contributor.month;
                    String day = contributor.day;
                    String high = contributor.high;
                    String doromyung = contributor.doromyung;
                    String jibun = contributor.jibun;
                    String geunmulcode = contributor.geunmulcode;
                    String jiyeokcode = contributor.jiyeokcode;
                    String bupjungdong = contributor.bupjungdong;
                    String gunchukyear = contributor.gunchukyear;

                    String hightprice = contributor.hightprice;
                    String hightyear = contributor.hightyear;
                    String hightmonth = contributor.hightmonth;
                    String hightday = contributor.hightday;
                    int chaik = contributor.chaik;

                    String pyungmyuendo = contributor.pyungmyuendo;
                    String chongdongsu = contributor.chongdongsu;
                    String chongsedaesu = contributor.chongsedaesu;
                    String juchadaesu = contributor.juchadaesu;
                    String pyungeunjucha = contributor.pyungeunjucha;
                    String yongjeukryul = contributor.yongjeukryul;
                    String gunpaeyul = contributor.gunpaeyul;
                    String ganrisamuso = contributor.ganrisamuso;
                    String nanbang = contributor.nanbang;
                    String gunseoulsa = contributor.gunseoulsa;
                    String jihachul = contributor.jihachul;
                    String mart = contributor.mart;
                    String hospital = contributor.hospital;
                    String park = contributor.park;
                    String cho = contributor.cho;
                    String jung = contributor.jung;
                    String go = contributor.go;
                    String arin = contributor.arin;
                    String you = contributor.you;
                    Log.e("t2", "가나다라" + " / " + name + " / " + bupjungdong + " / " + area + " / " + seoul_count);

                    try { // 신고가 카운트 하기
                        //i_price = Integer.parseInt(price.replaceAll(",", "").replaceAll("\\p{Z}", ""));
                        i_highprice = Integer.parseInt(hightprice.replaceAll(",", "").replaceAll("\\p{Z}", ""));


                        if (price > i_highprice) {
                            total_singocount++;

                        }
                        if (bupjungdong.contains("서울특별시") && price > i_highprice) {
                            seoul_singocount++;


                        }
                        if (bupjungdong.contains("경기도") && price > i_highprice) {
                            gyeungi_singocount++;


                        }

                    } catch (Exception e) {
                    }


                    areac = new Util().AreaChange(area);   // 평형 바꾸기
                    ymd = new Util().Ymd(year, month, day); // 년월일
                    month = month.replace(",", "");
                    // Log.d("dhxodn1988", "" + ymd);

                    itemArrayList.add(new ListViewItem(name, price, area, year, month, day,
                            high, doromyung, jibun, geunmulcode,
                            jiyeokcode, bupjungdong, gunchukyear, hightprice,
                            hightyear, hightmonth, hightday, areac, ymd, chaik, pyungmyuendo, chongdongsu, chongsedaesu, juchadaesu, pyungeunjucha,
                            yongjeukryul, gunpaeyul, ganrisamuso, nanbang, gunseoulsa, jihachul, mart, hospital, park, cho, jung, go, arin, you));
                    Collections.sort(itemArrayList);


                    Log.e("오하람", "" + name + " / " + price);
                    DataView(); //데이터 화면에 뿌리기

                    if (bupjungdong.matches(".*서울특별시.*")) {
                        seoul_count++;





                    } else {
                        gyeungi_count++;



                    }


                }

                int total_c=seoul_count+gyeungi_count;
                int total_sc=seoul_singocount+gyeungi_singocount;

                double seoul_v = (double) seoul_singocount / (double) seoul_count * 100;
                double gyeungi_v = (double) gyeungi_singocount / (double) gyeungi_count * 100;
                double total2_v = (double) total_sc / (double) total_c * 100;



                Log.e("taewoooh88", "서울특별시"+ " / " + seoul_count+" / "+seoul_singocount+" / "+String.format("%.0f", seoul_v));
                Log.e("taewoooh88", "경기도"+ " / " + gyeungi_count + " / " + gyeungi_singocount+" / "+String.format("%.0f", gyeungi_v));
                Log.e("taewoooh88", "서울경기,경기도"+ " / " + total_c + " / " + total_sc+ " / "+total2_v);


                seoul_c.setText(String.valueOf(seoul_count));
                seoul_singoga.setText(String.valueOf(seoul_singocount));
                seoul_singogayul.setText(String.format("%.0f", seoul_v));


                gyeungi_c.setText(String.valueOf(gyeungi_count));
                gyeungi_singoga.setText(String.valueOf(gyeungi_singocount));
                gyeungi_singogayul.setText(String.format("%.0f", gyeungi_v));

            }

            @Override
            // 실패시
            public void onFailure(Call<List<ListViewItem>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void DataView() {  // itemArraylist 에 담김 데이터를 화면에 뿌려준다


        Collections.sort(itemArrayList);


        Log.e("오태우", " / " + itemArrayList.get(0).getName() + " / " + itemArrayList.get(0).getMart());


        adapter = new RvAdapter(itemArrayList, MainActivity.this);
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




//        double total_v = (double) total_singocount / (double) itemArrayList.size() * 100;
//        totalgunsu_textview.setText(String.valueOf(itemArrayList.size()));
//        singogun.setText(String.valueOf(total_singocount));
//        jisu.setText(String.valueOf(String.format("%.0f", total_v)));



        swipeRefreshLayout.setRefreshing(false);

    }

}