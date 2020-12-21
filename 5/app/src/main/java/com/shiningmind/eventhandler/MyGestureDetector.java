package com.shiningmind.eventhandler;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyGestureDetector implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    protected TextView info;
    public MyGestureDetector(TextView textView) {
        info = textView;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        info.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        info.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        info.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        info.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        info.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        info.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        info.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        info.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        info.setText("onFling");
        return true;
    }
}
