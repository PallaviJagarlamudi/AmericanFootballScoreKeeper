package com.example.android.americanfootballscorekeeper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int MAX_QUARTERS = 4;
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int quarterWiseScoreTeamA[] = new int[MAX_QUARTERS];
    int quarterWiseScoreTeamB[] = new int[MAX_QUARTERS];
    int currQuarter = 1;
    boolean sentWarning = false;
    boolean isGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < MAX_QUARTERS; i++) {
            quarterWiseScoreTeamA[i] = 0;
            quarterWiseScoreTeamB[i] = 0;
        }
        displayQuarter();
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayScoreForTeamA() {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayScoreForTeamB() {
        displayQuarterScoreForTeamB();
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayQuarterScoreForTeamA() {

        TextView scoreView = (TextView) findViewById(getResources().getIdentifier("q" + currQuarter + "_teamA_score", "id", getPackageName()));
        scoreView.setText(String.valueOf(quarterWiseScoreTeamA[currQuarter - 1]));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayQuarterScoreForTeamB() {
        TextView scoreView = (TextView) findViewById(getResources().getIdentifier("q" + currQuarter + "_teamB_score", "id", getPackageName()));
        scoreView.setText(String.valueOf(quarterWiseScoreTeamB[currQuarter - 1]));
    }

    public void displayResetQuarterScoreBoard() {
        TextView qscoreView = null;
        for ( int i = 1; i <= MAX_QUARTERS ; i++ ){
            qscoreView = (TextView) findViewById(getResources().getIdentifier("q" + i + "_teamA_score", "id", getPackageName()));
            qscoreView.setText(String.valueOf(quarterWiseScoreTeamA[i - 1]));

            qscoreView = (TextView) findViewById(getResources().getIdentifier("q" + i + "_teamB_score", "id", getPackageName()));
            qscoreView.setText(String.valueOf(quarterWiseScoreTeamB[i - 1]));
        }
        displayScoreForTeamA();
        displayScoreForTeamB();
        displayQuarter();
    }

    /**
     * Displays the current quarter of the game in progress
     */
    public void displayQuarter() {
        TextView inningView = (TextView) findViewById(R.id.quarter_count_text);
        inningView.setText(String.valueOf(currQuarter));
        highCurrentQuarterScore();
    }

    public void scoreChange(View view) {

        String srcId = getResources().getResourceEntryName(view.getId());
        Log.i("Button pressed", srcId);
        int quarterIndex = currQuarter - 1;

        if (isGameOver) {
            if (srcId.equals("touch_down_teamA_button") || srcId.equals("field_goal_teamA_button")
                    || srcId.equals("twopoint_safety_teamA_button") || srcId.equals("extrapoint_teamA_button")
                    || srcId.equals("touch_down_teamB_button") || srcId.equals("field_goal_teamB_button")
                    || srcId.equals("twopoint_safety_teamB_button") || srcId.equals("extrapoint_teamB_button")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Game Over!! Hit reset!!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        } else {
            if (srcId.equals("touch_down_teamA_button")) {
                quarterWiseScoreTeamA[quarterIndex] += 6;
                scoreTeamA = scoreTeamA + 6;
                displayQuarterScoreForTeamA();
                displayScoreForTeamA();
            } else if (srcId.equals("field_goal_teamA_button")) {
                quarterWiseScoreTeamA[quarterIndex] += 3;
                scoreTeamA = scoreTeamA + 3;
                displayQuarterScoreForTeamA();
                displayScoreForTeamA();
            } else if (srcId.equals("twopoint_safety_teamA_button")) {
                quarterWiseScoreTeamA[quarterIndex] += 2;
                scoreTeamA = scoreTeamA + 2;
                displayQuarterScoreForTeamA();
                displayScoreForTeamA();
            } else if (srcId.equals("extrapoint_teamA_button")) {
                quarterWiseScoreTeamA[quarterIndex] += 1;
                scoreTeamA = scoreTeamA + 1;
                displayQuarterScoreForTeamA();
                displayScoreForTeamA();
            } else if (srcId.equals("touch_down_teamB_button")) {
                quarterWiseScoreTeamB[quarterIndex] += 6;
                scoreTeamB = scoreTeamB + 6;
                displayQuarterScoreForTeamB();
                displayScoreForTeamB();
            } else if (srcId.equals("field_goal_teamB_button")) {
                quarterWiseScoreTeamB[quarterIndex] += 3;
                scoreTeamB = scoreTeamB + 3;
                displayQuarterScoreForTeamB();
                displayScoreForTeamB();
            } else if (srcId.equals("twopoint_safety_teamB_button")) {
                quarterWiseScoreTeamB[quarterIndex] += 2;
                scoreTeamB = scoreTeamB + 2;
                displayQuarterScoreForTeamB();
                displayScoreForTeamB();
            } else if (srcId.equals("extrapoint_teamB_button")) {
                quarterWiseScoreTeamB[quarterIndex] += 1;
                scoreTeamB = scoreTeamB + 1;
                displayQuarterScoreForTeamB();
                displayScoreForTeamB();
            }
        }

        if (srcId.equals("reset")) {
            if (currQuarter < MAX_QUARTERS) {
                if (!sentWarning) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Game is in progres!! Hit Reset again to confirm", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    sentWarning = true;
                    return;
                }
            }
            scoreTeamA = 0;
            scoreTeamB = 0;
            currQuarter = 1;
            for (int i = 0; i < MAX_QUARTERS; i++) {
                quarterWiseScoreTeamA[i] = 0;
                quarterWiseScoreTeamB[i] = 0;
            }
            sentWarning = false;
            isGameOver = false;
            displayResetQuarterScoreBoard();
        } else if (srcId.equals("timeup")) {
            String msg = "";
            Toast toast;
            if (currQuarter < MAX_QUARTERS) {
                msg = "Quarter " + currQuarter + " is over!";
                toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                currQuarter += 1;
                displayQuarter();
            } else {
                msg = "Game Over!!  " + getResult();
                isGameOver = true;
                toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    private void highCurrentQuarterScore() {
        TextView qscoreViewA = null;
        TextView qscoreViewB = null;
        for ( int i = 1; i <= MAX_QUARTERS ; i++ ){
            qscoreViewA = (TextView) findViewById(getResources().getIdentifier("q" + i + "_teamA_score", "id", getPackageName()));
            qscoreViewB = (TextView) findViewById(getResources().getIdentifier("q" + i + "_teamB_score", "id", getPackageName()));
            if ( i == currQuarter){
                qscoreViewA.setBackgroundColor(Color.LTGRAY);
                qscoreViewB.setBackgroundColor(Color.LTGRAY);
            }else{
                qscoreViewA.setBackgroundColor(Color.parseColor("#dddddd"));
                qscoreViewB.setBackgroundColor(Color.parseColor("#dddddd"));
            }

        }
    }


    private String getResult() {
        String msg = "";
        if (scoreTeamA < scoreTeamB) {
            msg = getString(R.string.teamB_name) + " beat " + getString(R.string.teamA_name) + " " + scoreTeamB + "-" + scoreTeamA;
        } else if (scoreTeamA > scoreTeamB) {
            msg = getString(R.string.teamA_name) + " beat " + getString(R.string.teamB_name) + " " + scoreTeamA + "-" + scoreTeamB;
        } else {
            msg = " Its a draw!!" + " " + scoreTeamA + "-" + scoreTeamB;
        }
        return msg;
    }
}
