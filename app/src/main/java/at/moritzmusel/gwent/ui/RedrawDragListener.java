package at.moritzmusel.gwent.ui;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;

public class RedrawDragListener implements View.OnDragListener {

    private boolean isDropped = false;

    /**
     * Constructor
     */
    public RedrawDragListener() {
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            isDropped = true;
            int positionTarget = -1;

            View viewSource = (View) event.getLocalState();
            int viewId = view.getId();

            if(viewId == R.id.btnRedraw){
                Toast.makeText(view.getContext(), "new Card should appear lol", Toast.LENGTH_SHORT).show();
            }

//            final int rvOne = R.id.recyclerViewCardLineOne;
//            final int rvTwo = R.id.recyclerViewCardLineTwo;
//            final int rvThree = R.id.recyclerViewCardLineThree;
//            final int rvFour = R.id.recyclerViewCardLineFour;
//            final int rvUser = R.id.recyclerViewUserCardStack;
//            RecyclerView target;
//            switch (viewId) {
//                case rvOne:
//                    target = view.getRootView().findViewById(rvOne);
//                    break;
//                case rvTwo:
//                    target = view.getRootView().findViewById(rvTwo);
//                    break;
//                case rvThree:
//                    target = view.getRootView().findViewById(rvThree);
//                    break;
//                case rvFour:
//                    target = view.getRootView().findViewById(rvFour);
//                    break;
//                case rvUser:
//                    target = view.getRootView().findViewById(rvUser);
//                    break;
//                default:
//                    target = (RecyclerView) view.getParent();
//                    positionTarget = (int) view.getTag();
//                    break;
//            }
//
//            if (viewSource != null) {
//                RecyclerView source = (RecyclerView) viewSource.getParent();
//
//                UserCardAdapter adapterSource = (UserCardAdapter) source.getAdapter();
//                int positionSource = (int) viewSource.getTag();
//
//                Card list = adapterSource.getList().get(positionSource);
//                List<Card> listSource = adapterSource.getList();
//
//                listSource.remove(positionSource);
//                adapterSource.updateList(listSource);
//                adapterSource.notifyDataSetChanged();
//
//                UserCardAdapter adapterTarget = (UserCardAdapter) target.getAdapter();
//                List<Card> customListTarget = adapterTarget.getList();
//
//                if (positionTarget >= 0) {
//                    customListTarget.add(positionTarget, list);
//                } else {
//                    customListTarget.add(list);
//                }
//                adapterTarget.updateList(customListTarget);
//                adapterTarget.notifyDataSetChanged();
//            }
//        }
//
//        if (!isDropped && event.getLocalState() != null) {
//            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
   }

}
