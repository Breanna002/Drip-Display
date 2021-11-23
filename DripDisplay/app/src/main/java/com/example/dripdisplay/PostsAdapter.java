package com.example.dripdisplay;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    public static final String TAG ="PostsAdapter";
    private Context context;
    private List<Post> posts;
    public Integer likes;
    public ParseUser currentUser = ParseUser.getCurrentUser();
    public Boolean likeChecker = false;
    public PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvTag;
        private ImageView ivProfile;
        private ImageView ibLike;
        private TextView tvLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTag = itemView.findViewById(R.id.tvTag);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            ibLike = itemView.findViewById(R.id.ibLike);
            tvLikes= itemView.findViewById(R.id.tvLikes);

        }

        public void bind(Post post){
            //Bind the data coming from post to view elements;
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvTag.setText(post.getTag());
            likes = post.getLikes();
            tvLikes.setText(String.valueOf(likes));
            ParseFile ProfileImage = post.getUser().getParseFile("profilepic");
            if(ProfileImage!= null){
                Glide.with(context).load(ProfileImage.getUrl()).into(ivProfile);
            }

            ParseFile image = post.getImage();
            if(image != null){
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }

            ibLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if (likeChecker == false)
                   {
                       likeChecker = true;
                       likes += 1;
                       post.setLikes(likes);
                       tvLikes.setText(String.valueOf(likes));
                       post.saveInBackground(new SaveCallback() {
                           @Override
                           public void done(ParseException e) {
                               if (e != null) {
                                   Log.e(TAG, "Error while saving", e);
                               }
                               Log.i(TAG, "Post was save successful!");
                                post.setLikes(likes);
                           }
                       });
                       ibLike.setImageResource(R.drawable.ic_like_icon);
                   }
                    else {
                        likeChecker = false;
                        likes -= 1;
                        post.setLikes(likes);
                        tvLikes.setText(String.valueOf(likes));
                        post.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.e(TAG, "Error while saving", e);
                                }
                                Log.i(TAG, "Post was save successful!");
                                post.setLikes(likes);
                            }
                        });
                        ibLike.setImageResource(R.drawable.ic_dislike_icon);
                    }
                }

            });

        }

    }
}