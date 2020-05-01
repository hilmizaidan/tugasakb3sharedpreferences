package com.zdans.tugasakb3sharedpreferences.tugas3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zdans.tugasakb3sharedpreferences.R;
import com.zdans.tugasakb3sharedpreferences.utils.Preferences;

public class activityhome extends AppCompatActivity {

    private TextView txtLogout;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        declareView();
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Clear Set Preferences
                Preferences.setLogout(getBaseContext());

                //Pindah Halaman ke Login
                startActivity(new Intent(getBaseContext(), activitylogin.class));
                finish();
            }
        });
    }



    private void declareView() {
        txtLogout = findViewById(R.id.btn_logout);
        txtName = findViewById(R.id.txt_name);

        txtName.setText(Preferences.getRegisteredUser(getBaseContext()));
    }
}
