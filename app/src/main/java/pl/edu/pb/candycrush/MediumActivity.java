package pl.edu.pb.candycrush;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediumActivity extends AppCompatActivity {
    int[] tableCandy = {
            R.drawable.czolmg1,
            R.drawable.czolmg2,
            R.drawable.czolmg3,
            R.drawable.czolmg4,
            R.drawable.czolmg5,
            R.drawable.czolmg6,
            R.drawable.level_medium_1,
            R.drawable.level_medium_2,
    };
    int widthOfBlock, noOfBlock =10, widthOfScreen; // --->zmienić nazwy potem//to są rozmiary
    ArrayList<ImageView> candy = new ArrayList<>();
    int candyToBeDragged, candyToBeReplaced;
    int notCandy = R.drawable.nobackground;//________________________> super wersja bez cofania jak usune te petle co dorabiaja dalej :)
    Handler mHandler = new Handler();
    int interval = 100;
    TextView scoreResult;
    int score =0;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medium_activity);
        scoreResult = findViewById(R.id.score);
        DisplayMetrics displayMetrics = new DisplayMetrics(); //wyswietlanie na ekran
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //tu są rozmiary planszy (8X8)
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthOfBlock = widthOfScreen/noOfBlock;
        //tworzymy obraz na ekranie -> planszę
        createBoard();
        for(ImageView imageView:candy)
        {
            imageView.setOnTouchListener(new MovePiece(this)
            {
                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged -1;
                    candyInterChange();

                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged +1;
                    candyInterChange();
                }
                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged -noOfBlock;
                    candyInterChange();
                }
                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged +noOfBlock;
                    candyInterChange();
                }
            });
        }
        mHandler = new Handler();
        startRepeat();
    }
    private void checkRowForThree()
    {
        for(int i =0; i<98; i++)
        {
            int chosedCandy = (int) candy.get(i).getTag();
            boolean isBlank = (int) candy.get(i).getTag() == notCandy;
            Integer[] notValid = {8,9,24,25,32,33,40,41,48,49,56,57,64,65,72,73,80,81,88,89};//pasujace do danych kolumn numerki(nie moze być tam 3 cukierkow)
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i))
            {
                int x=i;
                if((int) candy.get(x++).getTag() == chosedCandy && !isBlank &&
                        (int) candy.get(x++).getTag() == chosedCandy &&
                        (int) candy.get(x).getTag() == chosedCandy)
                {
                    score++;
                    scoreResult.setText(String.valueOf(score));
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);
                    x--;
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);
                    x--;
                    candy.get(x).setImageResource(notCandy);
                    candy.get(x).setTag(notCandy);

                }
            }

        }
        moveDownCandy();
    }

    private void checkColumnForThree()///////Blad z doelm i gora naprawic
    {
        for(int i =0; i<79; i++)
        {
            int chosedCandy = (int) candy.get(i).getTag();
            boolean isBlank = (int) candy.get(i).getTag() == notCandy;
            int x=i;
            if((int) candy.get(x).getTag() == chosedCandy && !isBlank &&
                    (int) candy.get(x+noOfBlock).getTag() == chosedCandy &&
                    (int) candy.get(x+2*noOfBlock).getTag() == chosedCandy)
            {
                score++;
                scoreResult.setText(String.valueOf(score));
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);
                x = x+noOfBlock;
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);
                x = x+noOfBlock;
                candy.get(x).setImageResource(notCandy);
                candy.get(x).setTag(notCandy);

            }

        }
        moveDownCandy();

    }

    private void moveDownCandy()
    {
        Integer[] firstRow = {0,1,2,3,4,5,6,7,8,9};
        List<Integer> list = Arrays.asList(firstRow);
        for (int i = 89; i >=0;i--)
        {
            if ((int)candy.get(i + noOfBlock).getTag() == notCandy)
            {
                candy.get(i+noOfBlock).setImageResource((int) candy.get(i).getTag());
                candy.get(i+noOfBlock).setTag(candy.get(i).getTag());
                candy.get(i).setImageResource(notCandy);
                candy.get(i).setTag(notCandy);

                if(list.contains(i) && (int) candy.get(i).getTag() == notCandy)
                {
                    int randomColor = (int) Math.floor(Math.random()*(7 +1)+0);

                    candy.get(i).setImageResource(tableCandy[randomColor]);
                    candy.get(i).setTag(tableCandy[randomColor]);
                }
            }
        }
        for(int i =0; i <10; i++)
        {
            if((int) candy.get(i).getTag()==notCandy)
            {
                int randomColor = (int) Math.floor(Math.random()*(5 +1)+0);
                candy.get(i).setImageResource(tableCandy[randomColor]);
                candy.get(i).setTag(tableCandy[randomColor]);
            }
        }
    }

    Runnable repeatChecker = new Runnable() {
        @Override
        public void run() {
            try{
                checkRowForThree();
                checkColumnForThree();
                moveDownCandy();
            }
            finally {
                mHandler.postDelayed(repeatChecker,interval);
            }
        }
    };
    void startRepeat()
    {
        repeatChecker.run();
    }

    private void candyInterChange()
    {
        int background =(int) candy.get(candyToBeReplaced).getTag();
        int background1 =(int) candy.get(candyToBeDragged).getTag();
        candy.get(candyToBeDragged).setImageResource(background);
        candy.get(candyToBeReplaced).setImageResource(background1);
        candy.get(candyToBeDragged).setTag(background);
        candy.get(candyToBeReplaced).setTag(background1);
    }

    private void createBoard() {
        GridLayout layoutGrid = findViewById(R.id.sceneGrid);
        layoutGrid.setRowCount(noOfBlock);//ustawienie ile ma być wierszy i kolumn
        layoutGrid.setColumnCount(noOfBlock);
        layoutGrid.getLayoutParams().width = widthOfScreen;//gdy scena ma być kwadratem
        layoutGrid.getLayoutParams().height = widthOfScreen;

        //dawanie cukierków na obrazie
        for(int k = 0;k<noOfBlock*noOfBlock;k++)
        {
            ImageView viewWithImage = new ImageView(this);
            viewWithImage.setId(k);
            viewWithImage.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
            viewWithImage.setMaxHeight(widthOfBlock);
            viewWithImage.setMaxWidth(widthOfBlock);
            int candyRandom = (int) Math.floor(Math.random() * tableCandy.length);//to daje indexy cukierków
            viewWithImage.setImageResource(tableCandy[candyRandom]);
            viewWithImage.setTag(tableCandy[candyRandom]);
            candy.add(viewWithImage);
            layoutGrid.addView(viewWithImage);
        }
    }

    public void goToProfile(View view)
    {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    public void goTable(View view)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
    public void goToLevel(View view)
    {
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
}
