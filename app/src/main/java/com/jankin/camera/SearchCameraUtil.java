package com.jankin.camera;


import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SearchCameraUtil {
    private String IP = "";
    private String TAG = "UDPClient";
    private final int PORT = 3565;
    private final int SERVER_PORT = 8600;
    private byte[] mbyte = new byte[]{68, 72, 1, 1};
    private DatagramSocket dSocket = null;
    private byte[] msg = new byte[1024];

    public SearchCameraUtil() {
    }

    public String send() {
        InetAddress local = null;

        try {
            local = InetAddress.getByName("255.255.255.255");
            Log.e(this.TAG, "已找到服务器,连接中...");
        } catch (UnknownHostException var7) {
            Log.e(this.TAG, "未找到服务器.");
            var7.printStackTrace();
        }

        try {
            if (this.dSocket != null) {
                this.dSocket.close();
                this.dSocket = null;
            }

            this.dSocket = new DatagramSocket(3565);
            Log.e(this.TAG, "正在连接服务器...");
        } catch (SocketException var6) {
            var6.printStackTrace();
            Log.e(this.TAG, "服务器连接失败.");
        }

        DatagramPacket sendPacket = new DatagramPacket(this.mbyte, 4, local, 8600);
        DatagramPacket recPacket = new DatagramPacket(this.msg, this.msg.length);

        try {
            this.dSocket.send(sendPacket);
            this.dSocket.receive(recPacket);
            String text = new String(this.msg, 0, recPacket.getLength());
            if (text.substring(0, 2).equals("DH")) {
                this.getIP(text);
            }

            Log.e("IP值", this.IP);
            this.dSocket.close();
            Log.e(this.TAG, "消息发送成功!");
        } catch (IOException var5) {
            var5.printStackTrace();
            Log.e(this.TAG, "消息发送失败.");
        }

        return this.IP;
    }

    private void getIP(String text) {
        try {
            byte[] ipbyte = text.getBytes("UTF-8");

            for(int i = 4; i < 22 && ipbyte[i] != 0; ++i) {
                if (ipbyte[i] == 46) {
                    this.IP = this.IP + ".";
                } else {
                    this.IP = this.IP + (ipbyte[i] - 48);
                }
            }
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

    }
}
