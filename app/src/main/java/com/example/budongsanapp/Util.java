package com.example.budongsanapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by taewoo on 2020-02-18.
 */

public class Util {
    Context context;
    ProgressBar progress;

    public Util(Context context) {
        this.context = context;
        progress = new ProgressBar(context);
    } //기능 모음집  return 메서드만

    public Util() {
    } //기

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String Getday() { //현재 날짜구하기


        Date currentTime = Calendar.getInstance().getTime();
        String date_text = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(currentTime);


        return date_text;

    }

    public String AreaChange(String area) { //평형 바꾸기
        double value;
        value = Double.valueOf(area) / 2.45;
        area = String.valueOf(value);

        try {
            Double aDouble = Double.parseDouble(area);
            return String.format("%.0f", aDouble);  // 원하는 자릿수 1자리 = %.1f  2자리 %.2f
        } catch (Exception e) {
            return "0";
        }


    }

    public String Priceedit(String price) {  // 금액 한글로 변경하기

        Log.e("taewoooh88", "");


        if (price == null || price.isEmpty()) {
            return null;
        } else {


            double db = Double.valueOf(price.replaceAll("\\,", ""));//쉼표 제거하기


            DecimalFormat d = new DecimalFormat("#,####");

            String[] unit = new String[]{"", "억 "};
            String[] str = d.format(db).split(",");
            String result = "";
            int cnt = 0;
            for (int i = str.length; i > 0; i--) {
                if (Integer.parseInt(str[i - 1]) != 0) {
                    result = String.valueOf(Integer.parseInt(str[i - 1])) + unit[cnt] + result;
                }
                cnt++;
            }


            return result;
        }
    }

    public int Areachange(String pyungmyuendo, String area) {  //전용면적을 평수로 변경
        int i1 = 0;
        double v = Double.parseDouble(area);

        String[] strings = pyungmyuendo.split(" ");

        for (int i = 0; i < strings.length; i++) {

            if (v == Double.parseDouble(strings[i])) {

                double v1 = Double.parseDouble(strings[i - 1]);
                double v2 = v1 * 3;
                v2 = v2 / 10;

                i1 = Integer.parseInt(String.valueOf(Math.round(v2)));


            }


        }

        return i1;
    }


    public String Ymd(String year, String moonth, String day) { // 년도 점 붙히기

        String ymd;

        year = year.substring(2);

        ymd = year + "." + moonth + "." + day;


        return ymd;
    }


    public List Jungbok(ArrayList arlList) { // 중복제거 및 순차 정렬 함수

        HashSet set = new HashSet();

        List newList = new ArrayList();

        for (Iterator iter = arlList.iterator(); iter.hasNext(); ) {

            Object element = iter.next();

            if (set.add(element)) newList.add(element);

        }

        arlList.clear();

        arlList.addAll(newList);


        return arlList;

    }

    public String Daygagong(String hightyear, String hightmonth, String hightday) {


        String daygagong;

        daygagong = hightyear + "." + hightmonth + "." + hightday;


        return daygagong;
    }

    public void loading() {
        //로딩
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        progress.setIndeterminate(true);

                        progress.setVisibility(View.VISIBLE);
                    }
                }, 0);
    }

    public void loadingEnd() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                    }
                }, 0);
    }

    public String pyungdangga(String price, String pyungsu) {


        double p = Double.parseDouble(price);
        double py = Double.parseDouble(pyungsu);

        double i = py / p;

       String a=String.format("%.0f", i);









        //String s = String.format("%.1f", a);


        return a;
    }

    ;


}