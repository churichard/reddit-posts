package hackny2015.redditposts.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import hackny2015.redditposts.R;
import hackny2015.redditposts.activity.PostActivity;
import hackny2015.redditposts.holder.RedditPostViewHolder;
import hackny2015.redditposts.model.RedditPost;

// This adapter essentially populates the RecyclerView with data.
public class RedditPostAdapter extends RecyclerView.Adapter<RedditPostViewHolder> {

    private RedditPost[] redditPosts; // This array holds all of the reddit posts
    private Activity activity;

    // Constructor that takes in an array of reddit posts
    public RedditPostAdapter(RedditPost[] posts, Activity activity) {
        redditPosts = posts;
        this.activity = activity;
    }

    // Default constructor
    public RedditPostAdapter() {
        redditPosts = null;
    }

    // This method is called when the view holder is being created; essentially, this method
    // inflates the list_reddit_posts layout file
    @Override
    public RedditPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // The layout file "list_reddit_posts" is inflated; this means that the views inside the file
        // are being populated into the view v
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reddit_posts, parent, false);

        // Return a reddit post view holder, passing in the view that was just created and an
        // implementation of RedditPostViewHolderClick
        return new RedditPostViewHolder(v, new RedditPostViewHolder.RedditPostViewHolderClick() {
            @Override
            public void onClick(View v, int position) {
                // To start a new activity you need an intent
                Intent intent = new Intent(activity, PostActivity.class);
                // You can attach extras, or variables, to intents
                intent.putExtra("Post url", redditPosts[position].getUrl());
                // You then start the new activity by calling this method
                activity.startActivity(intent);
            }
        });
    }

    // This method binds data to each of the views inside the viewholder
    @Override
    public void onBindViewHolder(RedditPostViewHolder holder, int position) {
        RedditPost post = redditPosts[position];
        holder.title.setText(post.getTitle());
        Glide.with(activity).load(post.getImgUrl()).override(100, 100).placeholder(R.drawable.no_image_available).into(holder.image);
    }

    // This method returns how many items are in the recyclerview
    @Override
    public int getItemCount() {
        if (redditPosts != null) {
            return redditPosts.length;
        }
        return 0;
    }
}