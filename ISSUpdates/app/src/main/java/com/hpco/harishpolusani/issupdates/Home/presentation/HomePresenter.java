package com.hpco.harishpolusani.issupdates.Home.presentation;

import com.hpco.harishpolusani.issupdates.Home.model.IssInfo;
import com.hpco.harishpolusani.issupdates.Home.network.NetworkManager;

import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by harishpolusani on 1/17/18.
 */

public class HomePresenter  implements HomeContract.Presenter{
    private NetworkManager mNetworkManager;
    private CompositeSubscription mComposesubs;
    private HomeContract.View mView;
    public HomePresenter(HomeContract.View view){
        mNetworkManager=NetworkManager.getInstance();
        mComposesubs=new CompositeSubscription();
        mView=view;
    }

    /**
     *
     * @param queryMap : A map of keyvalue  pairs of query params appends to the Url which we hit in retrofit
     */
    @Override
    public void callISSapi(Map<String,String> queryMap) {
      Subscription subscription= mNetworkManager.getChannelGuideData(getIssUrl(),queryMap,new Subscriber<IssInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
mView.onFailure(e);
            }

            @Override
            public void onNext(IssInfo issInfo) {
mView.updateUI(issInfo);
            }
        });
      mComposesubs.add(subscription);
    }


    /**
     * this method could be handled properly instead of returning a raw String at present. considering only this single api call
     * i made it hardcoded.
     * @return String : BaseUrl+path
     */
    private String getIssUrl() {

        return "http://api.open-notify.org/iss-pass.json";
    }

    /**
     * This method is to clear all the subscriptions after view is destryoyed
     */
    @Override
    public void unSubscribe() {
      mComposesubs.clear();
    }
}
