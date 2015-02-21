package com.bestilogic.baintaininggme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends Activity {

	private Button newgame, continuegame, about, exitgame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		newgame = (Button) findViewById(R.id.newbtn);
		continuegame = (Button) findViewById(R.id.cntbtn);
		about = (Button) findViewById(R.id.aboutbtn);
		exitgame = (Button) findViewById(R.id.exitbtn);

		newgame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomePage.this, Difficultylevel.class));
			}
		});
		continuegame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				continueGame();
			}
		});
		about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				about_game_window();
			}
		});
		exitgame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	// About the game
	private void about_game_window() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				HomePage.this);
		alertDialogBuilder.setTitle("About Game");
		alertDialogBuilder
				.setMessage(
						"1) Solve the equations within 10sec!\n 2) Allow hint to get 4 attempt per question \n 3) Answer all 10 question and get your score")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						dialog.cancel();

					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	// Continue game functionality
	private void continueGame() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String restoreValue = sharedPreferences.getString("diffLevel", null);
		if (restoreValue != null) {

			if (!restoreValue.equals("null")) {
				String diffLevel = sharedPreferences.getString("diffLevel",
						"No name defined");// "No name defined" is the default
											// value.
				String Expression_cnt = sharedPreferences.getString(
						"Expression_cnt", "No name defined");
				String Expression = sharedPreferences.getString("Expression",
						"No name defined");
				String timer = sharedPreferences.getString("timer",
						"No name defined");
				String answer = sharedPreferences.getString("answer",
						"No name defined");
				String hint = sharedPreferences.getString("hint",
						"No name defined");
				String hint_count = sharedPreferences.getString("hint_count",
						"No name defined");
				String score = sharedPreferences.getString("score",
						"No name defined");
				String answeris = sharedPreferences.getString("answeris",
						"No name defined");

				/*
				 * Log.d("SHARED", "level=" + diffLevel); Log.d("SHARED",
				 * "Expression_cnt=" + Expression_cnt); Log.d("SHARED",
				 * "Expression=" + Expression); Log.d("SHARED", "timer=" +
				 * timer); Log.d("SHARED", "answer=" + answer); Log.d("SHARED",
				 * "hint=" + hint); Log.d("SHARED", "hint_count=" + hint_count);
				 * Log.d("SHARED", "score=" + score);
				 */

				Intent i = new Intent(this, Gamescreen.class);
				i.putExtra("FromClass", "class1");
				i.putExtra("difficultylevel", diffLevel);
				i.putExtra("Expression_cnt", Expression_cnt);
				i.putExtra("Expression", Expression);
				i.putExtra("timer", timer);
				i.putExtra("answer", answer);
				i.putExtra("hint", hint);
				i.putExtra("hint_count", hint_count);
				i.putExtra("score", score);
				i.putExtra("answeris", answeris);
				startActivity(i);

			} else {
				Toast.makeText(getApplicationContext(), "Game not saved",
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(getApplicationContext(), "Game not saved",
					Toast.LENGTH_LONG).show();
		}
	}

}
