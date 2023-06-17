package at.moritzmusel.gwent.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.model.Ability;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.network.data.GameState;
import at.moritzmusel.gwent.ui.DragListener;
import at.moritzmusel.gwent.ui.GameViewActivity;

public class UserCardAdapter extends
        RecyclerView.Adapter<UserCardAdapter.ViewHolder> implements
        View.OnTouchListener {
    private static final String TAG = "GameViewActivity";
    private List<Card> list;
    private Context context;
    private GameState gameState;
    private Boolean isMyHand;
    private int rvHeight;

    public UserCardAdapter(List<Card> list, Boolean isMyHand, Context
            context, int rvHeight, GameState gameState) {
        this.list = list;
        this.context = context;
        this.isMyHand = isMyHand;
        this.gameState = gameState;
        this.rvHeight = rvHeight;
    }

    @NonNull
    @Override
    public UserCardAdapter.ViewHolder onCreateViewHolder(@NonNull
                                                                 ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.card_placeholder,
                parent, false);
        return new ViewHolder(listItem);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Card card = this.list.get(position);
        String cardNumber = card.getStrength()+"";
        holder.textView.setText(cardNumber);
        holder.frameLayout.setTag(position);
        String placeholder = "neutral_cow.jpg";
        try {
            String fileName = card.getType().toString().toLowerCase()
                    +"_"+card.getFilename().toLowerCase() + ".jpg";
            setImageFromAsset(context.getAssets().open(fileName),
                    holder.imageView);
        } catch (IOException e) {
            try {
                setImageFromAsset(context.getAssets().open(placeholder),
                        holder.imageView);
            } catch (IOException ex) {
                Log.e(TAG, ex.toString());
            }
        }

        if(card.getAbility() == Ability.DECOY || this.isMyHand == true ) {
            holder.frameLayout.performClick();
            holder.frameLayout.setOnTouchListener(this);
            try {
                holder.frameLayout.setOnDragListener(new DragListener(
                        this.gameState));
            } catch (JSONException e) {
                Log.e(TAG, e.toString());
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    /**
     * Card is currently randomly chosen.
     *
     * @param image
     */
    public void setImageFromAsset(InputStream imgStream, ImageView image)
    {
        Drawable d = Drawable.createFromStream(imgStream, null);
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        Drawable dr = new BitmapDrawable(context.getResources(),
                Bitmap.createScaledBitmap(bitmap, rvHeight-rvHeight/3, rvHeight, true));
        image.setImageDrawable(dr);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        view.performClick();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new
                    View.DragShadowBuilder(view);
            view.startDragAndDrop(data, shadowBuilder, view, 0);
            return true;
        }
        return false;
    }

    public DragListener getDragInstance() throws JSONException,
            IOException {
        return new DragListener( this.gameState);
    }

    public void updateList(List<Card> list) {
        this.list = list;
    }

    public List<Card> getList() {
        return this.list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        FrameLayout frameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView =
                    itemView.findViewById(R.id.card_placeholder_image);
            this.textView =
                    itemView.findViewById(R.id.card_placeholder_tv);
            this.frameLayout = itemView.findViewById(R.id.framelayout1);
        }
    }
}