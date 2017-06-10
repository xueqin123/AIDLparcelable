package com.xue.qin.aidl;
import com.xue.qin.demo.myparcelableclass.MyObject;
interface IMyAidlInterface {
    MyObject inObject(in MyObject obj);
    MyObject outObject(out MyObject obj);
    MyObject inoutObject(inout MyObject obj);
}