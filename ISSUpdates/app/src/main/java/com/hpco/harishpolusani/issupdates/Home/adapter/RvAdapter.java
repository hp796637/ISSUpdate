package com.hpco.harishpolusani.issupdates.Home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpco.harishpolusani.issupdates.Home.model.IssInfo;
import com.hpco.harishpolusani.issupdates.Home.model.Response;
import com.hpco.harishpolusani.issupdates.R;

import java.util.List;

/**
 * Created by harishpolusani on 1/17/18.
 */

public class RvAdapter extends RecyclerView.Adapter<RvViewHolder> {
private List<Response> info;
    public RvAdapter(IssInfo info){
        if(info!=null){
            this.info=info.getResponse();
        }

    }
    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return  new RvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        if(info!=null) {
            holder.bind(info.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(info!=null &&info.size()>0){
            return  info.size();
        }else {
            return 0;
        }
    }

 public void  updateData(IssInfo info){
        this.info=info.getResponse();

    }
}
