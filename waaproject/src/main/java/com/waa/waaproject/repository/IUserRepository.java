package com.waa.waaproject.repository;

import com.waa.waaproject.domain.Comment;
import com.waa.waaproject.domain.Post;
import com.waa.waaproject.domain.User;
import com.waa.waaproject.dto.CommentDto;
import com.waa.waaproject.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > 1")
    List<User> findUsersWithMoreThanOnePosts();

    @Query("SELECT u FROM User u WHERE SIZE(u.posts) > ?1")
    List<User> findUsersWithMoreThanNPosts(int n);
    @Query("SELECT u FROM User u JOIN u.posts p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%',:title, '%'))")
    List<User> findUsersPostByTitle(String title);
    @Query("SELECT c FROM User u JOIN u.posts p JOIN p.comments c " +
            "WHERE u.id = :userid and p.id = :postid and c.id = :commentid")
    Comment findCommentByUserIdByPostIdByCommentId(Long userid, Long postid, Long commentid);
    @Query("SELECT p FROM User u JOIN u.posts p WHERE u.id = :userid and p.id = :postid")
    Post findPostByUserIdByPostId(Long userid, Long postid);
    User findByEmail(String email);

}
