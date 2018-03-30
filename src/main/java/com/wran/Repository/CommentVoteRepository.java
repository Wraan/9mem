package com.wran.Repository;

import com.wran.Model.Comment;
import com.wran.Model.CommentVote;
import com.wran.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentVoteRepository extends JpaRepository<CommentVote, Long> {

    CommentVote findByCommentAndUser(Comment comment, User user);

    @Query(value = "SELECT coalesce(SUM(cv.value),0) FROM CommentVote cv, Comment c where cv.comment.id = c.id and c.id = :id")
    int getVotesValueByCommentId(@Param("id") long id);

    @Query(value = "SELECT coalesce(SUM(cv.value), 0) from User u, Comment c, CommentVote cv where cv.comment.id = :commentId and cv.user.id = :userId and cv.comment.id = c.id and cv.user.id = u.id")
    int getUserCommentVote(@Param("userId") long userId, @Param("commentId") long commentId);
}
