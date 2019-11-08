package id.co.endang.mymoviesubmission2.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.endang.mymoviesubmission2.MovieDetail;
import id.co.endang.mymoviesubmission2.R;
import id.co.endang.mymoviesubmission2.Settings;
import id.co.endang.mymoviesubmission2.model.Movie;
import id.co.endang.mymoviesubmission2.model.MovieAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private String[] movieName;
    private String[] movieDescription;
    private String[] movieReleasDt;
    private TypedArray moviePhoto;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;
    private Context context;
    private RecyclerView rvMovies;


    public MovieFragment() {
        // Required empty public constructor

    }

    public MovieFragment(Context context) {
        this.context = context;
    }

    private void init() {
        movieName = getResources().getStringArray(R.array.movie_name);
        movieDescription = getResources().getStringArray(R.array.movie_desc);
        movieReleasDt = getResources().getStringArray(R.array.movie_releasedt);
        moviePhoto = getResources().obtainTypedArray(R.array.movie_photo);
    }

    private void populateData() {
        movies = new ArrayList<>();

        for (int i = 0; i < movieName.length; i++) {
            Movie movie = new Movie();
            movie.setName(movieName[i]);
            movie.setDetail(movieDescription[i]);
            movie.setReleasDt(movieReleasDt[i]);
            movie.setPhoto(moviePhoto.getResourceId(i, -1));
            movies.add(movie);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        Settings.setLanguage(context, sharedPreferences);
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        init();
        populateData();
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        rvMovies.setLayoutManager(new LinearLayoutManager(this.context));
        MovieAdapter adapter = new MovieAdapter(movies);
        rvMovies.setAdapter(adapter);

        adapter.setOnItemClickCallBack(new MovieAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Movie data) {
                showDetailMovie(data);
            }
        });

        return view;
    }

    private void showDetailMovie(Movie data) {
        Intent intent = new Intent(getActivity(), MovieDetail.class);
        intent.putExtra(MovieDetail.EXTRA_MOVIE, data);
        startActivity(intent);
    }


}
