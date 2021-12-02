package com.example.dripdisplay.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dripdisplay.Comment;
import com.example.dripdisplay.CommentAdapter;
import com.example.dripdisplay.Post;
import com.example.dripdisplay.PostsAdapter;
import com.example.dripdisplay.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {

    public static final String TAG = "PostsFragment";
    private RecyclerView rvComments;
    protected CommentAdapter adapter;
    protected List<Comment> allComments;


    private EditText etComment;
    private Button btnSubmit;

    public CommentFragment() {
        //Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvComments = view.findViewById(R.id.rvComments);

        allComments = new ArrayList<>();
        adapter = new CommentAdapter(getContext(),allComments);

        //Set adapter on recycler view
        rvComments.setAdapter(adapter);

        //Set layout on recycler view
        rvComments.setLayoutManager(new LinearLayoutManager(getContext()));
        queryComments();

    }

    private void queryComments() {
        // Specify which class to query
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        query.include(Comment.KEY_USER);
        query.setLimit(20);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> comments, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting comments", e);
                    return;
                }
                for (Comment comment: comments) {
                    Log.i(TAG, "Comment: " + comment.getComment() + ", username" + comment.getUser().getUsername());

                }
                allComments.addAll(comments);
                adapter.notifyDataSetChanged();
            }
        });
    }

}