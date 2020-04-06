package com.coder.knight.jetpack.secondsubmission.ui.tvshow;


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
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.viewmodel.ViewModelFactory;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvShowViewModel viewModel;
    private ProgressBar loadingBar;
    private ScrollView mScroll;

    // For on air tv show list
    // Adapter for on air tv shows (big poster)
    private TvBigCardAdapter onAirAdapter;
    private RecyclerView rvOnAir;

    // For Top rated tv show list
    // adapter for top rated tv shows (normal poster)
    private TvCardAdapter topRatedAdapter;
    private RecyclerView rvTopRated;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // find the recycler view and other views
        rvOnAir = view.findViewById(R.id.rv_on_air);
        rvTopRated = view.findViewById(R.id.rv_top_rated);
        loadingBar = view.findViewById(R.id.loading_progress);
        mScroll = view.findViewById(R.id.scroll_container);

        setUpLoadingBar();
        loadingBar.setVisibility(View.VISIBLE);
        mScroll.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpRecyclerView();

        if (getActivity() != null) {
            viewModel = obtainViewModel(getActivity());
            // Get on air tv show lists
            getOnAir();
            // Get top rated tv show list
            getTopRated();
        }
    }

    // get on air data from view model
    private void getOnAir() {
        viewModel.getOnAirTvShows().observe(getViewLifecycleOwner(), tvShowResponse -> {
            loadingBar.setVisibility(View.GONE);
            mScroll.setVisibility(View.VISIBLE);
            ArrayList<TvShowEntity> tvShowEntities = tvShowResponse.getTvShows();
            onAirAdapter.setTvShowList(tvShowEntities);
            onAirAdapter.notifyDataSetChanged();
        });
    }

    // get top rated data from view model
    private void getTopRated() {
        viewModel.getTopRatedTvShows().observe(getViewLifecycleOwner(), tvShowResponse -> {
            loadingBar.setVisibility(View.GONE);
            mScroll.setVisibility(View.VISIBLE);
            ArrayList<TvShowEntity> tvShowEntities = tvShowResponse.getTvShows();
            topRatedAdapter.setTvShowList(tvShowEntities);
            topRatedAdapter.notifyDataSetChanged();
        });
    }

    private void setUpLoadingBar() {
        Sprite doubleBounce = new DoubleBounce();
        loadingBar.setIndeterminateDrawable(doubleBounce);
    }

    /*
     * Set up the recycler view and adapters
     * */
    private void setUpRecyclerView() {
        // For on air tv show
        if (onAirAdapter == null) {
            onAirAdapter = new TvBigCardAdapter(getActivity());
            rvOnAir.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
            rvOnAir.setAdapter(onAirAdapter);
        } else {
            onAirAdapter.notifyDataSetChanged();
        }

        // For top rated tv show
        if (topRatedAdapter == null) {
            topRatedAdapter = new TvCardAdapter(getActivity());
            rvTopRated.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
            rvTopRated.setAdapter(topRatedAdapter);
        } else {
            topRatedAdapter.notifyDataSetChanged();
        }
    }
}
