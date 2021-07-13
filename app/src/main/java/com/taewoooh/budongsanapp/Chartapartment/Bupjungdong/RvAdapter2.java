package com.taewoooh.budongsanapp.Chartapartment.Bupjungdong;


import android.content.Context;
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


import com.taewoooh.budongsanapp.Chartapartment.Apartname.ListViewItem3;
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

public class RvAdapter2 extends RecyclerView.Adapter<RvAdapter2.CustomViewHolder> {

    private ArrayList<ListViewItem2> items = null;
    private ArrayList<ListViewItem2> arrayList;

    Context context;

    TWPreference twPreference;

    String searchString;

    public RvAdapter2(ArrayList<ListViewItem2> items, Context context) {
        this.context = context;
        this.items = items;
        arrayList = new ArrayList<ListViewItem2>();
        arrayList.addAll(items);


    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_bup_list_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        int safePosition = holder.getAdapterPosition();
        int count = safePosition + 1;


        holder.bupjungdong.setText(items.get(safePosition).getBupjungdong()); //단지이름
        int v = twPreference.getInt("value", 0);


        if (v % 2 == 0) {  //짝수
            holder.totalgunsu.setText(String.valueOf(items.get(safePosition).getTotalgunsu())); //총건수
            holder.totalgunsu.setTypeface(holder.totalgunsu.getTypeface(), Typeface.BOLD_ITALIC);
            holder.totalgunsu.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);

        } else {  //홀수
            holder.totalgunsu.setText(String.valueOf(items.get(safePosition).getTotalgunsu())); //총건수

            holder.inflation.setText(String.valueOf(items.get(safePosition).getInflation() + "")); //신고가율
            holder.inflation.setTypeface(holder.inflation.getTypeface(), Typeface.BOLD_ITALIC);
            holder.inflation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);


        }
        holder.singogunsu.setText(String.valueOf(items.get(safePosition).getSingogunsu())); //신고건수
        holder.inflation.setText(String.valueOf(items.get(safePosition).getInflation() + "%")); //신고가율
        holder.number.setText(String.valueOf(count)); //신고가율


        if (count == 1) {
            holder.crown.setVisibility(View.VISIBLE);
            holder.crown.setImageResource(R.drawable.crown2);
        } else if (count == 2) {
            holder.crown.setVisibility(View.VISIBLE);
            holder.crown.setImageResource(R.drawable.crown3);

        } else if (count == 3) {
            holder.crown.setVisibility(View.VISIBLE);
            holder.crown.setImageResource(R.drawable.crown4);
        } else {
            holder.crown.setVisibility(View.INVISIBLE);
        }

        String b= items.get(safePosition).getBupjungdong();
        String bup = b.toLowerCase(Locale.getDefault());

        try {
            if (searchString.length() == 0) {
                Log.e("searchString", "");

            } else if (bup.contains(searchString)) {

                int startPos = bup.indexOf(searchString);
                int endPos = startPos + searchString.length();

                Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.bupjungdong.getText());
                spanString.setSpan(new BackgroundColorSpan(0xFFFFFF00), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.bupjungdong.setText(spanString);

            }


        } catch (Exception e) {


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
            for (ListViewItem2 news : arrayList) {
                if (news.getBupjungdong().toLowerCase(Locale.getDefault()).contains(charText)) {
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
        ImageView crown;


        public CustomViewHolder(View itemView) {
            super(itemView);


            // title = itemView.findViewById(R.id.item_tv_title);
            bupjungdong = itemView.findViewById(R.id.bupjungdong);
            totalgunsu = itemView.findViewById(R.id.totalgunsu);
            singogunsu = itemView.findViewById(R.id.singogunsu);
            inflation = itemView.findViewById(R.id.inflation);
            number = itemView.findViewById(R.id.number);
            crown = itemView.findViewById(R.id.crown);
            twPreference = new TWPreference(context);

        }


    }

}