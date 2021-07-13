package com.taewoooh.budongsanapp.Chartapartment.Apartname;

import com.taewoooh.budongsanapp.TWPreference;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by taewoo on 2019-11-16.
 */

public class ListViewItem3 implements Comparable<ListViewItem3> {


    @SerializedName("name")
    @Expose
    String name;

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
    @SerializedName("gunchukyear")
    @Expose
    int gunchukyear;

    public int getGunchukyear() {
        return gunchukyear;
    }

    public String getName() {
        return name;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setGunchukyear(int gunchukyear) {
        this.gunchukyear = gunchukyear;
    }

    public ListViewItem3(String name, String bupjungdong, int totalgunsu,
                         int singogunsu, int inflation, int gunchukyear) {
        this.name = name;
        this.bupjungdong = bupjungdong;
        this.totalgunsu = totalgunsu;
        this.singogunsu = singogunsu;
        this.inflation = inflation;
        this.gunchukyear = gunchukyear;

    }


    // 내림차순
    @Override
    public int compareTo(ListViewItem3 entry) { //금액 낮은순

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
