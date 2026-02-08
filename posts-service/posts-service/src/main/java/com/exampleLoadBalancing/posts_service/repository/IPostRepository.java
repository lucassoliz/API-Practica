package com.exampleLoadBalancing.posts_service.repository;

import com.exampleLoadBalancing.posts_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.user_id = :userId")
    List<Post> findPostByUserId(Long userId);

}
