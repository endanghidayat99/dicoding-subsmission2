package id.co.endang.mymoviesubmission2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import id.co.endang.mymoviesubmission2.model.Movie;

public class MovieDetail extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView photo = findViewById(R.id.item_photo);
        TextView txtName = findViewById(R.id.item_name);
        TextView txtRelease = findViewById(R.id.item_release_dt);
        TextView txtDetail = findViewById(R.id.item_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        photo.setImageResource(movie.getPhoto());
        txtName.setText(movie.getName());
        txtDetail.setText(movie.getDetail());
        txtRelease.setText(movie.getReleasDt());
        photo.setContentDescription(movie.getName());
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(movie.getName());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
