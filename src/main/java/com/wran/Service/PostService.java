package com.wran.Service;

import com.wran.Model.Post;
import com.wran.Model.PostDto;

public interface PostService {
    Post findById(long id);

    void save(Post post);

    Post convertFromDao(PostDto post);

    byte[] findImageById(long id);
}
