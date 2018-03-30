package com.wran.Model;

import javax.persistence.*;

@Entity
@Table(name = "post_vote")
public class PostVote {

    @Id
    @GeneratedValue
    @Column(name = "post_vote_id")
    private long id;

    private int value;

    public PostVote(User user, Post post, int value) {
        this.post = post;
        this.user = user;

        if(value > 0)
            this.value = 1;
        else if(value < 0)
            this.value = -1;
        else
            this.value = 0;
    }

    public PostVote() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
