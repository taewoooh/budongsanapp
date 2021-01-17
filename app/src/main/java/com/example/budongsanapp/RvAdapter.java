package com.example.budongsanapp;


import android.content.Context;
import android.graphics.Color;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

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

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CustomViewHolder> {

    private ArrayList<ListViewItem> items = null;
    private ArrayList<ListViewItem> arrayList;

    Context context;

    int a;
    int b;


    public RvAdapter(ArrayList<ListViewItem> items, Context context) {
        this.context = context;
        this.items = items;
        arrayList = new ArrayList<ListViewItem>();
        arrayList.addAll(items);


    }




    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {


        try {

            holder.price.setTextColor(Color.parseColor("#000000")); // 기본색
            holder.highicon.setVisibility(View.INVISIBLE); // 신고가 아이콘
            holder.minusprice.setText("");
            holder.name.setTextColor(Color.parseColor("#000000")); // 검정색
            holder.pyeungsu.setText("");




            int count = 0;
//            a = Integer.parseInt(items.get(position).getPrice().replaceAll(",", "").replaceAll("\\p{Z}", ""));
//            b = Integer.parseInt(items.get(position).getHightprice().replaceAll(",", "").replaceAll("\\p{Z}", ""));


            a = Integer.parseInt(items.get(position).getChaik());


//            Log.e("taewoooh"+"\n\n" + " 거래 금액 - > " + a + "\n" + "최고가 - > " + b, "");



            if (a > 0) {

                count++;

                holder.highicon.setVisibility(View.VISIBLE);// 신고가 아이콘
                holder.price.setTextColor(Color.parseColor("#F44336")); // 분홍색

                int d = a - b;
                holder.minusprice.setText(new Util().Priceedit(String.valueOf(a)));

                Log.e("taewoooh1988 - > ", "" + count);

            } else {
                holder.highicon.setVisibility(View.INVISIBLE);// 신고가 아이콘
                holder.price.setTextColor(Color.parseColor("#000000")); // 기본색
                holder.minusprice.setText("");


            }





        } catch (NumberFormatException e) {



        } catch (Exception e) {

        }




        String price_s = new Util().Priceedit(items.get(position).getPrice()); // 금액에 억 붙히기
        String price_o = new Util().Priceedit(items.get(position).getHightprice()); // 금액에 억 붙히기(최고가)


        String highday = new Util().Daygagong(items.get(position).getHightyear()
                , items.get(position).getHightmonth(), items.get(position).getHightday());





//        }

        String c = items.get(position).getChongsedaesu();
        String j = items.get(position).getPyungeunjucha();
        String ji = items.get(position).getJihachul();
        String yong = items.get(position).getYongjeukryul();
        String gun = items.get(position).getGunpaeyul();


        holder.Name.setText(items.get(position).getName()); //단지이름
        holder.Area.setText(items.get(position).getArea()); //면적
        holder.Bupjungdong.setText(items.get(position).getBupjungdong()); // 주소
        holder.ymd.setText(items.get(position).getYmd()); //계약일
        holder.high.setText(String.valueOf(items.get(position).getHigh())); // 층수
        //holder.Areac.setText(items.get(position).getAreac()); // 평형
        holder.CREATE_YYYY.setText(items.get(position).getGunchukyear()); // 평형
        holder.hight_day.setText(highday); // 계약 날짜
        holder.price.setText(price_s); ///거래 금액
        holder.previ_pricenumber.setText(price_o); // 최고가
        //


        holder.chongsedaesu.setText(items.get(position).getChongsedaesu()); // 총세대수
        holder.pyungeunjucha.setText(items.get(position).getPyungeunjucha()); // 펑균주차대수
        holder.pyungeunjucha.setText(items.get(position).getYongjeukryul()); // 용적률
        holder.pyungeunjucha.setText(items.get(position).getGunpaeyul()); // 건페율


try {
    //items.get(position).getMart().equals("분양권/입주권") &&
    if ( items.get(position).getGunchukyear().equals("0")){


        holder.name.setTextColor(Color.parseColor("#2196F3")); // 검정색

    }
}catch (Exception e){

}

        try {

            if (Integer.parseInt(items.get(position).getPyungmyuendo()) > 0) {

                Log.e("pyungmyuendo", " -->> " + items.get(position).getName() + " / " + items.get(position).getArea() + " / " + items.get(position).getPyungmyuendo() +
                        " / " + items.get(position).getYongjeukryul() + " / " + items.get(position).getGunpaeyul());
                //Log.e("pyungmyuendo2"," / "+items.get(position).getName()+" / "+items.get(position).getArea()+" / "+items.get(position).getPyungmyuendo());


                holder.pyeungsu.setText(" / " + items.get(position).getPyungmyuendo() + "평");


            }
        } catch (Exception e) {


        }



        if (ji.equals("")) {
            ji = "-";
            holder.jihachul.setText(ji);
        } else {

            Log.e("jihachul"," / "+ji);
            ji = items.get(position).getJihachul().replace("\n", "");
            Log.e("jihachul2"," / "+ji);

            holder.jihachul.setText(ji);

        }
        if (c.equals("")) {

            c = "-";
            holder.chongsedaesu.setText(c);


        } else {


            holder.chongsedaesu.setText(c);

        }
        if (j.equals("")) {
            j = "-";
            holder.pyungeunjucha.setText(j);

        } else {

            holder.pyungeunjucha.setText(j);

        } if (yong.equals("") || yong.equals("0")) {
            yong = "-";
            holder.yongjeukryul.setText(yong);

        } else {

            holder.yongjeukryul.setText(yong);

        }if (gun.equals("")|| gun.equals("0")) {
            gun = "-";
            holder.gunpaeyul.setText(gun);

        } else {

            holder.gunpaeyul.setText(gun);

        }





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
            for (ListViewItem news : arrayList) {
                if (news.getName().toLowerCase(Locale.getDefault()).contains(charText) //타이틀 or 주소 검색 가능
                        || news.getBupjungdong().toLowerCase(Locale.getDefault()).contains(charText) ||
                        news.getPrice().toLowerCase(Locale.getDefault()).contains(charText) ||
                        news.getAreac().toLowerCase(Locale.getDefault()).contains(charText) ||
                        news.getArea().toLowerCase(Locale.getDefault()).contains(charText) ||
                        news.getMonth().toLowerCase(Locale.getDefault()).contains(charText)) {
                    items.add(news);
                }
            }
        }
        notifyDataSetChanged();


    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        TextView Name;
        TextView Area;
        TextView Bupjungdong;
        TextView ymd;
        TextView CREATE_YYYY;
        TextView high;
        TextView Areac;
        TextView minusprice;
        TextView previ_pricenumber;
        TextView hight_day;

        TextView chongsedaesu;
        TextView pyungeunjucha;
        TextView jihachul;
        TextView pyeungsu;


        ImageView highicon;

        TextView yongjeukryul;
        TextView gunpaeyul;
        TextView name;

        public CustomViewHolder(View itemView) {
            super(itemView);


            // title = itemView.findViewById(R.id.item_tv_title);
            yongjeukryul = itemView.findViewById(R.id.yongjeukryul);
            gunpaeyul = itemView.findViewById(R.id.gunpaeyul);
            previ_pricenumber = itemView.findViewById(R.id.Previ_pricenumber);
            hight_day = itemView.findViewById(R.id.high_day);
            minusprice = itemView.findViewById(R.id.minusprice);
            highicon = itemView.findViewById(R.id.highicon);
            price = itemView.findViewById(R.id.price);
            Name = itemView.findViewById(R.id.Name);
            Area = itemView.findViewById(R.id.Area);
            Bupjungdong = itemView.findViewById(R.id.Bupjungdong);
            ymd = itemView.findViewById(R.id.ymd);
            CREATE_YYYY = itemView.findViewById(R.id.CREATE_YYYY);
            high = itemView.findViewById(R.id.high);
            chongsedaesu = itemView.findViewById(R.id.chongsedaesu);
            pyungeunjucha = itemView.findViewById(R.id.pyungeunjucha);
            jihachul = itemView.findViewById(R.id.jihachul);
            pyeungsu = itemView.findViewById(R.id.pyeungsu);
            name= itemView.findViewById(R.id.Name);



        }


    }

}