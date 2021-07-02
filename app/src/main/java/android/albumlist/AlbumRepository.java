package android.albumlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlbumRepository {
	private AlbumDao albumDao;
	private LiveData<List<Album>> Albums;

	public AlbumRepository(Application application){
		albumDao= AlbumDataBase.getInstance(application).albumDao();

		new APIAccess(albums->{
			new DatabaseUpdate(albumDao,albums).execute();
		});

		Albums= albumDao.getAlbumList();
	}

	public LiveData<List<Album>> getAlbums() {
		return Albums;
	}

	private  static class DatabaseUpdate extends AsyncTask<Void,Void,Void>{
		private AlbumDao albumDao;
		private List<Album> albums;

		private DatabaseUpdate(AlbumDao albumDao,List<Album> albums){
			this.albumDao= albumDao;
			this.albums=albums;

		}
		@Override
		protected Void doInBackground(Void... voids) {
			albumDao.Truncate();
			albumDao.Insert(albums);
			return null;

		}
	}

}
