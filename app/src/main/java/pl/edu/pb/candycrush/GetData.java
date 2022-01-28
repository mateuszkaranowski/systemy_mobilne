package pl.edu.pb.candycrush;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.pb.candycrush.Adapter.UserAdapter;

public class GetData extends AppCompatActivity {


    RecyclerView recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        recyclerview=findViewById(R.id.recyclerview);
        
        getData();
    }

    private void getData() {

        recyclerview.setAdapter(new UserAdapter(getApplicationContext(), UserDatabase.getDatabase(getApplicationContext()).userDao().getUsers()));
    }
}
