package android.albumlist;

import org.junit.Test;

import static org.junit.Assert.*;

public class APIAccessTest {
	@Test
	public void  UrlString () {
		assertEquals("API endpont Incorect","https://jsonplaceholder.typicode.com/albums",APIAccess.APIUrl);
	}
}