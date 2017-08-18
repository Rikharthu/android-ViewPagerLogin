package com.example.uberv.viewpagerlogin;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class AuthFragment extends Fragment {
    protected Callback callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(authLayout(), container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    public void setCallback(@NonNull Callback callback) {
        this.callback = callback;
    }

    /**
     * Called for the next/previous page before a switch (get's called when a click event occurs on that vertical TextView)
     */
    @OnClick(R.id.caption)
    public void unfold() {
        /* animation code goes here
           ...   ....
        */
        //after everything's been set up, tell the ViewPager to flip the page
        callback.show(this);
    }

    /**
     * Provides a resource to inflate in the Fragment.OnCreateView() method
     *
     * @return
     */
    @LayoutRes
    public abstract int authLayout();

    /**
     * Called when you are switching to next/previous page for the current fragment
     */
    public abstract void fold();

    /**
     * Called when you release the inputs. For example when you've entere your password or login, we need
     * to clear the focus. At this moment a scale animation is fired as well
     */
    public abstract void clearFocus();

    interface Callback {
        void show(AuthFragment fragment);

        void scale(boolean hasFocus);
    }
}
