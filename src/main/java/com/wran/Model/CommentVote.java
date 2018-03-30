package com.wran.Model;

import javax.persistence.*;

@Entity
@Table(name = "comment_vote")
public class CommentVote {

    @Id
    @GeneratedValue
    @Column(name = "comment_vote_id")
    private long id;

    public CommentVote(User user, Comment comment, int value) {
        this.comment = comment;
        this.user = user;

        if(value > 0)
            this.value = 1;
        else if(value < 0)
            this.value = -1;
        else
            this.value = 0;
    }

    public CommentVote() {

    }

    private int value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
