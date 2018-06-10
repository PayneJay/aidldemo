package jack.aidldemo;

import java.util.UUID;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.aidldemo
 * author: PayneJay
 * date: 2018/6/10.
 */

public class BankBinder extends IBankAIDL.Stub {
    private String account = UUID.randomUUID().toString();

    @Override
    public String openAccount(String name, String passWord) {
        return "恭喜" + name + "，开户成功！账户为：" + account + "   密码：" + passWord;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String saveMoney(String account, double number) {
        return "您已成功存入" + number + "(¥)元到账户" + account;
    }

    @Override
    public String takeMoney(String account, String passWord, double number) {
        return "您已成功从账户从账户为" + account + "的卡取款" + number + "(¥)元";
    }

    @Override
    public String closeAccount(String account, String passWord) {
        return "您已成功注销账户：" + account;
    }
}
