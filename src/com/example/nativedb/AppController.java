package com.example.nativedb;

import com.example.nativedb.NativeLoader;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {
	
	public static volatile Context applicationContext = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		applicationContext = getApplicationContext();
		NativeLoader.initNativeLibs(AppController.applicationContext);
	}
}
