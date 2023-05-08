package at.moritzmusel.gwent.ui;

import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import at.moritzmusel.gwent.R;

public class RedrawDragListener implements View.OnDragListener {

    TextView redrawDrop;
    RedrawActivity mRedrawActivity;
    int cnt = 0;

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
        if (view.getId()== R.id.txtRedrawDrop) {
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                        //redrawDrop.setText(cnt + "/3");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                        //redrawDrop.setText("Hier ablegen");
                    break;
                case DragEvent.ACTION_DROP:
                        //redrawDrop.setText("dropped");
//                        if (cnt <= 2){
                            mRedrawActivity.replaceCard((View) event.getLocalState());
//                            cnt++;
//                            mRedrawActivity.setRedrawCountView(cnt);
//                        }if (cnt == 3){
//                            redrawDrop.setText("Bitte auf Fertig tippen");
//                        }
                    //Toast.makeText(view.getContext(), "new card should appear", Toast.LENGTH_SHORT).show();
                    break;
            }
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
