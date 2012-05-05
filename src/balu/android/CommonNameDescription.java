package balu.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import balu.android.database.CommonNamesAdapter;

public class CommonNameDescription extends Activity {

	String common_name;
	String common_name_count;
	long common_name_rowid;

	CharSequence text = " users of facebook have the name - ";
	CharSequence description;

	public static TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_names_description);

		Bundle extras = getIntent().getExtras();

		common_name_rowid = extras.getLong(CommonNamesAdapter.COMMON_NAME_ROWID );
		common_name = extras.getString(CommonNamesAdapter.COMMON_NAME);
		common_name_count = extras.getString(CommonNamesAdapter.COMMON_NAME_COUNT).toString();

		description = common_name_count + text + common_name;
		tv = (TextView)findViewById(R.id.common_name_description_text);

		tv.setText(description);

	}

}
