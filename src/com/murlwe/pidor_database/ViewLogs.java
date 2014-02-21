package com.murlwe.pidor_database;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewLogs extends Activity{
	TableLayout dataTable;
	DBInterface db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logs);
		
		dataTable = (TableLayout)findViewById(R.id.data_table);
		final DBInterface db = new DBInterface(this);
		
		
		List<Entry> entries = db.getAllEntries();
		for(Entry entry:entries) {
			TableRow tableRow = new TableRow(this);
			//Toast.makeText(getApplicationContext(), entry.getName().toString(), Toast.LENGTH_SHORT).show();
			
			TextView nameTxt = new TextView(this);
			nameTxt.setText(entry.getName().toString());
			tableRow.addView(nameTxt);
			
			TextView deptTxt = new TextView(this);
			deptTxt.setText(entry.getDept().toString());
			tableRow.addView(deptTxt);
			
			TextView stampTxt = new TextView(this);
			stampTxt.setText(entry.getStamp().toString());
			tableRow.addView(stampTxt);
			
			dataTable.addView(tableRow);
		}
	}
}
