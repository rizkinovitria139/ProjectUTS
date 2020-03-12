package com.dhanifudin.cashflow;

import android.content.Intent;
import android.os.Bundle;

import com.dhanifudin.cashflow.adapters.TransactionAdapter;
import com.dhanifudin.cashflow.models.Account;
import com.dhanifudin.cashflow.models.Transaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements TransactionAdapter.OnItemTransactionListener {

    public static final String TRANSACTION_KEY = "TRANSACTION";
    public static final String INDEX_KEY = "INDEX";
    public static final int INSERT_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;

    private TextView welcomeText;
    private TextView balanceText;
    private RecyclerView transactionsView;
    private TransactionAdapter adapter;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        welcomeText = findViewById(R.id.text_welcome);
        balanceText = findViewById(R.id.text_balance);
        transactionsView = findViewById(R.id.rv_transactions);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Tambahkan event click fab di sini
            }
        });

        account = Application.getAccount();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTransactionClicked(int index, Transaction item) {
        welcomeText.setText(String.format("welcome %s", account.getName()));
        balanceText.setText(String.valueOf(account.getBalance()));

        adapter = new TransactionAdapter(account.getTransactions(), this);
        transactionsView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        transactionsView.setLayoutManager(layoutManager);

        Intent in = new Intent(this, SaveActivity.class); //definisi intent
        in.putExtra(TRANSACTION_KEY, item); //pengiriman data transaksi
        in.putExtra(INDEX_KEY, 0); //posisi index
        startActivityForResult(in, UPDATE_REQUEST);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void handleFab(View view) {
        Intent in = new Intent(MainActivity.this, SaveActivity.class);
        in.putExtra(TRANSACTION_KEY, new Transaction());
        startActivityForResult(in, INSERT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Transaction transaction = data.getParcelableExtra(TRANSACTION_KEY);
            if(requestCode == INSERT_REQUEST){
                account.addTransaction(transaction);
            }else if(requestCode == UPDATE_REQUEST){
                int index = data.getIntExtra(INDEX_KEY, 0);
                account.updateTransaction(index, transaction);
            }
            adapter.notifyDataSetChanged(); //method yang memberitahukan kepada RecyclerView bahwa telah terjadi perubahan data
            welcomeText.setText(String.valueOf(account.getBalance()));
        }
    }
}
