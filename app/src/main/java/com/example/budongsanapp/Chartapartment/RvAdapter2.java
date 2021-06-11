package com.example.budongsanapp.Chartapartment;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.budongsanapp.Buyapartment.ListViewItem;
import com.example.budongsanapp.R;
import com.example.budongsanapp.Util;

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

    private ArrayList<com.example.budongsanapp.Chartapartment.ListViewItem2> items = null;
    private ArrayList<com.example.budongsanapp.Chartapartment.ListViewItem2> arrayList;

    Context context;


    public RvAdapter2(ArrayList<com.example.budongsanapp.Chartapartment.ListViewItem2> items, Context context) {
        this.context = context;
        this.items = items;
        arrayList = new ArrayList<com.example.budongsanapp.Chartapartment.ListViewItem2>();
        arrayList.addAll(items);


    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        int safePosition = holder.getAdapterPosition();




        holder.bupjungdong.setText(items.get(safePosition).getBupjungdong()); //단지이름
        holder.totalgunsu.setText(String.valueOf(items.get(safePosition).getTotalgunsu())); //총건수
        holder.singogunsu.setText(String.valueOf(items.get(safePosition).getSingogunsu())); //신고건수
        holder.inflation.setText(String.valueOf(items.get(safePosition).getInflation())); //신고가율


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String charText) { // 리사이클러뷰 검색

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


        public CustomViewHolder(View itemView) {
            super(itemView);


            // title = itemView.findViewById(R.id.item_tv_title);
            bupjungdong=itemView.findViewById(R.id.bupjungdong);
            totalgunsu = itemView.findViewById(R.id.totalgunsu);
            singogunsu = itemView.findViewById(R.id.singogunsu);
            inflation = itemView.findViewById(R.id.inflation);


        }


    }

}