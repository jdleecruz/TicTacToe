/*
 * Team 3
 * Tic-Tac-Toe App
 * Rachella Hampton, John Lee-Cruz
 */

package com.johnleecruz.tictactoe;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NumericalTicTacToe extends Activity implements View.OnClickListener{
    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private TextView outputText;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numerical_tictactoe);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        outputText = findViewById(R.id.output_textview);
        outputText.setText("Player 1 starts, enter your number then click above to end your turn.");

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        editText = findViewById(R.id.edit_text_players);
        outputText = findViewById(R.id.output_textview);
    }

    @Override
    public void onClick(View v) {

        outputText.setText("");

        String value= editText.getText().toString();

        if (player1Turn) {

            if (value.equals("1") | value.equals("3") | value.equals("5") | value.equals("7") | value.equals("9")) {
                ((Button) v).setText(editText.getText().toString());

                if (!checkForWin() && roundCount != 8) {
                    Toast.makeText(this, "Player 2's Turn.", Toast.LENGTH_SHORT).show();
                }
                player1Turn = false;

                if(checkForWin()) {
                    player1Turn = true;
                    if(player1Turn) {
                        player1Wins();
                    }
                }

            } else {
                outputText.setText("Player One can only enter 1, 3, 5, 7, 9");
                player1Turn = true;
            }
        }
        else {
            if (value.equals("2") || value.equals("4") || value.equals("6") || value.equals("8")) {
                ((Button) v).setText(value);

                if (!checkForWin() && roundCount != 8) {
                    Toast.makeText(this, "Player 1's Turn.", Toast.LENGTH_SHORT).show();
                }

                if(checkForWin()) {
                    if(!player1Turn) {
                        player2Wins();
                    }
                }

                player1Turn = true;
            } else {
                outputText.setText("Player two can only enter 2, 4, 6, 8");
                player1Turn = false;
            }
        }

        roundCount++;

        if(roundCount == 9) {
            draw();
        }
    }

    private boolean checkForWin() {
       int answer = 15;
        int[][] field = new int[3][3];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                String v = buttons[i][j].getText().toString();
                field[i][j] = 0;
                if(v.length() > 0)
                    field[i][j] = Integer.parseInt(v);

            }
        }

  boolean winnerCheck = false;
        
        for (int i = 0; i < 3; i++) {
            if(field[i][0] + field[i][1] + field[i][2]
                    == answer) {
                if(field[i][0] == 0 || field[i][1] == 0 || field[i][2] == 0) {
                    winnerCheck = false;
                }
                else {
                    winnerCheck = true;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if((field[0][i]) + (field[1][i]) + (field[2][i])
                    == answer) {
                if(field[0][i] == 0 || field[1][i] == 0 || field[2][i] == 0) {
                    winnerCheck = false;
                }
                else {
                    winnerCheck = true;
                }
            }
        }


            if((field[0][0]) + (field[1][1]) + (field[2][2])
                    == answer) {
                if(field[0][0] == 0 || field[1][1] == 0 || field[2][2] == 0) {
                    winnerCheck = false;
                }
                else {
                    winnerCheck = true;
                }
            }



            if((field[0][2]) + (field[1][1]) + (field[2][0])
                    == answer) {
                if(field[0][2] == 0 || field[1][1] == 0 || field[2][0] == 0) {
                    winnerCheck = false;
                }
                else {
                    winnerCheck = true;
                }
            }

        return winnerCheck;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + Integer.toString(player1Points));
        textViewPlayer2.setText("Player 2: " + Integer.toString(player2Points));
    }

    private void resetBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player2Points = 0;
        player1Points = 0;
        editText.setText("");
        outputText.setText("Player 1 starts, enter your number then click above to end your turn.");
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }
}
