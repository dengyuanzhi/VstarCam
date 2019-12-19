package com.jankin.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class CameraUtil {
    public CameraUtil() {
    }
    private BufferedReader input;

    public Bitmap httpForImage(String IP) {
        String httpUrl1 = "http://" + IP + "/snapshot.cgi?loginuse=admin&loginpas=888888&res=0";
        Bitmap bitmap = null;
        URL imageUrl = null;

        try {
            imageUrl = new URL(httpUrl1);
        } catch (MalformedURLException var8) {
            var8.printStackTrace();
        }

        if (imageUrl != null) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection)imageUrl.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
                httpURLConnection.disconnect();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        }

        return bitmap;
    }

    public void postHttp(String IP, int command, int step) {
        String httpUrl2 = "http://" + IP + "/decoder_control.cgi?loginuse=admin&loginpas=888888&" + "command=" + command + "&onestep=" + step;
        URL getUrl = null;

        try {
            getUrl = new URL(httpUrl2);
        } catch (MalformedURLException var9) {
            var9.printStackTrace();
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection)getUrl.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            urlConnection.disconnect();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
    public void setBright(String IP, int command ) {
//        ：/camera_control.cgi?param=&value=&user=&pwd=&next_url=
//        String httpUrl2 = "http://" + IP + "/decoder_control.cgi?loginuse=admin&loginpas=888888&" + "command=" + command + "&onestep=" + step;
        String httpUrl2 = "http://" + IP + "/camera_control.cgi?param=1&value="+command+"&loginuse=admin&loginpas=888888";
        URL getUrl = null;

        try {
            getUrl = new URL(httpUrl2);
        } catch (MalformedURLException var9) {
            var9.printStackTrace();
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection)getUrl.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            urlConnection.disconnect();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
    public void setContrast(String IP, int command ) {
//        ：/camera_control.cgi?param=&value=&user=&pwd=&next_url=
//        String httpUrl2 = "http://" + IP + "/decoder_control.cgi?loginuse=admin&loginpas=888888&" + "command=" + command + "&onestep=" + step;
        String httpUrl2 = "http://" + IP + "/camera_control.cgi?param=2&value="+command+"&loginuse=admin&loginpas=888888";
        URL getUrl = null;

        try {
            getUrl = new URL(httpUrl2);
        } catch (MalformedURLException var9) {
            var9.printStackTrace();
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection)getUrl.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            urlConnection.disconnect();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
    public void setHue(String IP, int command ) {
//        ：/camera_control.cgi?param=&value=&user=&pwd=&next_url=
//        String httpUrl2 = "http://" + IP + "/decoder_control.cgi?loginuse=admin&loginpas=888888&" + "command=" + command + "&onestep=" + step;
        String httpUrl2 = "http://" + IP + "/camera_control.cgi?param=9&value="+command+"&loginuse=admin&loginpas=888888";
        URL getUrl = null;

        try {
            getUrl = new URL(httpUrl2);
        } catch (MalformedURLException var9) {
            var9.printStackTrace();
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection)getUrl.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            urlConnection.disconnect();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
    public void setResolution(String IP, int command ) {
//        ：/camera_control.cgi?param=&value=&user=&pwd=&next_url=
//        String httpUrl2 = "http://" + IP + "/decoder_control.cgi?loginuse=admin&loginpas=888888&" + "command=" + command + "&onestep=" + step;
        String httpUrl2 = "http://" + IP + "/camera_control.cgi?param=0&value="+command+"&loginuse=admin&loginpas=888888";
        URL getUrl = null;

        try {
            getUrl = new URL(httpUrl2);
        } catch (MalformedURLException var9) {
            var9.printStackTrace();
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection)getUrl.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            urlConnection.disconnect();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }
    public void setSaturation(String IP, int command ) {
//        ：/camera_control.cgi?param=&value=&user=&pwd=&next_url=
//        String httpUrl2 = "http://" + IP + "/decoder_control.cgi?loginuse=admin&loginpas=888888&" + "command=" + command + "&onestep=" + step;
        String httpUrl2 = "http://" + IP + "/camera_control.cgi?param=8&value="+command+"&loginuse=admin&loginpas=888888";
        URL getUrl = null;

        try {
            getUrl = new URL(httpUrl2);
        } catch (MalformedURLException var9) {
            var9.printStackTrace();
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection)getUrl.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            urlConnection.disconnect();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    //：/get_camera_params.cgi[?user=&pwd=]
    public String getCameraParams(String IP){
        String result = null;
//        String httpUrl1 = "http://get_camera_params.cgi?loginuse=admin&loginpas=888888";
//        String httpUrl1 = "http://" + IP + "/get_camera_params.cgi?loginuse=admin&loginpas=888888";
//        String httpUrl1 = "http://" + IP + "/get_status.cgi?loginuse=admin&loginpas=888888";
        String httpUrl1 = "http://" + IP + "/get_camera_params.cgi?loginuse=admin&loginpas=888888";
//        String httpUrl1 = "http://" + IP + "/get_misc.cgi?loginuse=admin&loginpas=888888";
//        String httpUrl1 = "http://get_camera_params.cgi?";
        URL imageUrl = null;

        try {
            imageUrl = new URL(httpUrl1);
        } catch (MalformedURLException var8) {
            var8.printStackTrace();
        }

        if (imageUrl != null) {
            try {
               String temp;
                HttpURLConnection httpURLConnection = (HttpURLConnection)imageUrl.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                input = new BufferedReader(new InputStreamReader(in));
//                input.read(buffer,0,64);
                while ((temp = input.readLine()) != null ){
                    result += temp;
                }
                input.close();
                in.close();
                httpURLConnection.disconnect();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        }
        return result;
    }
}
