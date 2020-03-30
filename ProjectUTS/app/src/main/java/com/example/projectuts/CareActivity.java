package com.example.projectuts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CareActivity extends AppCompatActivity {
    private EditText namaHewanInput;
    private EditText namaPemilikInput;
    private RadioButton standardCareInput;
    private RadioButton premiumCareInput;
    private EditText tanggalDatangInput;
    private EditText tanggalJemputInput;
    private ImageView fotoCareInput;
    private RadioGroup rGroupCare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        namaHewanInput = findViewById (R.id.namaHewan);
        namaPemilikInput = findViewById(R.id.namaPemilik);
        standardCareInput = findViewById(R.id.standardCare);
        premiumCareInput = findViewById(R.id.premiumCare);
        tanggalDatangInput = findViewById(R.id.tanggalDatang);
        tanggalJemputInput = findViewById(R.id.tanggalJemput);
        fotoCareInput = findViewById(R.id.inputImageCare);
        rGroupCare = findViewById(R.id.rGroup);
    }

    public void handleSubmit(View view) {
        if(namaHewanInput.getText().toString().isEmpty()){
            namaHewanInput.setError("Nama Hewan harus diisi");
        }else if(namaPemilikInput.getText().toString().isEmpty()){
            namaPemilikInput.setError("Nama Pemilik harus diisi");
        }else if(rGroupCare.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Isi tipe !", Toast.LENGTH_SHORT).show();
        }else if(tanggalDatangInput.getText().toString().isEmpty()){
            tanggalDatangInput.setError("Isi tanggal Datang!");
        }else if (tanggalJemputInput.getText().toString().isEmpty()){
            tanggalJemputInput.setError("Isi tanggal Jemput!");
        }
    }
}
