package com.coder.knight.jetpack.secondsubmission.di;

import com.coder.knight.jetpack.secondsubmission.data.source.remote.repository.RemoteRepository;
import com.coder.knight.jetpack.secondsubmission.networking.ApiInterface;
import com.coder.knight.jetpack.secondsubmission.networking.RetrofitService;
import com.coder.knight.jetpack.secondsubmission.networking.repository.ContentRepository;

public class Injection {
    public static ContentRepository providerRepository() {
        ApiInterface apiInterface = RetrofitService.createService(ApiInterface.class);
        RemoteRepository remoteRepository = RemoteRepository.getInstance(apiInterface);
        return ContentRepository.getInstance(remoteRepository);
    }
}
