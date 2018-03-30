package com.wran.Repository;

import com.wran.Model.Post;
import com.wran.Model.PostVote;
import com.wran.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostVoteRepository extends JpaRepository<PostVote, Long> {

    PostVote findByPostAndUser(Post post, User user);

    @Query(value = "SELECT coalesce(SUM(pv.value),0) FROM PostVote pv, Post p where pv.post.id = p.id and p.id = :id")
    int getVotesValueByPostId(@Param("id") long id);

    @Query(value = "SELECT coalesce(SUM(pv.value), 0) from User u, Post p, PostVote pv where pv.post.id = :postId and pv.user.id = :userId and pv.post.id = p.id and pv.user.id = u.id")
    int getUserPostVote(@Param("userId") long userId, @Param("postId") long postId);
}
