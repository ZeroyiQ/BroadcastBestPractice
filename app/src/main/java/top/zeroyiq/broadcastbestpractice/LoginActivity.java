package top.zeroyiq.broadcastbestpractice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    private SharedPreferences pre;
    private SharedPreferences.Editor editor;

    @BindView(R.id.account)
    EditText accountText;
    @BindView(R.id.password)EditText passwordText;

    @BindView(R.id.checkbox_remember_psw)
    CheckBox checkBoxRememberPsw;
    @OnClick(R.id.login) void onClick(View v){
        //获取账户和密码
        String account = accountText.getText().toString();
        String password = passwordText.getText().toString();
        if (account.equals("admin")&&password.equals("1234")){
            editor = pre.edit();
            if(checkBoxRememberPsw.isChecked()){
                editor.putBoolean("remember_password",true);
                editor.putString("account",account);
                editor.putString("password",password);
            }else {
                editor.clear();
            }
            editor.apply();
            MainActivity.actionStart(LoginActivity.this);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,  "account or password is invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public static void  actionStart(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pre= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pre.getBoolean("remember_password",false);
        if(isRemember){
            String account = pre.getString("account","");
            String password = pre.getString("password","");
            accountText.setText(account);
            passwordText.setText(password);
            checkBoxRememberPsw.setChecked(true);
        }
    }
}
