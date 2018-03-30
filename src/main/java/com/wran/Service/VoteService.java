package com.wran.Service;

import com.wran.Model.*;

public interface VoteService {

    PostVote save(PostVote postVote);

    CommentVote save(CommentVote commentVote);

    PostVote findByPostAndUser(Post post, User user);

    CommentVote findByCommentAndUser(Comment comment, User user);

    void delete(PostVote postVote);

    void delete(CommentVote commentVote);

    void updateDelete(PostVote postVote, int value);

    void updateDelete(CommentVote commentVote, int value);

    int getVotesValueByPostId(long id);

    int getVotesValueByCommentId(long id);

    int getUserPostVote(long userId, long postId);

    int getUserCommentVote(long userId, long commentId);
}
