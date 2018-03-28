package com.wran.Service;

import com.wran.Model.Post;
import com.wran.Model.PostTag;
import com.wran.Model.Tag;
import com.wran.Model.TagsDto;
import com.wran.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    public Set<PostTag> getPostTagsAsSet(Post post, TagsDto tagsDto) {

        Set<PostTag> postTagSet = new HashSet<>();
        Set<Tag> tags = getUniqueTags(tagsDto);

        for(Tag tag: tags) {
            postTagSet.add(convertToPostTag(post, tag));
        }

        return postTagSet;
    }

    private PostTag convertToPostTag(Post post, Tag tag){
        PostTag postTag = new PostTag();

        postTag.setTag(saveIfNotExist(tag));
        postTag.setPost(post);

        return postTag;
}

    private Tag saveIfNotExist(Tag tag){
        Tag newTag = tagRepository.findByTagName(tag.getTagName());

        if(newTag == null)
            newTag = tagRepository.save(tag);

        return newTag;
    }

    private Set<Tag> getUniqueTags(TagsDto tagsDto){
        Set<String> strings = new HashSet<>();

        strings.add(tagsDto.getTag1().trim());
        strings.add(tagsDto.getTag2().trim());
        strings.add(tagsDto.getTag3().trim());
        strings.add(tagsDto.getTag4().trim());
        strings.add(tagsDto.getTag5().trim());

        Set<Tag> tags = new HashSet<>();

        for (String string: strings) {
            tags.add(new Tag(string));
        }


        return tags;
    }

}
