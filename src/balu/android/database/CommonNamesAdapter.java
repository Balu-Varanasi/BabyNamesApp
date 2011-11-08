package balu.android.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import balu.android.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CommonNamesAdapter {

	public static final String DATABASE_TABLE_1 = "common_names";
	
	public static final String COMMON_NAME_ROWID = "_id";
	public static final String COMMON_NAME = "common_name";
	public static final String COMMON_NAME_COUNT = "common_name_count";
		
	private final Context context;
	private SQLiteDatabase database;

	public static final String TAG = "COMMON_NAMES_TABLE";	
	private BabyNamesDBHelper baby_names_db_helper;

	public CommonNamesAdapter(Context context) {
		  
	    this.context = context;
	    this.open();
	      
	    try{
	    	InputStream is = context.getResources().openRawResource(R.raw.commonnames);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String strLine = null;

	    	while ((strLine = br.readLine()) != null) {
	    		String[] temp;

	    		strLine = strLine.trim();
	    		temp = strLine.split("\\s+");
	    		  
		    	this.createCommonName(temp[0], temp[1]);
		    }
	    	
	    	is.close();
	    }
	    catch (Exception e){
	    	Log.i(TAG, "Error while inserting common names into table");
	    }
	    this.close();
	}

	public CommonNamesAdapter open() throws SQLException {
		
		Log.i(TAG, "OPening DataBase Connection....");
		baby_names_db_helper = new BabyNamesDBHelper(context);
		database = baby_names_db_helper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		database.close();
	}
		  
	public long createCommonName(String commonName, String commonNameCount) {
		Log.i(TAG, "Inserting record...");

	    ContentValues initialValues = new ContentValues();

	    initialValues.put(COMMON_NAME, commonName);
	    initialValues.put(COMMON_NAME_COUNT, commonNameCount);
	
	    return database.insert(DATABASE_TABLE_1, null, initialValues);
	} 
	public boolean deleteCommonName(long rowId) {
	    return database.delete(DATABASE_TABLE_1, COMMON_NAME_ROWID + "=" + rowId, null) > 0;
	}
	 
	public Cursor fetchAllCommonNames() {	 
	    return database.query(DATABASE_TABLE_1, new String[] {COMMON_NAME_ROWID, COMMON_NAME , COMMON_NAME_COUNT}, null, null, null, null, null);
	}
	 
	public Cursor fetchCommonName(long commonNameId) throws SQLException {
		
		Cursor mCursor = database.query(true, DATABASE_TABLE_1, new String[] {
				COMMON_NAME_ROWID, COMMON_NAME, COMMON_NAME_COUNT}, COMMON_NAME_ROWID + "=" +
				commonNameId, null, null, null, null, null);
		
	    if(mCursor != null) {
	    	mCursor.moveToFirst();
	    }
	    return mCursor;
	}
	
	public boolean updateCommonName(int commonNameId, String commonName, String commonNameCount) {
	    ContentValues args = new ContentValues();
	    args.put(COMMON_NAME, commonName);
	    args.put(COMMON_NAME_COUNT, commonNameCount);
	 
	    return database.update(DATABASE_TABLE_1, args, COMMON_NAME_ROWID + "=" + commonNameId, null) > 0;
	  }
	 

}
