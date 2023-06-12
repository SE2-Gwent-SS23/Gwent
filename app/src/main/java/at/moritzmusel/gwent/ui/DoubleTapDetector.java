package at.moritzmusel.gwent.ui;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DoubleTapDetector implements View.OnTouchListener {

    private GestureDetector gestureDetector;
    private View clickedView;

    public DoubleTapDetector(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        clickedView = view;
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            // This method will be called when a double tap is detected
            // TODO Handle the double tap event here
            Toast.makeText(clickedView.getContext(), "Double-tap event detected", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}