package com.hpco.harishpolusani.issupdates.Home.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hpco.harishpolusani.issupdates.Home.adapter.RvAdapter;
import com.hpco.harishpolusani.issupdates.Home.model.IssInfo;
import com.hpco.harishpolusani.issupdates.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by harishpolusani on 1/17/18.
 */

public class HomeFragment extends Fragment implements HomeContract.View {
private Unbinder mUnbinder;
@BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private HomePresenter mHomePresenter;
    private RvAdapter rvAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.recycler_view, container ,false);
    mHomePresenter = new HomePresenter(this);
    mHomePresenter.callISSapi(buildQuery());
    mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    /**
     * altitude and number of passes can also be added here to the map,the  default gonna be altitude:100 and passes:5
     * @returns Map with query key value pairs which are required for api request .
     */
    private Map<String,String> buildQuery() {
       Bundle bundle= getArguments();
        Map<String,String> queryMap=new HashMap<String,String>();
       if(bundle!=null&&bundle.getString("lat")!=null&&bundle.getString("lon")!=null){
           queryMap.put("lat",bundle.getString("lat"));
           queryMap.put("lon",bundle.getString("lon"));
       }else{
           Toast.makeText(getActivity(),"Unable to access the location. Please check the network or accept permissions",Toast.LENGTH_LONG).show();
       }
       return queryMap;
    }


    /**
     * This method is used to initalize the recyclerview and load the adapter
     *
     */
    private void initandUpdateRV(IssInfo issInfo) {
        if(rvAdapter==null) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            rvAdapter = new RvAdapter(issInfo);
            mRecyclerView.setAdapter(rvAdapter);
        }else {
            rvAdapter.updateData(issInfo);
            rvAdapter.notifyDataSetChanged();
        }
    }

    /**
     * method gets called when the fragment UI is about to destroy . clear the subscritption and UI binding in this method.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        mHomePresenter.unSubscribe();
        super.onDestroy();
    }

    /**
     * This method is a callback to view from presenter.
     * @param info consists success response from the api
     */
    @Override
    public void updateUI(IssInfo info) {
     initandUpdateRV(info);
    }

    /**
     *
     * @param e Throwable will be passed to this method in the event of exception or failure in the api call .
     */
    @Override
    public void onFailure(Throwable e) {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


}
