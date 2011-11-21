package balu.android;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import balu.android.database.UnCommonNamesAdapter;


public class UnCommonNamesDescription extends Activity {
	
	
	String un_common_name;
	String un_common_name_meaning;
	long un_common_name_rowid;
	
	CharSequence text = " means... ";
	CharSequence description;
	
	public static TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.un_common_names_description);
		
        Bundle extras = getIntent().getExtras();
        
        un_common_name_rowid = extras.getLong(UnCommonNamesAdapter.UN_COMMON_NAME_ROWID );
        un_common_name = extras.getString(UnCommonNamesAdapter.UN_COMMON_NAME);
        un_common_name_meaning = extras.getString(UnCommonNamesAdapter.UN_COMMON_NAME_MEANING).toString();
        
        description = un_common_name+text+ un_common_name_meaning;
        tv = (TextView)findViewById(R.id.un_common_name_description_text);
        
        tv.setText(description);
        
	}


}
