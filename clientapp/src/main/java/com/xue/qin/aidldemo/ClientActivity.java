package com.xue.qin.aidldemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xue.qin.aidl.IMyAidlInterface;
import com.xue.qin.demo.myparcelableclass.MyObject;

/**
 * Created by xue.qin on 2017/6/1.
 */

public class ClientActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ClientActivity";
    private TextView mTextView;
    private Button mButton;
    private IMyAidlInterface mService = null;

    private ServiceConnection mConntection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected()");
            mService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected()");
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        mTextView = (TextView) findViewById(R.id.result);
        mButton = (Button) findViewById(R.id.switchmodel);
        mButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent("remoteService");
        intent.setPackage("com.xue.qin.server");
        boolean success = bindService(intent, mConntection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "onResume() success = " + success);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConntection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switchmodel:
                try {
                    MyObject obj1 = new MyObject(1, "cat", "there");
                    MyObject obj2 = new MyObject(1, "cat", "there");
                    MyObject obj3 = new MyObject(1, "cat", "there");
                    Log.i(TAG, "before fun obj1 = " + obj1);
                    MyObject result1in = mService.inObject(obj1);
                    Log.i(TAG, "after fun obj1 = " + obj1);
                    Log.i(TAG, "result1in = " + result1in);


                    Log.i(TAG, "before fun obj2 = " + obj2);
                    MyObject resultlout = mService.outObject(obj2);
                    Log.i(TAG, "after fun obj2 = " + obj2);
                    Log.i(TAG, "resultlout = " + resultlout);


                    Log.i(TAG, "before fun obj3 = " + obj3);
                    MyObject resultlinout = mService.inoutObject(obj3);
                    Log.i(TAG, "after fun obj3 = " + obj3);
                    Log.i(TAG, "resultlinout = " + resultlinout);


                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
