package com.example.budongsanapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * Created by taewoo on 2019-11-16.
 */

public class ListViewItem implements Comparable<ListViewItem> {


    int value;

    @SerializedName("ymd")
    @Expose
    String ymd;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("price")
    @Expose
    String price;
    @SerializedName("area")
    @Expose
    String area;
    @SerializedName("year")
    @Expose
    String year;
    @SerializedName("month")
    @Expose
    String month;
    @SerializedName("day")
    @Expose
    String day;
    @SerializedName("high")
    @Expose
    String high;
    @SerializedName("doromyung")
    @Expose
    String doromyung;
    @SerializedName("jibun")
    @Expose
    String jibun;
    @SerializedName("geunmulcode")
    @Expose
    String geunmulcode;
    @SerializedName("jiyeokcode")
    @Expose
    String jiyeokcode;
    @SerializedName("bupjungdong")
    @Expose
    String bupjungdong;
    @SerializedName("Gunchukyear")
    @Expose
    String gunchukyear;


    @SerializedName("hightprice")
    @Expose
    String hightprice;
    @SerializedName("hightyear")
    @Expose
    String hightyear;
    @SerializedName("hightmonth")
    @Expose
    String hightmonth;
    @SerializedName("hightday")
    @Expose
    String hightday;


    @SerializedName("areac")
    @Expose
    String areac;


    @SerializedName("chaik")
    @Expose
    String chaik;

///////////////////////////////////////

    @SerializedName("pyungmyuendo")
    @Expose
    String pyungmyuendo;


    @SerializedName("chongdongsu")
    @Expose
    String chongdongsu;


    @SerializedName("chongsedaesu")
    @Expose
    String chongsedaesu;

    @SerializedName("juchadaesu")
    @Expose
    String juchadaesu;

    @SerializedName("pyungeunjucha")
    @Expose
    String pyungeunjucha;

    @SerializedName("yongjeukryul")
    @Expose
    String yongjeukryul;

    @SerializedName("gunpaeyul")
    @Expose
    String gunpaeyul;

    @SerializedName("ganrisamuso")
    @Expose
    String ganrisamuso;

    @SerializedName("nanbang")
    @Expose
    String nanbang;

    @SerializedName("gunseoulsa")
    @Expose
    String gunseoulsa;

    @SerializedName("jihachul")
    @Expose
    String jihachul;

    @SerializedName("mart")
    @Expose
    String mart;

    @SerializedName("hospital")
    @Expose
    String hospital;

    @SerializedName("park")
    @Expose
    String park;

    @SerializedName("cho")
    @Expose
    String cho;

    @SerializedName("jung")
    @Expose
    String jung;

    @SerializedName("go")
    @Expose
    String go;

    @SerializedName("arin")
    @Expose
    String arin;

    @SerializedName("you")
    @Expose
    String you;


    public ListViewItem(String name, String price,
                        String area, String year,
                        String month, String day,
                        String high, String doromyung,
                        String jibun, String geunmulcode,
                        String jiyeokcode,
                        String bupjungdong,
                        String gunchukyear,
                        String hightprice, String hightyear, String hightmonth, String hightday,
                        String areac, String ymd, String chaik, String pyungmyuendo, String chongdongsu,
                        String chongsedaesu, String juchadaesu, String pyungeunjucha, String yongjeukryul, String gunpaeyul, String ganrisamuso,
                        String nanbang, String gunseoulsa, String jihachul, String mart, String hospital, String park,
                        String cho, String jung, String go, String arin, String you) {
        this.name = name;
        this.price = price;
        this.area = area;
        this.year = year;
        this.month = month;
        this.day = day;
        this.high = high;
        this.doromyung = doromyung;
        this.jibun = jibun;
        this.geunmulcode = geunmulcode;
        this.jiyeokcode = jiyeokcode;
        this.bupjungdong = bupjungdong;
        this.gunchukyear = gunchukyear;
        this.chaik = chaik;
        this.hightprice = hightprice;
        this.hightyear = hightyear;
        this.hightmonth = hightmonth;
        this.hightday = hightday;
        this.areac = areac;
        this.ymd = ymd;


        this.pyungmyuendo = pyungmyuendo;
        this.chongdongsu = chongdongsu;
        this.chongsedaesu = chongsedaesu;
        this.juchadaesu = juchadaesu;
        this.pyungeunjucha = pyungeunjucha;
        this.yongjeukryul = yongjeukryul;
        this.gunpaeyul = gunpaeyul;
        this.ganrisamuso = ganrisamuso;
        this.nanbang = nanbang;
        this.gunseoulsa = gunseoulsa;
        this.jihachul = jihachul;
        this.mart = mart;
        this.hospital = hospital;
        this.park = park;
        this.cho = cho;
        this.jung = jung;
        this.go = go;
        this.arin = arin;
        this.you = you;
    }

    public ListViewItem(String name, String price, String area, String year, String month, String day, String high, String doromyung, String jibun, String geunmulcode, String jiyeokcode, String bupjungdong, String gunchukyear, String areac, String ymd) {

        this.name = name;
        this.price = price;
        this.area = area;
        this.year = year;
        this.month = month;
        this.day = day;
        this.high = high;
        this.doromyung = doromyung;
        this.jibun = jibun;
        this.geunmulcode = geunmulcode;
        this.jiyeokcode = jiyeokcode;
        this.bupjungdong = bupjungdong;
        this.gunchukyear = gunchukyear;

        this.areac = areac;
        this.ymd = ymd;


    }


    public String getPyungmyuendo() {
        return pyungmyuendo;
    }

    public String getChongdongsu() {
        return chongdongsu;
    }

    public String getChongsedaesu() {
        return chongsedaesu;
    }

    public String getJuchadaesu() {
        return juchadaesu;
    }

    public String getPyungeunjucha() {
        return pyungeunjucha;
    }

    public String getYongjeukryul() {
        return yongjeukryul;
    }

    public String getGunpaeyul() {
        return gunpaeyul;
    }

    public String getGanrisamuso() {
        return ganrisamuso;
    }

    public String getNanbang() {
        return nanbang;
    }

    public String getGunseoulsa() {
        return gunseoulsa;
    }

    public String getJihachul() {
        return jihachul;
    }

    public String getMart() {
        return mart;
    }

    public String getHospital() {
        return hospital;
    }

    public String getPark() {
        return park;
    }

    public String getCho() {
        return cho;
    }

    public String getJung() {
        return jung;
    }

    public String getGo() {
        return go;
    }

    public String getArin() {
        return arin;
    }

    public String getYou() {
        return you;
    }

    public void setPyungmyuendo(String pyungmyuendo) {
        this.pyungmyuendo = pyungmyuendo;
    }

    public void setChongdongsu(String chongdongsu) {
        this.chongdongsu = chongdongsu;
    }

    public void setChongsedaesu(String chongsedaesu) {
        this.chongsedaesu = chongsedaesu;
    }

    public void setJuchadaesu(String juchadaesu) {
        this.juchadaesu = juchadaesu;
    }

    public void setPyungeunjucha(String pyungeunjucha) {
        this.pyungeunjucha = pyungeunjucha;
    }

    public void setYongjeukryul(String yongjeukryul) {
        this.yongjeukryul = yongjeukryul;
    }

    public void setGunpaeyul(String gunpaeyul) {
        this.gunpaeyul = gunpaeyul;
    }

    public void setGanrisamuso(String ganrisamuso) {
        this.ganrisamuso = ganrisamuso;
    }

    public void setNanbang(String nanbang) {
        this.nanbang = nanbang;
    }

    public void setGunseoulsa(String gunseoulsa) {
        this.gunseoulsa = gunseoulsa;
    }

    public void setJihachul(String jihachul) {
        this.jihachul = jihachul;
    }

    public void setMart(String mart) {
        this.mart = mart;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setPark(String park) {
        this.park = park;
    }

    public void setCho(String cho) {
        this.cho = cho;
    }

    public void setJung(String jung) {
        this.jung = jung;
    }

    public void setGo(String go) {
        this.go = go;
    }

    public void setArin(String arin) {
        this.arin = arin;
    }

    public void setYou(String you) {
        this.you = you;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public String getYmd() {
        return ymd;
    }

    public void setAreac(String areac) {
        this.areac = areac;
    }

    public String getAreac() {
        return areac;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public String getChaik() {
        return chaik;
    }

    public void setChaik(String chaik) {
        this.chaik = chaik;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getDoromyung() {
        return doromyung;
    }

    public void setDoromyung(String doromyung) {
        this.doromyung = doromyung;
    }

    public String getJibun() {
        return jibun;
    }

    public void setJibun(String jibun) {
        this.jibun = jibun;
    }

    public String getGeunmulcode() {
        return geunmulcode;
    }

    public void setGeunmulcode(String geunmulcode) {
        this.geunmulcode = geunmulcode;
    }

    public String getJiyeokcode() {
        return jiyeokcode;
    }

    public void setJiyeokcode(String jiyeokcode) {
        this.jiyeokcode = jiyeokcode;
    }

    public String getBupjungdong() {
        return bupjungdong;
    }

    public void setBupjungdong(String bupjungdong) {
        this.bupjungdong = bupjungdong;
    }

    public String getGunchukyear() {
        return gunchukyear;
    }

    public void setGunchukyear(String gunchukyear) {
        this.gunchukyear = gunchukyear;
    }


    public void setHightprice(String hightprice) {
        this.hightprice = hightprice;
    }

    public void setHightyear(String hightyear) {
        this.hightyear = hightyear;
    }

    public void setHightmonth(String hightmonth) {
        this.hightmonth = hightmonth;
    }

    public void setHightday(String hightday) {
        this.hightday = hightday;
    }

    public String getHightprice() {
        return hightprice;
    }

    public String getHightyear() {
        return hightyear;
    }

    public String getHightmonth() {
        return hightmonth;
    }

    public String getHightday() {
        return hightday;
    }


    //내림차순
//    @Override
//    public int compareTo(ListViewItem entry) {
//
//
//
//
//
//
//        return entry.getPrice().compareTo(this.getPrice());
//
//
//    }
//
//


    // 내림차순
    @Override
    public int compareTo(ListViewItem entry) { //금액 낮은순

        if (new TWPreference().getInt("value", 0) % 2 == 0) {  //짝수
            return entry.getPrice().compareTo(this.getPrice());
        } else {  //홀수
            int a = 0;
            int b = 0;


            if ("".equals(this.getChaik())) { //만약 받아온 데이터가 공백이 아니면 데이터를 형변환 하여 적재

                a = 0;

            } else {
                a = Integer.parseInt(this.getChaik());

            }
            if ("".equals(entry.getChaik())) {

                b = 0;
            } else {


                b = Integer.parseInt(entry.getChaik());

            }


            if (a < b)

            {
                return 1;

            } else if (a == b)

            {
                return 0;
            }

        }

//        if (new TWPreference().getInt("value",0)==0) {
//            return entry.getPrice().compareTo(this.getPrice());
//        } else if (new TWPreference().getInt("value",0)==1) {
//            int a = 0;
//            int b = 0;
//
//
//            if ("".equals(this.getChaik())) { //만약 받아온 데이터가 공백이 아니면 데이터를 형변환 하여 적재
//
//                a = 0;
//
//            } else {
//                a = Integer.parseInt(this.getChaik());
//
//            }
//            if ("".equals(entry.getChaik())) {
//
//                b = 0;
//            } else {
//
//
//                b = Integer.parseInt(entry.getChaik());
//
//            }
//
//
//            if (a < b)
//
//            {
//                return 1;
//
//            } else if (a == b)
//
//            {
//                return 0;
//            }


        return -1;
    }

}