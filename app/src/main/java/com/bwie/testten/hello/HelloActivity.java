package com.bwie.testten.hello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwie.testten.MainActivity;
import com.bwie.testten.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HelloActivity extends AppCompatActivity {
    @BindView(R.id.tvhello)
    TextView tvhello;
    private int count = 4;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count--;
            if (count == 0) {
                Intent in = new Intent(HelloActivity.this, MainActivity.class);
                startActivity(in);
                finish();
            } else {
                handler.sendEmptyMessageDelayed(99, 1000);
                tvhello.setText(count + "秒  跳过");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        ButterKnife.bind(this);
        handler.sendEmptyMessageDelayed(99,1000);
    }

    @OnClick(R.id.tvhello)
    public void onViewClicked() {
        handler.removeCallbacksAndMessages(null);
        Intent in = new Intent(HelloActivity.this,MainActivity.class);
        startActivity(in);
        finish();
    }
}
