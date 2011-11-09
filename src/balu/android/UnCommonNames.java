package balu.android;

import balu.android.database.UnCommonNamesAdapter;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class UnCommonNames extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.un_common_names);

		UnCommonNamesAdapter ucnTable = new UnCommonNamesAdapter(this);
	
		
		ucnTable.open();
		Cursor c = ucnTable.fetchAllUnCommonNames();
		startManagingCursor(c);
		
		if (c != null){
			SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
					R.layout.un_common_listview,
					c, // Give the cursor to the list adapter
					new String[] {c.getColumnName(1)},
					
					new int[] {R.id.uncommonName});
			
					ListView ucnListView = (ListView)findViewById(R.id.un_common_name_layout);
		
					ucnListView.setAdapter(adapter2);
		    }
		ucnTable.close();
		
	}


}
