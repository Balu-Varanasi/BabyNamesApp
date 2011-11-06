package balu.android;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class CommonNameTable {

	  public static final String COMMON_NAME = "common_name";
	  public static final String COMMON_NAME_COUNT = "common_name_count";
	  public static final String COMMON_NAME_ROWID = "_id";
	 
	  private static final String TAG = "CommonNameTable";
	  private DatabaseHelper mDbHelper;
	  private SQLiteDatabase mDb;
	 
	  private static final String DATABASE_NAME = "baby_names_database";
	  private static final String DATABASE_TABLE = "common_names";
	  private static final int DATABASE_VERSION = 1;
	 
	  private static final String DATABASE_CREATE =
	    "create table " + DATABASE_TABLE + " (" + COMMON_NAME_ROWID + 
	    " integer primary key autoincrement, " + COMMON_NAME +
	    " text not null, " + COMMON_NAME_COUNT + " text not null);";
	 
	  private final Context mCtx; 
	 
	  private static class DatabaseHelper extends SQLiteOpenHelper {

		  DatabaseHelper(Context context) {
	      super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	      Log.i(TAG, "Creating DataBase: " + DATABASE_CREATE);
	      db.execSQL(DATABASE_CREATE);	      
	    }
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	          + newVersion + ", which will destroy all old data");
	      db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	      onCreate(db);
	    }
	  }

	  public CommonNameTable(Context ctx) {
		  
	      this.mCtx = ctx;
	      this.open();
	      
	      try{
	    	  
	    	  InputStream is = ctx.getResources().openRawResource(R.raw.commonnames);
	          BufferedReader br = new BufferedReader(new InputStreamReader(is));
	          String strLine = null;

	    	  while ((strLine = br.readLine()) != null)   {
	    		  String[] temp;

	    		  strLine = strLine.trim();
	    		  temp = strLine.split("\\s+");
	    		  
		    	  this.createCommonName(temp[1], temp[0]);
	    	  }
	    	  is.close();
	      }
	      catch (Exception e){//Catch exception if any
		      Log.i(TAG, "Error while inserting common names into table");
	      }
	      this.close();
	  }
	 
	  public CommonNameTable open() throws SQLException {
	    Log.i(TAG, "OPening DataBase Connection....");
	    mDbHelper = new DatabaseHelper(mCtx);
	    mDb = mDbHelper.getWritableDatabase();
	    return this;
	  }
	 
	  public void close() {
	    mDbHelper.close();
	  }
	 
	  public long createCommonName(String commonName, String commonNameCount) {
	    Log.i(TAG, "Inserting record...");
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(COMMON_NAME, commonName);
	    initialValues.put(COMMON_NAME_COUNT, commonNameCount);
	
	    return mDb.insert(DATABASE_TABLE, null, initialValues);
	  }
	 
	  public boolean deleteCommonName(long rowId) {
	    return mDb.delete(DATABASE_TABLE, COMMON_NAME_ROWID + "=" + rowId, null) > 0;
	  }
	 
	  public Cursor fetchAllCommonNames() {	 
	    return mDb.query(DATABASE_TABLE, new String[] {COMMON_NAME_ROWID, COMMON_NAME,
	        COMMON_NAME_COUNT}, null, null, null, null, null);
	  }
	 
	  public Cursor fetchCommonName(long commonNameId) throws SQLException {

	    Cursor mCursor =
	 
	      mDb.query(true, DATABASE_TABLE, new String[] {COMMON_NAME_ROWID,
	          COMMON_NAME, COMMON_NAME_COUNT}, COMMON_NAME_ROWID + "=" + commonNameId, null,
	          null, null, null, null);
	    if (mCursor != null) {
	      mCursor.moveToFirst();
	    }
	    return mCursor;
	  }
	 
	  public boolean updateCommonName(int commonNameId, String commonName, String commonNameCount) {
	    ContentValues args = new ContentValues();
	    args.put(COMMON_NAME, commonName);
	    args.put(COMMON_NAME_COUNT, commonNameCount);
	 
	    return mDb.update(DATABASE_TABLE, args, COMMON_NAME_ROWID + "=" + commonNameId, null) > 0;
	  }
	 

}
