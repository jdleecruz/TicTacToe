package com.johnleecruz.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_var1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_var1 = findViewById(R.id.button_numerical);
        button_var1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_numerical) {
            Intent intent = new Intent(getApplicationContext(),
                    NumericalTicTacToe.class);
            startActivity(intent);
        }
    }


}
