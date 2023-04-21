package at.moritzmusel.gwent.ui;

import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.model.Listener;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;
    private Listener listener;

    public DragListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            isDropped = true;
            int positionTarget = -1;

            View viewSource = (View) event.getLocalState();
            int viewId = view.getId();
            final int cvItem = R.id.framelayout1;
            //final int tvEmptyListTop = R.id.tvEmptyListStackLineOne;
            //final int tvEmptyListBottom = R.id.tvEmptyListUserStack;
            final int rvTop = R.id.recyclerViewCardLineOne;
            final int rvBottom = R.id.recyclerViewUserCardStack;

            switch (viewId) {
                case cvItem:
                    //case tvEmptyListTop:
                    //case tvEmptyListBottom:
                case rvTop:
                case rvBottom:

                    RecyclerView target;
                    switch (viewId) {
                        //case tvEmptyListTop:
                        case rvTop:
                            target = (RecyclerView) view.getRootView().findViewById(rvTop);
                            break;
                        //case tvEmptyListBottom:
                        case rvBottom:
                            target = (RecyclerView) view.getRootView().findViewById(rvBottom);
                            break;
                        default:
                            target = (RecyclerView) view.getParent();
                            //positionTarget = (int) view.getTag();
                            //positionTarget = 3;
                    }

                    if (viewSource != null) {
                        RecyclerView source = (RecyclerView) viewSource.getParent();

                        UserCardAdapter adapterSource = (UserCardAdapter) source.getAdapter();
                        int positionSource = (int) viewSource.getTag();
                        int sourceId = source.getId();

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

                        if (sourceId == rvBottom && adapterSource.getItemCount() < 1) {
                            listener.setEmptyListLineOne(true);
                        }
                        /*if (viewId == tvEmptyListBottom) {
                            listener.setEmptyListLineOne(false);
                        }*/
                        if (sourceId == rvTop && adapterSource.getItemCount() < 1) {
                            listener.setEmptyListUserStack(true);
                        }
                        /*if (viewId == tvEmptyListTop) {
                            listener.setEmptyListUserStack(false);
                        }*/
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
