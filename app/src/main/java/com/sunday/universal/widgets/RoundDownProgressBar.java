package com.sunday.universal.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.taole.sunday.app.R;


/**
 * 
 * Class Name: RoundDownProgressBar<br/>
 * 
 * @author FU ZHIXUE<br/>
 *         Description: 此圆形进度条用于下载图片等显示进度百分比或不显示百分比,可直接在线程中更新进度<br/>
 *         Copy Rights: FU ZHIXUE<br/>
 *         Created on : 2013-11-13 下午2:43:30<br/>
 *         Comments:<br/>
 */
public class RoundDownProgressBar extends View
{
    /**
     * 画笔对象的引用
     */
    private Paint paint;

    /**
     * 圆环的颜色
     */
    private int roundColor;

    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;

    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;

    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;

    /**
     * 圆环的宽度
     */
    private float roundWidth;

    /**
     * 最大进度
     */
    private int max;

    /**
     * 当前进度
     */
    private int progress;
    /**
     * 是否显示中间的进度
     */
    private boolean textIsDisplayable;

    /**
     * 进度的风格，实心或者空心
     */
    private int style;

    public static final int STROKE = 0;
    public static final int FILL = 1;

    /**
     * 绘制圆形的起点（默认为-90度即12点钟方向）
     */
    private float roundStartAngle = -90;

    public RoundDownProgressBar(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub

        paint = new Paint();
    }

    public RoundDownProgressBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        paint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);

        // 获取自定义属性和默认值
        roundColor = mTypedArray.getColor(
                R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(
                R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(
                R.styleable.RoundProgressBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(
                R.styleable.RoundProgressBar_textSize, 15);
        roundWidth = mTypedArray.getDimension(
                R.styleable.RoundProgressBar_roundWidth, 3);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_maxProgress,
                100);
        textIsDisplayable = mTypedArray.getBoolean(
                R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

        roundStartAngle = mTypedArray.getFloat(
                R.styleable.RoundProgressBar_roundStartAngle, -90);

        mTypedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas)
    {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2); // 圆环的半径
        paint.setColor(roundColor); // 设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); // 设置空心
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setAntiAlias(true); // 消除锯齿
        canvas.drawCircle(centre, centre, radius, paint); // 画出圆环

        // Log.e("log", centre + "");

        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置字体
        int percent = (int) (((float) progress / (float) max) * 100); // 中间的进度百分比，先转换成float在进行除法运算，不然都为0
        float textWidth = paint.measureText(percent + "%"); // 测量字体宽度，我们需要根据字体的宽度设置在圆环中间

        if (textIsDisplayable && percent != 0 && style == STROKE)
        {
            canvas.drawText(percent + "%", centre - textWidth / 2, centre
                    + textSize / 2, paint); // 画出进度百分比
        }

        /**
         * 画圆弧 ，画圆环的进度
         */

        // 设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setColor(roundProgressColor); // 设置进度的颜色
        RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

        // 计算圆弧度数
        float rate = (float) progress / max;
        float sweep = 360 * rate;

        switch (style)
        {
            case STROKE:
            {
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, roundStartAngle, sweep, false, paint); // 根据进度画圆弧
                break;
            }
            case FILL:
            {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0)
                    canvas.drawArc(oval, roundStartAngle, sweep, true, paint); // 根据进度画圆弧
                break;
            }
        }

    }

    /**
     * 
     * Methods Name: setMax<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:设置进度的最大值<br/>
     *         Comments:<br/>
     */
    public synchronized int getMaxProgress()
    {
        return max;
    }

    /**
     * 
     * Methods Name: setMax<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:设置进度的最大值<br/>
     * @param max
     * <br/>
     *            Comments:<br/>
     */
    public synchronized void setMaxProgress(int max)
    {
        if (max <= 0)
        {
            max = 0;
        }
        this.max = max;
    }

    /**
     * 
     * Methods Name: getProgress<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:获取进度.需要同步<br/>
     * @return<br/> Comments:<br/>
     */
    public synchronized int getProgress()
    {
        return progress;
    }

    /**
     * 
     * Methods Name: setProgress<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     *         刷新界面调用postInvalidate()能在非UI线程刷新<br/>
     * @param progress
     * <br/>
     *            Comments:<br/>
     */
    public synchronized void setProgress(int progress)
    {
        if (progress <= 0)
        {
            progress = 0;
        }

        if (progress >= max)
        {
            progress = max;
        }

        this.progress = progress;
        postInvalidate();

    }

    /**
     * 
     * Methods Name: getCricleColor<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:圆环的颜色<br/>
     *         Comments:<br/>
     */
    public int getCricleColor()
    {
        return roundColor;
    }

    /**
     * 
     * Methods Name: setCricleColor<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:圆环的颜色<br/>
     * @param cricleColor
     * <br/>
     *            Comments:<br/>
     */
    public void setCricleColor(int cricleColor)
    {
        this.roundColor = cricleColor;
    }

    /**
     * 
     * Methods Name: getCricleProgressColor<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:圆环进度的颜色<br/>
     *         Comments:<br/>
     */
    public int getCricleProgressColor()
    {
        return roundProgressColor;
    }

    /**
     * 
     * Methods Name: setCricleProgressColor<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:圆环进度的颜色<br/>
     * @param cricleProgressColor
     * <br/>
     *            Comments:<br/>
     */
    public void setCricleProgressColor(int cricleProgressColor)
    {
        this.roundProgressColor = cricleProgressColor;
    }

    /**
     * 
     * Methods Name: getTextColor<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:中间进度百分比的字符串的颜色<br/>
     *         Comments:<br/>
     */
    public int getTextColor()
    {
        return textColor;
    }

    /**
     * 
     * Methods Name: setTextColor<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:中间进度百分比的字符串的颜色<br/>
     * @param textColor
     * <br/>
     *            Comments:<br/>
     */
    public void setTextColor(int textColor)
    {
        this.textColor = textColor;
    }

    /**
     * 
     * Methods Name: getTextSize<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:中间进度百分比的字符串的字体<br/>
     *         Comments:<br/>
     */
    public float getTextSize()
    {
        return textSize;
    }

    /**
     * 
     * Methods Name: setTextSize<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:中间进度百分比的字符串的字体<br/>
     * @param textSize
     * <br/>
     *            Comments:<br/>
     */
    public void setTextSize(float textSize)
    {
        this.textSize = textSize;
    }

    /**
     * 
     * Methods Name: getRoundWidth<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:圆环的宽度<br/>
     * @return<br/> Comments:<br/>
     */
    public float getRoundWidth()
    {
        return roundWidth;
    }

    /**
     * 
     * Methods Name: setRoundWidth<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:设置圆环的宽度<br/>
     * @param roundWidth
     * <br/>
     *            Comments:<br/>
     */
    public void setRoundWidth(float roundWidth)
    {
        this.roundWidth = roundWidth;
    }

    /**
     * 
     * Methods Name: setRoundStartAngle<br/>
     * 
     * @author FU ZHIXUE<br/>
     *         Description:绘制圆形的起点（默认为-90度即12点钟方向）<br/>
     * @param roundStartAngle
     * <br/>
     *            Comments:<br/>
     */
    public void setRoundStartAngle(float roundStartAngle)
    {
        this.roundStartAngle = roundStartAngle;
    }

}
