package com.quanquan.swiprecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeSwitch;

/**
 * Created by ChangquanSun
 * 2017/3/28
 * layout侧滑
 */

public class SecondActivity extends AppCompatActivity {
    private TextView mTvContent,mBtnRight;

    private SwipeSwitch mSwipeSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mSwipeSwitch = (SwipeSwitch) findViewById(R.id.swipe_layout);
        mTvContent = (TextView) findViewById(R.id.content_view);
        mBtnRight = (TextView) findViewById(R.id.right_view);
        mBtnRight.setOnClickListener(xOnClickListener);
    }
    private View.OnClickListener xOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.right_view) {
                mSwipeSwitch.smoothCloseMenu();// 关闭菜单。
                Toast.makeText(SecondActivity.this, "我是右面的", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
