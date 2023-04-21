package at.moritzmusel.gwent.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.data.Card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Card> list = new ArrayList<>();
    private String msg;
    private android.widget.LinearLayout.LayoutParams layoutParams;
    private Context context;

    public CardAdapter(List<Card> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.card_placeholder, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        Card card = this.list.get(position);
        holder.textView.setText(this.list.get(position).getNumber());
        //holder.imageView.setImageResource(this.list.get(position).getImage());
        setImageFromAsset(holder.imageView);

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public FrameLayout frameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.card_placeholder_image);
            this.textView = (TextView) itemView.findViewById(R.id.card_placeholder_tv);
            frameLayout = (FrameLayout)itemView.findViewById(R.id.framelayout1);
        }
    }
}
