package android.albumlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class APIAccess {
	//site url for Album API
	public static final String
			APIUrl = "https://jsonplaceholder.typicode.com/albums";
			

	callBackAlbumList callBackResponseList;
	/**
	 * Connect to API to get a list of Albums
	 * @param response Callback with a interface that contains list type Album, contains null if not successful
	 */
	public APIAccess(callBackAlbumList response) {
		callBackResponseList =response;
		new GetList().execute();
	}

	private class GetList extends AsyncTask<Void, String, String> {
		@Override
		protected String doInBackground(Void... voids) {

			try {
				return ApIResponse(APIUrl);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(String response) {
			if (response==null) return;

			//format and add the response to list of type Album
			try {

				JSONArray JsonList=new JSONArray(response);
				if(JsonList.length()>0) {
					List<Album> Albums= new ArrayList<>();
					for (int i = 0; i < JsonList.length(); i++) {
						Album Album=new Album();

						Album.userId=JsonList.getJSONObject(i).getInt("userId");
						Album.id    =JsonList.getJSONObject(i).getInt("id");
						Album.title =JsonList.getJSONObject(i).getString("title");

						Albums.add(Album);
					}
					callBackResponseList.List(Albums);//callback with List of type Album
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}
	public interface callBackAlbumList {void  List(List<Album> Albums);}

	/**
	 * Connect to Api site and JSON string of requested endpoint.
	 * @param Url Api site Endpoint
	 * @return JSON string, will return null if not successful
	 * @throws IOException
	 */
	private String ApIResponse(String Url) throws IOException {
		HttpURLConnection urlConnection = (HttpURLConnection) new URL(Url).openConnection();

		urlConnection.setRequestMethod("GET");

		if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			urlConnection.disconnect();
			return null;
		}

		BufferedReader bufferedReaderIN = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;

		while ((line = bufferedReaderIN.readLine()) != null) {
			response.append(line);
		}
		bufferedReaderIN.close();
		urlConnection.disconnect();

		return response.toString();
	}
}
