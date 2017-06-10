package top.zeroyiq.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends BaseActivity {

    @OnClick(R.id.force_offline)
    void onClick() {
        Intent intent = new Intent("top.zeroyiq.broadcastbestpractice.FORCE_OFFLINE");
        sendBroadcast(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


}
