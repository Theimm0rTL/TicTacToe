package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer =0;
    boolean gameActive = true;
    int [] gameState = {2,2,2,2,2,2,2,2,2}; //2->
    int [][] winningPositions= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    String Player1="Player 1";
    String Player2="Player 2";



    // 0-> P1 | 1-> P2
    public void dropping(View view) {
        ImageView counter = (ImageView) view;
        int tagCounter= Integer.parseInt(counter.getTag().toString());
        //Log.i("TagCounter",temp);


        if(gameState[tagCounter]==2 && gameActive) {

            gameState[tagCounter] = activePlayer;
            counter.setTranslationY(-2000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.coin1);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.coin2);
                activePlayer = 0;
            }


            counter.animate().translationYBy(2000f).rotation(3600f).setDuration(500);
            for(int[] winningPosition: winningPositions){
                if (gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2) {

                    //checking for Winner
                    gameActive = false;
                    String winner = Player1;
                    if (gameState[winningPosition[0]] == 1) {
                        winner = Player2;

                    }


                    TextView winnerMsg = (TextView) findViewById(R.id.winnerMessage);
                    winnerMsg.setText(winner + " has won!! ;)");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainID);
                    GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                    gridLayout.setVisibility(View.INVISIBLE);
                    layout.animate()
                            .setDuration(100);
                    layout.setVisibility(View.VISIBLE);
                    layout.animate()
                            .rotation(3600f)
                            .setDuration(100);
                }
                else {
                    boolean gameDraw = true;
                        for(int counterState : gameState){
                            if(counterState==2) gameDraw = false;
                        }
                        if (gameDraw){
                            TextView winnerMsg = (TextView) findViewById(R.id.winnerMessage);
                            winnerMsg.setText("Its a Draw");
                            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainID);
                            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                            gridLayout.setVisibility(View.INVISIBLE);
                            layout.animate()
                                    .setDuration(100);
                            layout.setVisibility(View.VISIBLE);
                            layout.animate()
                                    .rotation(3600f)
                                    .setDuration(100);

                        }
                }
            }
        }
        else{
            Toast.makeText(this, "Try again in some other block!", Toast.LENGTH_SHORT).show();
        }

    }




    public void playAgain(View view) {
        LinearLayout playerName = (LinearLayout) findViewById(R.id.playerInfo);
        playerName.setVisibility(View.VISIBLE);

        gameActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainID);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i =0 ;i<gameState.length;i++) {
            gameState[i] = 2;


        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    public void infoSubmit(View view){
        TextView player1 = (TextView) findViewById(R.id.player1ID);
        TextView player2 = (TextView) findViewById(R.id.player2ID);
        Player1 = player1.getText().toString();
        Player2 = player2.getText().toString();

        LinearLayout playerName = (LinearLayout) findViewById(R.id.playerInfo);

        playerName.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);
        if(Player1.length()==0) {
        Player1 = "Player 1";
        }

        if(Player2.length()==0) {
            Player2 = "Player 2";
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
