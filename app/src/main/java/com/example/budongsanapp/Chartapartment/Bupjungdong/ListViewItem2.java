package com.example.budongsanapp.Chartapartment.Bupjungdong;

import com.example.budongsanapp.TWPreference;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by taewoo on 2019-11-16.
 */

public class ListViewItem2 implements Comparable<ListViewItem2> {



    @SerializedName("bupjungdong")
    @Expose
    String bupjungdong;
    @SerializedName("totalgunsu")
    @Expose
    int totalgunsu;
    @SerializedName("singogunsu")
    @Expose
    int singogunsu;
    @SerializedName("inflation")
    @Expose
    int inflation;



    public String getBupjungdong() {
        return bupjungdong;
    }

    public int getTotalgunsu() {
        return totalgunsu;
    }

    public int getSingogunsu() {
        return singogunsu;
    }

    public int getInflation() {
        return inflation;
    }

    public void setBupjungdong(String bupjungdong) {
        this.bupjungdong = bupjungdong;
    }

    public void setTotalgunsu(int totalgunsu) {
        this.totalgunsu = totalgunsu;
    }

    public void setSingogunsu(int singogunsu) {
        this.singogunsu = singogunsu;
    }

    public void setInflation(int inflation) {
        this.inflation = inflation;
    }



    public ListViewItem2( String bupjungdong, int totalgunsu,
                         int singogunsu, int inflation) {
        this.bupjungdong = bupjungdong;
        this.totalgunsu = totalgunsu;
        this.singogunsu = singogunsu;
        this.inflation = inflation;

    }


    // 내림차순
    @Override
    public int compareTo(ListViewItem2 entry) { //금액 낮은순
   //return entry.getTotalgunsu() - this.getTotalgunsu();
        //return entry.getPrice() - this.getPrice();


        int prefer = new TWPreference().getInt("value", 0);


        if (prefer % 2 == 0) {  //짝수

            return entry.getTotalgunsu() - this.getTotalgunsu();
        } else {  //홀수
            return entry.getInflation() - this.getInflation();
            //return entry.getChaik() - this.getChaik();
        }

    }
}
