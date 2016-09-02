/*
 * Copyright 2015 Soo [154014022@qq.com | sootracker@gmail.com]
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *
 */

package soo.swallow.view.sv;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;

/**
 * @author Soo.
 */
public class StatusView extends FrameLayout implements IStatusView {
    private static final String TAG = "StatusLayout--->";

    private Recycler recycler;

    private StatusAdapter statusAdapter;
    private int currentStatus = -1;

    public StatusView(Context context) {
        this(context, null);
    }

    public StatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.recycler = new Recycler();
    }

    @Deprecated
    @Override
    public void addView(View child) {
        super.addView(child);
    }

    @Deprecated
    @Override
    public void addView(View child, int index) {
        super.addView(child, index);
    }

    @Deprecated
    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
    }

    @Deprecated
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
    }

    @Deprecated
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
    }

    @Override
    public void setAdapter(StatusAdapter statusAdapter) {
        if (statusAdapter == null) {
            throw new NullPointerException("StatusAdapter is null");
        }
        if (this.statusAdapter == statusAdapter) {
            return;
        }
        this.statusAdapter = statusAdapter;
        recycler.clear();
        setCurrent(0);
    }

    @Override
    public void setCurrent(int status) {
        if (status < 0) {
            throw new IllegalArgumentException("The value of status is must super than zero");
        }
        if (statusAdapter == null) {
            throw new IllegalStateException("Please invoking setAdapter before invoke setCurrent");
        }
        if (currentStatus == status) {
            return;
        }
        boolean animate = false;
        View newView;
        newView = recycler.fetch(status);
        newView = statusAdapter.getView(this, newView, status);
        if (newView == null) {
            throw new NullPointerException("Can`t make a null view");
        }

        recycler.recycle(currentStatus, newView);

        View currentView = getChildAt(0);
        if (currentView != null) {
            currentView.clearAnimation();
            Animation hideAnimation = null;
            if (animate) {
                hideAnimation = statusAdapter.getHideAnimation(currentStatus);
            }
            if (hideAnimation != null) {
                currentView.startAnimation(hideAnimation);
            }
            this.removeView(currentView);
        }

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(newView, layoutParams);
        Animation showAnimation = null;
        if (animate) {
            showAnimation = statusAdapter.getShowAnimation(status);
        }
        if (showAnimation != null) {
            newView.startAnimation(showAnimation);
        }
    }

    static class Recycler {

        SparseArray<View> pool;

        Recycler() {
            this.pool = new SparseArray<>(3);
        }

        void recycle(int tag, View view) {
            pool.put(tag, view);
        }

        View fetch(int tag) {
            return pool.get(tag);
        }

        void clear() {
            pool.clear();
        }
    }
}
