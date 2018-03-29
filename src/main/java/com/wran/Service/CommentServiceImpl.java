package com.wran.Service;

import com.wran.Model.Comment;
import com.wran.Model.Post;
import com.wran.Model.User;
import com.wran.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment createComment(User user, Post post, String text) {
        Comment comment = new Comment();

        comment.setText(text);
        comment.setPost(post);
        comment.setUser(user);

        return comment;
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment findById(long id) {
        return commentRepository.findById(id);
    }

}
