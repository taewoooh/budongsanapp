package com.taewoooh.budongsanapp.Chartapartment.jiyeokgu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.taewoooh.budongsanapp.TWPreference;


/**
 * Created by taewoo on 2019-11-16.
 */

public class ListViewItem4 implements Comparable<ListViewItem4> {




    @SerializedName("jiyeokgu")
    @Expose
    String jiyeokgu;
    @SerializedName("totalgunsu")
    @Expose
    int totalgunsu;
    @SerializedName("singogunsu")
    @Expose
    int singogunsu;
    @SerializedName("inflation")
    @Expose
    int inflation;



    public String getJiyeokgu() {
        return jiyeokgu;
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

    public void setJiyeokgu(String jiyeokgu) {
        this.jiyeokgu = jiyeokgu;
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



    public ListViewItem4(String jiyeokgu, int totalgunsu,
                         int singogunsu, int inflation) {

        this.jiyeokgu = jiyeokgu;
        this.totalgunsu = totalgunsu;
        this.singogunsu = singogunsu;
        this.inflation = inflation;

    }


    // 내림차순
    @Override
    public int compareTo(ListViewItem4 entry) { //금액 낮은순
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
