package com.wran.Service;

import com.wran.Model.PageNavigator;
import com.wran.Model.Post;
import com.wran.Model.PostDto;
import com.wran.Repository.PostRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post convertFromDto(PostDto post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());

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

    public List<Post> getAcceptedPostsInRange(int start, int stop){
        String sql = "Select p from Post p where p.accepted = 1 order by p.id desc";
        List<Post> result = new ArrayList<>();

        result.addAll(entityManager.createQuery(sql).setFirstResult(start).setMaxResults(stop).getResultList());

        return result;
    }

    public List<Post> getNotAcceptedPostsInRange(int start, int stop){
        String sql = "Select p from Post p where p.accepted = 0 order by p.id desc";
        List<Post> result = new ArrayList<>();

        result.addAll(entityManager.createQuery(sql).setFirstResult(start).setMaxResults(stop).getResultList());

        return result;
    }

    public List<PostDto> convertPostListToDto(List<Post> postList) {
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post:postList){
            PostDto postDto = new PostDto();

            postDto.setAuthor(post.getUser().getUsername());
            postDto.setTitle(post.getTitle());
            postDto.setBase64image(new String(Base64.encodeBase64(post.getImage())));
            postDto.setId(post.getId());
            postDto.setAccepted(post.isAccepted());

            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Override
    public PostDto convertPostToDto(Post post) {
        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setAccepted(post.isAccepted());
        postDto.setTitle(post.getTitle());
        postDto.setAuthor(post.getUser().getUsername());
        postDto.setBase64image(new String(Base64.encodeBase64(post.getImage())));

        return postDto;
    }

    @Transactional
    public void deletePostById(long id) {
        postRepository.delete(postRepository.findById(id));
    }

    @Override
    public List<PageNavigator> getPageList(int id) {
        List<PageNavigator> list = new ArrayList<>();

        //Getting list of PageNavigators in rage +- 5 from id
        int i = id - 5;
        if(i < 1) i = 1;

        if(id != 1) {
            PageNavigator prev = new PageNavigator();
            prev.setCurrent(false);
            prev.setText("Previous");
            prev.setPage(id - 1);
            list.add(prev);
        }

        while(i <= id + 5){
            PageNavigator pn = new PageNavigator();
            pn.setPage(i);
            pn.setText(Integer.toString(i));

            if(i == id)
                pn.setCurrent(true);
            else
                pn.setCurrent(false);

            list.add(pn);
            i++;
        }
        PageNavigator next = new PageNavigator();
        next.setCurrent(false);
        next.setText("Next");
        next.setPage(id + 1);
        list.add(next);


        return list;
    }
}
