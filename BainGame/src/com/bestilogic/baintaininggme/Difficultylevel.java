package com.bestilogic.baintaininggme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Difficultylevel extends Activity {

	private Button Novice, Easy, Medium, Guru;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.difficultylevels);
		Novice = (Button) findViewById(R.id.level1btn);
		Easy = (Button) findViewById(R.id.level2btn);
		Medium = (Button) findViewById(R.id.level3btn);
		Guru = (Button) findViewById(R.id.level4btn);

		Novice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				levelstart("one");
			}
		});
		Easy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				levelstart("two");
			}
		});
		Medium.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				levelstart("three");
			}
		});
		Guru.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				levelstart("four");
			}
		});
	}

	//Calling game screen with given difficulty level  
	private void levelstart(String level) {

		Intent i = new Intent(this, Gamescreen.class);
		i.putExtra("FromClass", "class2");
		i.putExtra("difficultylevel", level);
		startActivity(i);
		finish();
	}

}
