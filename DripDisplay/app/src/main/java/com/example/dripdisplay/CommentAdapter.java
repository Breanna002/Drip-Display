package com.example.dripdisplay;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dripdisplay.Fragments.CommentFragment;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    public static final String TAG = "CommentAdapter";
    private Context context;
    private List<Comment> comments;
    Fragment fragment;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProfilePic;
        private TextView tvUsername;
        private TextView tvComment;
        private EditText etComment;
        private Button btnClose;
        private Button btnSubmit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvComment = itemView.findViewById(R.id.tvComment);
            btnClose = itemView.findViewById(R.id.btnClose);
            btnSubmit = itemView.findViewById(R.id.btnSubmit);
            etComment = itemView.findViewById(R.id.etComment);
        }

        public void bind(Comment comment) {
            //Bind the data coming from post to view elements;
            tvUsername.setText(comment.getUser().getUsername());
            tvComment.setText(comment.getComment());
            ParseFile ProfileImage = comment.getUser().getParseFile("profilepic");
            if(ProfileImage!= null){
                Glide.with(context).load(ProfileImage.getUrl()).into(ivProfilePic);
            }

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String comment = etComment.getText().toString();
                    if(comment.isEmpty()){
                        Toast.makeText(context, "Comment cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ParseUser user = ParseUser.getCurrentUser();
                    ParseFile profilePic = ParseUser.getCurrentUser().getParseFile("profilepic");

                    saveComment(comment,user,profilePic);
                }
            });
        }

        private void saveComment(String comment, ParseUser user, ParseFile profilePic) {
            Comment comments = new Comment();
            comments.setComment(comment);
            comments.setUser(user);
            comments.getProfilePic();

            comments.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null){
                        Log.e(TAG, "Error while saving", e);
                        Toast.makeText(context, "Error while saving", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.i(TAG, "Post was save successful!");
                    etComment.setText("");
                    tvUsername.setText("");
                    ivProfilePic.setImageResource(0);
                }
            });
        }
    }
}
