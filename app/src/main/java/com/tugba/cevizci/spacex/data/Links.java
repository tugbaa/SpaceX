package com.tugba.cevizci.spacex.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Links implements Parcelable {

    @SerializedName("mission_patch")
    public String missionPatch;

    @SerializedName("article_link")
    public String articleLink;

    @SerializedName("video_link")
    public String videoLink;

    protected Links(Parcel in) {
        missionPatch = in.readString();
        articleLink = in.readString();
        videoLink = in.readString();
    }

    public static final Creator<Links> CREATOR = new Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(missionPatch);
        parcel.writeString(articleLink);
        parcel.writeString(videoLink);
    }
}
