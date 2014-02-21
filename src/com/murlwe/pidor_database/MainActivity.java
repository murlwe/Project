package com.murlwe.pidor_database;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	ViewLogs logs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final DBInterface db = new DBInterface(this);
		
		Button logIn = (Button)findViewById(R.id.loginBtn);
		Button viewLog = (Button)findViewById(R.id.viewLogBtn);
		
		logs = new ViewLogs();
		
		logIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText idNo = (EditText)findViewById(R.id.idET);
				int _idNo = Integer.parseInt(idNo.getText().toString());
				EditText name = (EditText)findViewById(R.id.nameET);
				String _name = name.getText().toString();
				EditText dept = (EditText)findViewById(R.id.deptET);
				String _dept = dept.getText().toString();
				Entry temp = new Entry(_idNo, _name, _dept);
				//Toast.makeText(getApplicationContext(), temp.getName(), Toast.LENGTH_LONG).show();
				db.addEntry(temp);
			}
		});
		
		viewLog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				List<Entry> entries = db.getAllEntries();
				for(Entry entry:entries) {
					Toast.makeText(getApplicationContext(), entry.getName().toString(), Toast.LENGTH_SHORT).show();
				}
				
				Intent intent = new Intent(MainActivity.this, ViewLogs.class);
				
				startActivity(intent);
			}
		});
		
		//Entry entry = new Entry(1, "Marloue", "Engineering");
		//Toast.makeText(this, entry.getStamp().toString(), Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
