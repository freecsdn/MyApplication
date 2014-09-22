package com.taole.universalimageloader.core;

public class MyImageLoader extends ImageLoader {

    private volatile static MyImageLoader instance;

    /** Returns singletone class instance */
    public static MyImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new MyImageLoader();
                }
            }
        }
        return instance;
    }
}
