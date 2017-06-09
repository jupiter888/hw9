package com.murach.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class ReminderActivity extends Activity implements OnClickListener {

    private Button startServiceButton;
    private Button stopServiceButton;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);
		
        startServiceButton = (Button) findViewById(R.id.startServiceButton);
        stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
        
        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);		
	}

    @Override
    public void onClick(View v) {
        Intent serviceIntent = new Intent(this, ReminderService.class);


        switch (v.getId()) {
        	case R.id.startServiceButton:
        		startService(serviceIntent);
                Toast.makeText(this,"service started", Toast.LENGTH_SHORT).show();
                Log.d("reminder","service started");
        		break;

        	case R.id.stopServiceButton:
                stopService(serviceIntent);
                Toast.makeText(this,"service stopped", Toast.LENGTH_SHORT).show();
                Log.d("reminder","service stopped");
                break;
        }
    }
}