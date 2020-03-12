package com.dhanifudin.cashflow;

import com.dhanifudin.cashflow.models.Account;

public class Application extends android.app.Application {

    private static Account account;

    @Override
    public void onCreate() {

        super.onCreate();
        account = new Account("Budi");
    }

    public static Account getAccount() {
        return account;
    }
}
