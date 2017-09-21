package com.example.luyy.practice26;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.id.message;


public class MainActivity extends Activity {

    private TextView textView;
    private Button button;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvid);
        button = (Button) findViewById(R.id.btnStart);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                textView.setText(String.valueOf(msg.arg1));

            }
        };

        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
                int num = 0;

                while (num <=10) {
                    Message msg= new Message();
                    msg.arg1 = num;
                    handler.sendMessage(msg);
                    num = ++num;
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Message msg=handler.obtainMessage();
                msg.arg1=-1;
                handler.sendMessage(msg);
            }
        };
        button = (Button) findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker, "WorkThread");
                workThread.start();
            }
        });
    }
}








