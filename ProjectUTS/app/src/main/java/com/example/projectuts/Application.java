package com.example.projectuts;

import com.example.projectuts.model.Account;
import com.example.projectuts.model.Session;


public class Application extends android.app.Application{
    private static Account account;
    private static Session session;

    @Override
    public void onCreate() {

        super.onCreate();
        account = new Account("Budi");

        session = new Session(this);
    }

    public static Session getSession(){
        return session;
    }

    public static Account getAccount() {
        return account;
    }
}
