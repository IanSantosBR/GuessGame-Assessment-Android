package iansantos.guessgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    private int selectedCard;
    private ImageView cardLeft, cardMiddle, cardRight;
    private int[] card = {R.drawable.joker, R.drawable.front, R.drawable.joker2};
    private ArrayList<Integer> cards;
    private TextView message;
    private Button confirmBtn, playAgainBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardLeft = findViewById(R.id.joker_image);
        cardMiddle = findViewById(R.id.front_image);
        cardRight = findViewById(R.id.joker2_image);
        message = findViewById(R.id.message);
        confirmBtn = findViewById(R.id.confirmBtn);
        playAgainBtn = findViewById(R.id.playAgainBtn);

        shuffleCards();
    }

    public void shuffleCards() {
        int i;
        cards = new ArrayList<>();
        message.setVisibility(View.INVISIBLE);
        do {
            i = new Random().nextInt(3);
            if (! cards.contains(card[i])) {
                cards.add(card[i]);
            }
        } while (cards.size() < 3);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.joker_image:
                leftImageSelected();
                break;
            case R.id.front_image:
                middleImageSelected();
                break;
            case R.id.joker2_image:
                rightImageSelected();
                break;
            case R.id.confirmBtn:
                manageViews();
                assignImages(selectedCard);
                break;
            case R.id.playAgainBtn:
                shuffleCards();
                setDefaultStyle();
                break;
        }
    }

    private void assignImages(int c) {
        cardLeft.setImageResource(cards.get(0));
        cardMiddle.setImageResource(cards.get(1));
        cardRight.setImageResource(cards.get(2));
        if (R.drawable.front == cards.get(c)) {
            message.setText("Acertô miseravi");
            message.setVisibility(View.VISIBLE);
        } else {
            message.setText("EROOOOU!");
            message.setVisibility(View.VISIBLE);
        }
    }

    private void manageViews() {
        cardLeft.setClickable(false);
        cardMiddle.setClickable(false);
        cardRight.setClickable(false);
        confirmBtn.setEnabled(false);
        playAgainBtn.setEnabled(true);
    }

    private void leftImageSelected() {
        selectedCard = 0;
        cardLeft.setImageAlpha(1000);
        cardMiddle.setImageAlpha(100);
        cardRight.setImageAlpha(100);
        confirmBtn.setEnabled(true);
    }

    private void middleImageSelected() {
        selectedCard = 1;
        cardMiddle.setImageAlpha(1000);
        cardLeft.setImageAlpha(100);
        cardRight.setImageAlpha(100);
        confirmBtn.setEnabled(true);
    }

    private void rightImageSelected() {
        selectedCard = 2;
        cardRight.setImageAlpha(1000);
        cardLeft.setImageAlpha(100);
        cardMiddle.setImageAlpha(100);
        confirmBtn.setEnabled(true);
    }

    private void setDefaultStyle() {
        cardLeft.setImageResource(R.drawable.back);
        cardMiddle.setImageResource(R.drawable.back);
        cardRight.setImageResource(R.drawable.back);
        cardLeft.setImageAlpha(1000);
        cardMiddle.setImageAlpha(1000);
        cardRight.setImageAlpha(1000);
        cardLeft.setClickable(true);
        cardMiddle.setClickable(true);
        cardRight.setClickable(true);
        confirmBtn.setEnabled(false);
        playAgainBtn.setEnabled(false);
    }
}