package com.xue.qin.demo.myparcelableclass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xue.qin on 2017/6/5.
 */

public class MyObject implements Parcelable {
    private int n;
    private String name;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseless() {
        return useless;
    }

    public void setUseless(String useless) {
        this.useless = useless;
    }

    private String useless;

    public MyObject(){
        n=0;
        name="default";
        useless="default";
    }




    public MyObject(int n, String name, String useless) {
        this.n = n;
        this.name = name;
        this.useless = useless;
    }

    public MyObject(Parcel source) {
        readFromParcel(source);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(n);
        dest.writeString(name);
        dest.writeString(useless);
    }

    public void readFromParcel(Parcel source) {
        this.n = source.readInt();
        this.name = source.readString();
        this.useless = source.readString();
    }

    public static final Parcelable.Creator<MyObject> CREATOR = new Creator<MyObject>() {
        @Override
        public MyObject createFromParcel(Parcel source) {
            MyObject myObject2 = new MyObject(source);
            return myObject2;
        }

        @Override
        public MyObject[] newArray(int size) {
            return new MyObject[size];
        }
    };

    @Override
    public String toString() {
        return "( n:" + n + " name:" + name + " useless:" + useless + " 地址:" + super.toString() + " )";
    }
}
