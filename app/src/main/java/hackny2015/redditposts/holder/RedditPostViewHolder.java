package hackny2015.redditposts.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hackny2015.redditposts.R;

// This class holds the views in the recyclerview item layout and handles on clicks.
public class RedditPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title; // The TextView that holds the title of the reddit post
    public ImageView image; // The ImageView that holds the image of the reddit post
    public RedditPostViewHolderClick mListener; // Our on click listener for each reddit post

    public RedditPostViewHolder(View itemView, RedditPostViewHolderClick listener) {
        super(itemView);

        // Instantiates the views in the view holder
        title = (TextView) itemView.findViewById(R.id.post_title);
        image = (ImageView) itemView.findViewById(R.id.post_image);
        // Instantiate the listener
        mListener = listener;

        // Set the on click listener
        itemView.setOnClickListener(this);
    }

    // This method is called when a recyclerview item is clicked
    @Override
    public void onClick(View v) {
        mListener.onClick(v, getLayoutPosition());
    }

    // This is an interface that handles clicks
    public interface RedditPostViewHolderClick {
        void onClick(View v, int position);
    }
}