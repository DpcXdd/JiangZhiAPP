package com.example.jiangzhiapp.login.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context, String name, CursorFactory factory,
						int version) {
		super(context, name, factory, version);
	}

	//数据库创建时，此方法会调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table user (id varchar(30) PRIMARY KEY NOT NULL, " +
				" password varchar(32) NOT NULL " +
				")");

	}

	//数据库升级时，此方法会调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}



}
