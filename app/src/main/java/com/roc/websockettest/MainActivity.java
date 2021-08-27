package com.roc.websockettest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ServerManager serverManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverManager=new ServerManager();
        findViewById(R.id.btn_open_websocket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverManager.Start(8888);
            }
        });
        findViewById(R.id.btn_close_websocket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverManager.Stop();
            }
        });
        findViewById(R.id.btn_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverManager.SendMessageToAll("Hello，我是服务端！");
            }
        });
    }
}
