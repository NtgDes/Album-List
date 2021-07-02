package android.albumlist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Album {
	public int userId;
	@PrimaryKey
	public int id;
	public String title;
}
