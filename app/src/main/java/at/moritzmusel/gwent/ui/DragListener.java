package at.moritzmusel.gwent.ui;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.network.data.GameState;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;
    private GameState gameState;
    private Context context;

    private int positionTarget;

    /**
     * Constructor
     */
    public DragListener(Context context, GameState gameState) throws JSONException, IOException {
        this.context = context;
        this.gameState = gameState;
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            isDropped = true;
            positionTarget = -1;
            View viewSource = (View) event.getLocalState();
            RecyclerView target = chooseTargetRV(view);
            doActionOnDragAdapterTarget(viewSource, target);
            GameViewActivity.updateUI();
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }

    private RecyclerView chooseTargetRV(View view) {
        RecyclerView target;
        int viewId = view.getId();
        final int rvOpponentOne = R.id.recyclerViewCardOpponentLaneOne;
        final int rvOpponentTwo = R.id.recyclerViewCardOpponentLaneTwo;
        final int rvUserOne = R.id.recyclerViewCardUserLaneOne;
        final int rvUserTwo = R.id.recyclerViewCardUserLaneTwo;
        final int rvUser = R.id.recyclerViewUserCardStack;
        switch (viewId) {
            case rvOpponentOne:
            case rvOpponentTwo:
                positionTarget = 1;
                target = view.getRootView().findViewById(rvUser);
                break;
            case rvUserOne:
                target = view.getRootView().findViewById(rvUserOne);
                break;
            case rvUserTwo:
                target = view.getRootView().findViewById(rvUserTwo);
                break;
            case rvUser:
                target = view.getRootView().findViewById(rvUser);
                break;
            default:
                target = view.getRootView().findViewById(rvUser);
                positionTarget = 1;
                break;
        }
        return target;
    }

    private void doActionOnDragAdapterTarget(View viewSource, RecyclerView target){
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
                customListTarget.add(positionSource, list);
            } else {
                customListTarget.add(list);
            }
            adapterTarget.updateList(customListTarget);
            adapterTarget.notifyDataSetChanged();

            // this.gwentViewModel.play(gameSate); GameState schicken
        }
    }
}
