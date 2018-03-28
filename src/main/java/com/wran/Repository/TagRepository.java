package com.wran.Repository;

import com.wran.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

    Tag findByTagName(String tag);

}
