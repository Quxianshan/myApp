package com.quinn.myutilapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button startGsensorActivity;
	private Button startLightSensorActivity;
	private Button startProximityActivity;
	private Button startSortListActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		setOnClickListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initView() {
		startGsensorActivity = (Button) findViewById(R.id.start_gsensor_activity);
		startLightSensorActivity = (Button) findViewById(R.id.start_light_sensor_activity);
		startProximityActivity = (Button) findViewById(R.id.start_proximity_sensor_activity);
		startSortListActivity = (Button) findViewById(R.id.start_sort_list_activity);
	}
	
	private void setOnClickListener() {
		startGsensorActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myItent = new Intent(MainActivity.this, com.quinn.myutilapp.StartGsensorActivity.class);
				startActivity(myItent);
			}
		});
		
		startLightSensorActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainActivity.this, com.quinn.myutilapp.StartLightSensorActivity.class);
				startActivity(myIntent);
			}
		});
		
		startProximityActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainActivity.this, com.quinn.myutilapp.StartProximitySensorActivity.class);
				startActivity(myIntent);
			}
		});
		
		startSortListActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainActivity.this, com.quinn.sortlistview.MainActivity.class);
				startActivity(myIntent);
			}
		});
	}
}
