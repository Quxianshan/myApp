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

public class StartLightSensorActivity extends Activity implements SensorEventListener{

	private final static String TAG = "StartLightSensorActivity";
	private TextView generalInfo, accuracyInfo, value_0, value_1, value_2;
	private Button delayFastest, delayGame, delayNormal, delayUi;
	private int DELAY = SensorManager.SENSOR_DELAY_NORMAL;
	private SensorManager sensorManager;
	private Sensor lightSensor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_sensors);
		
		initView();
	} 
	private void initView() {
		generalInfo = (TextView) findViewById(R.id.general_info);
		accuracyInfo = (TextView) findViewById(R.id.accuracy_info);
		value_0 = (TextView) findViewById(R.id.value_0);
		value_1 = (TextView) findViewById(R.id.value_1);
		value_2 = (TextView) findViewById(R.id.value_2);
		
		delayFastest = (Button) findViewById(R.id.change_delay_fastest);
		delayGame = (Button) findViewById(R.id.change_delay_game);
		delayNormal = (Button) findViewById(R.id.change_delay_nomal);
		delayUi = (Button) findViewById(R.id.change_delay_ui);
		
		delayFastest.setVisibility(View.GONE);
		delayGame.setVisibility(View.GONE);
		delayNormal.setVisibility(View.GONE);
		delayUi.setVisibility(View.GONE);
		
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		if (null != lightSensor) {
			StringBuffer info = new StringBuffer();
			info.append("Sensor name : " + lightSensor.getName() + "\n");
			info.append("vendor : " + lightSensor.getVendor() + "\n");
			generalInfo.setText(info.toString());
		} else {
			generalInfo.setText("no light sensor working now.");
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this, lightSensor);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, lightSensor, DELAY);
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		if(sensor.getType() == Sensor.TYPE_LIGHT) {
			Log.i(TAG, "light sensor onAccuracyChanged!");
			accuracyInfo.setText("Accuracy is :" + accuracy);
		}
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
			Log.i(TAG, "onSensorChanged ..");
			float[] value = event.values;
			value_0.setText("value[0] = " + value[0]);
			value_1.setText("value[1] = " + value[1]);
			value_2.setText("value[2] = " + value[2]);
		}
	}
}
