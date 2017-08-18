package com.example.uberv.viewpagerlogin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class LoginAdapter extends FragmentStatePagerAdapter {

    private float mFactor;

    public LoginAdapter(FragmentManager fm, ViewPager pager) {
        super(fm);
//        // size of the text when it's folded (vertical)
//        final float textSize = pager.getResources().getDimension(R.dimen.folded_size);
//        // padding from left and right
//        final float textPadding = pager.getResources().getDimension(R.dimen.folded_label_padding);
//        // This is page width factor (1 being 100%) taking into account space reserved for vertical text clip
//        mFactor = 1 - (textSize + textPadding) / (pager.getWidth());
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
