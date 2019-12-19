package com.jankin.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissions {

    private Context context;
    List<String> permissionList;
    public Permissions(Context context) {
        this.context = context;
         permissionList = new ArrayList<>();
    }

    public void requestPermissions(){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) !=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.INTERNET);
        }
        if(ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_WIFI_STATE);
        }

        // 如果列表为空，就是全部权限都获取了，不用再次获取了。不为空就去申请权限
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context,
                    permissionList.toArray(new String[permissionList.size()]), 1002);
        } else {
            Toast.makeText(context, "多个权限你都有了，不用再次申请", Toast.LENGTH_LONG).show();
        }

    }
}
