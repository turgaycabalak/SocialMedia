package com.project.questapp.dataAccess;

import com.project.questapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUserId(Long userId);


}
