package com.NG.viewpager;

import com.NG.moviesearchbeta.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class T1Activity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		
		Toast.makeText(this, "test", 1).show();
		
		setContentView(R.layout.sub);
		((TextView) findViewById(R.id.tv_show)).setText("11111111111");
	}
}

