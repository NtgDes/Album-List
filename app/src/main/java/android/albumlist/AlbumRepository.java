package android.albumlist;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlbumRepository {
	private final AlbumDao albumDao;
	private final LiveData<List<Album>> Albums;

	public AlbumRepository(Application application){
		albumDao= AlbumDataBase.getInstance(application).albumDao();



		new APIAccess(albums->{
			if(albums==null){
				Toast.makeText(application,"Connection Error",Toast.LENGTH_LONG).show();
				return;
			}

			Toast.makeText(application,"Syncing Data",Toast.LENGTH_LONG).show();
			new DatabaseUpdate(albumDao,albums).execute();//syncing for offline viewing
		});

		Albums= albumDao.getAlbumList();
	}

	public LiveData<List<Album>> getAlbums() {
		return Albums;
	}

	private  static class DatabaseUpdate extends AsyncTask<Void,Void,Void>{
		private final AlbumDao albumDao;
		private final List<Album> albums;

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
