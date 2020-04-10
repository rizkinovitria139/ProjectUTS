package com.example.projectuts.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    public Transaction() {

    }

    public enum Type{
        EMPTY,
        STANDARD,
        PREMIUM
    }
    private String namaPemilik;
    private String namaHewan;
    private Type type;
    private String tanggal;
    private int amount;

    public int getAmount() {
        if(type == Type.STANDARD){
            setAmount(30000);
        }else{
            setAmount(50000);
        }
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Transaction(Parcel in) {
        this.namaHewan = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
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
        dest.writeString(this.namaHewan);
        dest.writeString(this.tanggal);
        dest.writeString(this.namaHewan);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
    }
}
