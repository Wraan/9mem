package com.wran.Service;

import com.wran.Model.*;
import com.wran.Repository.CommentVoteRepository;
import com.wran.Repository.PostVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    PostVoteRepository postVoteRepository;

    @Autowired
    CommentVoteRepository commentVoteRepository;

    @Autowired
    PostService postService;

    public PostVote save(PostVote postVote) {
        return postVoteRepository.save(postVote);
    }

    public CommentVote save(CommentVote commentVote) {
        return commentVoteRepository.save(commentVote);
    }

    public PostVote findByPostAndUser(Post post, User user){
        return postVoteRepository.findByPostAndUser(post, user);
    }

    public CommentVote findByCommentAndUser(Comment comment, User user) {
        return commentVoteRepository.findByCommentAndUser(comment, user);
    }

    public void delete(PostVote postVote) {
        postVoteRepository.delete(postVote);
    }

    public void delete(CommentVote commentVote) {
        commentVoteRepository.delete(commentVote);
    }

    public void updateDelete(PostVote postVote, int value) {
        if(postVote.getValue() == -value){
            postVote.setValue(value);
            save(postVote);
        }
        else
            delete(postVote);
    }

    public void updateDelete(CommentVote commentVote, int value) {
        if(commentVote.getValue() == -value){
            commentVote.setValue(value);
            save(commentVote);
        }
        else
            delete(commentVote);
    }

    public int getVotesValueByPostId(long id) {
        return postVoteRepository.getVotesValueByPostId(id);
    }

    public int getVotesValueByCommentId(long id) {
        return commentVoteRepository.getVotesValueByCommentId(id);
    }

    public int getUserPostVote(long userId, long postId) {
        return postVoteRepository.getUserPostVote(userId, postId);
    }

    public int getUserCommentVote(long userId, long commentId) {
        return commentVoteRepository.getUserCommentVote(userId, commentId);
    }

}
