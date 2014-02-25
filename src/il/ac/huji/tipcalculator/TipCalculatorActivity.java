package il.ac.huji.tipcalculator;



import il.ac.huji.tipcalculator.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		
		
		Button calculateTip = (Button) findViewById(R.id.btnCalculate);
        final EditText totalCost = (EditText) findViewById(R.id.edtBillAmount);
        final EditText tipAmount = (EditText) findViewById(R.id.tipPercentField);
        final TextView tipToPayResult = (TextView) findViewById(R.id.txtTipResult);
        final TextView totalToPay = (TextView) findViewById(R.id.totalCostText);
        final CheckBox checked = (CheckBox) findViewById(R.id.chkRound);
        final String tipResultText = (String) tipToPayResult.getText();
        final String totalToPayText = (String) totalToPay.getText();
        final RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.myRelLay);
        
        calculateTip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String input = totalCost.getText().toString();
				String inputTip = tipAmount.getText().toString();
				if(input.length() == 0) //if empty bill set
					Toast.makeText(TipCalculatorActivity.this, "Please set the table check", Toast.LENGTH_LONG).show();
				else if(inputTip.length() == 0) //if empty percent amount set
					Toast.makeText(TipCalculatorActivity.this, "Please set the the tip amount", Toast.LENGTH_LONG).show();
				else{
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0); //To hide the keyboard when pressing the button
//					double tipToPay = 0;
					double additionToRound = 0;
					double tipPercent = Double.parseDouble(inputTip)/100; //TODO let x.y percent
					double payCheckAmount = Double.parseDouble(input);
					if(checked.isChecked()){
						additionToRound = 0.5;
						int tipToPay = (int)(payCheckAmount*tipPercent+additionToRound);
						tipToPayResult.setText(tipResultText + " $" + tipToPay);
						totalToPay.setText(totalToPayText + " $" + (payCheckAmount+tipToPay));
					}
					else{
						double tipToPay = (double)(payCheckAmount*tipPercent+additionToRound);
						double totalPay = tipToPay + payCheckAmount;
						String res = String.format("%.2f",tipToPay);
						tipToPay = Double.parseDouble(res);
						res = String.format("%.2f",totalPay);
						totalPay = Double.parseDouble(res);
						tipToPayResult.setText(tipResultText + " $" + tipToPay);
						totalToPay.setText(totalToPayText + " $" + totalPay);
					}
					
				}
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calculator, menu);
		return true;
	}

}
