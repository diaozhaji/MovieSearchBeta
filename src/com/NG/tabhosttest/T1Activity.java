/**
 * Program  : T1Activity.java
 * Author   : qianj
 * Create   : 2012-5-31 涓嬪崍4:24:32
 *
 * Copyright 2012 by newyulong Technologies Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of newyulong Technologies Ltd.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with newyulong Technologies Ltd.
 *
 */

package com.NG.tabhosttest;

import com.NG.moviesearchbeta.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author   qianj
 * @version  1.0.0
 * @2012-5-31 涓嬪崍4:24:32
 */
public class T1Activity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//Context context = this.getParent();
		
		Toast.makeText(this, "test", 1).show();
		
		setContentView(R.layout.sub);
		((TextView) findViewById(R.id.tv_show)).setText("11111111111");
	}
}

