package com.zdans.tugasakb3sharedpreferences.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zdans.tugasakb3sharedpreferences.R;
import com.zdans.tugasakb3sharedpreferences.model.UserModel;
import com.zdans.tugasakb3sharedpreferences.utils.Preferences;

public class activitylogin extends AppCompatActivity {

    private TextView txtMasuk;
    private TextView txtRegister;
    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        declareView();

        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), activityregister.class));
            }
        });


    }

    @Override
    protected void onStart() {

        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), activityhome.class));
            finish();
        }

    }





    private void declareView() {
        txtRegister = findViewById(R.id.txt_login_register);
        txtMasuk = findViewById(R.id.btn_login_masuk);
        edtUsername = findViewById(R.id.edttxt_login_username);
        edtPassword = findViewById(R.id.edttxt_login_password);
    }


    private void validasiLogin(){
        edtUsername.setError(null);
        edtPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String userName = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            edtUsername.setError("Harus diisi");
            fokus = edtUsername;
            cancel = true;
        } else if (!cekUser(userName)) {
            edtUsername.setError("Username Tidak Ditemukan");
            fokus = edtUsername;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Harus Diisi");
            fokus = edtPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            edtPassword.setError("Data yang dimasukkan tidak sesuai");
            fokus = edtPassword;
            cancel = true;
        }

        if (cancel) {
            fokus.requestFocus();
        } else {
            // Deklarasi Model
            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);
            // Simpan data ke shared preferences
            Preferences.setUserPreferences(getBaseContext(), userModel);
            Preferences.setLoggedInStatus(getBaseContext(), true);
            //Pindah Halaman ke home
            startActivity(new Intent(getBaseContext(), activityhome.class));
            finish();
        }
    }


    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }


    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPassword(getBaseContext()));
    }

/*
    NIM           : 10117142
    Nama          : Hilmi Zaidan Amari
    Kelas         : IF-4 / AKB-4
    Tugas         : AKB ke 3 (Shared Preferences)
    Tanggal Mulai : 28 April 2020

    Update :
    - membuat Tampilan dan class activity login dan register
    - membuat Tampilan dan class activity home
    - membuat package model dan utils
    - membuat validasi login, register
    - membuat deklarasi username, pass, default_string, dll
    - membuat fungsi tampilan username user yang login pada home
    - push Github
    - generate Release APK

 */







}
