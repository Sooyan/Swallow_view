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
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PlusViewPager extends ViewPager {
    
    private boolean scrollAble = true;
    
    public PlusViewPager(Context context) {
        super(context);
    }

    public PlusViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PlushViewPager);
        scrollAble = ta.getBoolean(R.styleable.PlushViewPager_scrollAble, true);
        ta.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        try {
            return scrollAble ? super.onInterceptTouchEvent(arg0) : scrollAble;
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        try {
            return scrollAble ? super.onTouchEvent(arg0) : scrollAble;
        } catch (Exception e) {
        }
        return false;
    }
    
    public void setScrollAble(boolean able) {
        this.scrollAble = able;
    }
    
}
