/*
 * Copyright (C) 2023-2024 The risingOS Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.haloui.plana.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.widget.ImageView

class WallpaperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    private val handler = Handler(Looper.getMainLooper())
    private var currentWallpaperDrawable: Drawable? = null

    private val wallpaperChecker = object : Runnable {
        override fun run() {
            updateWallpaper()
            handler.postDelayed(this, REFRESH_INTERVAL_MS)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        handler.post(wallpaperChecker)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(wallpaperChecker)
    }

    private fun updateWallpaper() {
        val wallpaperDrawable: Drawable? = try {
            WallpaperManager.getInstance(context).drawable
        } catch (e: SecurityException) {
            null
        } catch (e: RuntimeException) {
            null
        }
        if (wallpaperDrawable != currentWallpaperDrawable) {
            currentWallpaperDrawable = wallpaperDrawable
            wallpaperDrawable?.let { setImageDrawable(it) }
        }
    }

    companion object {
        private const val REFRESH_INTERVAL_MS = 2000L
    }
}
