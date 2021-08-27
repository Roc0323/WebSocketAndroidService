package com.roc.websockettest;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Created by Roc on 2018/10/9.
 */
public class ServerSocket extends WebSocketServer {

    private ServerManager _serverManager;

    public ServerSocket(ServerManager serverManager,int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        _serverManager=serverManager;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Log.i("TAG","Some one Connected...");
        _serverManager.UserLogin("111",conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        _serverManager.UserLeave(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        Log.i("TAG","OnMessage:"+message.toString());

        if (message.equals("1")) {
            _serverManager.SendMessageToUser(conn, "What?");
        }

        String[] result=message.split(":");
        if (result.length==2) {
            if (result[0].equals("user")) {
                _serverManager.UserLogin(result[1], conn);
            }
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.i("TAG","Socket Exception:"+ex.toString());
    }

    @Override
    public void onStart() {

    }
}
