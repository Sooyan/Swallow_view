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

package soo.swallow.view.ptz;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2014/11/7  14:21.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2014/11/7        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */

import android.content.res.TypedArray;
import android.view.View;

public interface IPullToZoom<T extends View> {
    /**
     * Get the Wrapped Zoom View. Anything returned here has already been
     * added to the content view.
     *
     * @return The View which is currently wrapped
     */
    public View getZoomView();

    public View getHeaderView();

    /**
     * Get the Wrapped root View.
     *
     * @return The View which is currently wrapped
     */
    public T getPullRootView();

    /**
     * Whether Pull-to-Refresh is enabled
     *
     * @return enabled
     */
    public boolean isPullToZoomEnabled();

    /**
     * Returns whether the Widget is currently in the Zooming state
     *
     * @return true if the Widget is currently zooming
     */
    public boolean isZooming();

    /**
     * Returns whether the Widget is currently in the Zooming anim type
     *
     * @return true if the anim is parallax
     */
    public boolean isParallax();

    public boolean isHideHeader();

    public void handleStyledAttributes(TypedArray a);
}
