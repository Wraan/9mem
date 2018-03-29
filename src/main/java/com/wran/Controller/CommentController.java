package com.wran.Controller;

import com.wran.Model.Comment;
import com.wran.Model.Post;
import com.wran.Model.User;
import com.wran.Service.CommentService;
import com.wran.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @PostMapping("/submitComment")
    public String saveComment(@RequestParam("text") String text,
                              @RequestParam("postId") Long postId,
                              @AuthenticationPrincipal User user){

        Post post = postService.findById(postId);
        Comment comment = commentService.createComment(user, post, text);
        commentService.save(comment);

        return "redirect:/post/" + post.getId() + "#comments";
    }

    @GetMapping("/delete/comment/{id}")
    public String deleteComment(@PathVariable("id") long id) {
        Comment comment = commentService.findById(id);
        Post post = comment.getPost();
        commentService.delete(comment);

        return "redirect:/post/" + post.getId() + "#comments";
    }
}
