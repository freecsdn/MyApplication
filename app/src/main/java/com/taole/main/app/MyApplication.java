package com.taole.main.app;

import android.app.Application;
import android.content.Context;

import com.taole.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.taole.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.taole.universalimageloader.core.ImageLoader;
import com.taole.universalimageloader.core.ImageLoaderConfiguration;
import com.taole.universalimageloader.core.assist.QueueProcessingType;


/**
 * Created by Administrator on 2014/8/21.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(3 * 1024 * 1024))
                .memoryCacheSize(3 * 1024 * 1024)
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

    }
}
