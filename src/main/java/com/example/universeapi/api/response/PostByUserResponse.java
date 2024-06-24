package com.example.universeapi.api.response;

import com.example.universeapi.api.model.Post;

import java.util.List;

public class PostByUserResponse extends Response{
    private List<Post> posts;
    public PostByUserResponse(boolean success, List<Post> posts) {
        super(success);
        this.posts = posts;
    }

    public PostByUserResponse(boolean success, String message) {
        super(success, message);
    }

    public List<Post> getPosts() {
        return posts;
    }
}
