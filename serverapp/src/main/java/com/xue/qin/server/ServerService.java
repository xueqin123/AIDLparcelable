package com.xue.qin.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.xue.qin.aidl.IMyAidlInterface;
import com.xue.qin.demo.myparcelableclass.MyObject;

/**
 * Created by xue.qin on 2017/6/1.
 */

public class ServerService extends Service {
    private static final String TAG = "ServerService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate() Thread.currentThread() = "+Thread.currentThread());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public MyObject inObject(MyObject obj) throws RemoteException {
            Log.i(TAG,"Thread.currentThread() = "+Thread.currentThread());
            Log.i(TAG, "inObject = " + obj);
            obj.setN(100);
            obj.setName("dog");
            obj.setUseless("there");
            return new MyObject(3,"pig","home");
        }

        @Override
        public MyObject outObject(MyObject obj) throws RemoteException {
            Log.i(TAG, "outObject = " + obj);
            obj.setN(100);

            obj.setName("dog");
            obj.setUseless("there");
            return new MyObject(3,"pig","home");
        }

        @Override
        public MyObject inoutObject(MyObject obj) throws RemoteException {
            Log.i(TAG, "inoutObject = " + obj);
            obj.setN(100);
            obj.setName("dog");
            obj.setUseless("there");
            return new MyObject(3,"pig","home");

        }
    }
}
