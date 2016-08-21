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

package soo.swallow.view.toggle;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;

/**
 * Android version of the spring looper that uses the most appropriate frame callback mechanism
 * available. It uses Android's {@link Choreographer} when available, otherwise it uses a
 * {@link Handler}.
 */
abstract class AndroidSpringLooperFactory {

  /**
   * Create an Android {@link com.facebook.rebound.SpringLooper} for the detected Android platform.
   * @return a SpringLooper
   */
  public static SpringLooper createSpringLooper() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      return ChoreographerAndroidSpringLooper.create();
    } else {
      return LegacyAndroidSpringLooper.create();
    }
  }

  /**
   * The base implementation of the Android spring looper, using a {@link Handler} for the
   * frame callbacks.
   */
  private static class LegacyAndroidSpringLooper extends SpringLooper {

    private final Handler mHandler;
    private final Runnable mLooperRunnable;
    private boolean mStarted;
    private long mLastTime;

    /**
     * @return an Android spring looper using a new {@link Handler} instance
     */
    public static SpringLooper create() {
      return new LegacyAndroidSpringLooper(new Handler());
    }

    public LegacyAndroidSpringLooper(Handler handler) {
      mHandler = handler;
      mLooperRunnable = new Runnable() {
        @Override
        public void run() {
          if (!mStarted || mSpringSystem == null) {
            return;
          }
          long currentTime = SystemClock.uptimeMillis();
          mSpringSystem.loop(currentTime - mLastTime);
          mHandler.post(mLooperRunnable);
        }
      };
    }

    @Override
    public void start() {
      if (mStarted) {
        return;
      }
      mStarted = true;
      mLastTime = SystemClock.uptimeMillis();
      mHandler.removeCallbacks(mLooperRunnable);
      mHandler.post(mLooperRunnable);
    }

    @Override
    public void stop() {
      mStarted = false;
      mHandler.removeCallbacks(mLooperRunnable);
    }
  }

  /**
   * The Jelly Bean and up implementation of the spring looper that uses Android's
   * {@link Choreographer} instead of a {@link Handler}
   */
  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  private static class ChoreographerAndroidSpringLooper extends SpringLooper {

    private final Choreographer mChoreographer;
    private final Choreographer.FrameCallback mFrameCallback;
    private boolean mStarted;
    private long mLastTime;

    /**
     * @return an Android spring choreographer using the system {@link Choreographer}
     */
    public static ChoreographerAndroidSpringLooper create() {
      return new ChoreographerAndroidSpringLooper(Choreographer.getInstance());
    }

    public ChoreographerAndroidSpringLooper(Choreographer choreographer) {
      mChoreographer = choreographer;
      mFrameCallback = new Choreographer.FrameCallback() {
        @Override
        public void doFrame(long frameTimeNanos) {
          if (!mStarted || mSpringSystem == null) {
            return;
          }
          long currentTime = SystemClock.uptimeMillis();
          mSpringSystem.loop(currentTime - mLastTime);
          mLastTime = currentTime;
          mChoreographer.postFrameCallback(mFrameCallback);
        }
      };
    }

    @Override
    public void start() {
      if (mStarted) {
        return;
      }
      mStarted = true;
      mLastTime = SystemClock.uptimeMillis();
      mChoreographer.removeFrameCallback(mFrameCallback);
      mChoreographer.postFrameCallback(mFrameCallback);
    }

    @Override
    public void stop() {
      mStarted = false;
      mChoreographer.removeFrameCallback(mFrameCallback);
    }
  }
}