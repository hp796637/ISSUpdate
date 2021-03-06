package com.hpco.harishpolusani.issupdates.Home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harishpolusani on 1/18/18.
 */

public class IssInfo {





        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("request")
        @Expose
        private Request request;
        @SerializedName("response")
        @Expose
        private List<Response> response = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Request getRequest() {
            return request;
        }

        public void setRequest(Request request) {
            this.request = request;
        }

        public List<Response> getResponse() {
            return response;
        }

        public void setResponse(List<Response> response) {
            this.response = response;
        }


}
