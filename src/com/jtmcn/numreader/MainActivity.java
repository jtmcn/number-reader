package com.jtmcn.numreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	EditText input;
	TextView output;
	Button goButton;
	String inputNum;
	int NumberOfRuns = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		input = (EditText) findViewById(R.id.etNumber);
		goButton = (Button) findViewById(R.id.bButton);
		output = (TextView) findViewById(R.id.tvOutput);

		goButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		inputNum = input.getText().toString();

		input.setText("");
		output.setText(" " + inputNum + "\n ");

		// watch for null
		if (inputNum.length() > 0)
			runNumbers(inputNum, 0);
		else
			Toast.makeText(this, "Enter a number to begin!", Toast.LENGTH_SHORT)
					.show();
	}

	private void runNumbers(String inputNum, int counter) {

		int runCounter = counter;

		if (runCounter < 15) {

			StringBuffer sb = new StringBuffer();
			int count = 0;
			boolean isNewNumber = true;
			char prevNum = '\n';
			char temp = '\n';

			for (int i = 0; i < inputNum.length(); i++) {
				temp = inputNum.charAt(i);
				if (isNewNumber) {
					prevNum = temp;
					count = 1;
					isNewNumber = false;

					// necessary if number is a single digit
					if (i == inputNum.length() - 1) {
						sb.append(count);
						sb.append(prevNum);
					}
					continue;
				}

				if (temp == prevNum) {
					count++;
				} else {
					sb.append(count);
					sb.append(prevNum);

					prevNum = temp;
					count = 1;
				}
				if (i == inputNum.length() - 1) {
					// catch the last set of digits
					sb.append(count);
					sb.append(prevNum);
				}

			}
			output.append(sb);
			output.append("\n ");

			runCounter++;
			runNumbers(sb.toString(), runCounter);
		}
	}
}
