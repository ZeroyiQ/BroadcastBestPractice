package top.zeroyiq.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by ZeroyiQ on 2017/6/10.
 */

public class BaseActivity extends AppCompatActivity {
    private  ForceOffLineReceiver receiver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        ActionBar bar = getSupportActionBar();
        if (bar!=null){
            bar.hide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("top.zeroyiq.broadcastbestpractice.FORCE_OFFLINE");
        receiver= new ForceOffLineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private class ForceOffLineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            MaterialDialog dialog=new MaterialDialog(context);
            dialog.setTitle("Warning").setMessage("You are forced to be offline. Please try to login again.");
            dialog.setPositiveButton("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //销毁所有活动
                    ActivityCollector.finishALL();
                    //重新启动LoginActivity
                    LoginActivity.actionStart(context);
                }
            });

            dialog.show();

        }
    }
}
