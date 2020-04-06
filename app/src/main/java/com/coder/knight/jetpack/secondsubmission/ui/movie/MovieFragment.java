package com.coder.knight.jetpack.secondsubmission.ui.movie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.coder.knight.jetpack.secondsubmission.R;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.viewmodel.ViewModelFactory;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private MovieViewModel viewModel;
    private ScrollView mScroll;
    private ProgressBar loadingCircle;
    // adapter for recommended upcomingList (big poster)
    private MovieBigCardAdapter movieBigCardAdapter;
    private RecyclerView rvRecommended;

    // adapter for upcoming movie (normal poster)
    private MovieCardAdapter movieCardAdapter;
    private RecyclerView rvUpcoming;

    public MovieFragment() {
        // Required empty public constructor
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // find the recycler view and other views
        rvRecommended = view.findViewById(R.id.rv_recommended);
        rvUpcoming = view.findViewById(R.id.rv_upcoming);
        mScroll = view.findViewById(R.id.scroll_container);
        loadingCircle = view.findViewById(R.id.loading_progress);

        setUpLoadingBar();
        loadingCircle.setVisibility(View.VISIBLE);
        mScroll.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            setUpRecyclerView();
            viewModel = obtainViewModel(getActivity());
            // get recommended list of movie into the recycler view
            getRecommended();
            // get upcoming list of movie into the recycler view
            getUpcoming();
        }

    }

    // get recommended list
    private void getRecommended() {
        viewModel.getRecommendedMovie().observe(getViewLifecycleOwner(), movieResponse -> {
            if (movieResponse != null) {
                loadingCircle.setVisibility(View.GONE);
                mScroll.setVisibility(View.VISIBLE);
                ArrayList<MovieEntity> movieEntities = movieResponse.getMovies();
                movieBigCardAdapter.setMovieList(movieEntities);
                movieBigCardAdapter.notifyDataSetChanged();
            }
        });
    }

    // get upcoming list
    private void getUpcoming() {
        viewModel.getUpcomingMovie().observe(getViewLifecycleOwner(), movieResponse -> {
            if (movieResponse != null) {
                loadingCircle.setVisibility(View.GONE);
                mScroll.setVisibility(View.VISIBLE);
                ArrayList<MovieEntity> movieEntities = movieResponse.getMovies();
                movieCardAdapter.setMovieList(movieEntities);
                movieCardAdapter.notifyDataSetChanged();
            }
        });
    }

    /*
     * Set up loading bar
     * */
    private void setUpLoadingBar() {
        Sprite doubleBounce = new DoubleBounce();
        loadingCircle.setIndeterminateDrawable(doubleBounce);
    }

    /*
     * Set up the recycler view
     * */
    private void setUpRecyclerView() {
        if (movieBigCardAdapter == null) {
            // setup rvRecommended
            movieBigCardAdapter = new MovieBigCardAdapter(getActivity());
            rvRecommended.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
            rvRecommended.setAdapter(movieBigCardAdapter);
        } else {
            movieBigCardAdapter.notifyDataSetChanged();
        }

        if (movieCardAdapter == null) {
            // set up rvUpcoming
            movieCardAdapter = new MovieCardAdapter(getActivity());
            rvUpcoming.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
            rvUpcoming.setAdapter(movieCardAdapter);
        } else {
            movieCardAdapter.notifyDataSetChanged();
        }
    }
}