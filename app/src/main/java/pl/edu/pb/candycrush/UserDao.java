package pl.edu.pb.candycrush;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
    @Update
    public void update (User user);

    @Delete
    public void delete(User user);

    @Query("DELETE FROM user")
    public void deleteAll();

    @Query("SELECT * FROM user ORDER BY name")
    public LiveData<List<User>> findAll();

    @Query("select * from user")
    List<User> getUsers();

    @Query("SELECT * FROM user where nick LIKE :nick")
    public List<User> findUserWithNick (String nick);

}
