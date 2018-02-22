package com.hpco.harishpolusani.issupdates.Home.network;
import android.app.Activity;

import com.hpco.harishpolusani.issupdates.Home.model.IssInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by harishpolusani on 1/17/18.
 */

public class NetworkManager {
    private String BASE_URL="http://api.open-notify.org/";
        private static final int DEFAULT_TIMEOUT = 15;
        private volatile static NetworkManager INSTANCE;
        private NetworkRetrofitinterface networkService;

        // Singleton
        private NetworkManager() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

         //Intreceptor can be added to the Okhttpclient to handle retry
           Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

            networkService = retrofit.create(NetworkRetrofitinterface.class);
        }

    /**
     *
     * @returns instance of the NetworkManager
     */
        public static NetworkManager getInstance() {
            if (INSTANCE == null) {
                synchronized (NetworkManager.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new NetworkManager();
                    }
                }
            }
            return INSTANCE;
        }


    public Subscription getChannelGuideData( String url , Map<String,String> querymap,Subscriber<IssInfo> subscriber) {
        return networkService.getIssData(url,querymap).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
