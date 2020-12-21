package com.shiningmind.eventhandler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MotionEvent extends Fragment {

    protected TextView textView1;
    protected TextView textView2;
    public MotionEvent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motion_event, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView1 = view.findViewById(R.id.textViewMotion1);
        textView2 = view.findViewById(R.id.textViewMotion2);
        LinearLayout layout = view.findViewById(R.id.fragment_motion_event);
        layout.setOnTouchListener(new LinearLayout.OnTouchListener() {
            @Override
            public boolean onTouch(View v, android.view.MotionEvent event) {
                handleTouch(event);
                return true;
            }
        });
    }

    protected void handleTouch(android.view.MotionEvent event) {
        int pointerCount = event.getPointerCount();
        if (pointerCount == 1) {
            textView2.setText(R.string.placeholder);
        }
        for (int i = 0; i < pointerCount; i++) {
            int x = (int) event.getX(i);
            int y = (int) event.getY(i);
            int id = event.getPointerId(i);
            int action = event.getActionMasked();
            int actionIndex = event.getActionIndex();
            String actionString;
            switch (action) {
                case android.view.MotionEvent.ACTION_DOWN:
                    actionString = "DOWN";
                    break;
                case android.view.MotionEvent.ACTION_UP:
                    actionString = "UP";
                    break;
                case android.view.MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "PNTR DOWN";
                    break;
                case android.view.MotionEvent.ACTION_POINTER_UP:
                    actionString = "PNTR UP";
                    break;
                case android.view.MotionEvent.ACTION_MOVE:
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }

            String touchStatus = "Action: " + actionString + " Index: " +
                    actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
            if (id == 0)
                textView1.setText(touchStatus);
            else
                textView2.setText(touchStatus);
        }
    }
}