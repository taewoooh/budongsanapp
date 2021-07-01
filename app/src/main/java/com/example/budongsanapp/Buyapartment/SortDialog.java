package com.example.budongsanapp.Buyapartment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.budongsanapp.CustomDialogClickListener;
import com.example.budongsanapp.R;

import static com.google.android.material.color.MaterialColors.getColor;

public class SortDialog extends Dialog implements View.OnClickListener {
    private CustomDialogClickListener customDialogClickListener;
    private Context context;
    TextView j_price;
    TextView j_chaik;
    TextView j_termchaik;
    TextView j_area;
    TextView j_date;

    ImageView j_price_v;
    ImageView j_chaik_v;
    ImageView j_termchaik_v;
    ImageView j_area_v;
    ImageView j_date_v;

    public SortDialog(@NonNull Context context, CustomDialogClickListener customDialogClickListener) {
        super(context);
        this.context = context;
        this.customDialogClickListener = customDialogClickListener;
        //requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.


    }

    public SortDialog(@NonNull Context context) {
        super(context);
        this.context = context;

        //requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.sortdialog);     //다이얼로그에서 사용할 레이아웃입니다.


        j_price = (TextView) findViewById(R.id.j_price);
        j_chaik = (TextView) findViewById(R.id.j_chaik);
        j_termchaik = (TextView) findViewById(R.id.j_termchaik);
        j_area = (TextView) findViewById(R.id.j_area);
        j_date = (TextView) findViewById(R.id.j_date);

        j_price.setOnClickListener(this::onClick);
        j_chaik.setOnClickListener(this::onClick);
        j_termchaik.setOnClickListener(this::onClick);
        j_area.setOnClickListener(this::onClick);
        j_date.setOnClickListener(this::onClick);


        j_price_v = (ImageView) findViewById(R.id.j_price_v);
        j_chaik_v = (ImageView) findViewById(R.id.j_chaik_v);
        j_termchaik_v = (ImageView) findViewById(R.id.j_termchaik_v);
        j_area_v = (ImageView) findViewById(R.id.j_area_v);
        j_date_v = (ImageView) findViewById(R.id.j_date_v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.j_price:

                this.customDialogClickListener.onPriceClicked();

                j_price.setTextColor(getContext().getColor(R.color.On_Tcolor));
                j_price_v.setVisibility(View.VISIBLE);

                j_chaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_chaik_v.setVisibility(View.INVISIBLE);


                j_termchaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_termchaik_v.setVisibility(View.INVISIBLE);

                j_area.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_area_v.setVisibility(View.INVISIBLE);

                j_date.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_date_v.setVisibility(View.INVISIBLE);

                dismiss();


                break;
            case R.id.j_chaik:

                this.customDialogClickListener.onChaikClicked();


                j_chaik.setTextColor(getContext().getColor(R.color.On_Tcolor));
                j_chaik_v.setVisibility(View.VISIBLE);

                j_price.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_price_v.setVisibility(View.INVISIBLE);


                j_termchaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_termchaik_v.setVisibility(View.INVISIBLE);


                j_area.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_area_v.setVisibility(View.INVISIBLE);

                j_date.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_date_v.setVisibility(View.INVISIBLE);

                dismiss();
                break;
            case R.id.j_termchaik:

                this.customDialogClickListener.onTermChaikClicked();

                j_termchaik.setTextColor(getContext().getColor(R.color.On_Tcolor));
                j_termchaik_v.setVisibility(View.VISIBLE);

                j_chaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_chaik_v.setVisibility(View.INVISIBLE);

                j_price.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_price_v.setVisibility(View.INVISIBLE);

                j_area.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_area_v.setVisibility(View.INVISIBLE);

                j_date.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_date_v.setVisibility(View.INVISIBLE);

                dismiss();
                break;

            case R.id.j_area:

                this.customDialogClickListener.onAreaClicked();

                j_area.setTextColor(getContext().getColor(R.color.On_Tcolor));
                j_area_v.setVisibility(View.VISIBLE);

                j_termchaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_termchaik_v.setVisibility(View.INVISIBLE);

                j_chaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_chaik_v.setVisibility(View.INVISIBLE);

                j_price.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_price_v.setVisibility(View.INVISIBLE);

                j_date.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_date_v.setVisibility(View.INVISIBLE);

                dismiss();
                break;

            case R.id.j_date:

                this.customDialogClickListener.onDateClicked();


                j_date.setTextColor(getContext().getColor(R.color.On_Tcolor));
                j_date_v.setVisibility(View.VISIBLE);

                j_area.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_area_v.setVisibility(View.INVISIBLE);

                j_termchaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_termchaik_v.setVisibility(View.INVISIBLE);

                j_chaik.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_chaik_v.setVisibility(View.INVISIBLE);

                j_price.setTextColor(getContext().getColor(R.color.Off_Tcolor));
                j_price_v.setVisibility(View.INVISIBLE);

                dismiss();
                break;

        }
    }


}
