package id.co.endang.mymoviesubmission2.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.co.endang.mymoviesubmission2.MovieDetail;
import id.co.endang.mymoviesubmission2.R;
import id.co.endang.mymoviesubmission2.Settings;
import id.co.endang.mymoviesubmission2.model.Movie;
import id.co.endang.mymoviesubmission2.model.MovieAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    private String[] tvShowName;
    private String[] tvShowDescription;
    private String[] tvShowReleasDt;
    private TypedArray tvShowPhoto;
    private MovieAdapter tvShowAdapter;
    private ArrayList<Movie> tvShows;
    private Context context;
    private RecyclerView rvtvShows;

    public TVShowFragment() {
        // Required empty public constructor
    }

    private void init() {
        tvShowName = getResources().getStringArray(R.array.tv_name);
        tvShowDescription = getResources().getStringArray(R.array.tv_desc);
        tvShowReleasDt = getResources().getStringArray(R.array.tv_releasdt);
        tvShowPhoto = getResources().obtainTypedArray(R.array.tv_photo);
    }

    private void populateData() {
        tvShows = new ArrayList<>();

        for (int i = 0; i < tvShowName.length; i++) {
            Movie movie = new Movie();
            movie.setName(tvShowName[i]);
            movie.setDetail(tvShowDescription[i]);
            movie.setReleasDt(tvShowReleasDt[i]);
            movie.setPhoto(tvShowPhoto.getResourceId(i, -1));
            tvShows.add(movie);
        }
    }

    public TVShowFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        init();
        populateData();
        rvtvShows = view.findViewById(R.id.rv_tvShow);
        rvtvShows.setHasFixedSize(true);

        rvtvShows.setLayoutManager(new LinearLayoutManager(this.context));
        MovieAdapter adapter = new MovieAdapter(tvShows);
        rvtvShows.setAdapter(adapter);

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
