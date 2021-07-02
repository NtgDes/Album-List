package android.albumlist;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4ClassRunner.class)

public class AlbumDaoTest {
	AlbumDataBase albumDataBase;
	AlbumDao albumDao;
	@Before
	public void Setup() {
		albumDataBase= Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),AlbumDataBase.class).allowMainThreadQueries().build();
		albumDao= albumDataBase.albumDao();
	}

	@After
	public void CleanUP(){
		albumDataBase.close();
	}

	@Test
	public void AddData(){
		List<Album> albums= new ArrayList<>();

		Album album1= new Album(), album2= new Album();
		album1.userId=1;album1.id=0;album1.title= "Test";
		album2.userId=1;album2.id=1;album2.title= "Test2";
		albums.add(album1);
		albums.add(album2);

		albumDao.Insert(albums);

		assertTrue("Data not Added",albumDao.count()!=0);
	}
	@Test
	public void clearData(){
		albumDao.Truncate();
		assertEquals("Data not Deleted", 0, albumDao.count());
	}
	@Test
	public void SyncData(){
		clearData();
		AddData();
	}

}