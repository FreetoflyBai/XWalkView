package com.android.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result=((EditText)findViewById(R.id.tracking)).getText().toString();
                if(TextUtils.isEmpty(result)){
                    Toast.makeText(SplashActivity.this,"请输入offer tracking",Toast.LENGTH_SHORT).show();
                     return;
                }
                SharedPreferences sharedPreferences=getSharedPreferences("TEST", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("OFFER",result).commit();
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });



    }
}
