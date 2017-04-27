package com.sml.t1r.taskapp.Helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.sml.t1r.taskapp.R;

public class ProgressButton extends AppCompatButton {
    private float percent;
    private int color ;
    private Context context;

    public ProgressButton(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        color = ContextCompat.getColor(context, R.color.green);
    }

    public void setPercent(float percent) {
        this.percent = percent;
        checkPercent();
        invalidate();
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable fill;
        fill = ContextCompat.getDrawable(context, R.drawable.abc_btn_default_mtrl_shape);

        fill.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        fill.setAlpha(128);
        fill.setBounds(0, 0, (int) (getWidth() * percent), getHeight());
        fill.draw(canvas);
        super.onDraw(canvas);
    }

    private void checkPercent(){
        percent = percent > 1 ? 1 : percent;
    }
}
