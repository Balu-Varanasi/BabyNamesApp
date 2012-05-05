package balu.android;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import balu.android.database.UnCommonNamesAdapter;

public class UnCommonNames extends ListActivity {

	private static final int UN_COMMON_NAME_ACTIVITY_START = 0;
	UnCommonNamesAdapter ucnTable;
	ListView ucnListView;
	Cursor c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.un_common_names_list);

		ucnTable = new UnCommonNamesAdapter(this);
		ucnTable.open(getApplicationContext());

		c = ucnTable.fetchAllUnCommonNames();
		startManagingCursor(c);

		if (c != null){
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.un_common_name_row,
					c,
					new String[] {c.getColumnName(1)},
					new int[] {R.id.unCommonName});

			setListAdapter(adapter);
		}	
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		c.moveToPosition(position);
		Intent i = new Intent(this, UnCommonNamesDescription.class);
		i.putExtra(UnCommonNamesAdapter.UN_COMMON_NAME_ROWID, id);
		i.putExtra(UnCommonNamesAdapter.UN_COMMON_NAME, c.getString(
				c.getColumnIndexOrThrow(UnCommonNamesAdapter.UN_COMMON_NAME)));
		i.putExtra(UnCommonNamesAdapter.UN_COMMON_NAME_MEANING, c.getString(
				c.getColumnIndexOrThrow(UnCommonNamesAdapter.UN_COMMON_NAME_MEANING)));
		startActivityForResult(i, UN_COMMON_NAME_ACTIVITY_START);
	}

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
		ucnTable.close();
	}

}
