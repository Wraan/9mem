package com.wran.Service;

import com.wran.Model.PageNavigator;
import com.wran.Model.Post;
import com.wran.Model.PostDto;

import java.util.List;

public interface PostService {
    Post findById(long id);

    Post save(Post post);

    void delete(Post post);

    Post convertFromDto(PostDto post);

    byte[] findImageById(long id);

    List<Post> getAcceptedPostsInRange(int start, int stop);

    List<Post> getNotAcceptedPostsInRange(int start, int stop);

    List<PostDto> convertPostListToDto(List<Post> postList);

    PostDto convertPostToDto(Post post);

    void deletePostById(long id);

    List<PageNavigator> getPageNavigatorList(int id);
}
