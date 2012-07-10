package com.viva.mypad.Gesture;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener
{
    public static enum Action
    {
        LR, // Left to Right
        RL, // Right to Left
        TB, // Top to bottom
        BT, // Bottom to Top
        None // when no action was detected
    }

    private static final String logTag = "SwipeDetector";
    private static final int HORIZONTAL_MIN_DISTANCE = 100; 
    private float downX, upX;
    private Action mSwipeDetected = Action.None;

    public boolean swipeDetected()
    {
        return mSwipeDetected != Action.None;
    }

    public Action getAction()
    {
        return mSwipeDetected;
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                downX = event.getX();
                mSwipeDetected = Action.None;
                return false; // allow other events like Click to be processed
            }

            case MotionEvent.ACTION_MOVE:
            {
                upX = event.getX();
                float deltaX = downX - upX;

                // horizontal swipe detection
                if (Math.abs(deltaX) > HORIZONTAL_MIN_DISTANCE)
                {
                    // left or right
                    if (deltaX < 0)
                    {
                        Log.i(logTag, "Swipe Left to Right");
                        mSwipeDetected = Action.LR;
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }
}