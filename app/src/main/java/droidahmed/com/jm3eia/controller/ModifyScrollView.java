package droidahmed.com.jm3eia.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by ahmed on 1/22/2017.
 */
public class ModifyScrollView extends ScrollView {

    public ModifyScrollView(Context context) {
        super(context);
    }
    public ModifyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ModifyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ModifyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    public boolean onInterceptTouchEvent (MotionEvent ev)
    {
        return false;
    }
}
