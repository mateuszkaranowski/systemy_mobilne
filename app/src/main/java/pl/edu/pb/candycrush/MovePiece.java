package pl.edu.pb.candycrush;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MovePiece implements View.OnTouchListener{
    public GestureDetector gestureDetector;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    public MovePiece(Context context)
    {
        gestureDetector = new GestureDetector(context, new MoveListener());
    }
    private final class  MoveListener extends GestureDetector.SimpleOnGestureListener
    {
        public static final int SWIPE_THRESOLD =100;
        public static final int SWIPE_VELOCITY_THRESOLD =100;

        @Override
        public boolean onDown(MotionEvent e) {
            //return super.onDown(e);
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            boolean result = false;
            float yDiff = e2.getY() - e1.getY();
            float xDiff = e2.getX() - e1.getX();

            if(Math.abs(xDiff) > Math.abs(yDiff))
            {
                if(Math.abs(xDiff) > SWIPE_THRESOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESOLD)
                {
                    if(xDiff > 0)
                    {
                        onSwipeRight();
                    }
                    else
                    {
                        onSwipeLeft();
                    }
                    result = true;
                }
            }
            else if(Math.abs(yDiff) > SWIPE_THRESOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESOLD)
            {
                if(yDiff > 0)
                {
                    onSwipeBottom();
                }
                else
                    {
                        onSwipeTop();
                    }
                    result = true;
            }
            return result;
            //return super.onFling(e1, e2,velocityX,velocityY);
        }


    }
    void onSwipeLeft(){}
    void onSwipeRight(){}
    void onSwipeTop(){}
    void onSwipeBottom(){}

}
