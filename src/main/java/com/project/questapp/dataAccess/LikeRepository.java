package com.project.questapp.dataAccess;

import com.project.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {


    //List<Like> findByUserIdAndPostId(Long userId, Long postId);
    Optional<List<Like>> findByUserIdAndPostId(Long userId, Long postId);


    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);

}
