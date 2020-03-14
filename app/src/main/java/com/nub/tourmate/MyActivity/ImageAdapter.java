package com.nub.tourmate.MyActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nub.tourmate.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolders>
                        //extends RecyclerView.Adapter<ImageAdapter.ViewHolders>
{
    String data[], data2[];
    int images [];
    public Context mContext;

    public ImageAdapter(String[] data, String[] data2, int[] images, Context mContext) {
        this.data = data;
        this.data2 = data2;
        this.images = images;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_show, viewGroup, false);

        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolders viewHolders, final int i) {
        viewHolders.imageView.setImageResource(images[i]);
        viewHolders.title.setText(data[i]);
        viewHolders.description.setText(data2[i]);
        viewHolders.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("data",data[i]);
                intent.putExtra("data2",data2[i]);
                intent.putExtra("image",images[i]);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title,description;
        public ConstraintLayout constraintLayout;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            description = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            constraintLayout = itemView.findViewById(R.id.imageShow);
        }
    }
}
