package balu.android;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import balu.android.database.CommonNamesAdapter;

public class CommonNames extends Activity {
	
	TextView rowview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_names);
		
		rowview = (TextView)findViewById(R.id.commonName);
		
		CommonNamesAdapter cnTable = new CommonNamesAdapter(this);
		ListView cnListView = (ListView)findViewById(R.id.common_name_layout);
		
		cnTable.open(getApplicationContext());
		Cursor c = cnTable.fetch_all_common_names_only();
		startManagingCursor(c);
		
		if(c!=null){
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.listview,
					c,
					new String[] {c.getColumnName(0), c.getColumnName(1)},
					
					new int[] {R.id.rowLayout,R.id.commonName});
			
					cnListView.setAdapter(adapter);
					
		    }
		
		
		rowview.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Context context = getApplicationContext();
				
				String id = context.getString(R.id.commonName);
				CharSequence text = "This id of this item is... "+id ;
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				
			}


		});
		cnTable.close();
	}
	
	
}
