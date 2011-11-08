package balu.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BabyNamesDBHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "baby_names_database";
	public static final int DATABASE_VERSION = 2;
	
	public static final String DATABASE_TABLE_1 = "common_names";
	public static final String DATABASE_TABLE_2 = "un_common_names";
	
	public static final String COMMON_NAME_ROWID = "_id";
	public static final String COMMON_NAME = "common_name";
	public static final String COMMON_NAME_COUNT = "common_name_count";
	
	public static final String UN_COMMON_NAME_ROWID = "_id";
	public static final String UN_COMMON_NAME = "un_common_name";
	
	static final String CREATE_DATABASE_TABLE_1 =
		    "create table " + DATABASE_TABLE_1 + " (" + COMMON_NAME_ROWID + 
		    " integer primary key autoincrement, " + COMMON_NAME +
		    " text not null, " + COMMON_NAME_COUNT + " text not null);";

	static final String CREATE_DATABASE_TABLE_2 =
	    "create table " + DATABASE_TABLE_2 + " (" + UN_COMMON_NAME_ROWID + 
	    " integer primary key autoincrement, " + UN_COMMON_NAME +
	    " text not null);";
	
	public static final String TAG_1 = "COMMON_NAMES_TABLE";
	public static final String TAG_2 = "UNCOMMON_NAMES_TABLE";
	  
	public SQLiteDatabase mDb;
	
	public BabyNamesDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG_1, "Creating DataBase: " + CREATE_DATABASE_TABLE_1);
		db.execSQL(CREATE_DATABASE_TABLE_1);
		
		Log.i(TAG_2,"Creating Database: "+CREATE_DATABASE_TABLE_2);
		db.execSQL(CREATE_DATABASE_TABLE_2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG_1, "Upgrading database from version " + oldVersion + " to "
		         + newVersion + ", which will destroy all old data");
		
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_1);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_2);
		
		onCreate(db);

	}

}
