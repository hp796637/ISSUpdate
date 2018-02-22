package com.hpco.harishpolusani.issupdates.Home.network;

import com.hpco.harishpolusani.issupdates.Home.model.IssInfo;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by harishpolusani on 1/17/18.
 */

public interface NetworkRetrofitinterface {

    @GET
    Observable<IssInfo> getIssData(@Url String url , @QueryMap Map<String,String> queryMap);

}
