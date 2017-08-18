package com.example.uberv.viewpagerlogin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Activity created from Vasyl Paliy's article on medium:
 * https://medium.com/@vpaliy/do-you-dare-me-to-implement-this-login-screen-bf29b72d9e39
 */
public class LoginActivity extends AppCompatActivity {

    // logo and facebook/linkedin/twitter login icons
    @BindViews(value = {R.id.logo, R.id.first, R.id.second, R.id.last})
    protected List<ImageView> mSharedElements;
    @BindView(R.id.pager)
    AnimatedViewPager mViewPager;
    @BindView(R.id.scrolling_background)
    ImageView mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        int[] screenSize = getScreenSize();

        // Tint buttons
        for (ImageView element : mSharedElements) {
            @ColorRes int color = element.getId() != R.id.logo ?
                    R.color.white_transparent : R.color.color_logo_log_in;
            DrawableCompat.setTint(element.getDrawable(), ContextCompat.getColor(this, color));
        }

        // Load a very big image and resize it, so it fits our needs
        RequestOptions options = new RequestOptions()
                .override(screenSize[0] * 2, screenSize[1]) // we need it's width to be equal to 2 widths of screen
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE); // TODO what's the analog for .RESULT in glide v4?
        Glide.with(this)
                .asBitmap()
                .apply(options)
                .load(R.drawable.busy)
                .into(new ImageViewTarget<Bitmap>() {
                    @Override
                    protected void setResource(@Nullable Bitmap resource) {
                        mBackground.setImageBitmap(resource);
                        // Shift the user focus to the very left edge of the image
                        mBackground.scrollTo(-mViewPager.getWidth(), 0);
                        // Fire a scale animation
                        mBackground.post(new Runnable() {
                            @Override
                            public void run() {
                                //we need to scroll to the very left edge of the image
                                //fire the scale animation
                                ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mBackground, View.SCALE_X, 4f, mBackground.getScaleX());
                                ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mBackground, View.SCALE_Y, 4f, mBackground.getScaleY());
                                AnimatorSet set = new AnimatorSet();
                                set.playTogether(xAnimator, yAnimator);
                                set.setDuration(getResources().getInteger(R.integer.duration));
                                set.start();
                            }
                        });
                        AuthAdapter adapter = new AuthAdapter(getSupportFragmentManager(), mViewPager, mBackground, mSharedElements);
                        mViewPager.setAdapter(adapter);
                    }
                });

    }

    private int[] getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new int[]{size.x, size.y};
    }
}
