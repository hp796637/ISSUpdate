package com.hpco.harishpolusani.issupdates.Home.presentation;

import com.hpco.harishpolusani.issupdates.Home.model.IssInfo;

import java.util.Map;

/**
 * Created by harishpolusani on 1/17/18.
 */

public class HomeContract {

    interface View {

        void updateUI(IssInfo info);
        void onFailure(Throwable e);
    }

    interface Presenter{

        void callISSapi(Map<String,String > query);
        void unSubscribe();
    }
}
