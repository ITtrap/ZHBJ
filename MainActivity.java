package com.example.xuchen.zhbj;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

/**
 * Created by xuchen on 2016/8/10.
 */
public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"哈哈",Toast.LENGTH_SHORT).show();
    }
}
