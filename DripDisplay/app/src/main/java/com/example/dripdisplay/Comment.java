package com.example.dripdisplay;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Comment")
public class Comment extends ParseObject {
    public static String KEY_USER = "user";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_TAG = "Tag";
    public static final String KEY_POST = "Post";
    public static final String POST_ID = "objectId";
    public static final String CMMT_TEXT = "comment";
    public static final String PRFL_PIC = "profilepic";

    public String getComment(){
        return getString(CMMT_TEXT);
    }

    public void setComment(String comment) {
        put(CMMT_TEXT, comment);

    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    public String getTag(){ return getString(KEY_TAG);}

    public void setTag(String tag){
        put(KEY_TAG,tag);
    }

    public String getPostId(){String postId = getParseObject(KEY_POST).getObjectId().toString();
    return postId;
    }

    public void setPostId(String postId){put(getPostId(),postId);}

    public ParseFile getProfilePic(){ return ParseUser.getCurrentUser().getParseFile(PRFL_PIC);}


}
