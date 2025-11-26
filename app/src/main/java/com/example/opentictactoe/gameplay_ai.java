package com.example.opentictactoe;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class gameplay_ai extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay_ai);
        setBoardVisibility(View.GONE, ids);
    }
    int turn = 0;
    String playerSymbol;
    String aiSymbol;
    String[][] board = new String[3][3];
    int[] ids = {
            R.id.button00, R.id.button01, R.id.button02,
            R.id.button10, R.id.button11, R.id.button12,
            R.id.button20, R.id.button21, R.id.button22
    };
    private void disableAllButtons(int[] ids) {
        for (int id : ids) {
            findViewById(id).setEnabled(false);
        }
    }
    private void setBoardVisibility(int visibility, int[] ids) {
        for (int id : ids) {
            findViewById(id).setVisibility(visibility);
        }
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
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == null) return false;
            }
        }
        return !checkWin(board);
    }
    public void startAsX(View v){
        playerSymbol = "X";
        aiSymbol = "O";
        findViewById(R.id.buttonStartX).setVisibility(View.GONE);
        findViewById(R.id.buttonStartO).setVisibility(View.GONE);
        setBoardVisibility(View.VISIBLE, ids);
        TextView tv = findViewById(R.id.turnIndicator);
        tv.setText("Your turn!");
    }
    public void startAsO(View v){
        playerSymbol = "O";
        aiSymbol = "X";
        findViewById(R.id.buttonStartX).setVisibility(View.GONE);
        findViewById(R.id.buttonStartO).setVisibility(View.GONE);
        setBoardVisibility(View.VISIBLE, ids);
        makeAIMove(findViewById(R.id.turnIndicator));
    }
    private void makeAIMove(TextView tv) {
        Random r = new Random();
        int row, col;

        // Pick random empty position
        do {
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (board[row][col] != null);

        String buttonID = "button" + row + col;
        int id = getResources().getIdentifier(buttonID, "id", getPackageName());
        Button b = findViewById(id);

        board[row][col] = aiSymbol;
        b.setText(aiSymbol);
        b.setEnabled(false);
        tv.setText("Your turn!");

        if (checkWin(board)) {
            tv.setText(aiSymbol + " wins!");
            disableAllButtons(ids);
            return;
        }
        if (checkDraw(board)) {
            tv.setText("It's a draw!");
            disableAllButtons(ids);
            return;
        }
        turn = 0; // Back to player
    }
    public void onPress(View v){
        String tag = (String) v.getTag();
        int row = Character.getNumericValue(tag.charAt(0));
        int col = Character.getNumericValue(tag.charAt(2));
        Button b = (Button) v;
        TextView tv = findViewById(R.id.turnIndicator);
        board[row][col] = playerSymbol;
        b.setText(playerSymbol);
        v.setEnabled(false);

        if (checkWin(board)) {
            tv.setText(playerSymbol + " wins!");
            disableAllButtons(ids);
            return;
        }
        if (checkDraw(board)) {
            tv.setText("It's a draw!");
            disableAllButtons(ids);
            return;
        }

        tv.setText("Thinking...");
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );
        new Handler().postDelayed(() -> {
            makeAIMove(tv);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }, 1200);
        turn = 1; // AI turn
    }
    public void toMainMenu(View v){
        finish();
    }
    public void retry(View v){
        recreate();
    }
}