package com.quinn.myutilapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartGsensorActivity extends Activity implements SensorEventListener{
	
	private boolean DEBUG = true;
	private final static String TAG = "StartGensorActivity";
	private TextView generalInfo, accuraryInfo, value_0, value_1, value_2;
	private Button delayFastest, delayGame, delayNormal, delayUi;
	
	// default delay rate
	private int DELAY = SensorManager.SENSOR_DELAY_NORMAL;
	
	private SensorManager sensorManager;
	private Sensor gSensor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_sensors);
		
		initView();
		setOnClickListener();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this, gSensor);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		boolean flag = sensorManager.registerListener(this, gSensor, DELAY);
		if (flag && DEBUG) {
			Log.i(TAG, "registerListener success !");
		} else {
			Log.e(TAG, "registerListener failed !");
		}
	}
	
	private void initView() {
		generalInfo = (TextView) findViewById(R.id.general_info);
		accuraryInfo = (TextView) findViewById(R.id.accuracy_info);
		value_0 = (TextView) findViewById(R.id.value_0);
		value_1 = (TextView) findViewById(R.id.value_1);
		value_2 = (TextView) findViewById(R.id.value_2);
		
		delayFastest = (Button) findViewById(R.id.change_delay_fastest);
		delayGame = (Button) findViewById(R.id.change_delay_game);
		delayNormal = (Button) findViewById(R.id.change_delay_nomal);
		delayUi = (Button) findViewById(R.id.change_delay_ui);
		
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		gSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		if (null != gSensor) {
			StringBuffer info = new StringBuffer();
			info.append("Sensor name : " + gSensor.getName() + "\n");
			info.append("vendor : " + gSensor.getVendor() + "\n");
			generalInfo.setText(info.toString());
		} else {
			generalInfo.setText("no G-Sensor working now.");
		}
	}

	private void setOnClickListener() {
		delayFastest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean flag = changeSensorDelay(SensorManager.SENSOR_DELAY_FASTEST);
				if(flag && DEBUG) {
					Log.i(TAG, "change snesor delay to fastest!");
					Toast.makeText(StartGsensorActivity.this, "change snesor delay to fastest", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		delayGame.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean flag = changeSensorDelay(SensorManager.SENSOR_DELAY_GAME);
				if(flag && DEBUG) {
					Log.i(TAG, "change snesor delay to game!");
					Toast.makeText(StartGsensorActivity.this, "change snesor delay to game", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		delayNormal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean flag = changeSensorDelay(SensorManager.SENSOR_DELAY_NORMAL);
				if(flag && DEBUG) {
					Log.i(TAG, "change snesor delay to normal!");
					Toast.makeText(StartGsensorActivity.this, "change snesor delay to normal", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		delayUi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean flag = changeSensorDelay(SensorManager.SENSOR_DELAY_UI);
				if(flag && DEBUG) {
					Log.i(TAG, "change snesor delay to ui!");
					Toast.makeText(StartGsensorActivity.this, "change snesor delay to ui", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private boolean changeSensorDelay(int delay) {
		
		sensorManager.unregisterListener(StartGsensorActivity.this, gSensor);
		DELAY = delay;
		boolean flag = sensorManager.registerListener(StartGsensorActivity.this, gSensor, DELAY);
		return flag;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			Log.i(TAG, "gsensor onAccuracyChanged!");
			accuraryInfo.setText("Accuracy is :" + accuracy);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			Log.i(TAG, "onSensorChanged ..");
			float[] value = event.values;
			value_0.setText("value[0] = " + value[0]);
			value_1.setText("value[1] = " + value[1]);
			value_2.setText("value[2] = " + value[2]);
		}
	}
}
