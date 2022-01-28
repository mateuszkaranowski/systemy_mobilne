package pl.edu.pb.candycrush;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int[] tableTank = {
        R.drawable.czolmg1,
        R.drawable.czolmg2,
        R.drawable.czolmg3,
        R.drawable.czolmg4,
        R.drawable.czolmg5,
        R.drawable.czolmg6,
    };
    int widthOfBlock, noOfBlock =8, widthOfScreen;
    ArrayList<ImageView> tank = new ArrayList<>();
    int tankToBeDragged, tankToBeReplaced;
    int notTank = R.drawable.nobackground;
    Handler mHandler = new Handler();
    int interval = 100;
    TextView scoreResult;
    int score =0;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreResult = findViewById(R.id.score);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Button button = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.kanarskytravels.cba.pl"));
                startActivity(intent);
            }
        });

        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthOfBlock = widthOfScreen/noOfBlock;


        createBoard();
        for(ImageView imageView: tank)
        {
            imageView.setOnTouchListener(new MovePiece(this)
            {
                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    tankToBeDragged = imageView.getId();
                    tankToBeReplaced = tankToBeDragged -1;
                    candyInterChange();

                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    tankToBeDragged = imageView.getId();
                    tankToBeReplaced = tankToBeDragged +1;
                    candyInterChange();
                }
                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    tankToBeDragged = imageView.getId();
                    tankToBeReplaced = tankToBeDragged -noOfBlock;
                    candyInterChange();
                }
                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    tankToBeDragged = imageView.getId();
                    tankToBeReplaced = tankToBeDragged +noOfBlock;
                    candyInterChange();
                }
            });
        }
        mHandler = new Handler();
        startRepeat();
    }
    private void checkRowForThree()
    {
        for(int i =0; i<62; i++)
        {
            int chosedCandy = (int) tank.get(i).getTag();
            boolean isBlank = (int) tank.get(i).getTag() == notTank;
            Integer[] notValid = {6,7,14,15,22,23,30,31,38,39,46,47};
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i))
            {
                int x=i;
                if((int) tank.get(x++).getTag() == chosedCandy && !isBlank &&
                        (int) tank.get(x++).getTag() == chosedCandy &&
                        (int) tank.get(x).getTag() == chosedCandy)
                {
                    score++;
                    scoreResult.setText(String.valueOf(score));
                    tank.get(x).setImageResource(notTank);
                    tank.get(x).setTag(notTank);
                    x--;
                    tank.get(x).setImageResource(notTank);
                    tank.get(x).setTag(notTank);
                    x--;
                    tank.get(x).setImageResource(notTank);
                    tank.get(x).setTag(notTank);

                }
            }

        }
        moveDownCandy();
    }

    private void checkColumnForThree()
    {
        for(int i =0; i<47; i++)
        {
            int chosedCandy = (int) tank.get(i).getTag();
            boolean isBlank = (int) tank.get(i).getTag() == notTank;
                int x=i;
                if((int) tank.get(x).getTag() == chosedCandy && !isBlank &&
                        (int) tank.get(x+noOfBlock).getTag() == chosedCandy &&
                        (int) tank.get(x+2*noOfBlock).getTag() == chosedCandy)
                {
                    score++;
                    scoreResult.setText(String.valueOf(score));
                    tank.get(x).setImageResource(notTank);
                    tank.get(x).setTag(notTank);
                    x = x+noOfBlock;
                    tank.get(x).setImageResource(notTank);
                    tank.get(x).setTag(notTank);
                    x = x+noOfBlock;
                    tank.get(x).setImageResource(notTank);
                    tank.get(x).setTag(notTank);

                }

        }
        moveDownCandy();

    }

    private void moveDownCandy()
    {
        Integer[] firstRow = {0,1,2,3,4,5,6,7};
        List<Integer> list = Arrays.asList(firstRow);
        for (int i = 55; i >=0;i--)
        {
            if ((int) tank.get(i + noOfBlock).getTag() == notTank)
            {
                tank.get(i+noOfBlock).setImageResource((int) tank.get(i).getTag());
                tank.get(i+noOfBlock).setTag(tank.get(i).getTag());
                tank.get(i).setImageResource(notTank);
                tank.get(i).setTag(notTank);

                if(list.contains(i) && (int) tank.get(i).getTag() == notTank)
                {
                    int randomColor = (int) Math.floor(Math.random()*(5 +1)+0);

                    tank.get(i).setImageResource(tableTank[randomColor]);
                    tank.get(i).setTag(tableTank[randomColor]);
                }
            }
        }
        for(int i =0; i <8; i++)
        {
            if((int) tank.get(i).getTag()== notTank)
            {
                int randomColor = (int) Math.floor(Math.random()*(5 +1)+0);
                tank.get(i).setImageResource(tableTank[randomColor]);
                tank.get(i).setTag(tableTank[randomColor]);
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
        int background =(int) tank.get(tankToBeReplaced).getTag();
        int background1 =(int) tank.get(tankToBeDragged).getTag();
        tank.get(tankToBeDragged).setImageResource(background);
        tank.get(tankToBeReplaced).setImageResource(background1);
        tank.get(tankToBeDragged).setTag(background);
        tank.get(tankToBeReplaced).setTag(background1);
    }

    private void createBoard() {
        GridLayout layoutGrid = findViewById(R.id.sceneGrid);
        layoutGrid.setRowCount(noOfBlock);
        layoutGrid.setColumnCount(noOfBlock);
        layoutGrid.getLayoutParams().width = widthOfScreen;
        layoutGrid.getLayoutParams().height = widthOfScreen;

        //dawanie cukierków na obrazie
        for(int k = 0;k<noOfBlock*noOfBlock;k++)
        {
            ImageView viewWithImage = new ImageView(this);
            viewWithImage.setId(k);
            viewWithImage.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
            viewWithImage.setMaxHeight(widthOfBlock);
            viewWithImage.setMaxWidth(widthOfBlock);
            int candyRandom = (int) Math.floor(Math.random() * tableTank.length);
            viewWithImage.setImageResource(tableTank[candyRandom]);
            viewWithImage.setTag(tableTank[candyRandom]);
            tank.add(viewWithImage);
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