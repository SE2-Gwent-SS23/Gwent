package at.moritzmusel.gwent.ui;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import at.moritzmusel.gwent.network.data.GameState;

public class DoubleTapDetector implements View.OnTouchListener {

    private GestureDetector gestureDetector;
    private View clickedView;
    private GameState gameState;

    public DoubleTapDetector(Context context, GameState gameState) {
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.gameState = gameState;
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
            Toast.makeText(clickedView.getContext(), "Double-tap event detected", Toast.LENGTH_SHORT).show();
            int i = clickedView.getId();
            gameState.removeCardById(i);
            gameState.setCheated(true);
            GameViewActivity.gameStateUpdate.setValue(gameState);

            return true;
        }
    }
}