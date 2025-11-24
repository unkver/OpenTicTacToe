package com.example.opentictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class gameplay_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay_ai);
    }
    public boolean checkWin(String[][] board) {
        // rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0] != null &&
                    board[r][0].equals(board[r][1]) &&
                    board[r][1].equals(board[r][2])) return true;
        }

        // cols
        for (int c = 0; c < 3; c++) {
            if (board[0][c] != null &&
                    board[0][c].equals(board[1][c]) &&
                    board[1][c].equals(board[2][c])) return true;
        }

        // diag
        if (board[0][0] != null &&
                board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2])) return true;

        if (board[0][2] != null &&
                board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0])) return true;
        return false;
    }
    public boolean checkDraw(String[][] board) {
        // If ANY cell is still null, it's not a draw
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == null) return false;
            }
        }

        // If no empty cells AND no win → it's a draw
        return !checkWin(board);
    }
    int turn = 0;
    String[][] board = new String[3][3];
    public void onPress(View v){
        Button b = (Button) v;
        TextView tv = findViewById(R.id.turnIndicator);
        String tag = (String) v.getTag();
        int row = Character.getNumericValue(tag.charAt(0));
        int col = Character.getNumericValue(tag.charAt(2));

        // Set X and O accordingly
        if (turn == 0) {
            board[row][col] = "X";
            b.setText("X");
            tv.setText("It's O's turn!");
        } else {
            board[row][col] = "O";
            b.setText("O");
            tv.setText("It's X's turn!");
        }
        // Then make the button disabled
        v.setEnabled(false);
        // Then check for a win
        if (checkWin(board)) {
            tv.setText(turn == 0 ? "X wins!" : "O wins!");
            // Disable all buttons since we won (There's probably a better way... >_>)
            findViewById(R.id.button00).setEnabled(false);
            findViewById(R.id.button01).setEnabled(false);
            findViewById(R.id.button02).setEnabled(false);
            findViewById(R.id.button10).setEnabled(false);
            findViewById(R.id.button11).setEnabled(false);
            findViewById(R.id.button12).setEnabled(false);
            findViewById(R.id.button20).setEnabled(false);
            findViewById(R.id.button21).setEnabled(false);
            findViewById(R.id.button22).setEnabled(false);
            return;
        }
        if (checkDraw(board)) {
            tv.setText("It's a draw!");
            return;
        }
        turn = (turn == 0 ? 1 : 0); // If no win, switch the turn so it's O's turn
    }
    public void toMainMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void retry(View v){
        Intent intent = new Intent(this, gameplay_2.class);
        startActivity(intent);
    }
}