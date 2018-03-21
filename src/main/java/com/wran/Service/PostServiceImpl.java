package com.wran.Service;

import com.wran.Model.Post;
import com.wran.Model.PostDto;
import com.wran.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Post convertFromDao(PostDto post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());

        String extension = post.getImage().getContentType();
        String[] parts = post.getImage().getContentType().split("/");

        if(!parts[0].equals("image"))
            return null;

        if(post.getImage() != null) {
            try {
                newPost.setImage(post.getImage().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else return null;

        return newPost;

    }

    @Override
    public byte[] findImageById(long id) {
        return postRepository.findImageById(id);
    }
}
