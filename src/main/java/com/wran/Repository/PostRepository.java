package com.wran.Repository;

import com.wran.Model.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(long id);

    @Query(value = "Select p.image from Post p where p.id = :id")
    byte[] findImageById(@Param("id") long id);

    @Query(value = "Select p from Post p where p.user.id = :id order by post_id desc")
    Page<Post> getLatestPostById(@Param("id") long id, Pageable pageable);

}
