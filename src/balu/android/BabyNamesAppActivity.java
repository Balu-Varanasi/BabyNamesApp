package balu.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BabyNamesAppActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button b = (Button)findViewById(R.id.enter);

		b.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(BabyNamesAppActivity.this, Select.class);
				startActivity(i);

			}
		});
	}

}