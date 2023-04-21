package at.moritzmusel.gwent.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.model.Listener;
import at.moritzmusel.gwent.ui.DragListener;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> implements View.OnTouchListener {

    public View.OnDragListener dragInstance;
    private List<Card> list;
    private String msg;
    private android.widget.LinearLayout.LayoutParams layoutParams;
    private Context context;
    private Listener listener;

    public UserCardAdapter(List<Card> list, Context context, Listener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.card_placeholder, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardAdapter.ViewHolder holder, int position) {
        Card card = this.list.get(position);
        holder.textView.setText(this.list.get(position).getNumber());
        holder.frameLayout.setTag(position);
        //holder.imageView.setImageResource(this.list.get(position).getImage());
        setImageFromAsset(holder.imageView);
        holder.frameLayout.setOnTouchListener(this);
        holder.frameLayout.setOnDragListener(new DragListener(listener));
    }

    /**
     * Card is currently randomly chosen.
     *
     * @param image
     */
    public void setImageFromAsset(ImageView image) {

        AssetManager manager = context.getAssets();
        try {
            String[] file = manager.list("");
            int fileSize = file.length;
            int i = ThreadLocalRandom.current().nextInt(0, fileSize + 1);
            try {
                InputStream imgStream;
                imgStream = manager.open(file[i]);
                Drawable d = Drawable.createFromStream(imgStream, null);
                Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
                Drawable dr = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 150, 200, true));
                image.setImageDrawable(dr);
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(data, shadowBuilder, view, 0);
            } else {
                view.startDrag(data, shadowBuilder, view, 0);
            }
            return true;
        }
        return false;
    }

    public DragListener getDragInstance() {
        if (listener != null) {
            return new DragListener(listener);
        } else {
            Log.e("ListAdapter", "Listener wasn't initialized!");
            return null;
        }
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
            this.imageView = (ImageView) itemView.findViewById(R.id.card_placeholder_image);
            this.textView = (TextView) itemView.findViewById(R.id.card_placeholder_tv);
            this.frameLayout = (FrameLayout)itemView.findViewById(R.id.framelayout1);
        }
    }
}
