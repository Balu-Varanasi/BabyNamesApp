package balu.android;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import balu.android.database.CommonNamesAdapter;

public class CommonNames extends ListActivity {

	CommonNamesAdapter cnTable;
	ListView cnListView;
	Cursor c;

	private static final int COMMON_NAME_ACTIVITY_START = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_names_list);

		cnTable = new CommonNamesAdapter(this);
		cnTable.open(getApplicationContext());

		c = cnTable.fetchAllCommonNames();
		startManagingCursor(c);

		if(c!=null){
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.common_names_row,
					c,
					new String[] {c.getColumnName(1)},
					new int[] {R.id.commonName});
			setListAdapter(adapter);		
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		c.moveToPosition(position);

		Intent i = new Intent(this, CommonNameDescription.class);
		i.putExtra(CommonNamesAdapter.COMMON_NAME_ROWID, id);
		i.putExtra(CommonNamesAdapter.COMMON_NAME, c.getString(
				c.getColumnIndexOrThrow(CommonNamesAdapter.COMMON_NAME)));
		i.putExtra(CommonNamesAdapter.COMMON_NAME_COUNT, c.getString(
				c.getColumnIndexOrThrow(CommonNamesAdapter.COMMON_NAME_COUNT)));
		startActivityForResult(i, COMMON_NAME_ACTIVITY_START);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		//Bundle extras = intent.getExtras();
		switch(requestCode) {
		default: break;
		}
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		c.close();
		cnTable.close();
	}

}