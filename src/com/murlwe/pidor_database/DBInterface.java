package com.murlwe.pidor_database;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBInterface extends SQLiteOpenHelper {
	private static final String DBName = "Logbook1";
	private static final String TableName = "Entries";
	private static final int DBVersion = 1;
	
	private static final String key = "key";
	private static final String id = "id";
	private static final String name = "name";
	private static final String dept = "dept";
	private static final String stamp = "stamp";
	
	
	public DBInterface(Context context) { //, String name, CursorFactory factory, int version
		super(context, DBName, null, DBVersion);
		// TODO Auto-generated constructor stub
		
	}
	/*
	public DBInterface(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}
	*/

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_LOGBOOK = "create table " + TableName + 
				" (" + key + " integer primary key autoincrement not null, "
				+ id + " integer, " + name + " string, " 
				+ dept + " string, " + stamp + " date);";
		db.execSQL(CREATE_LOGBOOK);
	}
	
	public void addEntry(Entry entry) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(id, entry.getID());
		values.put(name, entry.getName());
		values.put(dept, entry.getDept());
		values.put(stamp, entry.getStamp().toGMTString());
		db.insert(TableName, null, values);
		db.close();
	}
	
	public List<Entry> getAllEntries() {
		
		SQLiteDatabase db = this.getWritableDatabase();
		/*
		String DELETE_LOGBOOK = "DELETE FROM Entries";
		db.execSQL(DELETE_LOGBOOK);
		*/
		List<Entry> temp = new ArrayList<Entry>();
		String getAll = "SELECT * FROM Entries";
		
		
		Cursor cursor = db.rawQuery(getAll, null);
		
		if(cursor.moveToFirst()) {
			do {
				@SuppressWarnings("deprecation")
				Entry entry = new Entry(Integer.parseInt(cursor.getString(1)), cursor.getString(2), 
						cursor.getString(3), new Date(cursor.getString(4).toString()));
				temp.add(entry);
			} while(cursor.moveToNext());
		}
		return temp;
	}

	public ArrayList<Object> getRowAsArray(long id1)	{
		SQLiteDatabase db = this.getWritableDatabase();

		ArrayList<Object> rowArray = new ArrayList<Object>();
		Cursor cursor;
 
		try
		{
			cursor = db.query
			(
					TableName,
					new String[] { key, id, name, stamp },
					key + "=" + id,
					null, null, null, null, null
			);
 
			cursor.moveToFirst();
 
			if (!cursor.isAfterLast())
			{
				do
				{
					rowArray.add(cursor.getLong(0));
					rowArray.add(cursor.getString(1));
					rowArray.add(cursor.getString(2));
					rowArray.add(cursor.getString(3));
				}
				while (cursor.moveToNext());
			}
 			cursor.close();
		}
		catch (SQLException e) 
		{
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		return rowArray;
	}
	
	public ArrayList<ArrayList<Object>> getAllRowsAsArrays() {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ArrayList<ArrayList<Object>> dataArrays = new ArrayList<ArrayList<Object>>();
 		Cursor cursor;
 
		try
		{
			cursor = db.query(
					TableName,
					new String[]{key, id, name, stamp},
					null, null, null, null, null
			);
 
			cursor.moveToFirst();
 
			if (!cursor.isAfterLast())
			{
				do
				{
					ArrayList<Object> dataList = new ArrayList<Object>();
 
					dataList.add(cursor.getLong(0));
					dataList.add(cursor.getString(1));
					dataList.add(cursor.getString(2));
					dataList.add(cursor.getString(3));
 
					dataArrays.add(dataList);
				}

				while (cursor.moveToNext());
			}
		}
		catch (SQLException e)
		{
			Log.e("DB Error", e.toString());
			e.printStackTrace();
		}
 
		return dataArrays;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
 
}
