package at.moritzmusel.gwent.ui;

import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import at.moritzmusel.gwent.R;

public class RedrawDragListener implements View.OnDragListener {

    TextView redrawDrop;
    RedrawActivity mRedrawActivity;

    /**
     * Constructor
     */
    public RedrawDragListener(RedrawActivity activity, TextView target) {
        mRedrawActivity = activity;
        redrawDrop = target;
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {

        int dragEvent = event.getAction();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                        //redrawDrop.setText(cnt + "/3");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                        //redrawDrop.setText("Hier ablegen");
                    break;
                case DragEvent.ACTION_DROP:
                     if (view.getId()== R.id.txtRedrawDrop) {
                            mRedrawActivity.replaceCard((View) event.getLocalState());
                        }
                    //Toast.makeText(view.getContext(), "new card should appear", Toast.LENGTH_SHORT).show();
                    break;
            }
        return true;
   }
}
