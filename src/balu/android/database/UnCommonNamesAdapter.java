package balu.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UnCommonNamesAdapter {

	public static final String DATABASE_TABLE_2 = "un_common_names";
	
	public static final String UN_COMMON_NAME_ROWID = "_id";
	public static final String UN_COMMON_NAME = "un_common_name";
	public static final String UN_COMMON_NAME_MEANING = "un_common_name_meaning";
	
	private SQLiteDatabase database;

	public static final String TAG = "UN_COMMON_NAMES_TABLE";	
	private BabyNamesDBHelper baby_names_db_helper;

	public UnCommonNamesAdapter(Context context) {

	}

	public UnCommonNamesAdapter open(Context context) throws SQLException {
		
		Log.i(TAG, "OPening DataBase Connection....");
		baby_names_db_helper = new BabyNamesDBHelper(context);
		database = baby_names_db_helper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		database.close();
	}
	
	public boolean deleteUnCommonName(long rowId) {
	    return database.delete(DATABASE_TABLE_2, UN_COMMON_NAME_ROWID + "=" + rowId, null) > 0;
	}
	 
	public Cursor fetchAllUnCommonNames() {	 
	    return database.query(DATABASE_TABLE_2, 
	    		new String[] {UN_COMMON_NAME_ROWID, UN_COMMON_NAME, UN_COMMON_NAME_MEANING},
	    		null, null, null, null, UN_COMMON_NAME);
	}
	 
	public Cursor fetchUnCommonName(long uncommonNameId) throws SQLException {
		
		Cursor mCursor = database.query(true, DATABASE_TABLE_2, new String[] {
				UN_COMMON_NAME_ROWID, UN_COMMON_NAME}, UN_COMMON_NAME_ROWID + "=" +
				uncommonNameId, null, null, null, null, null);
		
	    if(mCursor != null) {
	    	mCursor.moveToFirst();
	    }
	    return mCursor;
	}
	
	public boolean updateUnCommonName(int uncommonNameId, String uncommonName, String unCommonNameMeaning) {
	    ContentValues args = new ContentValues();
	    args.put(UN_COMMON_NAME, uncommonName);
	    args.put(UN_COMMON_NAME_MEANING, unCommonNameMeaning);
	   
	    return database.update(DATABASE_TABLE_2, args, UN_COMMON_NAME_ROWID, null) > 0;
	  }
	 

}
