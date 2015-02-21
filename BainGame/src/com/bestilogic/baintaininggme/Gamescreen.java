package com.bestilogic.baintaininggme;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Gamescreen extends Activity {

	private TextView question, timeremain, resultString;
	private EditText answer;
	private String answerstr = "", timeRemaining = "Time Remaining";
	private Button button1, button2, button3, button4, button5, button6,
			button7, button8, button9, button0, buttondel, buttonhash,
			buttonmin, buttonexit;
	private String equation;
	private int timecount = 10, marksGained;
	public static Handler mHandler = new Handler();
	private int answeris;
	private int expressionCount = 10, hintCount = 4;
	private boolean hint = false, hashpressed = false;
	private String diffLevel;
	private int Showpopup;
	SharedPreferences sharedpreferences;

	// To store game state
	public void StoreforFuture(String name, String value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor editor = sharedPreferences.edit();
		editor.putString(name, value);
		editor.commit();
	}

	// To display time counter
	public Runnable runnable = new Runnable() {
		public void run() {
			timeremain.setText(timeRemaining + "\n" + String.valueOf(timecount)
					+ " secs");
			if (!hashpressed) {
				if (timecount - 1 >= 0) {
					if (expressionCount >= 0) {
						timecount -= 1;
						mHandler.postDelayed(runnable, 1000);
					}

				} else {
					timecount = 10;
					passParameter(diffLevel);
					mHandler.postDelayed(runnable, 1000);
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_screen);

		question = (TextView) findViewById(R.id.question);
		timeremain = (TextView) findViewById(R.id.timeermain);
		resultString = (TextView) findViewById(R.id.showresult);
		answer = (EditText) findViewById(R.id.answer);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);
		button0 = (Button) findViewById(R.id.buttonzero);
		buttondel = (Button) findViewById(R.id.buttondel);
		buttonhash = (Button) findViewById(R.id.buttonhash);
		buttonmin = (Button) findViewById(R.id.buttonmin);
		buttonexit = (Button) findViewById(R.id.exitbutton);

		// To disable default number key pad
		answer.setInputType(InputType.TYPE_NULL);

		String param1, param3, param4, param5, param6, param7, param8, param9, param10;

		// To know resume game OR fresh start game
		Intent intent = getIntent();
		param1 = intent.getExtras().getString("FromClass");
		if (param1.equals("class2")) {
			diffLevel = intent.getExtras().getString("difficultylevel");
			passParameter(diffLevel);
			answer.setHint("?");
		} else if (param1.equals("class1")) {
			Showpopup = 1;
			diffLevel = intent.getExtras().getString("difficultylevel");
			param3 = intent.getExtras().getString("Expression_cnt");
			param4 = intent.getExtras().getString("Expression");
			param5 = intent.getExtras().getString("timer");
			param6 = intent.getExtras().getString("answer");
			param7 = intent.getExtras().getString("hint");
			param8 = intent.getExtras().getString("hint_count");
			param9 = intent.getExtras().getString("score");
			param10 = intent.getExtras().getString("answeris");

			expressionCount = Integer.parseInt(param3);
			equation = param4;
			question.setText(equation);
			timecount = Integer.parseInt(param5);
			answerstr = param6;
			answer.setText(answerstr);
			hint = Boolean.parseBoolean(param7);
			hintCount = Integer.parseInt(param8);
			marksGained = Integer.parseInt(param9);
			answeris = Integer.parseInt(param10);
			mHandler.postDelayed(runnable, 1000);
		}

		// Hint settings
		if (Showpopup == 0) {
			Showpopup = 0;
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					Gamescreen.this);
			alertDialogBuilder.setTitle("Allow hint?");
			alertDialogBuilder
					.setMessage(
							"You will get 4 chance for each question if you press Yes")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									hint = true;
									mHandler.postDelayed(runnable, 1000);
									dialog.cancel();

								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									hint = false;
									mHandler.postDelayed(runnable, 1000);
									dialog.cancel();

								}
							});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "1";
				answer.setText(answerstr);
			}
		});
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "2";
				answer.setText(answerstr);
			}
		});
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "3";
				answer.setText(answerstr);
			}
		});
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "4";
				answer.setText(answerstr);
			}
		});
		button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "5";
				answer.setText(answerstr);
			}
		});
		button6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "6";
				answer.setText(answerstr);
			}
		});
		button7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "7";
				answer.setText(answerstr);
			}
		});
		button8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "8";
				answer.setText(answerstr);
			}
		});
		button9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "9";
				answer.setText(answerstr);
			}
		});
		button0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "0";
				answer.setText(answerstr);
			}
		});
		buttondel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (answerstr.length() > 0) {
					answerstr = answerstr.substring(0, answerstr.length() - 1);
					answer.setText(answerstr);
					answer.setHint("");
				} else {
					answer.setText("");
					answer.setHint("");
				}
			}
		});
		buttonhash.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkanswer();
			}
		});
		buttonmin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				answerstr += "-";
				answer.setText(answerstr);
			}
		});
		buttonexit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(expressionCount>0)
				{
				 resumeGame();
				}
				else
				{
					finish();
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(expressionCount>0)
			{
			 resumeGame();
			}
			else
			{
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// Call store current game state
	private void resumeGame() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Gamescreen.this);
		alertDialogBuilder.setTitle("Save current game?");
		alertDialogBuilder
				.setMessage("You can start from here later")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (diffLevel.equals("one")) {
									StoreforFuture("diffLevel", "one");
								} else if (diffLevel.equals("two")) {
									StoreforFuture("diffLevel", "two");
								} else if (diffLevel.equals("three")) {
									StoreforFuture("diffLevel", "three");
								} else if (diffLevel.equals("four")) {
									StoreforFuture("diffLevel", "four");
								}
								StoreforFuture("Expression_cnt",
										String.valueOf(expressionCount));
								StoreforFuture("Expression", equation);
								StoreforFuture("timer",
										String.valueOf(timecount));
								StoreforFuture("answer", answer.getText()
										.toString().trim());
								StoreforFuture("hint", String.valueOf(hint));
								StoreforFuture("hint_count",
										String.valueOf(hintCount));
								StoreforFuture("score",
										String.valueOf(marksGained));
								StoreforFuture("answeris",
										String.valueOf(answeris));
								/*
								 * Log.d("SHARED", "level=" + diffLevel);
								 * Log.d("SHARED", "Expression_cnt=" +
								 * expressionCount); Log.d("SHARED",
								 * "Expression=" + equation); Log.d("SHARED",
								 * "timer=" + String.valueOf(timecount));
								 * Log.d("SHARED", "answer=" +
								 * answer.getText().toString().trim());
								 * Log.d("SHARED", "hint=" +
								 * String.valueOf(hint)); Log.d("SHARED",
								 * "hint_count=" + String.valueOf(hintCount));
								 * Log.d("SHARED", "score=" +
								 * String.valueOf(marksGained));
								 */
								dialog.cancel();
								finish();

							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						StoreforFuture("diffLevel", "null");
						dialog.cancel();
						finish();

					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	// Finding difficulty level and respective parameters
	private void passParameter(String param) {

		hintCount = 4;
		if (expressionCount >= 0) {
			expressionCount -= 1;
			if (diffLevel.equals("one")) {
				generateequation(2, 2);
			} else if (diffLevel.equals("two")) {
				generateequation(2, 3);
			} else if (diffLevel.equals("three")) {
				generateequation(2, 4);
			} else if (diffLevel.equals("four")) {
				generateequation(4, 6);
			}
		} else {
			resultString.setText("Your scrore =" + marksGained);
			int color = Integer.parseInt("008000", 16) + 0xFF000000;
			resultString.setTextColor(color);
		}

	}

	// Generate random expression and its answer
	public void generateequation(int low, int high) {

		int[] operands = new int[6];
		String[] operators = { "+", "-", "*", "/" };
		ArrayList<String> index = new ArrayList<String>();
		// get number of operands
		int numoperands = low + (int) (Math.random() * (high - low + 1));

		// get operands value
		for (int i = 0; i < numoperands; i++) {
			int number = 1 + (int) (Math.random() * (10 - 1 + 1));
			operands[i] = number;
		}
		equation = "Guess: ";
		// get operators and forming the equation
		for (int j = 0; j < numoperands - 1; j++) {
			int operatorindex = 0 + (int) (Math.random() * (3 - 0 + 1));
			equation += String.valueOf(operands[j]) + operators[operatorindex];
			index.add(operators[operatorindex]);
		}
		equation += String.valueOf(operands[numoperands - 1]) + "=";
		question.setText(equation);
		answer.setText("");
		answer.setHint("?");
		answerstr = "";

		// answer of the equation is calculated here
		answeris = operands[0];
		for (int m = 0; m < index.size(); m++) {

			if (index.get(m).equals("+")) {
				answeris = answeris + operands[m + 1];
			} else if (index.get(m).equals("-")) {
				answeris = answeris - operands[m + 1];
			} else if (index.get(m).equals("*")) {
				answeris = answeris * operands[m + 1];
			} else if (index.get(m).equals("/")) {
				Log.d("DIVIDING=", "answeris=" + answeris + " operands[m + 1]="
						+ operands[m + 1]);
				answeris = answeris / operands[m + 1];
			}
		}
	}

	// Evaluating user answer
	private void checkanswer() {

		if (!resultString.getText().toString().trim().equals("")) {
			if (expressionCount >= 0) {
				hashpressed = false;
				mHandler.postDelayed(runnable, 1000);
				resultString.setText("");
				passParameter(diffLevel);
				timecount = 10;

			} else {
				// end of level
				resultString.setText("Your scrore =" + marksGained);
				int color = Integer.parseInt("008000", 16) + 0xFF000000;
				resultString.setTextColor(color);
			}
		} else if (!answer.getText().toString().trim().equals("")) {
			if (!answer.getText().toString().trim().equals("-")) {
				if (!answer.getText().toString().trim()
						.substring(1, answer.getText().toString().length())
						.contains("-")) {
					int answergiven = Integer.parseInt(answer.getText()
							.toString().trim());
					if (answergiven == answeris) {
						resultString.setText("CORRECT!");
						int color = Integer.parseInt("008000", 16) + 0xFF000000;
						resultString.setTextColor(color);
						hashpressed = true;
						if (timecount > 0) {
							if (timecount == 10) {
								marksGained += 100;
							} else {
								marksGained += 100 / (10 - timecount);
							}
						} else {
							marksGained += 100;
						}
					} else {

						if (hint) {
							if (hintCount > 0) {
								hintCount -= 1;
								if (answergiven < answeris) {
									Toast.makeText(getApplicationContext(),
											"greater", Toast.LENGTH_SHORT)
											.show();
								} else if (answergiven > answeris) {
									Toast.makeText(getApplicationContext(),
											"less", Toast.LENGTH_SHORT).show();

								}
							} else {
								hashpressed = true;
								resultString.setText("WRONG!");
								int color = Integer.parseInt("FF0000", 16) + 0xFF000000;
								resultString.setTextColor(color);
							}
						} else {
							resultString.setText("WRONG!");
							int color = Integer.parseInt("FF0000", 16) + 0xFF000000;
							resultString.setTextColor(color);
							hashpressed = true;
						}
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"format not correct", Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getApplicationContext(), "format not correct",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			if (hashpressed) {
				if (expressionCount >= 0) {
					resultString.setText("");
					answer.setHint("?");
					passParameter(diffLevel);
					timecount = 10;
					answer.setText("");

				} else {
					resultString.setText("Your scrore =" + marksGained);
					int color = Integer.parseInt("008000", 16) + 0xFF000000;
					resultString.setTextColor(color);
				}
			} else {
				Toast.makeText(getApplicationContext(),
						"Please enter your answer", Toast.LENGTH_SHORT).show();
			}
		}

	}
}
