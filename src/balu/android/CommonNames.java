package balu.android;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import balu.android.database.CommonNamesAdapter;

public class CommonNames extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_names);
		
		CommonNamesAdapter cnTable = new CommonNamesAdapter(this);
		ListView cnListView = (ListView)findViewById(R.id.common_name_layout);

		cnListView.setClickable(true);
		
		cnTable.open();
		Cursor c = cnTable.fetch_all_common_names_only();
		startManagingCursor(c);
		
		if(c!=null){
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.listview,
					c, // Give the cursor to the list adapter
					new String[] {c.getColumnName(1)},
					
					new int[] {R.id.commonName});
			
					cnListView.setAdapter(adapter);
		    }
		
		cnTable.close();
	}
}
