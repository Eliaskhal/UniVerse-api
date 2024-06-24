package com.example.universeapi.repository;

import com.example.universeapi.api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> getPostsByUserIDOrderByPostingDate(int userID);


    @Query("SELECT p FROM Post p ORDER BY p.postID DESC")
    List<Post> findAllOrderByPostingDateDesc();
}
