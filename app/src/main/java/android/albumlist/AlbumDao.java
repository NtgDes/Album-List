package android.albumlist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlbumDao {
	@Insert
	void Insert(List<Album> albums);

	@Query("DELETE FROM Album")
	void Truncate();

	@Query("SELECT * FROM Album ORDER BY title")
	LiveData<List<Album>> getAlbumList();

	@Query("SELECT COUNT(*) FROM album")
	int count();
}
