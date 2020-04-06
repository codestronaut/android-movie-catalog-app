package com.coder.knight.jetpack.secondsubmission.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.coder.knight.jetpack.secondsubmission.di.Injection;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;
import com.coder.knight.jetpack.secondsubmission.ui.detail.DetailViewModel;
import com.coder.knight.jetpack.secondsubmission.ui.movie.MovieViewModel;
import com.coder.knight.jetpack.secondsubmission.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final ContentRepository mContentRepository;

    private ViewModelFactory(ContentRepository contentRepository) {
        mContentRepository = contentRepository;
    }

    public static ViewModelFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.providerRepository());
                }
            }
        }

        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(mContentRepository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            //noinspection unchecked
            return (T) new TvShowViewModel(mContentRepository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailViewModel(mContentRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
