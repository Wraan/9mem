package com.wran.Service;

import com.wran.Model.Comment;
import com.wran.Model.Post;
import com.wran.Model.User;

public interface CommentService {
    Comment save(Comment comment);

    Comment createComment(User user, Post post, String text);

    void delete(Comment comment);

    Comment findById(long id);
}

