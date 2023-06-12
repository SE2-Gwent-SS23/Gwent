package at.moritzmusel.gwent.ui;

import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import at.moritzmusel.gwent.R;

public class RedrawDragListener implements View.OnDragListener {

    TextView redrawDrop;
    RedrawActivity mRedrawActivity;

    public RedrawDragListener(RedrawActivity activity, TextView target) {
        mRedrawActivity = activity;
        redrawDrop = target;
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {

        int dragEvent = event.getAction();
        if (view.getId()== R.id.txtRedrawDrop) {
            switch (dragEvent) {
                case DragEvent.ACTION_DROP:
                    mRedrawActivity.replaceCard((View) event.getLocalState()); break;
                default: break;
            }
        }
        return true;
   }
}
