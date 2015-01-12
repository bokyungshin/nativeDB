package com.example.nativedb;

import java.io.File;

import android.util.Log;

public class DBManager {
	private SQLiteDatabase database;
	private File cacheFile;
	private static volatile DBManager Instance = null;	
	
	public static DBManager getInstance() {
		synchronized (DBManager.class) {
			if (Instance == null) {
				Instance = new DBManager();
			}
		}
		
		return Instance;
	}
	
	public DBManager() {
		openDatabase();
	}
	
	public void openDatabase() {
		Log.e("nativeDB", ">> openDatabase()");
		cacheFile = new File(AppController.applicationContext.getFilesDir(), "test.db");
		
		boolean createTable = false;
		if (!cacheFile.exists()) {
			createTable = true;
		}
		
		try {
			database = new SQLiteDatabase(cacheFile.getPath());
			Log.e("nativeDB", "++ database path = " + cacheFile.getPath());
			Log.e("nativeDB", "++ createTable= " + createTable);
			if (createTable) {
				database.executeFast("CREATE TABLE random(random_id INTEGER PRIMARY KEY, value INTEGER)").stepThis().dispose();
				database.executeFast("INSERT INTO random VALUES(1, 1)").stepThis().dispose();
			} 
//			else {
//				try {
//					SQLiteCursor cursor = database.queryFinalized("SELECT * FROM random");
//					if (cursor.next()) {
//						
//					}
//					
//					cursor.dispose();
//				} catch (Exception e) {
//					Log.e("nativeDB", e.getMessage());
//					
//				}
//			}
			
		} catch (Exception e) {
			Log.e("nativeDB", e.getMessage());
		}
	}
	
	public void loadMessage() {
		StringBuilder str = new StringBuilder();
		try {
			SQLiteCursor cursor = database.queryFinalized("SELECT * FROM random");
			while (cursor.next()) {
				int num = cursor.intValue(1);
				str.append(num);
			}
			cursor.dispose();
		} catch (SQLiteException e) {
				e.printStackTrace();
		}
		
		Log.e("nativeDB", "++++++++ message = " + str);
	}
}
