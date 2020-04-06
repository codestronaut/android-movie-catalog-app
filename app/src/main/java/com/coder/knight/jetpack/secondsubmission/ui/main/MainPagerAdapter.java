package com.coder.knight.jetpack.secondsubmission.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> childFragment = new ArrayList<>();
    private final List<String> childFragmentTitle = new ArrayList<>();

    MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    void addFragment(Fragment fragment, String title) {
        childFragment.add(fragment);
        childFragmentTitle.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return childFragment.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return childFragmentTitle.get(position);
    }

    @Override
    public int getCount() {
        return childFragment.size();
    }
}
