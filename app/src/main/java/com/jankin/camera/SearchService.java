package com.jankin.camera;
/*
摄像头连接二维码
*/

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


public class SearchService extends Service {
	private static final String TAG = "SearchService";
	// 搜索摄像头IP类
	private SearchCameraUtil searchCameraUtil = null;
	// 摄像头IP
	private String IP = null;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		thread.start();
	}

	private Thread thread = new Thread(new Runnable() {

		public void run() {
			// TODO Auto-generated method stub
			try{
			//while (IP == null || IP.equals("")) {
				searchCameraUtil = new SearchCameraUtil();
				try {IP = searchCameraUtil.send();

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Log.e("连接摄像头", "run: 摄像头连接失败");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("请链接wifi");
					Log.e("wifi", "请链接wifi");
					Toast.makeText(SearchService.this, "请链接wifi", Toast.LENGTH_SHORT);
					e.printStackTrace();
				 }
			  // }
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("请链接wifi123123");
			}
			handler.sendEmptyMessage(10);
		}
	});

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 10) {
				Intent intent = new Intent(MainActivity.A_S);
				intent.putExtra("IP", IP + ":81");
				Log.e(TAG, "handleMessage: "+IP);
				sendBroadcast(intent);
				SearchService.this.stopSelf();
			}
		};
	};

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
