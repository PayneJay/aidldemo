// IBankAIDL.aidl
package jack.aidldemo;

// Declare any non-default types here with import statements

interface IBankAIDL {
        //开户
        String openAccount(String name, String passWord);

        //获取账户
        String getAccount();

        //存钱
        String saveMoney(String account, double number);

        //取钱
        String takeMoney(String account, String passWord, double number);

        //注销账户
        String closeAccount(String account, String passWord);
}
