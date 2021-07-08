package com.taewoooh.budongsanapp.Buyapartment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface Version {




    // GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
    @GET("/VersionCheck.php")
    // JSON Array를 리턴하므로 List<>가 되었다
    // Call<List<ListViewItem2>> contributors(@Query("table1") String naljja);
    Call<List<Versionitem>> contributors(@Query("table1") String naljja);
}