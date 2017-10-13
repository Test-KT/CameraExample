package cameratool.lsl.com.cameraexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/5.
 */

public class CaptureView extends View {

    private Paint mPaint;
    private int w, h;

    private Rect mRect;


    public CaptureView(Context context) {
        this(context, null, 0);
    }

    public CaptureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);

    }

    public CaptureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(w / 2, h / 2);
        Rect rect = new Rect(-150, -150, 150, 150);
        canvas.drawRect(rect, mPaint);
        canvas.drawCircle(0, 0, 150, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                break;
        }
        return super.onTouchEvent(event);

    }
}

