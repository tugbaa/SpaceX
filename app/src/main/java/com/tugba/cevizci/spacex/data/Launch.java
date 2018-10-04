package com.tugba.cevizci.spacex.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Launch implements Parcelable {

    @SerializedName("mission_name")
    public String missionName;

    @SerializedName("flight_number")
    public Double flightNumber;

    @SerializedName("launch_year")
    public String launchYear;

    @SerializedName("launch_date_unix")
    public Double launchDateUnix;

    @SerializedName("launch_date_utc")
    public String launchDateUtc;

    @SerializedName("launch_date_local")
    public String launchDateLocal;

    @SerializedName("launch_success")
    private Boolean launchSuccess;

    @SerializedName("links")
    public Links links;

    @SerializedName("details")
    public String details;

    protected Launch(Parcel in) {
        missionName = in.readString();
        if (in.readByte() == 0) {
            flightNumber = null;
        } else {
            flightNumber = in.readDouble();
        }
        launchYear = in.readString();
        if (in.readByte() == 0) {
            launchDateUnix = null;
        } else {
            launchDateUnix = in.readDouble();
        }
        launchDateUtc = in.readString();
        launchDateLocal = in.readString();
        byte tmpLaunchSuccess = in.readByte();
        launchSuccess = tmpLaunchSuccess == 0 ? null : tmpLaunchSuccess == 1;
        details = in.readString();
    }

    public static final Creator<Launch> CREATOR = new Creator<Launch>() {
        @Override
        public Launch createFromParcel(Parcel in) {
            return new Launch(in);
        }

        @Override
        public Launch[] newArray(int size) {
            return new Launch[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (launchSuccess ? 1 : 0));
        parcel.writeParcelable(links, i);
        parcel.writeString(missionName);
        parcel.writeString(launchYear);
        parcel.writeString(launchDateLocal);
    }


}
