package com.taewoooh.budongsanapp.Chartapartment.Bupjungdong;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.taewoooh.budongsanapp.Chartapartment.Apartname.ChartDialogClickListener;
import com.taewoooh.budongsanapp.R;


public class Chart_bup_dialog extends Dialog implements View.OnClickListener {
    private ChartDialogClickListener chartDialogClickListener;
    private Context context;
    TextView name;
    TextView jiyeokdong;
    TextView jiyeokgu;


    public Chart_bup_dialog(@NonNull Context context, ChartDialogClickListener chartDialogClickListener) {
        super(context);
        this.context = context;
        this.chartDialogClickListener = chartDialogClickListener;
        //requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.


    }

    public Chart_bup_dialog(@NonNull Context context) {
        super(context);
        this.context = context;

        //requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.


    }





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.chart_dialog);     //다이얼로그에서 사용할 레이아웃입니다.


        name = (TextView) findViewById(R.id.name);
        jiyeokdong = (TextView) findViewById(R.id.jiyeokdong);
        jiyeokgu = (TextView) findViewById(R.id.jiyeokgu);

        name.setOnClickListener(this::onClick);
        jiyeokdong.setOnClickListener(this::onClick);
        jiyeokgu.setOnClickListener(this::onClick);


        jiyeokdong.setTextColor(getContext().getColor(R.color.On_Tcolor));
        // j_chaik_v.setVisibility(View.VISIBLE);

        jiyeokgu.setTextColor(getContext().getColor(R.color.Off_Tcolor));
        // j_price_v.setVisibility(View.INVISIBLE);


        name.setTextColor(getContext().getColor(R.color.Off_Tcolor));
        // j_termchaik_v.setVisibility(View.INVISIBLE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.name:

                this.chartDialogClickListener.onNameClicked();

                name.setTextColor(getContext().getColor(R.color.On_Tcolor));
                //j_price_v.setVisibility(View.VISIBLE);

                jiyeokdong.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                // j_chaik_v.setVisibility(View.INVISIBLE);


                jiyeokgu.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                //j_termchaik_v.setVisibility(View.INVISIBLE);




                dismiss();


                break;
            case R.id.jiyeokdong:

                this.chartDialogClickListener.onJiyoekdongClicked();


                jiyeokdong.setTextColor(getContext().getColor(R.color.On_Tcolor));
                // j_chaik_v.setVisibility(View.VISIBLE);

                jiyeokgu.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                // j_price_v.setVisibility(View.INVISIBLE);


                name.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                // j_termchaik_v.setVisibility(View.INVISIBLE);


                dismiss();
                break;
            case R.id.jiyeokgu:

                this.chartDialogClickListener.onJiyeokguClicked();

                jiyeokgu.setTextColor(getContext().getColor(R.color.On_Tcolor));
                //j_termchaik_v.setVisibility(View.VISIBLE);

                jiyeokdong.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                //j_chaik_v.setVisibility(View.INVISIBLE);

                name.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                //j_price_v.setVisibility(View.INVISIBLE);

//                j_area.setTextColor(getContext().getColor(R.color.Off_Tcolor));
//                j_area_v.setVisibility(View.INVISIBLE);
//
//                j_date.setTextColor(getContext().getColor(R.color.Off_Tcolor));
//                j_date_v.setVisibility(View.INVISIBLE);

                dismiss();
                break;



        }
    }


}
