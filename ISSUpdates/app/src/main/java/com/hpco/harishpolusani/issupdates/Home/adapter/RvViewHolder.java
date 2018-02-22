package com.hpco.harishpolusani.issupdates.Home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hpco.harishpolusani.issupdates.Home.model.Response;
import com.hpco.harishpolusani.issupdates.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harishpolusani on 1/17/18.
 */

public class RvViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iss_time)
    TextView issTime;
    @BindView(R.id.iss_seconds)
    TextView issSeconds;
    public RvViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
  public void bind(Response response){
      issTime.setText(String.valueOf(response.getRisetime()));
      issSeconds.setText(String.valueOf(response.getDuration()));
  }

}
