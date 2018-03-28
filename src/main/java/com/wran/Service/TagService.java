package com.wran.Service;

import com.wran.Model.Post;
import com.wran.Model.PostTag;
import com.wran.Model.TagsDto;

import java.util.Set;

public interface TagService {
    Set<PostTag> getPostTagsAsSet(Post post, TagsDto tagsDto);
}
