package pl.edu.pb.candycrush;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LevelActivity extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.level_activity);
        }

    public void goToEasy(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToMedium(View view)
    {
        Intent intent = new Intent(this, MediumActivity.class);
        startActivity(intent);
    }
    public void goToHard(View view)
    {
        Intent intent = new Intent(this, HardActivity.class);
        startActivity(intent);
    }
    public void goProfile(View view)
    {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    public void goTable(View view)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
