package com.wran.Service;

import com.wran.Model.Post;
import com.wran.Model.PostDto;
import com.wran.Repository.PostRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    EntityManager entityManager;

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Post convertFromDto(PostDto post) {
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

    public byte[] findImageById(long id) {
        return postRepository.findImageById(id);
    }

    public Post getLatestPostById(long id) {
        Page<Post> pages = postRepository.getLatestPostById(id, PageRequest.of(0,1));
        return pages.getContent().get(0);
    }

    public List<Post> getPostsInRange(int start, int stop){
        String sql = "Select p from Post p order by p.id desc";
        List<Post> result = new ArrayList<>();

        result.addAll(entityManager.createQuery(sql).setFirstResult(start).setMaxResults(stop).getResultList());

        return result;
    }

    @Override
    public List<PostDto> convertPostListToDto(List<Post> postList) {
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post:postList){
            PostDto postDto = new PostDto();

            postDto.setAuthor(post.getUser().getUsername());
            postDto.setTitle(post.getTitle());
            postDto.setBase64image(new String(Base64.encodeBase64(post.getImage())));
            postDto.setId(post.getId());

            postDtoList.add(postDto);
        }
        return postDtoList;
    }
}
