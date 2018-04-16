package com.metalwihen.sample.gitem.utils;

import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BindingUtils {

    private BindingUtils() {
    }

    public static void bindText(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public static void bindImage(ImageView imageView, String imageUrl, @DrawableRes int placeholderDrawableResId) {
        if (TextUtils.isEmpty(imageUrl)) {
            if (placeholderDrawableResId == 0) {
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setVisibility(View.VISIBLE);
                Picasso.with(imageView.getContext()).load(placeholderDrawableResId).into(imageView);
            }
        } else {
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }
}
