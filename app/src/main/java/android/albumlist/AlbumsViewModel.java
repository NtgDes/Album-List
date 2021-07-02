package android.albumlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AlbumsViewModel extends AndroidViewModel {
	private LiveData<List<Album>> Albums;

	public AlbumsViewModel(@NonNull Application application) {
		super(application);
		Albums= new AlbumRepository(application).getAlbums();
	}

	public LiveData<List<Album>> getAlbums() {
		return Albums;
	}
}
