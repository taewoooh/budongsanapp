package com.taewoooh.budongsanapp.Chartapartment.Apartname;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.taewoooh.budongsanapp.R;
import com.taewoooh.budongsanapp.TWPreference;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by taewoo on 2019-11-16.
 * import android.content.Context;
 * import android.support.v7.widget.RecyclerView;
 * import android.view.LayoutInflater;
 * import android.view.View;
 * import android.view.ViewGroup;
 * import android.widget.TextView;
 * <p>
 * import java.util.List;
 * <p>
 * /**
 * Created by yongyi on 2017-11-27.
 */

public class RvAdapter3 extends RecyclerView.Adapter<RvAdapter3.CustomViewHolder> {

    private ArrayList<ListViewItem3> items = null;
    private ArrayList<ListViewItem3> arrayList;

    Context context;
    String searchString;
    TWPreference twPreference;


    public RvAdapter3(ArrayList<ListViewItem3> items, Context context) {
        this.context = context;
        this.items = items;
        arrayList = new ArrayList<ListViewItem3>();
        arrayList.addAll(items);


    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_name_list_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        int safePosition = holder.getAdapterPosition();
        int count = safePosition + 1;

        holder.crown.setVisibility(View.VISIBLE);



    try {
        holder.name.setText(items.get(safePosition).getName()); //단지이름

    } catch (Exception e) {


    }


        int v = twPreference.getInt("value", 0);


        if (v % 2 == 0) {  //짝수
            holder.totalgunsu.setText(String.valueOf(items.get(safePosition).getTotalgunsu())); //총건수
            holder.totalgunsu.setTypeface(holder.totalgunsu.getTypeface(), Typeface.BOLD_ITALIC);
            holder.totalgunsu.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);

            holder.name.setTextColor(Color.parseColor("#000000")); // 검정색


        } else {  //홀수
            holder.totalgunsu.setText(String.valueOf(items.get(safePosition).getTotalgunsu())); //총건수

            holder.inflation.setText(String.valueOf(items.get(safePosition).getInflation() + "")); //신고가율
            holder.inflation.setTypeface(holder.inflation.getTypeface(), Typeface.BOLD_ITALIC);
            holder.inflation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);

            holder.name.setTextColor(Color.parseColor("#000000")); // 검정색
        }

        holder.bupjungdong.setText(items.get(safePosition).getBupjungdong()); //주소
        holder.totalgunsu.setText(String.valueOf(items.get(safePosition).getTotalgunsu())); //총건수
        holder.singogunsu.setText(String.valueOf(items.get(safePosition).getSingogunsu())); //신고건수
        holder.inflation.setText(String.valueOf(items.get(safePosition).getInflation() + "%")); //신고가율
        holder.number.setText(String.valueOf(count)); //신고가율


        String n = items.get(safePosition).getName();
        String name = n.toLowerCase(Locale.getDefault());




        String b= items.get(safePosition).getBupjungdong();
        String bup = b.toLowerCase(Locale.getDefault());

        try {
            if (searchString.length() == 0) {
                Log.e("searchString", "");

            } else if (name.contains(searchString)) {

                int startPos = name.indexOf(searchString);
                int endPos = startPos + searchString.length();

                Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.name.getText());
                spanString.setSpan(new BackgroundColorSpan(0xFFFFFF00), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.name.setText(spanString);



            }else if (bup.contains(searchString)) {

                int startPos = bup.indexOf(searchString);
                int endPos = startPos + searchString.length();

                Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.bupjungdong.getText());
                spanString.setSpan(new BackgroundColorSpan(0xFFFFFF00), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.bupjungdong.setText(spanString);

            }


        } catch (Exception e) {


        }


        if (count ==1 ){
            holder.crown.setVisibility(View.VISIBLE);
            holder.crown.setImageResource(R.drawable.crown2) ;
        }else if (count ==2){
            holder.crown.setVisibility(View.VISIBLE);
            holder.crown.setImageResource(R.drawable.crown3) ;

        }else if (count ==3){
            holder.crown.setVisibility(View.VISIBLE);
            holder.crown.setImageResource(R.drawable.crown4) ;
        }else {
            holder.crown.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String charText) { // 리사이클러뷰 검색
        this.searchString = charText;
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(arrayList);
        } else {
            for (ListViewItem3 news : arrayList) {
                if (news.getBupjungdong().toLowerCase(Locale.getDefault()).contains(charText) ||
                        news.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    items.add(news);
                }
            }
        }
        notifyDataSetChanged();


    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView bupjungdong;
        TextView totalgunsu;
        TextView singogunsu;
        TextView inflation;
        TextView number;
        TextView name;
        ImageView crown;


        public CustomViewHolder(View itemView) {
            super(itemView);


            // title = itemView.findViewById(R.id.item_tv_title);
            bupjungdong = itemView.findViewById(R.id.bupjungdong);
            totalgunsu = itemView.findViewById(R.id.totalgunsu);
            singogunsu = itemView.findViewById(R.id.singogunsu);
            name = itemView.findViewById(R.id.name);
            inflation = itemView.findViewById(R.id.inflation);
            number = itemView.findViewById(R.id.number);
            twPreference = new TWPreference(context);
            crown = itemView.findViewById(R.id.crown);

        }


    }

}