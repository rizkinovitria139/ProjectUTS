package com.example.projectuts.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    public enum Type{
        EMPTY,
        STANDARD,
        PREMIUM
    }
    private String image;
    private String namaPemilik;
    private String namaHewan;
    private Type type;
    private String tanggal;

    protected Transaction(Parcel in) {
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
