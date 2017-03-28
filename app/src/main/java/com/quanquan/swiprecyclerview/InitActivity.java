package com.quanquan.swiprecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class InitActivity extends AppCompatActivity {
    Button btnOne,btnSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        btnOne= (Button) findViewById(R.id.btn_one);
        btnSecond= (Button) findViewById(R.id.btn_second);
        btnOne.setOnClickListener(listener);
        btnSecond.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_one:
                    startActivity(new Intent(InitActivity.this,MainActivity.class));
                    break;
                case R.id.btn_second:
                    startActivity(new Intent(InitActivity.this,SecondActivity.class));
                    break;
            }
        }
    };
}
