/*
 * Copyright (C) 2017 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of rebase-android
 *
 * rebase-android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * rebase-android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with rebase-android. If not, see <http://www.gnu.org/licenses/>.
 */

package com.dangxy.readhub.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dangxy.readhub.R;

/**
 * @author drakeet
 */
public class SwipeBackDelegate implements SwipeBackLayout.SwipeBackListener {

    private SwipeBackLayout swipeBackLayout;
    private ImageView shadow;


    public void attach(Activity activity, int layoutResID) {
        View root = getContainer(activity);
        View view = LayoutInflater.from(activity).inflate(layoutResID, null);
        swipeBackLayout.addView(view);
        activity.setContentView(root);
    }


    @SuppressWarnings("deprecation")
    private View getContainer(Context context) {
        RelativeLayout container = new RelativeLayout(context);
        swipeBackLayout = new SwipeBackLayout(context);
        swipeBackLayout.setOnSwipeBackListener(this);
        shadow = new ImageView(context);
        shadow.setBackgroundColor(context.getResources().getColor(R.color.black_p50));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(shadow, params);
        container.addView(swipeBackLayout);
        return container;
    }


    public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
        swipeBackLayout.setDragEdge(dragEdge);
    }


    @Override
    public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
        shadow.setAlpha(1 - fractionScreen);
    }


    public void setSwipeBackEnabled(boolean enabled) {
        swipeBackLayout.setSwipeBackEnabled(enabled);
    }
}
