package at.moritzmusel.gwent.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.res.AssetManager;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.ui.DragListener;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> implements View.OnTouchListener {

    private List<Card> list;
    private Context context;

    public UserCardAdapter(List<Card> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.card_placeholder, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardAdapter.ViewHolder holder, int position) {
        Card card = this.list.get(position);
        String cardNumber = card.getNumber().toString();
        holder.textView.setText(cardNumber);
        holder.frameLayout.setTag(position);
        setImageFromAsset(card.getImage(), holder.imageView);
        if(card.isDecoyCard()) {
            holder.frameLayout.setOnTouchListener(this);
            holder.frameLayout.setOnDragListener(new DragListener());
            // calculate points ...
        } else if(card.isUserCard()) {
            holder.frameLayout.setOnTouchListener(this);
            holder.frameLayout.setOnDragListener(new DragListener());
        }
    }

    /**
     * Card is currently randomly chosen.
     *
     * @param cardImageId
     * @param image
     */
    public void setImageFromAsset(Integer cardImageId, ImageView image) {
        AssetManager manager = context.getAssets();
        try {
            String[] file = manager.list("");
            setImageWithInputStream(cardImageId, image, manager, file);
        } catch (IOException e) {
            Log.e("Error", e.getLocalizedMessage());
        }
    }

    private void setImageWithInputStream(Integer cardImageId, ImageView image, AssetManager manager, String [] file) {
        InputStream imgStream;
        try {
            imgStream = manager.open(file[cardImageId]);
            Drawable d = Drawable.createFromStream(imgStream, null);
            Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
            Drawable dr = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 150, 200, true));
            image.setImageDrawable(dr);
        } catch (IOException e) {
            Log.e("Error", e.getLocalizedMessage());
        }
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
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, shadowBuilder, view, 0);
            return true;
        }
        return false;
    }

    public DragListener getDragInstance() {
         return new DragListener();
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
            this.imageView = itemView.findViewById(R.id.card_placeholder_image);
            this.textView = itemView.findViewById(R.id.card_placeholder_tv);
            this.frameLayout = itemView.findViewById(R.id.framelayout1);
        }
    }
}
