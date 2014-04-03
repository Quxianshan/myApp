package com.quinn.myutilapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartShellCommamdActivity extends Activity{
	
	private final static String TAG = "StartShellCommamdActivity";
	private TextView titleView;
	private Button execShellCommandButton;
	private EditText shellCommandText, resultOfShellCommand;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_shell_command);
		
		initView();
		setOnClickListener();
	}
	
	private void initView() {
		titleView = (TextView) findViewById(R.id.title);
		execShellCommandButton = (Button) findViewById(R.id.button_of_exec_shell);
		shellCommandText = (EditText) findViewById(R.id.value_of_shell_command);
		resultOfShellCommand = (EditText) findViewById(R.id.result_of_shell_command);
	}
	
	private void setOnClickListener() {
		execShellCommandButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String command = shellCommandText.getText().toString();
				if(null != command && !"".equals(command)) {
					try {
						execShellCommand(command);
					} catch (IOException e) {
						// TODO: handle exception
					}
				} else {
					Toast.makeText(StartShellCommamdActivity.this, "please input a shell command!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private void execShellCommand(String command) throws IOException {
		String args[]= {"sh", "-c", command};
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(args);
		InputStream is = process.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(inputStreamReader);
		String line = "";
		StringBuilder stringBuilder = new StringBuilder(line);
		while((line = bf.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append("\n");
		}
		
		resultOfShellCommand.setText(stringBuilder.toString());
		try {
			if(process.waitFor() != 0) {
				Log.e(TAG, "exit value = " + process.exitValue());
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
}
