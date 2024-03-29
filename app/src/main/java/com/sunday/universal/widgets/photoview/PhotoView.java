/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.sunday.universal.widgets.photoview;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * 
 * Class Name: PhotoView<br/>
 * 
 * @author FU ZHIXUE<br/>
 *         Description: PhotoView是一个ImageView，可以布局文件中直接使用<br/>
 *         Copy Rights: FU ZHIXUE<br/>
 *         Created on : 2014-4-3 下午5:58:59<br/>
 *         Comments:<br/>
 */
public class PhotoView extends ImageView implements IPhotoView
{

    private final PhotoViewAttacher mAttacher;

    private ScaleType mPendingScaleType;

    public PhotoView(Context context)
    {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attr)
    {
        this(context, attr, 0);
    }

    public PhotoView(Context context, AttributeSet attr, int defStyle)
    {
        super(context, attr, defStyle);
        super.setScaleType(ScaleType.MATRIX);
        mAttacher = new PhotoViewAttacher(this);

        if (null != mPendingScaleType)
        {
            setScaleType(mPendingScaleType);
            mPendingScaleType = null;
        }
    }

    @Override
    public boolean canZoom()
    {
        return mAttacher.canZoom();
    }

    @Override
    public RectF getDisplayRect()
    {
        return mAttacher.getDisplayRect();
    }

    @Override
    public float getMinScale()
    {
        return mAttacher.getMinScale();
    }

    @Override
    public float getMidScale()
    {
        return mAttacher.getMidScale();
    }

    @Override
    public float getMaxScale()
    {
        return mAttacher.getMaxScale();
    }

    @Override
    public float getScale()
    {
        return mAttacher.getScale();
    }

    @Override
    public ScaleType getScaleType()
    {
        return mAttacher.getScaleType();
    }

    @Override
    public void setAllowParentInterceptOnEdge(boolean allow)
    {
        mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    @Override
    public void setMinScale(float minScale)
    {
        mAttacher.setMinScale(minScale);
    }

    @Override
    public void setMidScale(float midScale)
    {
        mAttacher.setMidScale(midScale);
    }

    @Override
    public void setMaxScale(float maxScale)
    {
        mAttacher.setMaxScale(maxScale);
    }

    @Override
    public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener listener) {

    }

    @Override
    public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener listener) {

    }

    @Override
    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener listener) {

    }

    @Override
    // setImageBitmap calls through to this method
    public void setImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
        if (null != mAttacher)
        {
            mAttacher.update();
        }
    }

    @Override
    public void setImageResource(int resId)
    {
        super.setImageResource(resId);
        if (null != mAttacher)
        {
            mAttacher.update();
        }
    }

    @Override
    public void setImageURI(Uri uri)
    {
        super.setImageURI(uri);
        if (null != mAttacher)
        {
            mAttacher.update();
        }
    }


    @Override
    public void setScaleType(ScaleType scaleType)
    {
        if (null != mAttacher)
        {
            mAttacher.setScaleType(scaleType);
        } else
        {
            mPendingScaleType = scaleType;
        }
    }

    @Override
    public void setZoomable(boolean zoomable)
    {
        mAttacher.setZoomable(zoomable);
    }

    @Override
    public void zoomTo(float scale, float focalX, float focalY)
    {
        mAttacher.zoomTo(scale, focalX, focalY);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        mAttacher.cleanup();
        super.onDetachedFromWindow();
    }


}