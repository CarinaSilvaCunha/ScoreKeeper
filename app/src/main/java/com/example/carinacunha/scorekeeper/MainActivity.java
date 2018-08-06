package com.example.carinacunha.scorekeeper;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Variables to be updated.
     */

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int passTeamA = 0;
    int passTeamB = 0;
    int rushTeamA = 0;
    int rushTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This makes sure the reset button is set as disabled when the app starts
        Button mainResetButton = findViewById(R.id.reset);
        mainResetButton.setEnabled(false);

        // This creates a "ghost" view in order to clear focus of keyboard when we click anywhere in the layout
        View mainLayoutTouch = findViewById(R.id.ghostLayout);
        mainLayoutTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                clearFocusEditText(view.findFocus());
                return false;
            }
        });


        //this calls the first team name and clears the focus off the view when clicking somewhere else // hides keyboard
        EditText firstTeamName = findViewById(R.id.firstTeamName);
        firstTeamName.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Clear focus here from edittext
                    clearFocusEditText(v.findFocus());
                    return false;
                }
                return false;}
        });

        //this calls the first team name and clears the focus off the view when clicking somewhere else // hides keyboard
        EditText secondTeamName = findViewById(R.id.secondTeamName);
        secondTeamName.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Clear focus here from edittext
                    clearFocusEditText(v.findFocus());
                    return false;
                }
                return false;}
        });
    }

    // Method is called from onCreate - with an onTouchListener, if the ghostLayout is touched and the focus is on the team names, it clears the focus.
    public void clearFocusEditText(View view) {
        EditText teamANameText = findViewById(R.id.firstTeamName);
        EditText teamBNameText = findViewById(R.id.secondTeamName);

        InputMethodManager mm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        mm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        teamANameText.clearFocus();
        teamBNameText.clearFocus();
        // If nothing is inserted in the name of the teams, do not enable the reset button. If something is inserted, enable it
        if (teamANameText.getText().toString().equals("") == false || teamBNameText.getText().toString().equals("") == false) {
            enableMainResetButton();
        }

    }


    /**
     * TEAM A
     */


    /**
     * Displays score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.scoreTeamAView);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the Pass for Team A.
     */
    public void displayPassForTeamA(int score) {
        TextView scoreView = findViewById(R.id.passTeamAView);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the Rush for Team A.
     */
    public void displayRushForTeamA(int score) {
        TextView scoreView = findViewById(R.id.rushTeamAView);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Add Team A +1 point
     */
    public void addPoint_A (View v) {
        clearFocusEditText(findViewById(R.id.firstTeamName));
        clearFocusEditText(findViewById(R.id.secondTeamName));
        enableMainResetButton();
        displayForTeamA(scoreTeamA += 1);
    }

    /**
     * Add Team A +1 pass
     */
    public void addPointPass_A (View v) {
        displayPassForTeamA(passTeamA += 1);
        enableMainResetButton();
        clearFocusEditText(findViewById(R.id.firstTeamName));
        clearFocusEditText(findViewById(R.id.secondTeamName));
    }

    /**
     * Add Team A +1 rush
     */
    public void addPointRush_A (View v) {
        displayRushForTeamA(rushTeamA += 1);
        enableMainResetButton();
        clearFocusEditText(findViewById(R.id.firstTeamName));
        clearFocusEditText(findViewById(R.id.secondTeamName));
    }



    /**
     * TEAM B
     */


    /**
     * Displays score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.scoreTeamBView);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the Pass for Team B.
     */
    public void displayPassForTeamB(int score) {
        TextView scoreView = findViewById(R.id.passTeamBView);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the Rush for Team B.
     */
    public void displayRushForTeamB(int score) {
        TextView scoreView = findViewById(R.id.rushTeamBView);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Add Team B +1 point
     */
    public void addPoint_B (View v) {
        displayForTeamB(scoreTeamB+= 1);
        enableMainResetButton();
        clearFocusEditText(findViewById(R.id.firstTeamName));
        clearFocusEditText(findViewById(R.id.secondTeamName));
    }

    /**
     * Add Team B +1 pass
     */
    public void addPointPass_B (View v) {
        displayPassForTeamB(passTeamB+=1);
        enableMainResetButton();
        clearFocusEditText(findViewById(R.id.firstTeamName));
        clearFocusEditText(findViewById(R.id.secondTeamName));
    }

    /**
     * Add Team B +1 rush
     */
    public void addPointRush_B (View v) {
        displayRushForTeamB(rushTeamB+=1);
        enableMainResetButton();
        clearFocusEditText(findViewById(R.id.firstTeamName));
        clearFocusEditText(findViewById(R.id.secondTeamName));
    }



    /**
     * Reset score.
     */

    public void reset (View v) {
        //resets the play scores
        displayForTeamB(scoreTeamA = 0);
        displayForTeamA(scoreTeamB = 0);
        displayPassForTeamA(passTeamA =0);
        displayPassForTeamB(passTeamB=0);
        displayRushForTeamA(rushTeamA=0);
        displayRushForTeamB(rushTeamB=0);
        //resets the name of the teams
        EditText firstTeamName = findViewById(R.id.firstTeamName);
        EditText secondTeamName = findViewById(R.id.secondTeamName);
        firstTeamName.getText().clear();
        secondTeamName.getText().clear();
        // gives toast
        String resetScoreText = "The score has been reset";
        final Toast resetScoreToast = Toast.makeText(this, resetScoreText, Toast.LENGTH_SHORT);
        resetScoreToast.show();
        // Added to make Toast be shorter and cancel last toast before adding a new one (if user presses many times)
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetScoreToast.cancel();
            }
        }, 1000);


        // Disable Reset Button
        Button mainResetButton = findViewById(R.id.reset);
        mainResetButton.setEnabled(false);
        //Clear Focus of buttons
        clearFocusEditText(findViewById(R.id.firstTeamName));
        clearFocusEditText(findViewById(R.id.secondTeamName));
    }


    public void enableMainResetButton() {
        Button mainResetButton = findViewById(R.id.reset);
        if (mainResetButton.isEnabled() == false) {
            mainResetButton.setEnabled(true);
        }
    }

}

