package android.albumlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AlbumActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView= findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new GridLayoutManager(this,1));
		recyclerView.setHasFixedSize(true);


		AlbumsViewModel albumsViewModel=  new ViewModelProvider(this).get(AlbumsViewModel.class);
		albumsViewModel.getAlbums().observe(this, new Observer<List<Album>>() {
			@Override
			public void onChanged(List<Album> albums) {
				if(albums!=null)recyclerView.setAdapter(new RecycleViewAdaptor(albums));
			}
		});
	}
	class RecycleViewAdaptor extends RecyclerView.Adapter<RecycleViewAdaptor.AlbumView>{
		List<Album> Albums;
		public RecycleViewAdaptor(List<Album> albums) {
			Albums =albums;
		}

		class AlbumView extends RecyclerView.ViewHolder{
			TextView txtID, txtTitle;

			public AlbumView(@NonNull View itemView) {
				super(itemView);

				txtID =itemView.findViewById(R.id.ID_Info);
				txtTitle=itemView.findViewById(R.id.AlbumTitle);
			}
		}
		@NonNull
		@Override
		public AlbumView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itim, parent, false);

			return new AlbumView(view);
		}

		@Override
		public void onBindViewHolder(@NonNull AlbumView holder, int position) {
			//set Card-view to show given pokemon data
			holder.txtTitle.setText(Albums.get(position).title);
			holder.txtID.setText("User ID:"+ Albums.get(position).userId);

		}
		@Override
		public int getItemCount() {
			return Albums.size();
		}
	}
}