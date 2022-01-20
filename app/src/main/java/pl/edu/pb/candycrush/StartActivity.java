package pl.edu.pb.candycrush;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
    }

    public void goToStart(View view)
    {
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
}
