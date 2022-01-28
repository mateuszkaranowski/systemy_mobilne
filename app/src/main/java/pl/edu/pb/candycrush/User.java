package pl.edu.pb.candycrush;

import android.graphics.Bitmap;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private String nick;
    private String score;
    private String score1;
    private String score2;
    private byte[] image;

    public User () { }

    public int getId() {return id;}
    public void setId(int id) { this.id = id;}
    public String getName() { return name;}
    public void setName (String name) {this.name = name;}
    public String getNick() { return nick;}
    public void setNick(String score) { this.nick = score;}
    public String getScore() { return nick;}
    public void setScore(String score) { this.score = score;}
    public String getScore1() { return score1;}
    public void setScore1(String score1) { this.score1 = score1;}
    public String getScore2() { return score2;}
    public void setScore2(String score2) { this.score2 = score2;}
    public byte[] getImage() {return image;}
    public void setImage(byte[] image) {this.image = image;}
}
