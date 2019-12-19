package com.jankin.camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CameraUtil cameraUtil;
    private String IP = null;
    public static final String A_S = "com.a_s";// 广播名称
    private Bitmap bitmap = null;
    private ImageView imageView;
    private ThreadImage threadImage;
    private String resutl;
    private int bright = 200;
    private int contrast = 255;
    private int saturation = 128;
    private int hue = 255;
    private int resolution = 1;
    private IndicatorSeekBar isbBright;
    private IndicatorSeekBar isbContrast;
    private IndicatorSeekBar isbSaturation;
    private IndicatorSeekBar isbHue;
    private Permissions permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissions = new Permissions(this);
        permissions.requestPermissions();
        Camer_Init();
        search();
        initView();
        listener();
    }

    private void listener() {
        isbBright.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(final SeekParams seekParams) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cameraUtil.setBright(IP,seekParams.progress);
                    }
                }).start();
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        isbContrast.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(final SeekParams seekParams) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cameraUtil.setContrast(IP,seekParams.progress);
                    }
                }).start();

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        isbSaturation.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(final SeekParams seekParams) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cameraUtil.setSaturation(IP,seekParams.progress);
                    }
                }).start();

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        isbHue.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(final SeekParams seekParams) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cameraUtil.setHue(IP,seekParams.progress);
                    }
                }).start();

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
    }

    private void initView() {
        imageView = findViewById(R.id.img_Camera);
        isbBright = findViewById(R.id.isb_1);
        isbContrast = findViewById(R.id.isb_2);
        isbSaturation = findViewById(R.id.isb_3);
        isbHue = findViewById(R.id.isb_4);
    }

    // 广播接收器接受SearchService搜索的摄像头IP地址加端口
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context arg0, Intent arg1) {
            IP = arg1.getStringExtra("IP");
            Toast.makeText(arg0, IP, Toast.LENGTH_SHORT).show();
            threadImage = new ThreadImage(arg0);
            threadImage.start();

        }
    };

    private class ThreadImage extends Thread{
        private Context context;

        public ThreadImage(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()){
                if(IP != null) {
                    bitmap = cameraUtil.httpForImage(IP);
                    setCarmer();
                    resutl = cameraUtil.getCameraParams(IP);
                    Log.e(TAG, "cameraUtil:"+resutl);
                    if(bitmap != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "图像无法获取", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "IP未知", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
//            System.out.println(cameraUtil.getCameraParams());

        }
    }

    private void setCarmer() {
        cameraUtil.setResolution(IP,resolution);
    }

    private void search() {
        // 搜索摄像cameraIP进度条
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SearchService.class);
        startService(intent);
    }
    private void Camer_Init() {
        // 广播接收器注册
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(A_S);
            registerReceiver(myBroadcastReceiver, intentFilter);
            // 搜索摄像头图片工具
            cameraUtil = new CameraUtil();
        } catch (Exception e) {
            unregisterReceiver(myBroadcastReceiver);
            Toast.makeText(this, "初始化异常", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1002:
                // 1002请求码对应的是申请多个权限
                if (grantResults.length > 0) {
                    // 因为是多个权限，所以需要一个循环获取每个权限的获取情况
                    for (int i = 0; i < grantResults.length; i++) {
                        // PERMISSION_DENIED 这个值代表是没有授权，我们可以把被拒绝授权的权限显示出来
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED){
                            Toast.makeText(MainActivity.this, permissions[i] + "权限被拒绝了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }
}
