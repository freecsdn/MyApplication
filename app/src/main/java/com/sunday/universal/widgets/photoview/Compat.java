package com.sunday.universal.widgets.photoview;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;

public class Compat
{
    private static final int SIXTY_FPS_INTERVAL = 1000 / 60;

    public static void postOnAnimation(View view, Runnable runnable)
    {
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN)
        {
            postOnAnimation_sdk16(view, runnable);
        } else
        {
            view.postDelayed(runnable, SIXTY_FPS_INTERVAL);
        }
    }

    @TargetApi(16)
    private static void postOnAnimation_sdk16(View view, Runnable r)
    {
        view.postOnAnimation(r);
    }

}
