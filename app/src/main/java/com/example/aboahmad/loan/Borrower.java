package com.example.aboahmad.loan;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;



@Entity(tableName = "loan_table")
public class Borrower implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private String loan_description;

     Borrower(String name, String loan_description) {
        this.name = name;
        this.loan_description = loan_description;
    }

    protected Borrower(Parcel in) {
        id = in.readInt();
        name = in.readString();
        loan_description = in.readString();
    }

    public static final Creator<Borrower> CREATOR = new Creator<Borrower>() {
        @Override
        public Borrower createFromParcel(Parcel in) {
            return new Borrower(in);
        }

        @Override
        public Borrower[] newArray(int size) {
            return new Borrower[size];
        }
    };

    public void setId(int id){
        this.id=id;
    }
    @NonNull
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoan_description() {
        return loan_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(loan_description);
    }
}
