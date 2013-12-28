package com.NG.viewpager;
		
import com.NG.moviesearchbeta.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class T3Activity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sub);
		((TextView) findViewById(R.id.tv_show)).setText("33333");
	}
}

