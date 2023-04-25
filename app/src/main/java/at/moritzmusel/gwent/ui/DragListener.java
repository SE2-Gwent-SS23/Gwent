package at.moritzmusel.gwent.ui;

import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;

    public DragListener() {
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            isDropped = true;
            int positionTarget = -1;

            View viewSource = (View) event.getLocalState();
            int viewId = view.getId();
            final int cvItem = R.id.framelayout1;
            final int rvOne = R.id.recyclerViewCardLineOne;
            final int rvTwo = R.id.recyclerViewCardLineTwo;
            final int rvThree = R.id.recyclerViewCardLineThree;
            final int rvFour = R.id.recyclerViewCardLineFour;
            final int rvUser = R.id.recyclerViewUserCardStack;

            switch (viewId) {
                case cvItem:
                case rvOne:
                case rvTwo:
                case rvThree:
                case rvFour:
                case rvUser:
                    RecyclerView target;
                    switch (viewId) {
                        case rvOne:
                            target = view.getRootView().findViewById(rvOne);
                            break;
                        case rvTwo:
                            target = view.getRootView().findViewById(rvTwo);
                            break;
                        case rvThree:
                            target = view.getRootView().findViewById(rvThree);
                            break;
                        case rvFour:
                            target = view.getRootView().findViewById(rvFour);
                            break;
                        case rvUser:
                            target = view.getRootView().findViewById(rvUser);
                            break;
                        default:
                            target = (RecyclerView) view.getParent();
                            positionTarget = (int) view.getTag();
                    }

                    if (viewSource != null) {
                        RecyclerView source = (RecyclerView) viewSource.getParent();

                        UserCardAdapter adapterSource = (UserCardAdapter) source.getAdapter();
                        int positionSource = (int) viewSource.getTag();

                        Card list = adapterSource.getList().get(positionSource);
                        List<Card> listSource = adapterSource.getList();

                        listSource.remove(positionSource);
                        adapterSource.updateList(listSource);
                        adapterSource.notifyDataSetChanged();

                        UserCardAdapter adapterTarget = (UserCardAdapter) target.getAdapter();
                        List<Card> customListTarget = adapterTarget.getList();
                        if (positionTarget >= 0) {
                            customListTarget.add(positionTarget, list);
                        } else {
                            customListTarget.add(list);
                        }
                        adapterTarget.updateList(customListTarget);
                        adapterTarget.notifyDataSetChanged();
                    }
                    break;
            }
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}
