package android.albumlist;

import android.app.Application;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlbumRepositoryTest {
	@Test
	public void set	(){
		AlbumRepository albumRepository= new AlbumRepository(ApplicationProvider.getApplicationContext());
		assertNotNull("No data Source",albumRepository.getAlbums());
	}

}