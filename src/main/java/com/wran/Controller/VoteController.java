package com.wran.Controller;

import com.wran.Model.*;
import com.wran.Service.CommentService;
import com.wran.Service.PostService;
import com.wran.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @PostMapping("/voteUp/post/{id}")
    public String votePostUp(@PathVariable("id") Long id,
                         @AuthenticationPrincipal User user,
                         @RequestHeader("Referer") String referer){

        Post post = postService.findById(id);
        PostVote postVote = voteService.findByPostAndUser(post, user);

        if(postVote == null)
            voteService.save(new PostVote(user, post, 1));
        else
            voteService.updateDelete(postVote, 1);

        return "redirect:" + referer + "#post" + post.getId();
    }

    @PostMapping("/voteUp/comment/{id}")
    public String voteCommentUp(@PathVariable("id") Long id,
                             @AuthenticationPrincipal User user,
                             @RequestHeader("Referer") String referer){

        Comment comment = commentService.findById(id);
        CommentVote commentVote = voteService.findByCommentAndUser(comment, user);

        if(commentVote == null)
            voteService.save(new CommentVote(user, comment, 1));
        else
            voteService.updateDelete(commentVote, 1);

        return "redirect:" + referer + "#comment" + comment.getId();
    }

    @PostMapping("/voteDown/post/{id}")
    public String votePostDown(@PathVariable("id") Long id,
                           @AuthenticationPrincipal User user,
                           @RequestHeader("Referer") String referer){

        Post post = postService.findById(id);
        PostVote postVote = voteService.findByPostAndUser(post, user);

        if(postVote == null)
            voteService.save(new PostVote(user, post, -1));
        else
            voteService.updateDelete(postVote, -1);

        return "redirect:" + referer + "#post" + post.getId();
    }

    @PostMapping("/voteDown/comment/{id}")
    public String voteCommentDown(@PathVariable("id") Long id,
                                @AuthenticationPrincipal User user,
                                @RequestHeader("Referer") String referer){

        Comment comment = commentService.findById(id);
        CommentVote commentVote = voteService.findByCommentAndUser(comment, user);

        if(commentVote == null)
            voteService.save(new CommentVote(user, comment, -1));
        else
            voteService.updateDelete(commentVote, -1);

        return "redirect:" + referer + "#comment" + comment.getId();
    }

}
