package pl.edu.pb.candycrush;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {

    EditText name, nick;
    Button saveButton, getData;
    ImageView picture;
    private UserViewModel userViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        ImageButton cameraButton = (ImageButton) findViewById(R.id.buttonCamera);
        picture = (ImageView) findViewById(R.id.camera);


        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        name=findViewById(R.id.nameUser);
        nick=findViewById(R.id.nickUser);
        saveButton=findViewById(R.id.submitButton);
        getData=findViewById(R.id.btn_getData);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GetData.class));
            }
        });
    }
    private void saveData()
    {
        String name_txt = name.getText().toString().trim();
        String nick_txt = nick.getText().toString().trim();

       // if(name_txt.isEmpty())
        //{
            User model = new User();
            model.setName(name_txt);
            model.setNick(nick_txt);
            UserDatabase.getDatabase(getApplicationContext()).userDao().insert(model);

            name.setText("");
            nick.setText("");
            Toast.makeText(this,"Dane zostały zapisane! :)", Toast.LENGTH_SHORT).show();
       // }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        picture.setImageBitmap(bitmap);
    }
}


