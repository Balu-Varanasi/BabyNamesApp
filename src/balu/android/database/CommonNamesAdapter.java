package balu.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CommonNamesAdapter {

	// Database table name
	public static final String DATABASE_TABLE_1 = "common_names";

	// Database table columns for DATABASE_TABLE_1
	public static final String COMMON_NAME_ROWID = "_id";
	public static final String COMMON_NAME = "common_name";
	public static final String COMMON_NAME_COUNT = "common_name_count";

	// Object for SQLiteDatabase
	private SQLiteDatabase database;

	// 
	public static final String TAG = "COMMON_NAMES_TABLE";	
	private BabyNamesDBHelper baby_names_db_helper;

	public CommonNamesAdapter() {
	}

	public CommonNamesAdapter open(Context context) throws SQLException {
		Log.i(TAG, "OPening DataBase Connection....");
		baby_names_db_helper = new BabyNamesDBHelper(context);
		database = baby_names_db_helper.getWritableDatabase();
		return this;
	}

	public void close() {
		database.close();
	}

	public boolean deleteCommonName(long rowId) {
		return database.delete(DATABASE_TABLE_1, COMMON_NAME_ROWID + "=" + rowId, null) > 0;
	}

	public Cursor fetchAllCommonNames() {	 
		return database.query(DATABASE_TABLE_1, new String[] {COMMON_NAME_ROWID, COMMON_NAME , COMMON_NAME_COUNT}, null, null, null, null, COMMON_NAME);
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
	public Cursor fetch_all_common_names_only() {	 
		return database.query(DATABASE_TABLE_1, new String[] {COMMON_NAME_ROWID, COMMON_NAME, COMMON_NAME_COUNT}, null, null, null, null, null);
	}

	public Cursor fetchCommonNameCount(long commonNameId) throws SQLException {

		Cursor mCursor = database.query(true, DATABASE_TABLE_1, new String[] {COMMON_NAME_ROWID, COMMON_NAME_COUNT}, 
				COMMON_NAME_ROWID + "=" + commonNameId, null, null, null, null, null);

		if(mCursor!=null) {
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
