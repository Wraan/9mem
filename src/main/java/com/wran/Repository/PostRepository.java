package com.wran.Repository;

import com.wran.Model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(long id);

    @Query(value = "Select p.image from Post p where p.id = :id")
    byte[] findImageById(@Param("id") long id);

    @Query(value = "Select p from Post p, Tag t, PostTag pt where p.id = pt.post.id and t.id = pt.tag.id and t.tagName = :tag order by p.id desc")
    List<Post> findByTag(@Param("tag") String tag);


    void delete(Post post);
}
