package com.example.projectuts;

import com.example.projectuts.model.Account;


public class Application extends android.app.Application{
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
