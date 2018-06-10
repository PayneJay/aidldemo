package jack.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.aidldemo
 * author: PayneJay
 * date: 2018/6/10.
 */

public class BankService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new BankBinder();
    }
}
