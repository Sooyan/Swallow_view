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

package soo.swallow.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author Soo.
 */
public class PlusLinearLayout extends LinearLayout {
    private static final String TAG = "PlusLinearLayout--->";

    private int startMargin;

    public PlusLinearLayout(Context context) {
        super(context);
    }

    public PlusLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlusLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PlusLinearLayout);
        int startMargin = ta.getDimensionPixelSize(R.styleable.PlusLinearLayout_startMargin, 0);
        setDividerMargin(startMargin);
        ta.recycle();
    }

    @Override
    public void setDividerDrawable(Drawable divider) {
        super.setDividerDrawable(divider);
    }

    public void setDividerMargin(int startMargin) {
        this.startMargin = startMargin;

        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = canvas.save();
        if (getOrientation() == HORIZONTAL) {
            canvas.translate(0, startMargin);
        } else {
            canvas.translate(startMargin, 0);
        }
        super.onDraw(canvas);
        canvas.restoreToCount(count);
    }

}
