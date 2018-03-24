package com.wran.Service;

import com.wran.Model.Post;
import com.wran.Model.PostDto;

import java.util.List;

public interface PostService {
    Post findById(long id);

    void save(Post post);

    Post convertFromDto(PostDto post);

    byte[] findImageById(long id);

    Post getLatestPostById(long id);

    List<Post> getPostsInRange(int start, int stop);

    List<PostDto> convertPostListToDto(List<Post> postList);
}
