package com.wran.Controller;

import com.wran.Model.User;
import com.wran.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotesValueRestController {

    @Autowired
    VoteService voteService;

    @GetMapping("/api/votesValue/post{id}")
    @ResponseBody
    public int getPostVotesValue(@PathVariable("id") long id){
        return voteService.getVotesValueByPostId(id);
    }

    @GetMapping("/api/votesValue/comment{id}")
    @ResponseBody
    public int getCommentVotesValue(@PathVariable("id") long id){
        return voteService.getVotesValueByCommentId(id);
    }

    @GetMapping("/api/userVote/post{id}")
    @ResponseBody
    public int getUserPostVote(@PathVariable("id") long postId,
                               @AuthenticationPrincipal User user){
        return voteService.getUserPostVote(user.getId(), postId);
    }

    @GetMapping("/api/userVote/comment{id}")
    @ResponseBody
    public int getUserCommentVote(@PathVariable("id") long commentId,
                                  @AuthenticationPrincipal User user){
        return voteService.getUserCommentVote(user.getId(), commentId);
    }
}
