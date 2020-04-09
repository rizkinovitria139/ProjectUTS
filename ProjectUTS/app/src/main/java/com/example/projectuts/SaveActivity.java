package com.example.projectuts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.projectuts.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.projectuts.MainActivity.TRANSACTION_KEY;
import static com.example.projectuts.R.*;

public class SaveActivity extends AppCompatActivity {
    private EditText namaHewan;
    private EditText namaPemilik;
    private RadioGroup radioGroup;
    private EditText tanggal;
    private Transaction item;
    private int index;

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.save_groom);

        namaHewan = findViewById(id.namaHewan);
        namaPemilik = findViewById(id.namaPemiliktxt);
        radioGroup = findViewById(id.radioGroup);
        tanggal = findViewById(id.dateGroom);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            item = extras.getParcelable(TRANSACTION_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            namaHewan.setText(item.getNamaHewan());
            namaPemilik.setText(item.getNamaPemilik());


            if(item.getType() == Transaction.Type.STANDARD){
                radioGroup.check(id.radioButtonSTandard);
            }else if(item.getType() == Transaction.Type.PREMIUM){
                radioGroup.check(id.radioButtonPremium);
            }

        }

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SaveActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private Transaction.Type getCheckedType(){
        if(radioGroup.getCheckedRadioButtonId() == id.radioButtonSTandard){
            return  Transaction.Type.STANDARD;
        }else if(radioGroup.getCheckedRadioButtonId() == id.radioButtonPremium){
            return Transaction.Type.PREMIUM;
        }
        return Transaction.Type.EMPTY;
    }

    public void handleSubmit(View view) {
        if (namaPemilik.getText().toString().isEmpty()) {
            namaPemilik.setError("Isi Nama Pemilik!");
        } else if (namaHewan.getText().toString().isEmpty()) {
            namaHewan.setError("Isi Nama Hewan!");
        } else if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Isi jenis!", Toast.LENGTH_SHORT).show();
        } else  if(tanggal.getText().toString().isEmpty()){
            tanggal.setError("isi Tanggal!");
        } else{
            String namaPem = namaPemilik.getText().toString();
            String namaHw = namaHewan.getText().toString();
            Transaction.Type type = getCheckedType();

            item.setNamaPemilik(namaPem);
            item.setNamaHewan(namaHw);
            item.setType(type);

            Intent in = new Intent();
            in.putExtra(TRANSACTION_KEY, item);
            in.putExtra(MainActivity.INDEX_KEY, index);
            setResult(RESULT_OK, in);
            finish();
        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tanggal.setText(sdf.format(myCalendar.getTime()));
    }
}
