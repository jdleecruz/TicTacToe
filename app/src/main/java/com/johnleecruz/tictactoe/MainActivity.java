/*
 * Team 3
 * Tic-Tac-Toe App
 * Rachella Hampton, John Lee-Cruz
 */

package com.johnleecruz.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_var1;
    private Button button_var2;
    private Button button_var3;
    private Button button_rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_var1 = findViewById(R.id.button_numerical);
        button_var1.setOnClickListener(this);

        button_var2 = findViewById(R.id.button_wild);
        button_var2.setOnClickListener(this);

        button_var3 = findViewById(R.id.button_notakto);
        button_var3.setOnClickListener(this);

        button_rules = findViewById(R.id.button_rules);
        button_rules.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_numerical) {
            Intent intent = new Intent(getApplicationContext(),
                    NumericalTicTacToe.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.button_wild) {
            Intent intent = new Intent(getApplicationContext(),
                    WildTicTacToe.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.button_notakto) {
            Intent intent = new Intent(getApplicationContext(),
                    NotaktoTicTacToe.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.button_rules) {
            Intent intent = new Intent(getApplicationContext(),
                    RulesActivity.class);
            startActivity(intent);
        }
    }
}
