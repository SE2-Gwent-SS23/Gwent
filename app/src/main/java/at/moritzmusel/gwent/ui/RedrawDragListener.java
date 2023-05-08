package at.moritzmusel.gwent.ui;

import android.view.DragEvent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import at.moritzmusel.gwent.R;

public class RedrawDragListener implements View.OnDragListener {

    TextView redraw_Drop;
    private boolean isDropped = false;

    /**
     * Constructor
     */
    public RedrawDragListener() {
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {


        //int redrawDrop = R.id.txt_Redraw_Drop;

        int dragEvent = event.getAction();

        switch (dragEvent){
            case DragEvent.ACTION_DRAG_ENTERED:
                redraw_Drop.setText("hi");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                isDropped = true;
                final View v = (View) event.getLocalState();

                if (v.getId()==R.id.txt_Redraw_Drop){
                    redraw_Drop.setText("hi");
                }

                break;

        }

//        if (event.getAction() == DragEvent.ACTION_DROP) {
//            isDropped = true;
//            int positionTarget = -1;
//
//            View viewSource = (View) event.getLocalState();
//            int viewId = view.getId();
//
//            if(viewId == R.id.txt_Redraw_Drop) {
//                Toast.makeText(view.getContext(), "new Card should appear lol", Toast.LENGTH_SHORT).show();
//            }
//        }
        return true;
   }

}
