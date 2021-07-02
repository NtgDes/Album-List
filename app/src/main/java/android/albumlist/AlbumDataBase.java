package android.albumlist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Album.class,version = 1)
public abstract class AlbumDataBase extends RoomDatabase {
	private static  AlbumDataBase instance;

	public abstract AlbumDao albumDao();

	public static synchronized AlbumDataBase getInstance(Context context){
		if(instance==null){
			instance= Room.databaseBuilder(context,AlbumDataBase.class,"Album")
					.fallbackToDestructiveMigration()
					.build();
		}
		return instance;
	}
}
