package com.glitchizer.dietfit.Modules;

/**
 * Created by RaHmOnY on 2/29/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {


    private String msg;


    protected Result(Parcel in) {
        msg = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };


    public void setMsg(String msg) {

        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);

    }
}