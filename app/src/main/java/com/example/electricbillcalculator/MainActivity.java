package com.example.electricbillcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


public class MainActivity extends Activity {

    private EditText txtUsage;
    private EditText txtRebate;
    private TextView txtViewResult;
    private Button btnCalculate;
    private Button btnClear;
    private Button btnClearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsage = findViewById(R.id.txtUsage);
        txtRebate = findViewById(R.id.txtRebate);
        txtViewResult = findViewById(R.id.txtViewResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        btnClearAll = findViewById(R.id.btnClearAll);
        Button btnDetails = findViewById(R.id.btnDetails);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCost();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextFields();
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllFields();
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, about_page.class);
                startActivity(intent);
            }
        });
    }

    private void calculateCost() {
        String usageString = txtUsage.getText().toString().trim();
        String rebateString = txtRebate.getText().toString().trim();

        if (usageString.isEmpty() || rebateString.isEmpty()) {
            txtViewResult.setText("Please enter both usage and rebate values.");
        } else {
            double usage = Double.parseDouble(usageString);
            double rebate = Double.parseDouble(rebateString);

            if (usage < 1 || usage > 900) {
                txtViewResult.setText("Please enter usage between 1 to 900.");
            } else if (rebate < 0 || rebate > 5) {
                txtViewResult.setText("Rebate must be between 0% and 5%.");
            } else {

                rebate = rebate / 100;
                double cost = 0.0;

                if (usage >= 1 && usage <= 200) {
                    cost = usage * 0.218;
                } else if (usage >= 201 && usage <= 300) {
                    cost = 200 * 0.218 + (usage - 200) * 0.334;
                } else if (usage >= 301 && usage <= 600) {
                    cost = 200 * 0.218 + 100 * 0.334 + (usage - 300) * 0.516;
                } else if (usage >= 601 && usage <= 900) {
                    cost = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (usage - 600) * 0.546;
                }

                double finalCost = cost - (cost * rebate);

                txtViewResult.setText("Final Cost: RM" + finalCost);
            }
        }
    }

    private void clearTextFields() {
        txtUsage.setText("");
        txtRebate.setText("");
    }

    private void clearAllFields() {
        txtUsage.setText("");
        txtRebate.setText("");
        txtViewResult.setText("");
    }
}
