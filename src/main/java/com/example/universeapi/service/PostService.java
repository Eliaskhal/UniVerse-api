package com.example.universeapi.service;

import com.example.universeapi.api.exceptions.UnavailableUser;
import com.example.universeapi.api.model.Post;
import com.example.universeapi.api.model.User;
import com.example.universeapi.repository.PostRepository;
import com.example.universeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void addPost(int userID, String content) throws Exception {
        try{
            Optional<User> user = userRepository.findById(userID);
            if(user.isEmpty()) throw new UnavailableUser();
            Post post = new Post(userID, content);
            postRepository.save(post);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllOrderByPostingDateDesc();
    }

    public List<Post> getAllPostsByUser(int userID) throws Exception {
        try{
            Optional<User> user = userRepository.findById(userID);
            if(user.isEmpty()) throw new UnavailableUser();
            return postRepository.getPostsByUserIDOrderByPostingDate(userID);
        } catch (UnavailableUser e) {
            throw new Exception(e);
        }
    }
}
