package com.rnawesomeproject.react.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Created by liumeng on 16/8/23.
 */
public class RnLabelTextView extends TextView {
    private static final int LEFT_TOP = 1;
    private static final int RIGHT_TOP = 2;
    private static final int LEFT_BOTTOM = 3;
    private static final int RIGHT_BOTTOM = 4;

    public RnLabelTextView(Context context) {
        super(context);
        init();
    }

    public RnLabelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RnLabelTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {

    }

    public static class RnLabelTextViewManager extends SimpleViewManager<TextView> {

        // React-Native官方大多数自定义View都是用RCT开头，这里保持规范性
        private static final String REACT_CLASS = "RCTRnLabelTextView";

        @Override
        public String getName() {
            return REACT_CLASS;
        }

        @Override
        protected RnLabelTextView createViewInstance(ThemedReactContext reactContext) {
            return new RnLabelTextView(reactContext);
        }

        @ReactProp(name = "labelText", customType = "String")
        public void setLabelText(RnLabelTextView view, String text) {
            //view.setLabelText(text);
        }

        @ReactProp(name = "labelBackgroundColor")
        public void setLabelBackgroundColor(RnLabelTextView view, String colorString) {
            //view.setLabelBackgroundColor(Color.parseColor(colorString));
        }

        @ReactProp(name = "labelDistance")
        public void setLabelDistance(RnLabelTextView view, int distance) {
            //view.setLabelDistance(distance);
        }

        @ReactProp(name = "labelTextSize")
        public void setLabelTextSize(RnLabelTextView view, int size) {
            //view.setLabelTextSize(ViewHelper.sp2px(view.getContext(), size));
        }
    }
}
