package jack.aidldemo;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private final String passWord = "123456";
    private TextView tvInfo;
    private IBankAIDL mBankBinder;
    private String mAccount;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBankBinder = IBankAIDL.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BankService.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    private void initView() {
        tvInfo = findViewById(R.id.tv_text_info);
        findViewById(R.id.btn_open_account).setOnClickListener(this);
        findViewById(R.id.btn_save_money).setOnClickListener(this);
        findViewById(R.id.btn_take_money).setOnClickListener(this);
        findViewById(R.id.btn_close_account).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        clearMsg();
        switch (view.getId()) {
            case R.id.btn_open_account:
                try {
                    mAccount = mBankBinder.getAccount();
                    showMsg(mBankBinder.openAccount("Jack", passWord));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_save_money:
                try {
                    showMsg(mBankBinder.saveMoney(mAccount, 6666.66));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_take_money:
                try {
                    showMsg(mBankBinder.takeMoney(mAccount, passWord, 6666));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_close_account:
                try {
                    showMsg(mBankBinder.closeAccount(mAccount, passWord));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showMsg(String text) {
        tvInfo.setText(text);
    }

    private void clearMsg() {
        tvInfo.setText("");
    }
}
